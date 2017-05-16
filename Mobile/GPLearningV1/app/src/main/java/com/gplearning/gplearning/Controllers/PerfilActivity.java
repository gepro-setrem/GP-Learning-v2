package com.gplearning.gplearning.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Pessoa;
import com.gplearning.gplearning.Models.PessoaDao;
import com.gplearning.gplearning.Models.Turma;
import com.gplearning.gplearning.Models.TurmaDao;
import com.gplearning.gplearning.R;
import com.gplearning.gplearning.Utils.MetodosPublicos;

public class PerfilActivity extends AppCompatActivity {

    Long idPessoa;

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
                MetodosPublicos.CarregaimagemPerfil(this, ((ImageView) findViewById(R.id.perfilImagem)), idPessoa);
                DaoSession daoSession = ((App) getApplication()).getDaoSession();
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
                            ((TextView) findViewById(R.id.perfilTurma)).setText(turma.getNome() + " - " + turma.getAno() + " " + R.string.year);
                    }
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
}
