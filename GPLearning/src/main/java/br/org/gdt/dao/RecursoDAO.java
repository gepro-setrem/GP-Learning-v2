package br.org.gdt.dao;

import br.org.gdt.model.EAP;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Recurso;
import br.org.gdt.model.Tarefa;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("recursoDAO")
public class RecursoDAO extends DAO<Recurso> {

    public RecursoDAO() {
        classe = Recurso.class;
    }

    public List<Recurso> findbyRecursos(int tar_id) {
        List<Recurso> recursos = null;
        if (tar_id > 0) {
            recursos = entityManager.createQuery("from Recurso where tarefa.id = :tar_id").setParameter("tar_id", tar_id).getResultList();
        }
        return recursos;
    }
}
