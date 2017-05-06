package br.org.gdt.dao;

import br.org.gdt.model.Etapa;
import br.org.gdt.model.Turma;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("etapaDAO")
public class EtapaDAO extends DAO<Etapa> {

    public EtapaDAO() {
        classe = Etapa.class;
    }
    
        public List<Etapa> findbyTurma(Turma turma) {
        return entityManager.createQuery("from Etapa where turma = :t")
                .setParameter("t", turma).getResultList();
    }
}
