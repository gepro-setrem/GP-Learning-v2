package br.org.gdt.dao;

import br.org.gdt.model.Recurso;
import org.springframework.stereotype.Repository;

@Repository("recursoDAO")
public class RecursoDAO extends DAO<Recurso> {

    public RecursoDAO() {
        classe = Recurso.class;
    }
}
