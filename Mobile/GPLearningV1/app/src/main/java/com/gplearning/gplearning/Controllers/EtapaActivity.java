package com.gplearning.gplearning.Controllers;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.Enums.EtapaProjeto;
import com.gplearning.gplearning.Models.Atividade;
import com.gplearning.gplearning.Models.AtividadeDao;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.R;

import org.greenrobot.greendao.query.WhereCondition;

public class EtapaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey("ID")) {
            Long id = intent.getExtras().getLong("ID");
            DaoSession daoSession = ((App) getApplication()).getDaoSession();
            AtividadeDao atividadeDao = daoSession.getAtividadeDao();
            ///selecionar atividade
            Atividade atv = new Atividade(); //atividadeDao.queryBuilder().where(new WhereCondition.PropertyCondition(AtividadeDao.Properties.Etapa==null).) //AtividadeDao.Properties.Etapa = EtapaProjeto.Escopo).

        }

        ((FloatingActionButton) findViewById(R.id.EtapaBtnComentario)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EtapaActivity.this, ComentarioActivity.class);
                startActivity(intent);
            }
        });
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


}
