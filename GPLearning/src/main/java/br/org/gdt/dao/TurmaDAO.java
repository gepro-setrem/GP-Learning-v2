package br.org.gdt.dao;

import br.org.gdt.model.Turma;
import org.springframework.stereotype.Repository;

@Repository("turmaDAO")
public class TurmaDAO extends DAO<Turma> {

    public TurmaDAO() {
        classe = Turma.class;
    }
}
