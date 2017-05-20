package br.org.gdt.bll;

import br.org.gdt.dao.LoginRoleDAO;
import br.org.gdt.model.Login;
import br.org.gdt.model.LoginRole;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginRoleBLL")
public class LoginRoleBLL extends BLL<LoginRole> {

    @Autowired
    private LoginRoleDAO dao;

    public List<LoginRole> findbyLogin(Login login) {
        List<LoginRole> lsLoginRole = new ArrayList<>();
        if (login != null && login.getEmail() != null && !login.getEmail().isEmpty()) {
            lsLoginRole = dao.findbyLogin(login);
        }
        return lsLoginRole;
    }
}
