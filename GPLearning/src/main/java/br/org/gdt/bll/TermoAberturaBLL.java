package br.org.gdt.bll;

import br.org.gdt.dao.TermoAberturaDAO;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("termoAberturaBLL")
public class TermoAberturaBLL {

    @Autowired
    private TermoAberturaDAO dao;

    @Transactional
    public void insert(TermoAbertura item) {
        dao.insert(item);
    }

    @Transactional
    public void update(TermoAbertura item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public TermoAbertura findById(long id) {
        return (TermoAbertura) dao.findById(id);
    }

    public List<TermoAbertura> findAll() {
        return dao.findAll();
    }

    public TermoAbertura findByIdRelatorio(int id) {
        return dao.findByIdRelatorio(id);
    }
}
