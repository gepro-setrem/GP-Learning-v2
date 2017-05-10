package com.gplearning.gplearning.Utils;


import android.content.Context;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.DAO.ComentarioDAO;
import com.gplearning.gplearning.DAO.ProjetoDAO;
import com.gplearning.gplearning.Enums.PapelUsuario;
import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.Models.ComentarioDao;
import com.gplearning.gplearning.Models.DaoSession;
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
import com.gplearning.gplearning.Models.TermoAbertura;
import com.gplearning.gplearning.Models.TermoAberturaDao;
import com.gplearning.gplearning.Models.Turma;
import com.gplearning.gplearning.Models.TurmaDao;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Sincronizacao {

    public static void SincronizaApp(Context context, PapelUsuario papel) {
        ProjetoDAO projetoDAO = new ProjetoDAO();
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
                Projeto prj = daoProjeto.queryBuilder().where(ProjetoDao.Properties.Id.eq(projeto.getId())).unique(); //.load(projeto.getId());
                if (prj == null) {
                    daoProjeto.insert(projeto);
                    Projeto pCompleto = projetoDAO.SelecionaProjetoCompleto(projeto.getId());
                    if (pCompleto != null) {
                        Projeto PRJ = daoProjeto.load(projeto.get_id());
                        if (pCompleto.getGerente() != null && pCompleto.getGerente().getId() > 0) {
                            //MetodosPublicos.Log("Event", "antes de chegar ID GERENTE:" + projeto.getIdGerente());
                            Pessoa gerente = daoPessoa.queryBuilder().where(PessoaDao.Properties.Id.eq(pCompleto.getGerente().getId())).unique();
                            if (gerente == null) {
                                long idGerente = daoPessoa.insert(pCompleto.getGerente());
                                PRJ.setIdGerente(idGerente);
                            } else {
                                PRJ.setIdGerente(gerente.get_id());
                            }
                        }

                        if (pCompleto.getTurma() != null && pCompleto.getTurma().getId() > 0) {
                            Turma turma = daoTurma.queryBuilder().where(TurmaDao.Properties.Id.eq(pCompleto.getTurma().getId())).unique();
                            if (turma == null) {
                                long idTurma = daoTurma.insert(pCompleto.getTurma());
                                PRJ.setIdTurma(idTurma);
                            } else {
                                PRJ.setIdTurma(turma.get_id());
                            }
                        }

                        if (pCompleto.getTermoabertura() != null && pCompleto.getTermoabertura().getId() > 0) {
                            TermoAbertura termoAbertura = daoTermoAbertura.queryBuilder().where(TermoAberturaDao.Properties.Id.eq(pCompleto.getTermoabertura().getId())).unique();
                            if (termoAbertura == null) {
                                long idTermoAbertura = daoTermoAbertura.insert(pCompleto.getTermoabertura());
                                PRJ.setIdTermoAbertura(idTermoAbertura);
                                termoAbertura = pCompleto.getTermoabertura();

                                if (termoAbertura.getMarcos() != null) {
                                    for (Marco marco : termoAbertura.getMarcos()) {
                                        if (daoMarco.queryBuilder().where(MarcoDao.Properties.Id.eq(marco.getId())).count() == 0) {
                                            daoMarco.insert(marco);
                                        }
                                    }
                                }

                                if (termoAbertura.getPremissas() != null) {
                                    for (Premissas premissas : termoAbertura.getPremissas()) {
                                        if (daoPremissas.queryBuilder().where(PremissasDao.Properties.Id.eq(premissas.getId())).count() == 0) {
                                            daoPremissas.insert(premissas);
                                        }
                                    }
                                }

                                if (termoAbertura.getRequisitosTermoAberturas() != null) {
                                    for (RequisitoTermoAbertura RTA : termoAbertura.getRequisitosTermoAberturas()) {
                                        if (daoRTA.queryBuilder().where(RequisitoTermoAberturaDao.Properties.Id.eq(RTA.getId())).count() == 0) {
                                            daoRTA.insert(RTA);
                                        }
                                    }
                                }

                                if (termoAbertura.getRestricoes() != null) {
                                    for (Restricoes restricoes : termoAbertura.getRestricoes()) {
                                        if (daoRestricoes.queryBuilder().where(RestricoesDao.Properties.Id.eq(restricoes.getId())).count() == 0) {
                                            daoRestricoes.insert(restricoes);
                                        }
                                    }
                                }
                            } else {
                                PRJ.setIdTermoAbertura(termoAbertura.get_id());
                            }
                        }

                        if (pCompleto.getRequisitos() != null) {
                            for (Requisito requisito : pCompleto.getRequisitos()) {
                                if (daoRequisito.queryBuilder().where(RequisitoDao.Properties.Id.eq(requisito.getId())).count() == 0) {
                                    daoRequisito.insert(requisito);
                                }
                            }
                        }

                        if (pCompleto.getStakeholders() != null) {
                            for (Stakeholder stakeholder : pCompleto.getStakeholders()) {
                                if (daoStakeholder.queryBuilder().where(StakeholderDao.Properties.Id.eq(stakeholder.getId())).count() == 0) {
                                    daoStakeholder.insert(stakeholder);
                                }
                            }
                        }

                        if (pCompleto.getComponentes() != null) {
                            for (Pessoa pessoa : pCompleto.getComponentes()) {
                                Long idPessoa;
                                Pessoa pesBD = daoPessoa.queryBuilder().where(PessoaDao.Properties.Id.eq(pessoa.getId())).unique();
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


    public static void SincronizaComentarios(Context context) throws ParseException {
        if (MetodosPublicos.IsConnected(context)) {
            final ComentarioDAO comentarioDAO = new ComentarioDAO();
            DaoSession daoSession = ((App) context.getApplicationContext()).getDaoSession();
            final ComentarioDao daoLite = daoSession.getComentarioDao();
            Calendar dataAtual = Calendar.getInstance();
            dataAtual.add(Calendar.DAY_OF_MONTH, -1);
            Date ultimaSincronizacao = dataAtual.getTime();  //MetodosPublicos.SelecionaUltimaSincronizacao(context, RecursosEnum.Comentario);
            List<Comentario> lsComentariosAPI = comentarioDAO.SelecionaComentarioPorData(ultimaSincronizacao);
            List<Comentario> lsComentariosLite = daoLite.queryBuilder().list();// .whereOr(ComentarioDao.Properties.Id.eq(0), (ComentarioDao.Properties.Deletado.eq(true))).list();
            if (lsComentariosAPI != null) {
                for (Comentario com : lsComentariosAPI) {
                    if (!EstaNaListaComentario(com, lsComentariosLite)) {
                        MetodosPublicos.Log("log", "Inserrir lite o id:" + com.getId());
                        daoLite.insert(com);
                    }
                }
            }

            lsComentariosLite = daoLite.queryBuilder().whereOr(ComentarioDao.Properties.Id.eq(0), (ComentarioDao.Properties.Deletado.eq(true))).list();
            if (lsComentariosLite != null) {
                for (final Comentario com : lsComentariosLite) {
                    if (!EstaNaListaComentario(com, lsComentariosAPI)) {// esta apenas no SQLite
                        if (com.getId() > 0) { // tem id= foi deletado no servidor
                            MetodosPublicos.Log("log", "deletou lite o id:" + com.getId());
                            daoLite.deleteByKey(com.get_id());
                        } else {// não tem ID= é um novo comentário
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
                            int id = comentarioDAO.SalvarComentario(com);
                            if (id > 0) {
                                MetodosPublicos.Log("log", "cadastrou o id:" + id);
                                com.setId(id);
                                daoLite.update(com);
                            } else {
                                MetodosPublicos.Log("log", "errou ao cadastrar o id:" + com.get_id());
                            }
//                            }
//                        }).start();
                        }
                    } else {
                        if (com.getDeletado()) {
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
                            boolean deletado = comentarioDAO.DeletaComentario(com);
                            if (deletado)
                                daoLite.deleteByKey(com.get_id());
//                            }
//                        }).start();
                        }
                    }
                }
            }
            lsComentariosLite = daoLite.queryBuilder().whereOr(ComentarioDao.Properties.Id.eq(0), (ComentarioDao.Properties.Deletado.eq(true))).list();
            MetodosPublicos.Log("Retono", " agora com os registros desatualizados:" + lsComentariosLite.size());
            // MetodosPublicos.SalvaUltimaSincronizacao(context, RecursosEnum.Comentario, new Date());
        }
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


}
