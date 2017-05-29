package com.gplearning.gplearning.Utils;


import android.content.Context;
import android.os.AsyncTask;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.DAO.AvaliacaoDAO;
import com.gplearning.gplearning.DAO.ComentarioDAO;
import com.gplearning.gplearning.DAO.EapDAO;
import com.gplearning.gplearning.DAO.ProjetoDAO;
import com.gplearning.gplearning.DAO.RequisitoDAO;
import com.gplearning.gplearning.DAO.StakeholderDAO;
import com.gplearning.gplearning.DAO.TermoAberturaDAO;
import com.gplearning.gplearning.Enums.PapelUsuario;
import com.gplearning.gplearning.Models.Avaliacao;
import com.gplearning.gplearning.Models.AvaliacaoDao;
import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.Models.ComentarioDao;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Eap;
import com.gplearning.gplearning.Models.EapDao;
import com.gplearning.gplearning.Models.Etapa;
import com.gplearning.gplearning.Models.EtapaDao;
import com.gplearning.gplearning.Models.Indicador;
import com.gplearning.gplearning.Models.IndicadorDao;
import com.gplearning.gplearning.Models.Marco;
import com.gplearning.gplearning.Models.MarcoDao;
import com.gplearning.gplearning.Models.Pessoa;
import com.gplearning.gplearning.Models.PessoaDao;
import com.gplearning.gplearning.Models.Premissas;
import com.gplearning.gplearning.Models.PremissasDao;
import com.gplearning.gplearning.Models.Projeto;
import com.gplearning.gplearning.Models.ProjetoComponentes;
import com.gplearning.gplearning.Models.ProjetoComponentesDao;
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
import com.gplearning.gplearning.Models.Turma;
import com.gplearning.gplearning.Models.TurmaDao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Sincronizacao {

    /**
     * Sincroniza o aplicativo ao usuário realizar o Login na primeira vez
     *
     * @param context
     * @param papel
     */
    public static void SincronizaApp(Context context, PapelUsuario papel) {
        ProjetoDAO projetoDAO = new ProjetoDAO(context);
        DaoSession daoSession = ((App) context.getApplicationContext()).getDaoSession();
        ProjetoDao daoProjeto = daoSession.getProjetoDao();
        PessoaDao daoPessoa = daoSession.getPessoaDao();
        TurmaDao daoTurma = daoSession.getTurmaDao();
        TermoAberturaDao daoTermoAbertura = daoSession.getTermoAberturaDao();
        MarcoDao daoMarco = daoSession.getMarcoDao();
        PremissasDao daoPremissas = daoSession.getPremissasDao();
        RequisitoTermoAberturaDao daoRTA = daoSession.getRequisitoTermoAberturaDao();
        RestricoesDao daoRestricoes = daoSession.getRestricoesDao();
        RequisitoDao daoRequisito = daoSession.getRequisitoDao();
        StakeholderDao daoStakeholder = daoSession.getStakeholderDao();
        ProjetoComponentesDao componentesDao = daoSession.getProjetoComponentesDao();

        List<Projeto> lsProjetos;  // =  projetoDAO.Sel
        int id = MetodosPublicos.SelecionaSessaoidExterno(context);
        if (papel == PapelUsuario.user)
            lsProjetos = projetoDAO.SelecionaProjetosAluno(id);
        else
            lsProjetos = projetoDAO.SelecionaProjetosProfessor(id);

        if (lsProjetos != null && lsProjetos.size() > 0) {
            for (Projeto projeto : lsProjetos) {
                Projeto prj = daoProjeto.queryBuilder().where(ProjetoDao.Properties.Id.eq(projeto.getId())).limit(1).unique(); //.load(projeto.getId());
                if (prj == null) {
                    daoProjeto.insert(projeto);
                    Projeto pCompleto = projetoDAO.SelecionaProjetoCompleto(projeto.getId());
                    if (pCompleto != null) {
                        Projeto PRJ = daoProjeto.load(projeto.get_id());
                        if (pCompleto.getGerente() != null && pCompleto.getGerente().getId() > 0) {
                            //MetodosPublicos.Log("Event", "antes de chegar ID GERENTE:" + projeto.getIdGerente());

                            long idGerente = InsereSeNaoEncontraPessoa(daoSession, pCompleto.getGerente());
                            PRJ.setIdGerente(idGerente);
//                            Pessoa gerente = daoPessoa.queryBuilder().where(PessoaDao.Properties.Id.eq(pCompleto.getGerente().getId())).limit(1).unique();
//                            if (gerente == null) {
//                                long idGerente = daoPessoa.insert(pCompleto.getGerente());
//                                PRJ.setIdGerente(idGerente);
//                            } else {
//                                PRJ.setIdGerente(gerente.get_id());
//                            }
                        }

                        if (pCompleto.getTurma() != null && pCompleto.getTurma().getId() > 0) {
                            Turma turma = daoTurma.queryBuilder().where(TurmaDao.Properties.Id.eq(pCompleto.getTurma().getId())).limit(1).unique();
                            if (turma == null) {
                                Long idProfessor;
                                if (turma.getProfessor() != null && turma.getProfessor().getId() > 0) {
                                    Pessoa professor = daoPessoa.queryBuilder().where(PessoaDao.Properties.Id.eq(turma.getProfessor().getId())).limit(1).unique();
                                    if (professor == null) {
                                        idProfessor = daoPessoa.insert(turma.getProfessor());
                                    } else {
                                        idProfessor = professor.get_id();
                                    }
                                    turma.setPro_id(idProfessor);
                                }
                                long idTurma = daoTurma.insert(pCompleto.getTurma());
                                PRJ.setIdTurma(idTurma);
                            } else {
                                PRJ.setIdTurma(turma.get_id());
                            }
                        }

                        if (pCompleto.getTermoabertura() != null && pCompleto.getTermoabertura().getId() > 0) {
                            TermoAbertura termoAbertura = daoTermoAbertura.queryBuilder().where(TermoAberturaDao.Properties.Id.eq(pCompleto.getTermoabertura().getId())).limit(1).unique();
                            if (termoAbertura == null) {
                                pCompleto.getTermoabertura().setIdProjeto(PRJ.get_id());
                                long idTermoAbertura = InsereTermoAbertura(daoSession, pCompleto.getTermoabertura());
                            }
//                            if (termoAbertura == null) {
//                                pCompleto.getTermoabertura().setIdProjeto(PRJ.get_id());
//                                long idTermoAbertura = daoTermoAbertura.insert(pCompleto.getTermoabertura());
//                                PRJ.setIdTermoAbertura(idTermoAbertura);
//                                termoAbertura = pCompleto.getTermoabertura();
//
//                                if (termoAbertura.getMarcos() != null) {
//                                    for (Marco marco : termoAbertura.getMarcos()) {
//                                        if (daoMarco.queryBuilder().where(MarcoDao.Properties.Id.eq(marco.getId())).count() == 0) {
//                                            marco.setIdTermoAbertura(termoAbertura.get_id());
//                                            daoMarco.insert(marco);
//                                        }
//                                    }
//                                }
//
//                                if (termoAbertura.getPremissas() != null) {
//                                    for (Premissas premissas : termoAbertura.getPremissas()) {
//                                        if (daoPremissas.queryBuilder().where(PremissasDao.Properties.Id.eq(premissas.getId())).count() == 0) {
//                                            premissas.setIdTermoAbertura(termoAbertura.get_id());
//                                            daoPremissas.insert(premissas);
//                                        }
//                                    }
//                                }
//
//                                if (termoAbertura.getRequisitosTermoAberturas() != null) {
//                                    for (RequisitoTermoAbertura RTA : termoAbertura.getRequisitosTermoAberturas()) {
//                                        if (daoRTA.queryBuilder().where(RequisitoTermoAberturaDao.Properties.Id.eq(RTA.getId())).count() == 0) {
//                                            RTA.setIdTermoAbertura(termoAbertura.get_id());
//                                            daoRTA.insert(RTA);
//                                        }
//                                    }
//                                }
//
//                                if (termoAbertura.getRestricoes() != null) {
//                                    for (Restricoes restricoes : termoAbertura.getRestricoes()) {
//                                        if (daoRestricoes.queryBuilder().where(RestricoesDao.Properties.Id.eq(restricoes.getId())).count() == 0) {
//                                            restricoes.setIdTermoAbertura(termoAbertura.get_id());
//                                            daoRestricoes.insert(restricoes);
//                                        }
//                                    }
//                                }
//                            } else {
//                                PRJ.setIdTermoAbertura(termoAbertura.get_id());
//                            }
                        }

                        if (pCompleto.getRequisitos() != null) {
                            for (Requisito requisito : pCompleto.getRequisitos()) {
                                if (daoRequisito.queryBuilder().where(RequisitoDao.Properties.Id.eq(requisito.getId())).count() == 0) {
                                    requisito.setIdProjeto(PRJ.get_id());
                                    daoRequisito.insert(requisito);
                                }
                            }
                        }

                        if (pCompleto.getStakeholders() != null) {
                            for (Stakeholder stakeholder : pCompleto.getStakeholders()) {
                                if (daoStakeholder.queryBuilder().where(StakeholderDao.Properties.Id.eq(stakeholder.getId())).count() == 0) {
                                    stakeholder.setIdProjeto(PRJ.get_id());
                                    daoStakeholder.insert(stakeholder);
                                }
                            }
                        }

                        if (pCompleto.getComponentes() != null) {
                            for (Pessoa pessoa : pCompleto.getComponentes()) {
                                Long idPessoa;
                                Pessoa pesBD = daoPessoa.queryBuilder().where(PessoaDao.Properties.Id.eq(pessoa.getId())).limit(1).unique();
                                if (pesBD == null) {
                                    idPessoa = daoPessoa.insert(pessoa);
                                } else {
                                    idPessoa = pesBD.get_id();
                                }
                                if (componentesDao.queryBuilder().where(ProjetoComponentesDao.Properties.IdPessoa.eq(idPessoa), ProjetoComponentesDao.Properties.IdProjeto.eq(PRJ.get_id())).count() == 0) {
                                    ProjetoComponentes pc = new ProjetoComponentes(PRJ.get_id(), idPessoa);
                                    componentesDao.insert(pc);
                                }
                            }
                        }

                        daoProjeto.update(PRJ);
                    }

                }
            }
        }
    }


//    public static void AtualizaApp(Projeto projeto) {
//        if (projeto != null) {
//           if(projeto.getGer_id()>0){
//               Pessoa gerrente =
//           }
//        }
//    }


    public static void AtualizaComentarios(DaoSession daoSession, Context context, int idPessoa) {
        // try {
        if (MetodosPublicos.IsConnected(context)) {
            final ComentarioDAO comentarioDAO = new ComentarioDAO();
            final ComentarioDao daoLite = daoSession.getComentarioDao();
            final ProjetoDao daoProjeto = daoSession.getProjetoDao();
            final EtapaDao daoEtapa = daoSession.getEtapaDao();
            final PessoaDao daoPessoa = daoSession.getPessoaDao();

            List<Comentario> lsComentariosAPI = comentarioDAO.SelecionaComentarioPorData(context, idPessoa); //SelecionaComentarioPorData(ultimaSincronizacao);
            List<Comentario> lsComentariosLite = daoLite.queryBuilder().list();// .whereOr(ComentarioDao.Properties.Id.eq(0), (ComentarioDao.Properties.Deletado.eq(true))).list();
            if (lsComentariosAPI != null) {
                for (Comentario com : lsComentariosAPI) {
                    if (!EstaNaListaComentario(com, lsComentariosLite)) {
                        if (com.getProjeto() != null) {
                            Projeto projeto = daoProjeto.queryBuilder().where(ProjetoDao.Properties.Id.eq(com.getProjeto().getId())).limit(1).unique();
                            if (projeto != null) {
                                com.setIdProjeto(projeto.get_id());
                            }
                        }
                        if (com.getEtapa() != null) {
                            Etapa etapa = daoEtapa.queryBuilder().where(EtapaDao.Properties.Id.eq(com.getEtapa().getId())).limit(1).unique();
                            if (etapa != null) {
                                com.setIdEtapa(etapa.get_id());
                            }
                        }
                        if (com.getRemetente() != null) {
                            List<Pessoa> lsPessoa = daoPessoa.queryBuilder().where(PessoaDao.Properties.Id.eq(com.getRemetente().getId())).list();
                            if (lsPessoa != null && lsPessoa.size() > 0) {
                                com.setIdRemetente(lsPessoa.get(0).get_id());
                            }
                        }
                        daoLite.insert(com);
                        // MetodosPublicos.Log("log", "Inserrir lite o id:" + com.getId() + "projetoId:" + com.getIdProjeto() + " etapaId:" + com.getIdEtapa());
                    }
                }
            }

            lsComentariosLite = daoLite.queryBuilder().whereOr(ComentarioDao.Properties.Id.eq(0), (ComentarioDao.Properties.Deletado.eq(true))).list();
            if (lsComentariosLite != null) {
                for (final Comentario com : lsComentariosLite) {
                    if (!EstaNaListaComentario(com, lsComentariosAPI)) {// esta apenas no SQLite
                        if (com.getId() > 0) { // tem id= foi deletado no servidor
                            //   MetodosPublicos.Log("log", "deletou lite o id:" + com.getId());
                            daoLite.deleteByKey(com.get_id());
                        } else {// não tem ID= é um novo comentário
//                                new Thread(new Runnable() {
//                                    @Override
//                                    public void run() {
                            if (com.getRemetente() == null && com.getIdRemetente() > 0) {
                                Pessoa remetente = daoPessoa.load(com.getIdRemetente());
                                com.setRemetente(remetente);
                            }
                            if (com.getIdProjeto() > 0) {
                                Projeto projeto = daoProjeto.load(com.getIdProjeto());
                                com.setProjeto(projeto);
                            }
                            if (com.getIdEtapa() > 0) {
                                Etapa etapa = daoEtapa.load(com.getIdEtapa());
                                com.setEtapa(etapa);
                            }
                            MetodosPublicos.Log("log", "Vai salvar o idRemetente:" + com.getRemetente().getId());
                            int idC = comentarioDAO.SalvarComentario(com, context);
                            if (idC > 0) {
                                MetodosPublicos.Log("log", "cadastrou o id:" + idC);
                                com.setId(idC);
                                daoLite.update(com);
                            } else {
                                MetodosPublicos.Log("log", "errou ao cadastrar o id:" + com.get_id());
                            }
//                                    }
//                                }).start();
                        }
                    } else {
                        if (com.getDeletado()) {
                            boolean deletado = comentarioDAO.DeletaComentario(com, context);
                            if (deletado)
                                daoLite.deleteByKey(com.get_id());
                        }
                    }
                }
            }
//                lsComentariosLite = daoLite.queryBuilder().whereOr(ComentarioDao.Properties.Id.eq(0), (ComentarioDao.Properties.Deletado.eq(true))).list();
//                MetodosPublicos.Log("Retono", " agora com os registros desatualizados:" + lsComentariosLite.size());
//                MetodosPublicos.SalvaUltimaSincronizacao(context, new Date());
        }
//        } catch (Exception e) {
//            MetodosPublicos.Log("ERRO ATUALIZAÇÃO", e.toString());
//        }
    }

    private static boolean EstaNaListaComentario(Comentario com, List<Comentario> lsComentarios) {
        if (lsComentarios != null && lsComentarios.size() > 0) {
            for (Comentario CM : lsComentarios) {
                if (CM.getId() == com.getId())
                    return true;
            }
        }
        return false;
    }

    /**
     * Atuliza o aplicativo toda vez que ele é aberto
     *
     * @param context
     * @throws ParseException
     */
    public static void SincronizaAplicativoData(final Context context) {
        if (MetodosPublicos.IsConnected(context)) {

            DaoSession daoSession = ((App) context.getApplicationContext()).getDaoSession();
            int id = MetodosPublicos.SelecionaSessaoidExterno(context);
            AtualizaProjeto(daoSession, context, id);
            MetodosPublicos.Log("Event", "VAI TERMO_ABERTURA");
            AtualizaTermoAbertura(daoSession, context, id);
            MetodosPublicos.Log("Event", "VAI REQUISITOS");
            AtualizaRequisitos(daoSession, context, id);
            MetodosPublicos.Log("Event", "VAI STAKEHOLDERS");
            AtualizaStakeholders(daoSession, context, id);
            MetodosPublicos.Log("Event", "VAI COMENTARIOS");
            AtualizaComentarios(daoSession, context, id);
            MetodosPublicos.Log("Event", "VAI AVALIAÇÕES");
            AtualizaAvaliacoes(daoSession, context, id);
            MetodosPublicos.SalvaUltimaSincronizacao(context, new Date());
        }
    }

    // metodos
    public static void AtualizaTermoAbertura(DaoSession daoSession, Context context, int id) {
        //try {
        TermoAberturaDAO termoAberturaDAO = new TermoAberturaDAO();
        ProjetoDao daoProjeto = daoSession.getProjetoDao();

        List<TermoAbertura> lsTermoAbertura = termoAberturaDAO.SelecionaTermoAberturaData(context, id);
        if (lsTermoAbertura != null) {
            for (TermoAbertura abertura : lsTermoAbertura) {
                if (abertura != null) {
                    if (abertura.getProjeto() != null) {
                        Projeto projeto = daoProjeto.queryBuilder().where(ProjetoDao.Properties.Id.eq(abertura.getProjeto().getId())).limit(1).unique();
                        if (projeto != null)
                            abertura.setIdProjeto(projeto.get_id());
                        InsereTermoAbertura(daoSession, abertura);
                    }
                }
            }
        }
//        } catch (Exception e) {
//            MetodosPublicos.Log("ERRO ATUALIZAÇÃO", e.toString());
//        }
    }

    public static void AtualizaRequisitos(DaoSession daoSession, Context context, int id) {
        //  try {
        RequisitoDAO requisitoDAO = new RequisitoDAO(context);
        RequisitoDao daoRequisito = daoSession.getRequisitoDao();
        ProjetoDao daoProjeto = daoSession.getProjetoDao();

        List<Requisito> lsRequisitos = requisitoDAO.SelecionaRequisitosData(id);
        List<Requisito> lsRequisitoLite = daoRequisito.loadAll();

        if (lsRequisitos != null) {
            for (Requisito requisito : lsRequisitos) {
                if (requisito != null) {
                    if (!EstaNaListaRequisito(requisito, lsRequisitoLite)) {
                        if (requisito.getProjeto() != null) {
                            Projeto projeto = daoProjeto.queryBuilder().where(ProjetoDao.Properties.Id.eq(requisito.getProjeto().getId())).limit(1).unique();
                            if (projeto != null) {
                                requisito.setIdProjeto(projeto.get_id());
                            }
                        }
                        daoRequisito.insert(requisito);
                    } else {
                        Requisito requisitoLite = daoRequisito.queryBuilder().where(RequisitoDao.Properties.Id.eq(requisito.getId())).limit(1).unique();
                        if (requisitoLite != null) {
                            requisitoLite.setNome(requisito.getNome());
                            requisitoLite.setDescricao(requisito.getDescricao());
                            daoRequisito.update(requisitoLite);
                        }
                    }
                }
            }
        }

        if (lsRequisitoLite != null) {
            for (Requisito req : lsRequisitoLite) {
                if (!EstaNaListaRequisito(req, lsRequisitos)) {
                    daoRequisito.deleteByKey(req.get_id());
                }
            }
        }
//        } catch (Exception e) {
//            MetodosPublicos.Log("ERRO ATUALIZAÇÃO", e.toString());
//        }
    }

    public static void AtualizaStakeholders(DaoSession daoSession, Context context, int id) {
        //  try {
        StakeholderDAO stakeholderDAO = new StakeholderDAO(context);
        StakeholderDao daoStakeholder = daoSession.getStakeholderDao();
        ProjetoDao daoProjeto = daoSession.getProjetoDao();

        List<Stakeholder> lsStakeholder = stakeholderDAO.SelecionaStakeholderData(id);
        List<Stakeholder> lsStakeholderLite = daoStakeholder.queryBuilder().list();
        if (lsStakeholder != null) {
            for (Stakeholder stakeholder : lsStakeholder) {
                if (stakeholder != null) {
                    if (!EstaNaListaStakeholder(stakeholder, lsStakeholderLite)) {
                        if (stakeholder.getProjeto() != null) {
                            Projeto projeto = daoProjeto.queryBuilder().where(ProjetoDao.Properties.Id.eq(stakeholder.getProjeto().getId())).limit(1).unique();
                            if (projeto != null) {
                                stakeholder.setIdProjeto(projeto.get_id());
                            }
                        }
                        daoStakeholder.insert(stakeholder);
                    } else {
                        Stakeholder stakeholderLite = daoStakeholder.queryBuilder().where(StakeholderDao.Properties.Id.eq(stakeholder.getId())).limit(1).unique();
                        if (stakeholderLite != null) {
                            stakeholderLite.setNome(stakeholder.getNome());
                            stakeholderLite.setContribuicao(stakeholder.getContribuicao());
                            stakeholderLite.setPapel(stakeholder.getPapel());
                            daoStakeholder.update(stakeholderLite);
                        }
                    }
                }
            }
        }

        if (lsStakeholderLite != null) {
            for (Stakeholder stakeholderLite : lsStakeholderLite) {
                if (!EstaNaListaStakeholder(stakeholderLite, lsStakeholder)) {
                    daoStakeholder.deleteByKey(stakeholderLite.get_id());
                }
            }
        }
//        } catch (Exception e) {
//            MetodosPublicos.Log("ERRO ATUALIZAÇÃO", e.toString());
//        }
    }

    public static void AtualizaProjeto(DaoSession daoSession, Context context, int id) {

        ProjetoDAO projetoDAO = new ProjetoDAO(context);
        ProjetoDao daoProjeto = daoSession.getProjetoDao();
        List<Projeto> lsProjetos = projetoDAO.SelecionaProjetosAluno(id); //SelecionaProjetosData(data, id); //ProjetosAluno(MetodosPublicos.SelecionaSessaoidExterno(context)); //Data(data);
        if (lsProjetos != null) {
            for (Projeto projeto : lsProjetos) {
                List<Projeto> lsProjetoLite = daoProjeto.queryBuilder().where(ProjetoDao.Properties.Id.eq(projeto.getId())).list();
                MetodosPublicos.Log("EVENT", "retornou ProjetoLite:" + lsProjetoLite.size());
                if (lsProjetoLite != null && lsProjetoLite.size() > 0) {
                    lsProjetoLite.get(0).setNome(projeto.getNome());
                    lsProjetoLite.get(0).setEmpresa(projeto.getEmpresa());
                    lsProjetoLite.get(0).setAlteracao(projeto.getAlteracao());
                    lsProjetoLite.get(0).setDescricao(projeto.getDescricao());
                    lsProjetoLite.get(0).setEscopo(projeto.getEscopo());
                    lsProjetoLite.get(0).setEstado(projeto.getEstado());
                    lsProjetoLite.get(0).setPlanoProjeto(projeto.getPlanoProjeto());
                    if (projeto.getGerente() != null) {
                        Long idGerente = InsereSeNaoEncontraPessoa(daoSession, projeto.getGerente());
                        if (idGerente > 0) {
                            lsProjetoLite.get(0).setIdGerente(idGerente);
                        }
                    }
                    daoProjeto.update(lsProjetoLite.get(0));
                } else {
                    if (projeto.getGerente() != null) {
                        Long idGerente = InsereSeNaoEncontraPessoa(daoSession, projeto.getGerente());
                        if (idGerente > 0) {
                            projeto.setIdGerente(idGerente);
                        }
                    }
                    daoProjeto.insert(projeto);
                }
            }
        }
//        } catch (Exception e) {
//            MetodosPublicos.Log("ERRO ATUALIZAÇÃO", e.toString());
//        }
    }

    public static void AtualizaAvaliacoes(DaoSession daoSession, Context context, int id) {
        //   try {
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO(context);
        AvaliacaoDao daoAvaliacao = daoSession.getAvaliacaoDao();
        ProjetoDao daoProjeto = daoSession.getProjetoDao();
        EtapaDao daoEtapa = daoSession.getEtapaDao();
        IndicadorDao daoIndicador = daoSession.getIndicadorDao();

        List<Avaliacao> lsAvaliacao = avaliacaoDAO.SelecionaAvaliacoesPessoa(id);
        List<Avaliacao> lsAvaliacaoLite = daoAvaliacao.queryBuilder().list();
        if (lsAvaliacao != null) {
            for (Avaliacao avaliacao : lsAvaliacao) {
                // Avaliacao avaliacaoLite = daoAvaliacao.queryBuilder().where(AvaliacaoDao.Properties.Id.eq(avaliacao.getId())).limit(1).unique();
                if (!EstaNaListaAvaliacao(avaliacao, lsAvaliacaoLite)) {
                    if (avaliacao.getProjeto() != null) {
                        Projeto projeto = daoProjeto.queryBuilder().where(ProjetoDao.Properties.Id.eq(avaliacao.getProjeto().getId())).limit(1).unique();
                        if (projeto != null) {
                            avaliacao.setIdProjeto(projeto.get_id());
                        }
                    }
                    if (avaliacao.getEtapa() != null) {
                        Etapa etapa = daoEtapa.queryBuilder().where(EtapaDao.Properties.Id.eq(avaliacao.getEtapa().getId())).limit(1).unique();
                        if (etapa != null) {
                            avaliacao.setIdEtapa(etapa.get_id());
                        }
                    }

                    if (avaliacao.getIndicador() != null) {
                        Indicador indicador = daoIndicador.queryBuilder().where(IndicadorDao.Properties.Id.eq(avaliacao.getIndicador().getId())).limit(1).unique();
                        if (indicador != null) {
                            avaliacao.setIdIndicador(indicador.get_id());
                        }
                    }

                    daoAvaliacao.insert(avaliacao);
                }
            }
        }
        if (lsAvaliacaoLite != null) {
            for (Avaliacao avaliacao : lsAvaliacaoLite) {
                if (!EstaNaListaAvaliacao(avaliacao, lsAvaliacao)) {
                    daoAvaliacao.deleteByKey(avaliacao.get_id());
                }
            }
        }
//        } catch (Exception e) {
//            MetodosPublicos.Log("ERRO ATUALIZAÇÃO", e.toString());
//        }
        new AtualizaPuntuacaoTotal().execute(context);
    }

    public static void AtualizaEap(DaoSession daoSession, Context context, int id) {
        EapDAO eapDAO = new EapDAO();
        EapDao daoEap = daoSession.getEapDao();
        TarefaDao daoTarefa = daoSession.getTarefaDao();
        ProjetoDao daoProjeto = daoSession.getProjetoDao();

        List<Eap> eapServidor = eapDAO.SelecionaEaps(id);
        // List<Eap> eapLite = daoEap.loadAll();

        if (eapServidor != null) {
            for (Eap eap : eapServidor) {
                Eap eapLite = daoEap.queryBuilder().where(EapDao.Properties.Id.eq(eap.getId())).limit(1).unique();
                Long idEap;
                if (eapLite == null) {
                    if (eap.getProjeto() != null) {
                        Projeto projeto = daoProjeto.queryBuilder().where(ProjetoDao.Properties.Id.eq(eap.getProjeto().getId())).limit(1).unique();
                        if (projeto != null)
                            eap.setIdProjeto(projeto.get_id());
                    }

                    idEap = daoEap.insert(eap);
                } else {
                    idEap = eapLite.get_id();
                    eapLite.setNome(eap.getNome());
                    eapLite.setDescricao(eap.getDescricao());
                    eapLite.setEntrega(eap.getEntrega());
                    eapLite.setInicio(eap.getInicio());
                    daoEap.update(eapLite);
                }
                if (eap.getTarefas() != null) {
                    InsereTarefa(daoTarefa, eap.getIdProjeto(), idEap, eap.getTarefas());
                }

                if (eap.getEaps() != null) {
                    InsereEap(daoEap, daoTarefa, eap.getIdProjeto(), idEap, eap.getEaps());
                }

            }
        }
    }

    private static void InsereEap(EapDao daoEap, TarefaDao daoTarefa, Long idProjeto, Long idParent, List<Eap> lsEap) {
        if (lsEap != null) {
            MetodosPublicos.Log("Event", "inserre eaps:" + lsEap.size());
            for (Eap eap : lsEap) {
                Eap eapLite = daoEap.queryBuilder().where(EapDao.Properties.Id.eq(eap.getId())).limit(1).unique();
                Long idEap;
                if (eapLite == null) {
                    eap.setIdProjeto(idProjeto);
                    eap.setIdPai(idParent);
                    idEap = daoEap.insert(eap);
                } else {
                    idEap = eapLite.get_id();
                    eapLite.setNome(eap.getNome());
                    eapLite.setDescricao(eap.getDescricao());
                    eapLite.setEntrega(eap.getEntrega());
                    eapLite.setInicio(eap.getInicio());
                    daoEap.update(eapLite);
                }

                if (eap.getTarefas() != null) {
                    InsereTarefa(daoTarefa, eap.getIdProjeto(), idEap, eap.getTarefas());
                }

                if (eap.getEaps() != null)
                    InsereEap(daoEap, daoTarefa, idProjeto, idEap, eap.getEaps());
            }
        }
    }


    private static void InsereTarefa(TarefaDao daoTarefa, Long idParent, Long idEap, List<Tarefa> lsTarefas) {
        if (lsTarefas != null) {
            MetodosPublicos.Log("Event", "inserre Tarefas:" + lsTarefas.size());
            for (Tarefa tarefa : lsTarefas) {
                Long idTarefa;
                Tarefa tarefaLite = daoTarefa.queryBuilder().where(TarefaDao.Properties.Id.eq(tarefa.getId())).limit(1).unique();
                if (tarefaLite == null) {
                    tarefa.setIdEap(idEap);
                    tarefa.setIdPai(idParent);
                    idTarefa = daoTarefa.insert(tarefa);
                } else {
                    idTarefa = tarefa.get_id();
                    tarefaLite.setNome(tarefa.getNome());
                    tarefaLite.setTermino(tarefa.getTermino());
                    tarefaLite.setConclusao(tarefa.getConclusao());
                    tarefaLite.setMarco(tarefa.getMarco());
                    tarefaLite.setInicio(tarefa.getInicio());
                    daoTarefa.update(tarefaLite);
                }

                if (tarefa.getTarefas() != null) {
                    InsereTarefa(daoTarefa, idTarefa, idEap, tarefa.getTarefas());
                }
            }
        }
    }


    private static boolean EstaNaListaAvaliacao(Avaliacao avaliacao, List<Avaliacao> lsAvaliacao) {
        if (lsAvaliacao != null) {
            for (Avaliacao ava : lsAvaliacao) {
                if (ava.getId() == avaliacao.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean EstaNaListaStakeholder(Stakeholder stakeholder, List<Stakeholder> lsStakeholder) {
        if (stakeholder != null) {
            for (Stakeholder stak : lsStakeholder) {
                if (stak.getId() == stakeholder.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean EstaNaListaRequisito(Requisito requisito, List<Requisito> lsRequisito) {
        if (requisito != null) {
            for (Requisito req : lsRequisito) {
                if (req.getId() == requisito.getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static Long InsereTermoAbertura(DaoSession daoSession, TermoAbertura termoAbertura) {
        Long idTermoAbertura = Long.valueOf(0);
        TermoAberturaDao daoTermoAbertura = daoSession.getTermoAberturaDao();
        MarcoDao daoMarco = daoSession.getMarcoDao();
        PremissasDao daoPremissas = daoSession.getPremissasDao();
        RequisitoTermoAberturaDao daoRTA = daoSession.getRequisitoTermoAberturaDao();
        RestricoesDao daoRestricoes = daoSession.getRestricoesDao();

        if (termoAbertura != null && termoAbertura.getId() > 0) {
            TermoAbertura termoAberturaLite = daoTermoAbertura.queryBuilder().where(TermoAberturaDao.Properties.Id.eq(termoAbertura.getId())).limit(1).unique();
            if (termoAberturaLite == null) {
                idTermoAbertura = daoTermoAbertura.insert(termoAbertura);
            } else {
                idTermoAbertura = termoAberturaLite.get_id();
                termoAberturaLite.setDescricao(termoAbertura.getDescricao());
                termoAberturaLite.setAlteracao(termoAbertura.getAlteracao());
                termoAberturaLite.setJustificativa(termoAbertura.getJustificativa());
                daoTermoAbertura.update(termoAberturaLite);
            }
            if (termoAbertura.getMarcos() != null) {
                for (Marco mc : daoMarco.queryBuilder().where(MarcoDao.Properties.IdTermoAbertura.eq(idTermoAbertura)).list()) {
                    daoMarco.delete(mc);
                }
                for (Marco marco : termoAbertura.getMarcos()) {
                    if (daoMarco.queryBuilder().where(MarcoDao.Properties.Id.eq(marco.getId())).count() == 0) {
                        marco.setIdTermoAbertura(idTermoAbertura);
                        daoMarco.insert(marco);
                    }
                }
            }

            if (termoAbertura.getPremissas() != null) {
                for (Premissas prs : daoPremissas.queryBuilder().where(PremissasDao.Properties.IdTermoAbertura.eq(idTermoAbertura)).list()) {
                    daoPremissas.delete(prs);
                }
                for (Premissas premissas : termoAbertura.getPremissas()) {
                    if (daoPremissas.queryBuilder().where(PremissasDao.Properties.Id.eq(premissas.getId())).count() == 0) {
                        premissas.setIdTermoAbertura(termoAbertura.get_id());
                        daoPremissas.insert(premissas);
                    }
                }
            }

            if (termoAbertura.getRequisitosTermoAberturas() != null) {
                for (RequisitoTermoAbertura RTA : daoRTA.queryBuilder().where(RequisitoTermoAberturaDao.Properties.IdTermoAbertura.eq(idTermoAbertura)).list()) {
                    daoRTA.delete(RTA);
                }
                for (RequisitoTermoAbertura RTA : termoAbertura.getRequisitosTermoAberturas()) {
                    if (daoRTA.queryBuilder().where(RequisitoTermoAberturaDao.Properties.Id.eq(RTA.getId())).count() == 0) {
                        RTA.setIdTermoAbertura(termoAbertura.get_id());
                        daoRTA.insert(RTA);
                    }
                }
            }

            if (termoAbertura.getRestricoes() != null) {
                for (Restricoes restricoes : daoRestricoes.queryBuilder().where(RestricoesDao.Properties.IdTermoAbertura.eq(idTermoAbertura)).list()) {
                    daoRestricoes.delete(restricoes);
                }
                for (Restricoes restricoes : termoAbertura.getRestricoes()) {
                    if (daoRestricoes.queryBuilder().where(RestricoesDao.Properties.Id.eq(restricoes.getId())).count() == 0) {
                        restricoes.setIdTermoAbertura(termoAbertura.get_id());
                        daoRestricoes.insert(restricoes);
                    }
                }
            }
        }
        return idTermoAbertura;
    }

    private static Long InsereSeNaoEncontraPessoa(DaoSession daoSession, Pessoa pessoa) {
        PessoaDao daoPessoa = daoSession.getPessoaDao();
        Pessoa gerenteLite = daoPessoa.queryBuilder().where(PessoaDao.Properties.Id.eq(pessoa.getId())).limit(1).unique();
        if (gerenteLite == null) {
            return daoPessoa.insert(pessoa);
        } else {
            return gerenteLite.get_id();
        }
        //   return Long.valueOf(0);
    }

    //

    private static class AtualizaPuntuacaoTotal extends AsyncTask<Context, String, Integer> {
        private Context context;

        @Override
        protected Integer doInBackground(Context... params) {
            DaoSession daoSession = ((App) params[0].getApplicationContext()).getDaoSession();
            context = params[0];

            MetodosPublicos.Log("Event", " VAI ATUALIZAR PONTUAÇÃO-------------");

            AvaliacaoDao daoAvaliacao = daoSession.getAvaliacaoDao();
            EtapaDao daoEtapa = daoSession.getEtapaDao();
            PessoaDao daoPessoa = daoSession.getPessoaDao();
            Pessoa usuario = daoPessoa.load(MetodosPublicos.SelecionaSessaoId(params[0]));
            List<Etapa> lsEtapa = daoEtapa.queryBuilder().where(PessoaDao.Properties.IdTurma.eq(usuario.getIdTurma())).list();

            int pontuacaoTotal = 0;
            if (lsEtapa != null && lsEtapa.size() > 0) {
                for (Etapa etapa : lsEtapa) {
                    int valor = 0;
                    List<Avaliacao> lsAvaliacao = daoAvaliacao.queryBuilder().where(AvaliacaoDao.Properties.IdEtapa.eq(etapa.get_id())).list();
                    if (lsAvaliacao != null) {
                        for (Avaliacao ava : lsAvaliacao) {
                            valor += ava.getValor();
                        }
                        pontuacaoTotal += valor;
                    }
                }
                return pontuacaoTotal;
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer pontuacao) {
            MetodosPublicos.Log("Event", " VAI SALVA PONTUAÇÃO-------:" + pontuacao);
            MetodosPublicos.SalvaPontuacao(context, pontuacao);
            super.onPostExecute(pontuacao);
        }
    }

}
