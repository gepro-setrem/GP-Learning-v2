package com.gplearning.gplearning.Controllers;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.Models.Avaliacao;
import com.gplearning.gplearning.Models.AvaliacaoDao;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Indicador;
import com.gplearning.gplearning.Models.IndicadorDao;
import com.gplearning.gplearning.Models.Pessoa;
import com.gplearning.gplearning.Models.PessoaDao;
import com.gplearning.gplearning.Models.Turma;
import com.gplearning.gplearning.Models.TurmaDao;
import com.gplearning.gplearning.R;
import com.gplearning.gplearning.Utils.MetodosPublicos;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class PerfilActivity extends AppCompatActivity {

    Long idPessoa;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ///  getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras().containsKey("ID")) {
                idPessoa = intent.getExtras().getLong("ID");
                //   MetodosPublicos.CarregaimagemPerfil(this, ((ImageView) findViewById(R.id.perfilImagem)), idPessoa);

                daoSession = ((App) getApplication()).getDaoSession();
                new CarregaIndicadores().execute();

                File path = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                // File file = new File(path, MetodosPublicos.SelecionaCaminhoImagem(this,idPessoa));
                File file = new File(path, MetodosPublicos.key_imagemUser + idPessoa + ".jpg");
                if (file != null && file.exists()) {
                    Picasso.with(this).load(file).into(((ImageView) findViewById(R.id.perfilImagem)));
                }

                PessoaDao pessoaDao = daoSession.getPessoaDao();
                Pessoa pessoa = pessoaDao.load(idPessoa);
                if (pessoa != null) {

                    ((android.support.design.widget.CollapsingToolbarLayout) findViewById(R.id.perfilToolbar_layout)).setTitle(pessoa.getNome());
                    ((TextView) findViewById(R.id.perfilEmail)).setText(pessoa.getEmail());
                    ((TextView) findViewById(R.id.perfilTelefone)).setText(pessoa.getTelefone());

                    if (pessoa.getIdTurma() > 0) {
                        TurmaDao turmaDao = daoSession.getTurmaDao();
                        Turma turma = turmaDao.load(pessoa.get_id());
                        if (turma != null)
                            ((TextView) findViewById(R.id.perfilTurma)).setText(turma.getNome() + " - " + turma.getAno() + " " + getString(R.string.year));
                    }


//                    ratingBar.setLayoutParams(new LayoutParams(
//                            RatingBar.LayoutParam .FILL_PARENT,
//                            LayoutParams.WRAP_CONTENT));

                    //  LinearLayout com = (LinearLayout)findViewById(R.id.itemListViewComentario);
                    // com.fin


                }
            }
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


    }

    private class CarregaIndicadores extends AsyncTask<String, Integer, List<Indicador>> {

        @Override
        protected List<Indicador> doInBackground(String... params) {
            IndicadorDao indicadorDao = daoSession.getIndicadorDao();
            AvaliacaoDao avaliacaoDao = daoSession.getAvaliacaoDao();
            MetodosPublicos.Log("event", "Vai indicadores!!!!");

            List<Indicador> lsIndicadores = indicadorDao.loadAll();
            MetodosPublicos.Log("event", "taotal de indicadores:" + lsIndicadores.size());

            if (lsIndicadores != null && lsIndicadores.size() > 0) {
                for (Indicador ind : lsIndicadores) {
                    List<Avaliacao> lsAvaliacoes = avaliacaoDao.queryBuilder().where(AvaliacaoDao.Properties.IdIndicador.eq(ind.get_id())).list();
                    if (lsAvaliacoes != null && lsAvaliacoes.size() > 0) {
                        MetodosPublicos.Log("event", "total de avaliações:" + lsAvaliacoes.size());
                        int media = 0;
                        for (Avaliacao ava : lsAvaliacoes) {
                            media += ava.getValor();
                        }
                        ind.setValor(media / lsAvaliacoes.size());
                        MetodosPublicos.Log("event", "Media:" + media / lsAvaliacoes.size());
                    }
                }
            }
            return lsIndicadores;
        }

        @Override
        protected void onPostExecute(List<Indicador> lsIndicadores) {
            super.onPostExecute(lsIndicadores);
            if (lsIndicadores != null && lsIndicadores.size() > 0) {
                LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutIndicadores);
                for (Indicador indicador : lsIndicadores) {
                    LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View com = vi.inflate(R.layout.item_indicador_perfil, null);
                    linearLayout.addView(com);
                    ((TextView) com.findViewById(R.id.itemIndicadorPerfilNome)).setText(indicador.getNome());
                    RatingBar ratingBar = ((RatingBar) com.findViewById(R.id.itemIndicadorPerfilRattingbar));
                    ratingBar.setRating(indicador.getValor());
                    ratingBar.setNumStars(indicador.getValor());
                    ratingBar.setStepSize((float)0.5);
                    MetodosPublicos.Log("event", "ratting nome:"+indicador.getNome() +" valor:" + indicador.getValor());
                }
            }
        }
    }


}
