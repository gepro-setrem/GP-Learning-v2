package br.org.gdt.bll;

import br.org.gdt.dao.RequisitoDAO;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Requisito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("requisitoBLL")
public class RequisitoBLL {

    @Autowired
    private RequisitoDAO dao;

    @Transactional
    public void insert(Requisito item) {
        dao.insert(item);
    }

    @Transactional
    public void update(Requisito item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public Requisito findById(long id) {
        return (Requisito) dao.findById(id);
    }

    public List<Requisito> findAll() {
        return dao.findAll();
    }

    public List<Requisito> findByProjeto(Projeto projeto) {
        return dao.findByProjeto(projeto);
    }
}
