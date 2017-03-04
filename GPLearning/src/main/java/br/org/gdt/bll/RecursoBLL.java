package br.org.gdt.bll;

import br.org.gdt.dao.RecursoDAO;
import br.org.gdt.model.Recurso;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("recursoBLL")
public class RecursoBLL {

    @Autowired
    private RecursoDAO dao;

    @Transactional
    public void insert(Recurso item) {
        dao.insert(item);
    }

    @Transactional
    public void update(Recurso item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public Recurso findById(long id) {
        return (Recurso) dao.findById(id);
    }

    public List<Recurso> findAll() {
        return dao.findAll();
    }
}
