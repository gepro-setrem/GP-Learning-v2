package br.org.gdt.bll;

import br.org.gdt.dao.DAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public abstract class BLL<T> {

    @Autowired
    protected DAO<T> dao;

    @Transactional
    public void insert(T item) {
        dao.insert(item);
    }

    public DAO getDao() {
        return dao;
    }

    public void setDao(DAO dao) {
        this.dao = dao;
    }

    @Transactional
    public void update(T item) {
        dao.update(item);
    }

    @Transactional
    public void delete(int id) {
        dao.delete(id);
    }

    public T findById(int id) {
        return (T) dao.findById(id);
    }

    public List<T> findAll() {
        return dao.findAll();
    }

}
