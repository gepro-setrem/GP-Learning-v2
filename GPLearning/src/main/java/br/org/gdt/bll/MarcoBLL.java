package br.org.gdt.bll;

import br.org.gdt.dao.MarcoDAO;
import br.org.gdt.model.Marco;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("marcoBLL")
public class MarcoBLL {

    @Autowired
    private MarcoDAO dao;

    @Transactional
    public void insert(Marco item) {
        dao.insert(item);
    }

    @Transactional
    public void update(Marco item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public Marco findById(long id) {
        return (Marco) dao.findById(id);
    }

    public List<Marco> findAll() {
        return dao.findAll();
    }

    public List<Marco> findbytermoabertura(TermoAbertura termoabertura) {
        return dao.findbytermoabertura(termoabertura);
    }
}
