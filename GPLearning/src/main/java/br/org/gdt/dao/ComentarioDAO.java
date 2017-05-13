package br.org.gdt.dao;

import br.org.gdt.model.Etapa;
import br.org.gdt.model.Comentario;
import java.util.Date;
import java.util.List;
import javax.persistence.TemporalType;
import org.springframework.stereotype.Repository;

@Repository("comentarioDAO")
public class ComentarioDAO extends DAO<Comentario> {

    public ComentarioDAO() {
        classe = Comentario.class;
    }

    public List<Comentario> findbyEtapa(Etapa etapa, boolean orderDesc) {
        String order = " order by criacao";
        if (orderDesc) {
            order += " desc";
        }
        return entityManager.createQuery("from Comentario where etapa = :a" + order)
                .setParameter("a", etapa).getResultList();
//          return entityManager.createQuery("select c from Comentario as c left join c.atividade as atv where atv = :a")
//                .setParameter("a", atividade).getResultList();
    }

    public List<Comentario> findbyDate(Date date) {
        return entityManager.createQuery("from Comentario where com_dcriacao >= :d")
                .setParameter("d", date, TemporalType.DATE).getResultList();
    }

}
