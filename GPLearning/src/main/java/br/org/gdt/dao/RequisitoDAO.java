package br.org.gdt.dao;

import br.org.gdt.model.Requisito;
import org.springframework.stereotype.Repository;

@Repository("requisitoDAO")
public class RequisitoDAO extends DAO<Requisito> {

    public RequisitoDAO() {
        classe = Requisito.class;
    }
}
