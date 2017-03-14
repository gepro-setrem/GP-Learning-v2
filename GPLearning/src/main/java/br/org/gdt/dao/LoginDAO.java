package br.org.gdt.dao;

import br.org.gdt.model.Login;
import br.org.gdt.model.Pessoa;
import org.springframework.stereotype.Repository;

@Repository("loginDAO")
public class LoginDAO extends DAO<Login> {

    public LoginDAO() {
        classe = Login.class;
    }

    public Login findPessoa(Pessoa pessoa) {
        return (Login) entityManager.createQuery("from Login where pessoa = :p")
                .setParameter("p", pessoa)
                .getSingleResult();
    }

    public Login findLogin(Login login) {
        return (Login) entityManager.createQuery("from Login where email = :email and senha = :senha")
                .setParameter("email", login.getEmail())
                .setParameter("senha", login.getSenha())
                .getSingleResult();
    }
}
