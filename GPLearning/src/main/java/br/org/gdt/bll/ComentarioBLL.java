package br.org.gdt.bll;

import br.org.gdt.dao.ComentarioDAO;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Comentario;
import br.org.gdt.model.Projeto;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("comentarioBLL")
public class ComentarioBLL extends BLL<Comentario> {

    @Autowired
    private ComentarioDAO dao;

    public List<Comentario> findbyProjetoEtapa(Projeto projeto, Etapa etapa, boolean orderDesc, boolean clear) {
        List<Comentario> lsComentario = new ArrayList<>();
        if (projeto != null && projeto.getId() > 0 && etapa != null && etapa.getId() > 0) {
            lsComentario = dao.findbyProjetoEtapa(projeto, etapa, orderDesc);
            if (clear && lsComentario != null) {
                for (Comentario comentario : lsComentario) {
                    comentario = CleanComentario(comentario);
                }
            }
        }
        return lsComentario;
    }

    public List<Comentario> findbyEtapa(Etapa etapa, boolean orderDesc, boolean clear) {
        List<Comentario> lsComentario = new ArrayList<>();
        if (etapa != null && etapa.getId() > 0) {
            lsComentario = dao.findbyEtapa(etapa, orderDesc);
            if (clear && lsComentario != null) {
                for (Comentario com : lsComentario) {
                    com = CleanComentario(com);
                }
            }
        }
        return lsComentario;
    }

    public List<Comentario> findbyDate(Date date) {
        List<Comentario> lsComentarios = new ArrayList<>();
        if (date != null) {
            return dao.findbyDate(date);
        }
        return lsComentarios;
    }

    public Comentario CleanComentario(Comentario com) {
        if (com.getDestinatario() != null) {
            com.getDestinatario().setLogin(null);
            com.getDestinatario().setProjetos(null);
            com.getDestinatario().setTurmasprofessor(null);
            com.getDestinatario().setProjetosgerente(null);
            com.getDestinatario().setIndicadoresprofessor(null);
        }
        if (com.getRemetente() != null) {
            com.getRemetente().setLogin(null);
            com.getRemetente().setProjetos(null);
            com.getRemetente().setTurmasprofessor(null);
            com.getRemetente().setProjetosgerente(null);
            com.getRemetente().setIndicadoresprofessor(null);
            com.getRemetente().setImagem(null);
            com.getRemetente().setTurma(null);
        }
        if (com.getEtapa() != null) {
            com.getEtapa().setTurma(null);
            com.getEtapa().setComentarios(null);
            com.getEtapa().setAtividadeParametros(null);
            com.getEtapa().setAvaliacoes(null);
            com.getEtapa().setIndicadores(null);
        }
        if (com.getProjeto() != null) {
            com.getProjeto().setComponentes(null);
            com.getProjeto().setEaps(null);
            com.getProjeto().setGerente(null);
            com.getProjeto().setTermoabertura(null);
            com.getProjeto().setTurma(null);
            com.getProjeto().setRequisitos(null);
            com.getProjeto().setStakeholders(null);
            com.getProjeto().setTermoabertura(null);
            com.getProjeto().setComentarios(null);
            com.getProjeto().setAvaliacoes(null);
        }
        return com;
    }

}
