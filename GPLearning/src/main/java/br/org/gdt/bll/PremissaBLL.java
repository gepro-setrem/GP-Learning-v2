package br.org.gdt.bll;

import br.org.gdt.dao.PremissaDAO;
import br.org.gdt.model.Premissa;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("premissaBLL")
public class PremissaBLL {

    @Autowired
    private PremissaDAO dao;

    @Transactional
    public void insert(Premissa item) {
        dao.insert(item);
    }

    @Transactional
    public void update(Premissa item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public Premissa findById(long id) {
        return (Premissa) dao.findById(id);
    }

    public List<Premissa> findAll() {
        return dao.findAll();
    }

    public List<Premissa> findbytermoabertura(TermoAbertura termoabertura) {
        return dao.findbytermoabertura(termoabertura);
    }
}
