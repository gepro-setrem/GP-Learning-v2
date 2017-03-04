package br.org.gdt.bll;

import br.org.gdt.dao.RestricaoDAO;
import br.org.gdt.model.Restricao;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("restricaoBLL")
public class RestricaoBLL {

    @Autowired
    private RestricaoDAO dao;

    @Transactional
    public void insert(Restricao item) {
        dao.insert(item);
    }

    @Transactional
    public void update(Restricao item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public Restricao findById(long id) {
        return (Restricao) dao.findById(id);
    }

    public List<Restricao> findAll() {
        return dao.findAll();
    }

    public List<Restricao> findbytermoabertura(TermoAbertura termoabertura) {
        return dao.findbytermoabertura(termoabertura);
    }
}
