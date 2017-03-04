package br.org.gdt.bll;

import br.org.gdt.dao.GrupoDAO;
import br.org.gdt.model.Grupo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("grupoBLL")
public class GrupoBLL {

    @Autowired
    private GrupoDAO dao;

    @Transactional
    public void insert(Grupo item) {
        dao.insert(item);
    }

    @Transactional
    public void update(Grupo item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public Grupo findById(long id) {
        return (Grupo) dao.findById(id);
    }

    public List<Grupo> findAll() {
        return dao.findAll();
    }
}
