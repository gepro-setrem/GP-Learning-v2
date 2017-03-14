package br.org.gdt.dao;

import br.org.gdt.model.TurmaParametro;
import org.springframework.stereotype.Repository;

@Repository("turmaParametroDAO")
public class TurmaParametroDAO extends DAO<TurmaParametro> {

    public TurmaParametroDAO() {
        classe = TurmaParametro.class;
    }
}
