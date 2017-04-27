package br.org.gdt.dao;

import br.org.gdt.enumerated.Status;
import br.org.gdt.model.Login;
import br.org.gdt.model.Pessoa;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("loginDAO")
public class LoginDAO extends DAO<Login> {

    public LoginDAO() {
        classe = Login.class;
    }

    public Login findbyPessoa(Pessoa pessoa) {
        List<Login> lsLogin = entityManager.createQuery("from Login where pessoa = :p")
                .setParameter("p", pessoa).getResultList();
        Login login = null;
        if (lsLogin.size() > 0) {
            login = lsLogin.get(0);
        }
        return login;
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
