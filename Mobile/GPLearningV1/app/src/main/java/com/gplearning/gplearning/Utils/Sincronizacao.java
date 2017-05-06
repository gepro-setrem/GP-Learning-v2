package com.gplearning.gplearning.Utils;


import android.content.Context;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.DAO.ComentarioDAO;
import com.gplearning.gplearning.Enums.RecursosEnum;
import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.Models.ComentarioDao;
import com.gplearning.gplearning.Models.DaoSession;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Sincronizacao {

    public void SincronizaApp() {

    }

    public void SincronizaComentarios(Context context) throws ParseException {
        final ComentarioDAO comentarioDAO = new ComentarioDAO();
        DaoSession daoSession = ((App) context.getApplicationContext()).getDaoSession();
        final ComentarioDao daoLite = daoSession.getComentarioDao();
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.add(Calendar.DAY_OF_MONTH, -1);
        Date ultimaSincronizacao = dataAtual.getTime();  //MetodosPublicos.SelecionaUltimaSincronizacao(context, RecursosEnum.Comentario);
        List<Comentario> lsComentariosAPI = comentarioDAO.SelecionaComentarioPorData(ultimaSincronizacao);
        List<Comentario> lsComentariosLite = daoLite.queryBuilder().where(ComentarioDao.Properties.Criacao.ge(ultimaSincronizacao)).list();
        if (lsComentariosAPI != null) {
            for (Comentario com : lsComentariosAPI) {
                if (!EstaNaListaComentario(com, lsComentariosLite))
                    daoLite.insert(com);
            }
        }

        if (lsComentariosLite != null) {
            for (final Comentario com : lsComentariosLite) {
                if (!EstaNaListaComentario(com, lsComentariosAPI)) {// esta apenas no SQLite
                    if (com.getId() > 0) { // tem id= foi deletado no servidor
                        daoLite.deleteByKey(com.get_id());
                    } else {// não tem ID= é um novo comentário
//                        new Thread(new Runnable() {
//                            @Override
//                            public void run() {
                        int id = comentarioDAO.SalvarComentario(com);
                        if (id > 0) {
                            com.setId(id);
                            daoLite.update(com);
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

        MetodosPublicos.SalvaUltimaSincronizacao(context, RecursosEnum.Comentario, new Date());

    }


    private boolean EstaNaListaComentario(Comentario com, List<Comentario> lsComentarios) {
        if (lsComentarios != null && lsComentarios.size() > 0) {
            for (Comentario CM : lsComentarios) {
                if (CM.getId() == com.getId())
                    return true;
            }
        }
        return false;
    }


}
