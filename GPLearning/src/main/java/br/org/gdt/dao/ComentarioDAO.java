package br.org.gdt.dao;

import br.org.gdt.model.Atividade;
import br.org.gdt.model.Comentario;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("comentarioDAO")
public class ComentarioDAO extends DAO<Comentario> {

    public ComentarioDAO() {
        classe = Comentario.class;
    }
    
    public List<Comentario> findbyAtividade(Atividade atividade){
        return entityManager.createQuery("select c from Comentario as c left join c.act_id as atv where atv = :a")
                .setParameter("a", atividade).getResultList();
    }
}
