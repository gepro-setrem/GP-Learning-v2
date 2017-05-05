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

    public List<Recurso> findbyTarefa(Tarefa tarefa) {
        return entityManager.createQuery("from Recurso where tarefa = :t")
                .setParameter("t", tarefa).getResultList();
    }
}
