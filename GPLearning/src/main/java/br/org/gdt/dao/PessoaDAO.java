package br.org.gdt.dao;

import br.org.gdt.enumerated.Role;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
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

    public List<Pessoa> findbyTurmaUsers(Turma turma, Role role) {
        return entityManager.createQuery("select p from Pessoa as p left join p.login.loginRoles as r where p.turma = :t and r.role = :r")
                .setParameter("t", turma)
                .setParameter("r", role).getResultList();
    }

    public List<Pessoa> findbyTurma(Turma turma) {
        return entityManager.createQuery("from Pessoa where turma = :t")
                .setParameter("t", turma).getResultList();
    }

    public Pessoa findbyEmail(String email) {
        List<Pessoa> lsPessoa = entityManager.createQuery("from Pessoa where email = :p")
                .setParameter("p", email).getResultList();
        Pessoa pessoa = null;
        if (lsPessoa.size() > 0) {
            pessoa = lsPessoa.get(0);
        }
        return pessoa;
    }

    public List<Pessoa> findbyProfessor(Pessoa pessoa) {
        return entityManager.createQuery("from Pessoa where turma.professor = :p")
                .setParameter("p", pessoa).getResultList();
    }
}
