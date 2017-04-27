package br.org.gdt.dao;

import br.org.gdt.model.EAP;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Tarefa;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("tarefaDAO")
public class TarefaDAO extends DAO<Tarefa> {

    public TarefaDAO() {
        classe = Tarefa.class;
    }

    public List<Tarefa> findbyTarefa(Tarefa tarefa) {
        return entityManager.createQuery("from Tarefa where pai = :t")
                .setParameter("t", tarefa).getResultList();
    }

    public List<Tarefa> findbyEAP(EAP eap) {
        return entityManager.createQuery("from Tarefa where eap = :e")
                .setParameter("e", eap).getResultList();
    }

    public List<Tarefa> findbyProjeto(Projeto projeto) {
        return entityManager.createQuery("from Tarefa where eap.projeto = :p")
                .setParameter("p", projeto).getResultList();
    }
}
