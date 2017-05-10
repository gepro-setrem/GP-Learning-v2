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
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Etapa;
import com.gplearning.gplearning.Models.EtapaDao;
import com.gplearning.gplearning.Models.Marco;
import com.gplearning.gplearning.Models.Premissas;
import com.gplearning.gplearning.Models.Projeto;
import com.gplearning.gplearning.Models.ProjetoDao;
import com.gplearning.gplearning.Models.Requisito;
import com.gplearning.gplearning.Models.RequisitoDao;
import com.gplearning.gplearning.Models.RequisitoTermoAbertura;
import com.gplearning.gplearning.Models.Restricoes;
import com.gplearning.gplearning.Models.Stakeholder;
import com.gplearning.gplearning.Models.StakeholderDao;
import com.gplearning.gplearning.Models.TermoAbertura;
import com.gplearning.gplearning.Models.TermoAberturaDao;
import com.gplearning.gplearning.R;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import java.util.List;

public class EtapaActivity extends AppCompatActivity {


    private Long idEtapa;
    private Long idProjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa);

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey("ID")) {
            idEtapa = intent.getExtras().getLong(MetodosPublicos.key_idEtapa);
            idProjeto = intent.getExtras().getLong(MetodosPublicos.key_idProjeto);
            DaoSession daoSession = ((App) getApplication()).getDaoSession();
            EtapaDao etapaDao = daoSession.getEtapaDao();
            ///selecionar atividade
            Etapa atv = etapaDao.queryBuilder().where(EtapaDao.Properties._id.eq(idEtapa)).unique(); //AtividadeDao.Properties.Etapa = EtapaProjeto.Escopo).
            if (atv != null && atv.get_id() > 0) {
                AtualizaValores(atv);
                SetTitle(atv.getEtapa());
            }
        }

        ((FloatingActionButton) findViewById(R.id.EtapaBtnComentario)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EtapaActivity.this, ComentarioActivity.class);
                intent.putExtra(MetodosPublicos.key_idEtapa, idEtapa);
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

    /**
     * Atualiza a view, conforme o tipo de atividade;
     *
     * @param atv
     */
    private void AtualizaValores(Etapa atv) {
        DaoSession daoSession = ((App) getApplication()).getDaoSession();

        if (atv.getEtapa() == EtapaProjeto.DescricaoProjeto ||
                atv.getEtapa() == EtapaProjeto.JustificativaProjeto ||
                atv.getEtapa() == EtapaProjeto.PlanoGerenciamentoEscopo ||
                atv.getEtapa() == EtapaProjeto.Escopo) {
            //texto corrido
            ((ListView) findViewById(R.id.EtapaListview)).setVisibility(View.GONE);
            TextView etapaTextView = ((TextView) findViewById(R.id.EtapaTxtTexto));
            etapaTextView.setVisibility(View.VISIBLE);

            if (atv.getEtapa() == EtapaProjeto.DescricaoProjeto ||
                    atv.getEtapa() == EtapaProjeto.JustificativaProjeto) {
                TermoAberturaDao termoAberturaDao = daoSession.getTermoAberturaDao();
                TermoAbertura termoAbertura = termoAberturaDao.queryBuilder().where(TermoAberturaDao.Properties.IdProjeto.eq(idProjeto)).unique();
                if (termoAbertura != null) {
                    if (atv.getEtapa() == EtapaProjeto.DescricaoProjeto)
                        etapaTextView.setText(termoAbertura.getDescricao());
                    else
                        etapaTextView.setText(termoAbertura.getJustificativa());
                }
            } else {
                ProjetoDao projetoDao = daoSession.getProjetoDao();
                Projeto projeto = projetoDao.queryBuilder().where(ProjetoDao.Properties.Id.eq(idProjeto)).unique();

                if (atv.getEtapa() == EtapaProjeto.PlanoGerenciamentoEscopo) {
                    etapaTextView.setText(projeto.getPlanoEscopo());
                } //IMPLEMENTAR ESTES METODOS AINDA!!!
                else if (atv.getEtapa() == EtapaProjeto.Escopo) {
                    etapaTextView.setText(projeto.getEscopo());
                }
            }

        } else {
            ListView listview = ((ListView) findViewById(R.id.EtapaListview));
            listview.setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.EtapaTxtTexto)).setVisibility(View.GONE);

            if (atv.getEtapa() == EtapaProjeto.Stakeholders) {
                StakeholderDao stakeholderDao = daoSession.getStakeholderDao();
                List<Stakeholder> lsStakeholders = stakeholderDao.queryBuilder().where(StakeholderDao.Properties.IdProjeto.eq(idProjeto)).list();
                ArrayAdapter<Stakeholder> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lsStakeholders);
                listview.setAdapter(adapter);

            } else if (atv.getEtapa() == EtapaProjeto.Requisitos) {//IMPLEMENTAR ESTES METODOS AINDA!!!
                RequisitoDao requisitoDao = daoSession.getRequisitoDao();
                List<Requisito> lsRequisitos = requisitoDao.queryBuilder().where(RequisitoDao.Properties.IdProjeto.eq(idProjeto)).list();
                ArrayAdapter<Requisito> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lsRequisitos);
                listview.setAdapter(adapter);
            } else {

                TermoAberturaDao termoAberturaDao = daoSession.getTermoAberturaDao();
                TermoAbertura termoAbertura = termoAberturaDao.queryBuilder().where(TermoAberturaDao.Properties.IdProjeto.eq(idProjeto)).unique();

                if (atv.getEtapa() == EtapaProjeto.Premissas) {
                    ArrayAdapter<Premissas> adapter = new ArrayAdapter<Premissas>(this, android.R.layout.simple_list_item_1, termoAbertura.getPremissas());
                    listview.setAdapter(adapter);
                } else if (atv.getEtapa() == EtapaProjeto.RequisitosTermoAbertura) {
                    ArrayAdapter<RequisitoTermoAbertura> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, termoAbertura.getRequisitosTermoAberturas());
                    listview.setAdapter(adapter);
                } else if (atv.getEtapa() == EtapaProjeto.Marcos) {
                    ArrayAdapter<Marco> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, termoAbertura.getMarcos());
                    listview.setAdapter(adapter);
                } else if (atv.getEtapa() == EtapaProjeto.Restricoes) {
                    ArrayAdapter<Restricoes> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, termoAbertura.getRestricoes());
                    listview.setAdapter(adapter);
                }
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
