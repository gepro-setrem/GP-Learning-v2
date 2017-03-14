package br.org.gdt.dao;

import br.org.gdt.enumerated.Role;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("pessoaDAO")
public class PessoaDAO extends DAO<Pessoa> {

    public PessoaDAO() {
        classe = Pessoa.class;
    }

    public List<Pessoa> findByUsers(Turma turma, Role grupo) {
        return entityManager.createQuery("from Pessoa as p where p.turma = :p and p.login.role = :q")
                .setParameter("p", turma)
                .setParameter("q", grupo).getResultList();
    }

    public List<Pessoa> findByTurma(List<Turma> turmas) {
        return entityManager.createQuery("from Pessoa as u where u.turma in (:p) ")
                .setParameter("p", turmas).getResultList();
    }

    public Pessoa findByEmail(String email) {
        List<Pessoa> lsUsers = entityManager.createQuery("from Pessoa where email=:p")
                .setParameter("p", email).getResultList();
        Pessoa user = new Pessoa();
        if (lsUsers.size() > 0) {
            user = (Pessoa) lsUsers.get(0);
        }
        return user;
    }
}
