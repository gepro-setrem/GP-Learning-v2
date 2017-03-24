package br.org.gdt.bll;

import br.org.gdt.dao.LoginDAO;
import br.org.gdt.model.Login;
import br.org.gdt.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginBLL")
public class LoginBLL extends BLL<Login> {

    @Autowired
    private LoginDAO dao;

    public Login findPessoa(Pessoa usuario) {
        return dao.findPessoa(usuario);
    }

    public Login findLogin(Login login) {
        return dao.findLogin(login);
    }

    public LoginDAO getDao() {
        return dao;
    }

    public void setDao(LoginDAO dao) {
        this.dao = dao;
    }
}
