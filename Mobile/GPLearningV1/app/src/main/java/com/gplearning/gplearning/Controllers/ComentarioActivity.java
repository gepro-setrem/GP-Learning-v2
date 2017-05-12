package com.gplearning.gplearning.Controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.gplearning.gplearning.Adapters.ComentarioAdapter;
import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.DAO.ComentarioDAO;
import com.gplearning.gplearning.Enums.RecursosEnum;
import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.Models.ComentarioDao;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Pessoa;
import com.gplearning.gplearning.Models.PessoaDao;
import com.gplearning.gplearning.R;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComentarioActivity extends AppCompatActivity {

    private List<Comentario> lsComentario = new ArrayList<>();
    private ComentarioDao dao;
    private RecyclerView recyclerView;
    private ComentarioAdapter comentarioAdapter; //ArrayAdapter<Comentario> comentario2Adapter;
    private Long idEtapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        setTitle(R.string.comments);
        DaoSession daoSession = ((App) getApplication()).getDaoSession();

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras().containsKey(MetodosPublicos.key_idEtapa)) {
                idEtapa = intent.getLongExtra(MetodosPublicos.key_idEtapa, Long.valueOf(0));
            }
        }

        //   ComentarioDao cDao
        dao = daoSession.getComentarioDao();
        recyclerView = (RecyclerView) findViewById(R.id.comentarioListView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        //  lsComentario = dao.queryBuilder().orderAsc(ComentarioDao.Properties.Criacao).list();
        comentarioAdapter = new ComentarioAdapter(lsComentario, this); // new ArrayAdapter<Comentario>(this, android.R.layout.simple_list_item_1, lsComentario);
        recyclerView.setAdapter(comentarioAdapter);

        recyclerView.addOnItemTouchListener(new MetodosPublicos.RecyclerItemClickListener(this, recyclerView, new MetodosPublicos.RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("Event", "Clicou");
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Log.i("Event", "Long Click");
                PopupDeletaComentario(position);
            }
        }));

        PessoaDao pessoaDao = daoSession.getPessoaDao();
        lsComentario.addAll(dao.queryBuilder().where(ComentarioDao.Properties.IdEtapa.eq(idEtapa), ComentarioDao.Properties.Deletado.eq(false)).orderAsc(ComentarioDao.Properties.Criacao).list());
        for (Comentario comentario : lsComentario) {
            Pessoa pessoa = pessoaDao.load(comentario.getIdRemetente());
            if (pessoa != null) {
                comentario.setRemetente(pessoa);
            }
        }
        comentarioAdapter.notifyDataSetChanged();
        comentarioAdapter.notifyItemInserted(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  new SincronizaComentarios().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    public void SalvaComentario(View view) {
        EditText coment = (EditText) findViewById(R.id.comentarioNovo);
        try {
            if (!coment.getText().toString().isEmpty()) {
                final Comentario COM = new Comentario(null, 0, coment.getText().toString(), new Date(), MetodosPublicos.SelecionaSessaoId(this), idEtapa);
                final ComentarioDAO comentarioDAO = new ComentarioDAO();
                long id = dao.insert(COM);
                MetodosPublicos.Log("Event", "id:" + COM.get_id());

                if (id > 0) {
                    new SalvaComentario().execute(COM);
                }
                coment.setText("");
                Pessoa remetente = new Pessoa(MetodosPublicos.SelecionaSessaoId(this), MetodosPublicos.SelecionaSessaoNome(this));
                COM.setRemetente(remetente);
                lsComentario.add(COM);
                comentarioAdapter.notifyItemInserted(lsComentario.size() - 1);
                recyclerView.smoothScrollToPosition(lsComentario.size() - 1);
            }
        } catch (Exception e) {
            MetodosPublicos.Log("ERROR", e.toString());
        }
    }

    public void PopupDeletaComentario(final int position) {
        final Comentario comentario = dao.load(comentarioAdapter.getItemId(position));
        if (comentario != null && comentario.getIdRemetente() == MetodosPublicos.SelecionaSessaoId(this)) {
            final AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(R.string.comment_delete);
            alert.setCancelable(true);
            alert.setNeutralButton(R.string.cancel, null);
            alert.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        Log.i("Event", "Vai deletar o comet id:" + comentario.get_id());
                        lsComentario.remove(position);
                        comentarioAdapter.notifyItemRemoved(position);

                        if (MetodosPublicos.IsConnected(ComentarioActivity.this) && comentario != null && comentario.getId() > 0)
                            new DeletaComentario().execute(comentario);
                        else {
                            if (comentario.getId() > 0) {
                                comentario.setDeletado(true);
                                dao.update(comentario);
                            } else {
                                dao.deleteByKey(comentario.get_id());
                            }
                        }

                    } catch (Exception e) {
                        Log.e("ERROR", e.toString());
                    }
                }
            });
            alert.show();
        }
    }


    private class SalvaComentario extends AsyncTask<Comentario, String, Comentario> {

        @Override
        protected Comentario doInBackground(Comentario... comentarios) {
            ComentarioDAO comentarioDAO = new ComentarioDAO();
            try {
                if (MetodosPublicos.IsConnected(ComentarioActivity.this)) { //se estiver conectado na internet envia
                    comentarios[0].setRemetente(null);
                    int id = comentarioDAO.SalvarComentario(comentarios[0]);
                    if (id > 0) {
                        comentarios[0].setId(id);
                        return comentarios[0];
                    }
                }
            } catch (Exception e) {
                MetodosPublicos.Log("Error", " error :" + e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Comentario comentario) {
            super.onPostExecute(comentario);
            if (comentario != null) {
                dao.update(comentario);
            }
        }
    }

    private class DeletaComentario extends AsyncTask<Comentario, String, Boolean> {
        Comentario comentario;

        public DeletaComentario() {

        }

        @Override
        protected Boolean doInBackground(Comentario... comentarios) {
            try {
                if (MetodosPublicos.IsConnected(ComentarioActivity.this)) {
                    ComentarioDAO comentarioDAO = new ComentarioDAO();
                    this.comentario = comentarios[0];
                    return comentarioDAO.DeletaComentario(comentarios[0]);
                }
            } catch (Exception e) {
                MetodosPublicos.Log("ERROR", e.toString());
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean deletado) {
            super.onPostExecute(deletado);
            MetodosPublicos.Log("error", "Deletou:" + deletado);
            if (deletado) {
                dao.deleteByKey(comentario.get_id());
            } else {
                comentario.setDeletado(true);
                dao.update(comentario);
            }

        }

    }

    private class SincronizaComentarios extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            //  List<Comentario> lsComentariosDeletados= dao.
            MetodosPublicos.SalvaUltimaSincronizacao(ComentarioActivity.this, RecursosEnum.Comentario, new Date());
            return null;
        }


    }


}
