package br.org.gdt.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DAO<T> {

    @PersistenceContext
    EntityManager entityManager;

    protected Class<T> classe;

    public void insert(T item) {
        entityManager.persist(item);
    }

    public void update(T item) {
        entityManager.merge(item);
    }

    public void delete(int id) {
        T item = entityManager.getReference(classe, id);
        entityManager.remove(item);
    }

    public void delete(T item) {
        item = entityManager.merge(item);
        entityManager.remove(item);
    }

    public T findbyId(int id) {
        return entityManager.find(classe, id);
    }

    public List<T> findAll() {
        return entityManager.createQuery("from " + classe.getName() + " as t").getResultList();
    }
}
