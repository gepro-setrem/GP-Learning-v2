//package br.org.gdt.bll;
//
//import br.org.gdt.dao.DAO;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//
//public abstract class BLL<T> {
//
//    @Autowired
//    protected DAO dao;
//
//    @Transactional
//    public void insert(T item) {
//        dao.insert(item);
//    }
//
//    @Transactional
//    public void update(T item) {
//        dao.update(item);
//    }
//
//    @Transactional
//    public void delete(long id) {
//        dao.delete(id);
//    }
//
//    public T findById(long id) {
//        return (T) dao.findById(id);
//    }
//
//    public List<T> findAll() {
//        return dao.findAll();
//    }
//
//}
