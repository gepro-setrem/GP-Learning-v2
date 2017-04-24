package br.org.gdt.dao;

import br.org.gdt.enumerated.Role;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("pessoaDAO")
public class PessoaDAO extends DAO<Pessoa> {

    public PessoaDAO() {
        classe = Pessoa.class;
    }

    public List<Pessoa> findByComponentes(int pro_id) {
        List<Pessoa> componentes = new ArrayList<>();
        if (pro_id > 0) {
            componentes = entityManager.createQuery("from Pessoa as p join p.projetos as pro where pro.id = :pro_id")
                    .setParameter("pro_id", pro_id).getResultList();
        }
        return componentes;
    }

    public List<Pessoa> findByUsers(Turma turma, Role grupo) {
        return entityManager.createQuery("from Pessoa as p left join p.login.loginRoles as r where p.turma = :p and r.role = :q")
                .setParameter("p", turma)
                .setParameter("q", grupo).getResultList();
    }

    public List<Pessoa> findByTurma(List<Turma> turmas) {
        return entityManager.createQuery("from Pessoa as u where u.turma in (:p) ")
                .setParameter("p", turmas).getResultList();
    }

    public Pessoa findByEmail(String email) {
        Pessoa user = new Pessoa();
        if (email != null && !email.isEmpty()) {
            user = (Pessoa) entityManager.createQuery("from Pessoa where email=:p")
                    .setParameter("p", email).getSingleResult();
        }
//        Pessoa user = new Pessoa();
//        if (lsUsers.size() > 0) {
//            user = (Pessoa) lsUsers.get(0);
//        }
        return user;
    }

    public List<Pessoa> findAllUsers(Pessoa pessoa) {
        return entityManager.createQuery("from Pessoa as p where p.turma.professor = :p")
                .setParameter("p", pessoa).getResultList();
    }
}
