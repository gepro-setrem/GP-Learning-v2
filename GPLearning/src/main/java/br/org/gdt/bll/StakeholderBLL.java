package br.org.gdt.bll;

import br.org.gdt.dao.StakeholderDAO;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Stakeholder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("stakeholderBLL")
public class StakeholderBLL {

    @Autowired
    private StakeholderDAO dao;

    @Transactional
    public void insert(Stakeholder item) {
        dao.insert(item);
    }

    @Transactional
    public void update(Stakeholder item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public Stakeholder findById(long id) {
        return (Stakeholder) dao.findById(id);
    }

    public List<Stakeholder> findAll() {
        return dao.findAll();
    }

    public List<Stakeholder> findByProjeto(Projeto projeto) {
        return dao.findByProjeto(projeto);
    }
}
