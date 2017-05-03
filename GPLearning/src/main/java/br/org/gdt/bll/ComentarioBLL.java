package br.org.gdt.bll;

import br.org.gdt.dao.ComentarioDAO;
import br.org.gdt.model.Atividade;
import br.org.gdt.model.Comentario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("comentarioBLL")
public class ComentarioBLL extends BLL<Comentario> {

    @Autowired
    private ComentarioDAO dao;
    
    public List<Comentario> findbyAtividade(Atividade atividade){
        List<Comentario> lsComentario = new ArrayList<>();
        if(atividade!=null && atividade.getId()>0){
            lsComentario = dao.findbyAtividade(atividade);
        }
        return lsComentario;
    }
    
}
