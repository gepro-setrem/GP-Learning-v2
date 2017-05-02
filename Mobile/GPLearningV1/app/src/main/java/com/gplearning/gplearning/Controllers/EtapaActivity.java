package com.gplearning.gplearning.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.Enums.EtapaProjeto;
import com.gplearning.gplearning.Models.Atividade;
import com.gplearning.gplearning.Models.AtividadeDao;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Marco;
import com.gplearning.gplearning.Models.Premissas;
import com.gplearning.gplearning.Models.RequisitoTermoAbertura;
import com.gplearning.gplearning.Models.Restricoes;
import com.gplearning.gplearning.Models.TermoAbertura;
import com.gplearning.gplearning.Models.TermoAberturaDao;
import com.gplearning.gplearning.R;

public class EtapaActivity extends AppCompatActivity {

    private Long idAtividade;

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
            Atividade atv = atividadeDao.queryBuilder().where(AtividadeDao.Properties._id.eq(id)).unique(); //AtividadeDao.Properties.Etapa = EtapaProjeto.Escopo).
            if (atv != null && atv.get_id() > 0) {
                AtualizaValores(atv);
                SetTitle(atv.getEtapa());
            }
        }

        ((FloatingActionButton) findViewById(R.id.EtapaBtnComentario)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EtapaActivity.this, ComentarioActivity.class);
                intent.putExtra("ID", idAtividade);
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


    private void AtualizaValores(Atividade atv) {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();

        if (atv.getEtapa() == EtapaProjeto.TermoAberturaDescricao ||
                atv.getEtapa() == EtapaProjeto.TermoAberturaJustificativa ||
                atv.getEtapa() == EtapaProjeto.PlanejamentoEscopo ||
                atv.getEtapa() == EtapaProjeto.Escopo) {
            //texto corrido
            ((ListView) findViewById(R.id.EtapaListview)).setVisibility(View.GONE);
            TextView etapaTextView = ((TextView) findViewById(R.id.EtapaTxtTexto));
            etapaTextView.setVisibility(View.VISIBLE);

            if (atv.getEtapa() == EtapaProjeto.TermoAberturaDescricao ||
                    atv.getEtapa() == EtapaProjeto.TermoAberturaJustificativa) {
                TermoAberturaDao termoAberturaDao = daoSession.getTermoAberturaDao();
                TermoAbertura termoAbertura = termoAberturaDao.queryBuilder().where(TermoAberturaDao.Properties.Pro_id.eq(atv.getPro_id())).unique();
                if (termoAbertura != null) {
                    if (atv.getEtapa() == EtapaProjeto.TermoAberturaDescricao)
                        etapaTextView.setText(termoAbertura.getDescricao());
                    else
                        etapaTextView.setText(termoAbertura.getJustificativa());
                }
            } else if (atv.getEtapa() == EtapaProjeto.PlanejamentoEscopo) {
            } //IMPLEMENTAR ESTES METODOS AINDA!!!
            else if (atv.getEtapa() == EtapaProjeto.Escopo) {
            }

        } else if (atv.getEtapa() == EtapaProjeto.Stakeholders ||
                atv.getEtapa() == EtapaProjeto.Requisitos) {//IMPLEMENTAR ESTES METODOS AINDA!!!

        } else {
            ListView listview = ((ListView) findViewById(R.id.EtapaListview));
            listview.setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.EtapaTxtTexto)).setVisibility(View.GONE);

            TermoAberturaDao termoAberturaDao = daoSession.getTermoAberturaDao();
            TermoAbertura termoAbertura = termoAberturaDao.queryBuilder().where(TermoAberturaDao.Properties.Pro_id.eq(atv.getPro_id())).unique();

            if (atv.getEtapa() == EtapaProjeto.TermoAberturaPremissas) {
                ArrayAdapter<Premissas> adapter = new ArrayAdapter<Premissas>(this, android.R.layout.simple_list_item_1, termoAbertura.getLsPremissas());
                listview.setAdapter(adapter);
            } else if (atv.getEtapa() == EtapaProjeto.TermoAberturaRequisitos) {
                ArrayAdapter<RequisitoTermoAbertura> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, termoAbertura.getLsRequisitoTermoAbertura());
                listview.setAdapter(adapter);
            } else if (atv.getEtapa() == EtapaProjeto.TermoAberturaMarcos) {
                ArrayAdapter<Marco> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, termoAbertura.getLsMarco());
                listview.setAdapter(adapter);
            } else if (atv.getEtapa() == EtapaProjeto.TermoAberturaRestricoes) {
                ArrayAdapter<Restricoes> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, termoAbertura.getLsRestricoes());
                listview.setAdapter(adapter);
            }
        }

    }


    /**
     * atualiza o titulo da tela conforme o tipo de atividade
     *
     * @param etapa tipo de atividade
     */
    private void SetTitle(EtapaProjeto etapa) {

    }

}
