package com.gplearning.gplearning.Utils;


import android.content.Context;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.DAO.ComentarioDAO;
import com.gplearning.gplearning.DAO.ProjetoDAO;
import com.gplearning.gplearning.Enums.PapelUsuario;
import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.Models.ComentarioDao;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Projeto;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Sincronizacao {

    public static void SincronizaApp(Context context, PapelUsuario papel) {
        ProjetoDAO projetoDAO = new ProjetoDAO();
        List<Projeto> lsProjetos;  // =  projetoDAO.Sel
        int id = MetodosPublicos.SelecionaSessaoidExterno(context);
        if (papel == PapelUsuario.user)
            lsProjetos = projetoDAO.SelecionaProjetosAluno(id);
        else
            lsProjetos = projetoDAO.SelecionaProjetosProfessor(id);

        if (lsProjetos != null && lsProjetos.size() > 0) {
            
        }

    }

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
