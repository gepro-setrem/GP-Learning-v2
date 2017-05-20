package br.org.gdt.dao;

import br.org.gdt.model.Login;
import br.org.gdt.model.LoginRole;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("loginRoleDAO")
public class LoginRoleDAO extends DAO<LoginRole> {

    public LoginRoleDAO() {
        classe = LoginRole.class;
    }

    public List<LoginRole> findbyLogin(Login login) {
        return entityManager.createQuery("from LoginRole where login = :l")
                .setParameter("l", login).getResultList();
    }

}
