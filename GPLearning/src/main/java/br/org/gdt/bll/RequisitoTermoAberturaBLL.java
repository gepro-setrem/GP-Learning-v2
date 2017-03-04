package br.org.gdt.bll;

import br.org.gdt.dao.RequisitoTermoAberturaDAO;
import br.org.gdt.model.RequisitoTermoAbertura;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("requisitoTermoAberturaBLL")
public class RequisitoTermoAberturaBLL {

    @Autowired
    private RequisitoTermoAberturaDAO dao;

    @Transactional
    public void insert(RequisitoTermoAbertura item) {
        dao.insert(item);
    }

    @Transactional
    public void update(RequisitoTermoAbertura item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public RequisitoTermoAbertura findById(long id) {
        return (RequisitoTermoAbertura) dao.findById(id);
    }

    public List<RequisitoTermoAbertura> findAll() {
        return dao.findAll();
    }

    public List<RequisitoTermoAbertura> findbytermoabertura(TermoAbertura termoabertura) {
        return dao.findbytermoabertura(termoabertura);
    }
}
