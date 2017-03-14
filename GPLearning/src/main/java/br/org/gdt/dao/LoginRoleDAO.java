package br.org.gdt.dao;

import br.org.gdt.model.LoginRole;
import org.springframework.stereotype.Repository;

@Repository("loginRoleDAO")
public class LoginRoleDAO extends DAO<LoginRole> {

    public LoginRoleDAO() {
        classe = LoginRole.class;
    }
}
