package com.gplearning.gplearning.Controllers;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.Enums.EtapaProjeto;
import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.Models.ComentarioDao;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Eap;
import com.gplearning.gplearning.Models.EapDao;
import com.gplearning.gplearning.Models.Etapa;
import com.gplearning.gplearning.Models.EtapaDao;
import com.gplearning.gplearning.Models.Marco;
import com.gplearning.gplearning.Models.MarcoDao;
import com.gplearning.gplearning.Models.Pessoa;
import com.gplearning.gplearning.Models.PessoaDao;
import com.gplearning.gplearning.Models.Premissas;
import com.gplearning.gplearning.Models.PremissasDao;
import com.gplearning.gplearning.Models.Projeto;
import com.gplearning.gplearning.Models.ProjetoDao;
import com.gplearning.gplearning.Models.Requisito;
import com.gplearning.gplearning.Models.RequisitoDao;
import com.gplearning.gplearning.Models.RequisitoTermoAbertura;
import com.gplearning.gplearning.Models.RequisitoTermoAberturaDao;
import com.gplearning.gplearning.Models.Restricoes;
import com.gplearning.gplearning.Models.RestricoesDao;
import com.gplearning.gplearning.Models.Stakeholder;
import com.gplearning.gplearning.Models.StakeholderDao;
import com.gplearning.gplearning.Models.Tarefa;
import com.gplearning.gplearning.Models.TarefaDao;
import com.gplearning.gplearning.Models.TermoAbertura;
import com.gplearning.gplearning.Models.TermoAberturaDao;
import com.gplearning.gplearning.R;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EtapaActivity extends AppCompatActivity {


    private Long idEtapa;
    private Long idProjeto;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etapa);
        daoSession = ((App) getApplication()).getDaoSession();

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {// && intent.getExtras().containsKey("ID")) {
            idEtapa = intent.getExtras().getLong(MetodosPublicos.key_idEtapa);
            idProjeto = intent.getExtras().getLong(MetodosPublicos.key_idProjeto);
            EtapaDao etapaDao = daoSession.getEtapaDao();
            MetodosPublicos.Log("Etapa", " etapaID:" + idEtapa);
            ///selecionar atividade
            Etapa atv = etapaDao.queryBuilder().where(EtapaDao.Properties._id.eq(idEtapa)).unique(); //AtividadeDao.Properties.Etapa = EtapaProjeto.Escopo).
            if (atv != null && atv.get_id() > 0) {
                AtualizaValores(atv);
                GetTitle(atv.getEtapa());
                //   AtualizaUltimoComentario();
            }
        }

        ((FloatingActionButton) findViewById(R.id.EtapaBtnComentario)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EtapaActivity.this, ComentarioActivity.class);
                intent.putExtra(MetodosPublicos.key_idEtapa, idEtapa);
                intent.putExtra(MetodosPublicos.key_idProjeto, idProjeto);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AtualizaUltimoComentario();
    }

    private void AtualizaUltimoComentario() {
        MetodosPublicos.Log("Event", "AtualizaUltimoComentario!!");
        ComentarioDao comentarioDao = daoSession.getComentarioDao();
        List<Comentario> comentarios = comentarioDao.queryBuilder().where(ComentarioDao.Properties.IdEtapa.eq(idEtapa), ComentarioDao.Properties.IdProjeto.eq(idProjeto), ComentarioDao.Properties.Deletado.eq(false)).orderDesc(ComentarioDao.Properties.Criacao).limit(1).list();
        if (comentarios != null && comentarios.size() > 0) {
            //etapaIncludeComentario
            ((TextView) findViewById(R.id.EtapaNenhumComentario)).setVisibility(View.GONE);
            ((LinearLayout) findViewById(R.id.etapaIncludeComentario)).setVisibility(View.VISIBLE);

            PessoaDao pessoaDao = daoSession.getPessoaDao();
            java.text.DateFormat dateFormatnew = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            TextView comentarioTxt = ((TextView) findViewById(R.id.itemListviewComentTexto));
            comentarioTxt.setText(comentarios.get(0).getDescricao());
            comentarioTxt.setMaxLines(5);

            ((TextView) findViewById(R.id.itemListviewComentData)).setText(dateFormatnew.format(comentarios.get(0).getCriacao()));
            try {
                List<Pessoa> lsPessoa = pessoaDao.queryBuilder().where(PessoaDao.Properties._id.eq(comentarios.get(0).getIdRemetente())).list();
                if (lsPessoa != null && lsPessoa.size() >= 0) {
                    ((TextView) findViewById(R.id.itemListviewComentNome)).setText(lsPessoa.get(0).getNome());
                    MetodosPublicos.CarregaimagemPerfil(this, ((ImageView) findViewById(R.id.itemListviewComentImagem)), lsPessoa.get(0).get_id());
                }
            } catch (Exception e) {
            }
        } else {
            ((LinearLayout) findViewById(R.id.etapaIncludeComentario)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.EtapaNenhumComentario)).setVisibility(View.VISIBLE);
        }
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
        MetodosPublicos.Log("Etapa", " etapa do tipo:" + atv.getEtapa());

        if (atv.getEtapa() == EtapaProjeto.DescricaoProjeto ||
                atv.getEtapa() == EtapaProjeto.JustificativaProjeto ||
                atv.getEtapa() == EtapaProjeto.PlanoGerenciamentoEscopo ||
                atv.getEtapa() == EtapaProjeto.Escopo) {
            //texto corrido
            ((ListView) findViewById(R.id.EtapaListview)).setVisibility(View.GONE);
            TextView etapaTextView = ((TextView) findViewById(R.id.EtapaTxtTexto));
            // etapaTextView.setVisibility(View.VISIBLE);
            ((ScrollView) findViewById(R.id.EtapaScrollviewTxt)).setVisibility(View.VISIBLE);

            if (atv.getEtapa() == EtapaProjeto.DescricaoProjeto ||
                    atv.getEtapa() == EtapaProjeto.JustificativaProjeto) {
                TermoAberturaDao termoAberturaDao = daoSession.getTermoAberturaDao();
                TermoAbertura termoAbertura = termoAberturaDao.queryBuilder().where(TermoAberturaDao.Properties.IdProjeto.eq(idProjeto)).unique();
                if (termoAbertura != null) {
                    if (atv.getEtapa() == EtapaProjeto.DescricaoProjeto) {
                        if (termoAbertura.getDescricao() != null && termoAbertura.getDescricao() != "") {
                            etapaTextView.setText(termoAbertura.getDescricao());
                        } else {
                            NenhumItem();
                        }
                    } else {
                        if (termoAbertura.getJustificativa() != null && termoAbertura.getJustificativa() != "") {
                            etapaTextView.setText(termoAbertura.getJustificativa());
                        } else {
                            NenhumItem();
                        }
                    }
                } else {
                    NenhumItem();
                }
            } else {
                ProjetoDao projetoDao = daoSession.getProjetoDao();
                Projeto projeto = projetoDao.queryBuilder().where(ProjetoDao.Properties._id.eq(idProjeto)).unique();
                if (projeto != null) {
                    if (atv.getEtapa() == EtapaProjeto.PlanoGerenciamentoEscopo) {
                        if (projeto.getPlanoEscopo() != null && projeto.getPlanoEscopo() != "") {
                            etapaTextView.setText(projeto.getPlanoEscopo());
                        } else {
                            NenhumItem();
                        }
                    } //IMPLEMENTAR ESTES METODOS AINDA!!!
                    else if (atv.getEtapa() == EtapaProjeto.Escopo) {
                        if (projeto.getEscopo() != null && projeto.getEscopo() != "") {
                            etapaTextView.setText(projeto.getEscopo());
                        } else {
                            NenhumItem();
                        }
                    }
                }
            }

        } else {
            ListView listview = ((ListView) findViewById(R.id.EtapaListview));
            listview.setVisibility(View.VISIBLE);
            //  ((TextView) findViewById(R.id.EtapaTxtTexto)).setVisibility(View.GONE);
            ((ScrollView) findViewById(R.id.EtapaScrollviewTxt)).setVisibility(View.GONE);

            if (atv.getEtapa() == EtapaProjeto.Stakeholders) {
                StakeholderDao stakeholderDao = daoSession.getStakeholderDao();
                final List<Stakeholder> lsStakeholders = stakeholderDao.queryBuilder().where(StakeholderDao.Properties.IdProjeto.eq(idProjeto)).list();
                if (lsStakeholders != null && lsStakeholders.size() > 0) {
                    ArrayAdapter<Stakeholder> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lsStakeholders);
                    listview.setAdapter(adapter);
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ShowDescription(lsStakeholders.get(position).getNome(), lsStakeholders.get(position).getContribuicao());
                        }
                    });
                } else {
                    NenhumItem();
                }
            } else if (atv.getEtapa() == EtapaProjeto.Requisitos) {
                RequisitoDao requisitoDao = daoSession.getRequisitoDao();
                final List<Requisito> lsRequisitos = requisitoDao.queryBuilder().where(RequisitoDao.Properties.IdProjeto.eq(idProjeto)).list();
                if (lsRequisitos != null && lsRequisitos.size() > 0) {
                    ArrayAdapter<Requisito> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lsRequisitos);
                    listview.setAdapter(adapter);
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            ShowDescription(lsRequisitos.get(position).getNome(), lsRequisitos.get(position).getDescricao());
                        }
                    });
                } else {
                    NenhumItem();
                }

            } else if (atv.getEtapa() == EtapaProjeto.Eap) {
                EapDao eapDao = daoSession.getEapDao();
                //   final List<Eap> lsEap = eapDao.queryBuilder().where(EapDao.Properties.IdProjeto.eq(idProjeto)).list();
                List<Eap> lsEap = eapDao.queryBuilder().where(EapDao.Properties.IdProjeto.eq(idProjeto)).orderAsc(EapDao.Properties.Id).limit(1).list();
                if (lsEap != null && lsEap.size() > 0) {
                    final List<Eap> lsItens = SelecionaEapTarefasProjeto(daoSession, lsEap.get(0), "1", false);
                    if (lsItens.size() > 0) {
                        ArrayAdapter<Eap> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lsItens);
                        listview.setAdapter(adapter);
                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ShowDescription(lsItens.get(position).getNome(), lsItens.get(position).getDescricao());
                            }
                        });

                    }
                } else {
                    NenhumItem();
                }
            } else if (atv.getEtapa() == EtapaProjeto.Cronograma) {
                EapDao eapDao = daoSession.getEapDao();
                List<Eap> Eaps = eapDao.queryBuilder().where(EapDao.Properties.IdProjeto.eq(idProjeto)).orderAsc(EapDao.Properties.Id).limit(1).list();
                if (Eaps != null && Eaps.size() > 0) {
                    final List<Eap> lsItens = SelecionaEapTarefasProjeto(daoSession, Eaps.get(0), "1", true);
                    if (lsItens.size() > 0) {
                        MetodosPublicos.Log("Event", "total de itens tarefas/eaps com :" + lsItens.size());
                        ArrayAdapter<Eap> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lsItens);
                        listview.setAdapter(adapter);
                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                ShowDescription(lsItens.get(position).getNome(), lsItens.get(position).getDescricao());
                            }
                        });
                    }
                } else {
                    NenhumItem();
                }
            } else {

                TermoAberturaDao termoAberturaDao = daoSession.getTermoAberturaDao();
                TermoAbertura termoAbertura = termoAberturaDao.queryBuilder().where(TermoAberturaDao.Properties.IdProjeto.eq(idProjeto)).unique();
                if (termoAbertura != null) {
                    MetodosPublicos.Log("event", "Termoabertura:id" + termoAbertura.get_id());

                    if (atv.getEtapa() == EtapaProjeto.Premissas) {
                        PremissasDao premissasDao = daoSession.getPremissasDao();
                        final List<Premissas> lsPremissas = premissasDao.queryBuilder().where(PremissasDao.Properties.IdTermoAbertura.eq(termoAbertura.get_id())).list();
                        if (lsPremissas != null && lsPremissas.size() > 0) {
                            ArrayAdapter<Premissas> adapter = new ArrayAdapter<Premissas>(this, android.R.layout.simple_list_item_1, lsPremissas);
                            listview.setAdapter(adapter);
                            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    ShowDescription("Premissa", lsPremissas.get(position).getDescricao());
                                }
                            });
                        } else {
                            NenhumItem();
                        }
                    } else if (atv.getEtapa() == EtapaProjeto.RequisitosTermoAbertura) {
                        RequisitoTermoAberturaDao RTAdao = daoSession.getRequisitoTermoAberturaDao();
                        final List<RequisitoTermoAbertura> lsRTA = RTAdao.queryBuilder().where(RequisitoTermoAberturaDao.Properties.IdTermoAbertura.eq(termoAbertura.get_id())).list();
                        if (lsRTA != null && lsRTA.size() > 0) {
                            ArrayAdapter<RequisitoTermoAbertura> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lsRTA);
                            listview.setAdapter(adapter);
                            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    ShowDescription(lsRTA.get(position).getNome(), lsRTA.get(position).getDescricao());
                                }
                            });
                        } else {
                            NenhumItem();
                        }
                    } else if (atv.getEtapa() == EtapaProjeto.Marcos) {
                        MarcoDao marcoDao = daoSession.getMarcoDao();
                        final List<Marco> lsMarco = marcoDao.queryBuilder().where(MarcoDao.Properties.IdTermoAbertura.eq(termoAbertura.get_id())).list();
                        if (lsMarco != null && lsMarco.size() > 0) {
                            ArrayAdapter<Marco> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lsMarco);
                            listview.setAdapter(adapter);
                            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    ShowDescription("Marco", lsMarco.get(position).getObjetivo());
                                }
                            });
                        } else {
                            NenhumItem();
                        }
                    } else if (atv.getEtapa() == EtapaProjeto.Restricoes) {
                        RestricoesDao restricoesDao = daoSession.getRestricoesDao();
                        final List<Restricoes> lsRestricoes = restricoesDao.queryBuilder().where(RestricoesDao.Properties.IdTermoAbertura.eq(termoAbertura.get_id())).list();
                        if (lsRestricoes != null && lsRestricoes.size() > 0) {
                            ArrayAdapter<Restricoes> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lsRestricoes);
                            listview.setAdapter(adapter);
                            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    ShowDescription("Restrições", lsRestricoes.get(position).getDescricao());
                                }
                            });
                        } else {
                            NenhumItem();
                        }
                    }
                } else {
                    NenhumItem();
                }
            }
        }
    }


    /**
     * atualiza o titulo da tela conforme o tipo de atividade
     *
     * @param etapa tipo de atividade
     */

    private void GetTitle(EtapaProjeto etapa) {

        switch (etapa) {

            case DescricaoProjeto:
                setTitle(R.string.description_opening_term);
                break;
            case JustificativaProjeto:
                setTitle(R.string.justification_opening_term);
                break;
            case Premissas:
                setTitle(R.string.premisses);
                break;
            case Restricoes:
                setTitle(R.string.restrictions);
                break;
            case Marcos:
                setTitle(R.string.project_milestones);
                break;
            case RequisitosTermoAbertura:
                setTitle(R.string.requirements_opening_term);
                break;
            case Stakeholders:
                setTitle(R.string.stakeholders);
                break;
            case PlanoGerenciamentoEscopo:
                setTitle(R.string.scope_planing);
                break;
            case Requisitos:
                setTitle(R.string.requirements);
                break;
            case Escopo:
                setTitle(R.string.scope);
                break;
            case Eap:
                setTitle(R.string.eap);
                break;
            case Cronograma:
                setTitle(R.string.schedule);
                break;

//            DescricaoProjeto,
//                    JustificativaProjeto,
//                    Premissas,
//                    Restricoes,
//                    Marcos,
//                    RequisitosTermoAbertura,
//                    Stakeholders,
//                    PlanoGerenciamentoEscopo,
//                    Requisitos,
//                    Escopo,
//                    Eap,
//                    Cronograma
        }
    }


    private void NenhumItem() {
        ((ListView) findViewById(R.id.EtapaListview)).setVisibility(View.GONE);
        TextView txt = ((TextView) findViewById(R.id.EtapaTxtTexto));
        txt.setVisibility(View.VISIBLE);
        ((ScrollView) findViewById(R.id.EtapaScrollviewTxt)).setVisibility(View.VISIBLE);
        txt.setGravity(View.TEXT_ALIGNMENT_CENTER);
        txt.setText(R.string.item_not_developed);
    }

    private void ShowDescription(String nome, String descricao) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(nome);
        alert.setMessage(descricao);
        alert.show();
//        Dialog dialog = new Dialog(this);
//        dialog.setTitle(nome);
//        dialog.setc

    }


    private List<Eap> SelecionaEapTarefasProjeto(DaoSession daoSession, Eap eapParent, String position, boolean tarefas) {
        MetodosPublicos.Log("Event", "BuscaEap position" + position);
        List<Eap> listEaps = new ArrayList<>();
        if (eapParent != null && eapParent.get_id() > 0) {
            EapDao eapDao = daoSession.getEapDao();
            List<Eap> Eaps = eapDao.queryBuilder().where(EapDao.Properties.IdPai.eq(eapParent.get_id())).orderAsc(EapDao.Properties.Id).list();
            if (Eaps != null && Eaps.size() > 0) {
                for (int i = 0; i < Eaps.size(); i++) {
                    position += "." + (i + 1);
                    Eaps.get(i).setPosition(position);
                    listEaps.add(Eaps.get(i));
                    if (tarefas) {

                        TarefaDao tarefaDao = daoSession.getTarefaDao();
                        List<Tarefa> lsTarefas = tarefaDao.queryBuilder().where(TarefaDao.Properties.IdEap.eq(Eaps.get(i).get_id())).orderAsc(TarefaDao.Properties.Id).list();
                        if (lsTarefas != null && lsTarefas.size() > 0) {
                            for (Tarefa tar : lsTarefas) {
                                String positionTar = position + "." + (i + 1);
                                Eap eapt = new Eap(tar.get_id(), tar.getId(), tar.getNome(), "", positionTar, false);
                                listEaps.add(eapt);
                                listEaps.addAll(SelecionaTarefas(daoSession, tar, positionTar));
                                // List<Tarefa> tarefasEap = SelecionaTarefas(daoSession, tar, position);
//                                if (tarefasEap != null) {
//                                    for (Tarefa tarefa : tarefasEap) {
//                                        Eap eap = new Eap(tarefa.get_id(), tarefa.getId(), tarefa.getNome(), "", position, false);
//                                        listEaps.add(eap);
//                                    }
//                                }
                            }
                        }
                    }
                    listEaps.addAll(SelecionaEapTarefasProjeto(daoSession, Eaps.get(i), position, tarefas));
                }
            }
        }
        return listEaps;
    }

    private List<Eap> SelecionaTarefas(DaoSession daoSession, Tarefa tarefa, String position) {
        MetodosPublicos.Log("Event", "BuscaTarefa position" + position);
        List<Eap> listItens = new ArrayList<>();
        TarefaDao tarefaDao = daoSession.getTarefaDao();
        List<Tarefa> lsTarefas = tarefaDao.queryBuilder().where(TarefaDao.Properties.Id.eq(tarefa.getIdEap())).orderAsc(TarefaDao.Properties.Id).list();
        if (lsTarefas != null && lsTarefas.size() > 0) {
            // for (Tarefa tar : lsTarefas) {
            for (int i = 0; i < lsTarefas.size(); i++) {
                position += "." + (lsTarefas.size() + 1);
                Eap eap = new Eap(lsTarefas.get(i).get_id(), lsTarefas.get(i).getId(), lsTarefas.get(i).getNome(), "", position, false);
                listItens.add(eap);
                listItens.addAll(SelecionaTarefas(daoSession, lsTarefas.get(i), position));
            }
        }
        return listItens;
    }


}
