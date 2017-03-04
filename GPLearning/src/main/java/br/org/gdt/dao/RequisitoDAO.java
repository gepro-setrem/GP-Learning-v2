package br.org.gdt.dao;

import br.org.gdt.model.Projeto;
import br.org.gdt.model.Requisito;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("requisitoDAO")
public class RequisitoDAO extends DAO<Requisito> {

    public RequisitoDAO() {
        classe = Requisito.class;
    }

    public List<Requisito> findByProjeto(Projeto projeto) {
        return entityManager.createQuery("from Requisito where projeto = :p")
                .setParameter("p", projeto)
                .getResultList();
    }
}
