package br.org.gdt.dao;

import br.org.gdt.enumerated.EtapaProjeto;
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

    public Etapa findbyTurmaEtapa(Turma turma, EtapaProjeto etapaProjeto) {
        List<Etapa> lsEtapa = entityManager.createQuery("from Etapa where turma = :t and etapa= :e")
                .setParameter("t", turma).setParameter("e", etapaProjeto).getResultList();
        Etapa etapa = null;
        if (lsEtapa.size() > 0) {
            etapa = lsEtapa.get(0);
        }
        return etapa;
    }

}
