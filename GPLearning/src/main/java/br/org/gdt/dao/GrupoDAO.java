package br.org.gdt.dao;

import br.org.gdt.model.Grupo;
import org.springframework.stereotype.Repository;

@Repository("grupoDAO")
public class GrupoDAO extends DAO<Grupo> {

    public GrupoDAO() {
        classe = Grupo.class;
    }
}
