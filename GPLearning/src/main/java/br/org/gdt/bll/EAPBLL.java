package br.org.gdt.bll;

import br.org.gdt.dao.EAPDAO;
import br.org.gdt.model.EAP;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("eapBLL")
public class EAPBLL {

    @Autowired
    private EAPDAO dao;

    @Transactional
    public void insert(EAP item) {
        dao.insert(item);
    }

    @Transactional
    public void update(EAP item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public EAP findById(long id) {
        return (EAP) dao.findById(id);
    }

    public List<EAP> findAll() {
        return dao.findAll();
    }
}
