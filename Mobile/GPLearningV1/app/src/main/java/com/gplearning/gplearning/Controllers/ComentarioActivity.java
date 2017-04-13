package com.gplearning.gplearning.Controllers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.gplearning.gplearning.Adapters.Comentario2Adapter;
import com.gplearning.gplearning.Adapters.ComentarioAdapter;
import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.Models.ComentarioDao;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Value;
import com.gplearning.gplearning.Models.ValueDao;
import com.gplearning.gplearning.R;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComentarioActivity extends AppCompatActivity {

    private List<Comentario> lsComentario = new ArrayList<>();
    private ComentarioDao dao;
    //  private ValueDao valueDao;
    private ComentarioAdapter comentarioAdapter; //ArrayAdapter<Comentario> comentario2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        setTitle("Comentários");
        DaoSession daoSession = ((App) getApplication()).getDaoSession();
        //   ComentarioDao cDao
        dao = daoSession.getComentarioDao();
        //dao = App.getDaoSessionApp(this).getComentarioDao();
        // valueDao = daoSession.getValueDao();
        //  ListView listView = (ListView) findViewById(R.id.comentarioListView);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.comentarioListView);

        lsComentario = dao.queryBuilder().orderAsc(ComentarioDao.Properties.Criacao).list();
        // new  CarregaComentarios().execute();
        comentarioAdapter = new ComentarioAdapter(lsComentario, this); // new ArrayAdapter<Comentario>(this, android.R.layout.simple_list_item_1, lsComentario);
        recyclerView.setAdapter(comentarioAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.i("Event", "Clicou");
            }

            @Override
            public void onLongItemClick(View view, int position) {
                Log.i("Event", "Long Click");

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
                Comentario COM = new Comentario(null, null, coment.getText().toString(), new Date());

                Value val = new Value(null, coment.getText().toString());
                long id = dao.insert(COM);
                Log.i("Event", "id:" + COM.get_id());
                if (id > 0) {
                    coment.setText("");
                    comentarioAdapter.swap(dao.queryBuilder().orderAsc(ComentarioDao.Properties.Criacao).list());

                }
            }
        } catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
    }


//    public class CarregaComentarios extends AsyncTask<String, Intent, List<Comentario>> {
//        @Override
//        protected List<Comentario> doInBackground(String... strings) {
//
//            if (MetodosPublicos.IsConnected(ComentarioActivity.this)) {
//                Log.i("Event", " Conectado na Internetes");
//            }
//            //.where(ComentarioDao.Properties..eq("Joe"))
//            lsComentario = dao.queryBuilder().orderAsc(ComentarioDao.Properties.Criacao).list();
//
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(List<Comentario> comentarios) {
//            super.onPostExecute(comentarios);
//            comentarioAdapter.notifyDataSetChanged();
//        }
//    }


    public void showPopup() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Deletar comentário?");
        alert.setCancelable(false);


    }


    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }

}
