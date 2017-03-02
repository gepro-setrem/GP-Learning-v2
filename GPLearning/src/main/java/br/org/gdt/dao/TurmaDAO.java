package br.org.gdt.dao;

import br.org.gdt.model.Turma;
import br.org.gdt.model.Usuario;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("turmaDAO")
public class TurmaDAO extends DAO<Turma> {

    public TurmaDAO() {
        classe = Turma.class;
    }

    public List<Turma> findbyProfessor(Usuario professor) {
        List<Turma> turminha = entityManager.createQuery("from Turma as t where t.professor = :p")
                .setParameter("p", professor).getResultList();
//        for (Turma turma : turminha) {
//            Hibernate.initialize(turma.getAcademicos());
//        }
        return turminha;
    }
}
