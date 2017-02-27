package br.org.gdt.dao;

import br.org.gdt.model.Stakeholder;
import org.springframework.stereotype.Repository;

@Repository("stakeholderDAO")
public class StakeholderDAO extends DAO<Stakeholder> {

    public StakeholderDAO() {
        classe = Stakeholder.class;
    }
}
