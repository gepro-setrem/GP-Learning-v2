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

    public EAP findbyEAP(Projeto projeto) {
        EAP eap = null;
        if (projeto != null && projeto.getId() > 0) {
            List results = entityManager.createQuery("from EAP where projeto = :p and pai is null")
                    .setParameter("p", projeto).getResultList();
            if (!results.isEmpty()) {
                eap = (EAP) results.get(0);
            }
        }
        return eap;
    }

    public List<Tarefa> findbyChildren(int eap_id, int tar_id) {
        List<Tarefa> lsTarefa = null;
        if (eap_id > 0) {
            lsTarefa = entityManager.createQuery("from Tarefa where eap.id = :eap_id").setParameter("eap_id", eap_id).getResultList();
        } else if (tar_id > 0) {
            lsTarefa = entityManager.createQuery("from Tarefa where pai.id = :tar_id").setParameter("tar_id", tar_id).getResultList();
        }
        return lsTarefa;
    }
}
