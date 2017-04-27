package br.org.gdt.dao;

import br.org.gdt.enumerated.Role;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("pessoaDAO")
public class PessoaDAO extends DAO<Pessoa> {

    public PessoaDAO() {
        classe = Pessoa.class;
    }

    public List<Pessoa> findbyProjeto(Projeto projeto) {
        return entityManager.createQuery("from Pessoa as p join p.projetos as pro where pro = :p")
                .setParameter("p", projeto).getResultList();
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

    public Pessoa findbyEmail(String email) {
        List<Pessoa> lsPessoa = entityManager.createQuery("from Pessoa where email=:p")
                .setParameter("p", email).getResultList();
        Pessoa pessoa = null;
        if (lsPessoa.size() > 0) {
            pessoa = lsPessoa.get(0);
        }
        return pessoa;
    }

    public List<Pessoa> findAllUsers(Pessoa pessoa) {
        return entityManager.createQuery("from Pessoa as p where p.turma.professor = :p")
                .setParameter("p", pessoa).getResultList();
    }
}
