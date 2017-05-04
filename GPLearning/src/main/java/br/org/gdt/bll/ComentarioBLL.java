package br.org.gdt.bll;

import br.org.gdt.dao.ComentarioDAO;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Comentario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("comentarioBLL")
public class ComentarioBLL extends BLL<Comentario> {

    @Autowired
    private ComentarioDAO dao;

    public List<Comentario> findbyAtividade(Etapa atividade) {
        List<Comentario> lsComentario = new ArrayList<>();
        if (atividade != null && atividade.getId() > 0) {
            lsComentario = dao.findbyAtividade(atividade);
            if (lsComentario != null && lsComentario.size() > 0) {
                for (Comentario com : lsComentario) {
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
                    }
                    if(com.getAtividade()!=null){
                        com.getAtividade().setProjeto(null);
                        com.getAtividade().setComentarios(null);   
                        com.getAtividade().setAtividadeParametros(null);
                    }
                }
            }
        }
        return lsComentario;
    }
}
