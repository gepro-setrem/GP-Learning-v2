package com.gplearning.gplearning.Controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.Models.ComentarioDao;
import com.gplearning.gplearning.Models.DaoSession;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        setTitle(R.string.comments);
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        //   ComentarioDao cDao
        dao = daoSession.getComentarioDao();
        recyclerView = (RecyclerView) findViewById(R.id.comentarioListView);

        lsComentario = dao.queryBuilder().orderAsc(ComentarioDao.Properties.Criacao).list();
        comentarioAdapter = new ComentarioAdapter(lsComentario, this); // new ArrayAdapter<Comentario>(this, android.R.layout.simple_list_item_1, lsComentario);
        recyclerView.setAdapter(comentarioAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

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
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                Comentario COM = new Comentario(null, 0, coment.getText().toString(), new Date(), MetodosPublicos.SelecionaSessaoId(this));

                long id = dao.insert(COM);
                MetodosPublicos.Log("Event", "id:" + COM.get_id());

                if (id > 0) {
                    new SalvaComentario().execute(COM);
                    coment.setText("");
                    lsComentario.add(COM);
                    comentarioAdapter.notifyItemInserted(lsComentario.size() - 1);
                    recyclerView.smoothScrollToPosition(lsComentario.size() - 1);
                }
            }
        } catch (Exception e) {
            MetodosPublicos.Log("ERROR", e.toString());
        }
    }

    public void PopupDeletaComentario(final int position) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.comment_delete);
        alert.setCancelable(true);
        alert.setNeutralButton(R.string.cancel, null);
        alert.setNegativeButton(R.string.delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    Comentario comentario = dao.load(comentarioAdapter.getItemId(position));
                    Log.i("Event", "Vai deletar o comet id:" + comentario.get_id());
                    if (comentario != null && comentario.getId() > 0)
                        new DeletaComentario().execute(comentario);
                    dao.deleteByKey(comentario.get_id());
                    lsComentario.remove(position);
                    comentarioAdapter.notifyItemRemoved(position);
                } catch (Exception e) {
                    Log.e("ERROR", e.toString());
                }
            }
        });
        alert.show();

    }


    private class SalvaComentario extends AsyncTask<Comentario, String, Comentario> {

        @Override
        protected Comentario doInBackground(Comentario... comentarios) {
            ComentarioDAO comentarioDAO = new ComentarioDAO();
            try {
                if (MetodosPublicos.IsConnected(ComentarioActivity.this)) { //se estiver conectado na internet envia
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

        @Override
        protected Boolean doInBackground(Comentario... comentarios) {
            if (MetodosPublicos.IsConnected(ComentarioActivity.this)) {
                ComentarioDAO comentarioDAO = new ComentarioDAO();
                return comentarioDAO.DeletaComentario(comentarios[0]);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            MetodosPublicos.Log("error", "Deletou:" + aBoolean);
        }

    }


}
