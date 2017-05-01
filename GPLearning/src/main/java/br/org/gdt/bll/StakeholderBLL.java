package br.org.gdt.bll;

import br.org.gdt.dao.StakeholderDAO;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Stakeholder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("stakeholderBLL")
public class StakeholderBLL extends BLL<Stakeholder> {

    @Autowired
    private StakeholderDAO dao;

    public List<Stakeholder> findbyProjeto(Projeto projeto) {
        return dao.findbyProjeto(projeto);
    }
}
