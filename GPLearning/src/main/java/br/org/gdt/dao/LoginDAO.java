package br.org.gdt.dao;

import br.org.gdt.enumerated.Status;
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

    public Pessoa findLogin(String email, String senha) {
        Pessoa user = null;
        if (email != null && !email.isEmpty() && senha != null && !senha.isEmpty()) {
            user = (Pessoa) entityManager.createQuery("from Pessoa p join fetch p.login.loginRoles lr where p.login.email = :email and p.login.senha = :senha and p.status = :status")
                    .setParameter("email", email)
                    .setParameter("senha", senha)
                    .setParameter("status", Status.Ativo)
                    .getSingleResult();
        }
        return user;
    }
}
