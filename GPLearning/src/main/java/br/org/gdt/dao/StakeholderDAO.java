package br.org.gdt.dao;

import br.org.gdt.model.Projeto;
import br.org.gdt.model.Stakeholder;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("stakeholderDAO")
public class StakeholderDAO extends DAO<Stakeholder> {

    public StakeholderDAO() {
        classe = Stakeholder.class;
    }

    public List<Stakeholder> findbyProjeto(Projeto projeto) {
        return entityManager.createQuery("from Stakeholder where projeto = :p")
                .setParameter("p", projeto).getResultList();
    }
}
