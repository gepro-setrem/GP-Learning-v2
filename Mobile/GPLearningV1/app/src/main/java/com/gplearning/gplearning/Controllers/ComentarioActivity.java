package com.gplearning.gplearning.Controllers;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.Models.ComentarioDao;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.R;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ComentarioActivity extends AppCompatActivity {

    private List<Comentario> lsComentario  =new ArrayList<>();
    private ComentarioDao dao;
private ArrayAdapter<Comentario> comentarioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        dao = App.getDaoSessionApp(this).getComentarioDao();

        ListView listView = (ListView) findViewById(R.id.comentarioListView);

        new CarregaComentarios().execute();
        comentarioAdapter = new ArrayAdapter<Comentario>(this, android.R.layout.simple_list_item_1, lsComentario);
        listView.setAdapter(comentarioAdapter);
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


    public void SalvaComentario(View view){
        EditText coment = (EditText)findViewById(R.id.comentarioNovo);
        try{
        if(!coment.getText().toString().isEmpty()){
            Comentario COM = new Comentario(null,null,coment.getText().toString(),new Date(),null,null);
            DaoSession daoSession = ((App) getApplication()).getDaoSession();
          ComentarioDao  cDao = daoSession.getComentarioDao();
            Log.i("Event","id:"+COM.get_id());
           long id = cDao.insert(COM);
            if(id>0){
                coment.setText("");
            }

        }}catch (Exception e){
            Log.e("ERROR",e.toString());
        }
    }

    public class CarregaComentarios extends AsyncTask<String, Intent, String> {

        @Override
        protected String doInBackground(String... strings) {

            if (MetodosPublicos.IsConnected(ComentarioActivity.this)) {
                Log.i("Event", " Conectado na Internetes");
            }

            //.where(ComentarioDao.Properties..eq("Joe"))
            lsComentario = dao.queryBuilder().orderAsc(ComentarioDao.Properties.Criacao).list();

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            comentarioAdapter.notifyDataSetChanged();

        }
    }


}
