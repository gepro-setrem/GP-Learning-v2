package br.org.gdt.dao;

import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("turmaDAO")
public class TurmaDAO extends DAO<Turma> {

    public TurmaDAO() {
        classe = Turma.class;
    }

    public List<Turma> findbyProfessor(Pessoa pessoa) {
        return entityManager.createQuery("from Turma where professor = :p").setParameter("p", pessoa).getResultList();
    }
}
