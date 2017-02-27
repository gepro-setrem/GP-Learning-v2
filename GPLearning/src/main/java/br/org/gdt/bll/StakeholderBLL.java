package br.org.gdt.bll;

import br.org.gdt.dao.StakeholderDAO;
import br.org.gdt.model.Stakeholder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stakeholderBLL")
public class StakeholderBLL extends BLL<Stakeholder> {

    @Autowired
    private StakeholderDAO dao;
}
