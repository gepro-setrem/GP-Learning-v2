package br.org.gdt.bll;

import br.org.gdt.dao.LoginDAO;
import br.org.gdt.model.Login;
import br.org.gdt.model.LoginRole;
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

    public Pessoa findLogin(String email, String senha) {
        Pessoa user = dao.findLogin(email, senha);
        if (user != null) {
            if (user.getLogin().getLoginRoles() != null) {
                for (LoginRole lr : user.getLogin().getLoginRoles()) {
                    lr.setLogin(null);
                }
            }
            user.setIndicadoresprofessor(null);
            user.setLogin(null);
            user.setProjetos(null);
            user.setProjetosgerente(null);
            user.setTurma(null);
            user.setTurmasprofessor(null);
        }
        return user;
    }

    public LoginDAO getDao() {
        return dao;
    }

    public void setDao(LoginDAO dao) {
        this.dao = dao;
    }
}
