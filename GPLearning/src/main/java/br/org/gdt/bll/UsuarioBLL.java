package br.org.gdt.bll;

import br.org.gdt.dao.UsuarioDAO;
import br.org.gdt.model.Grupo;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("usuarioBLL")
public class UsuarioBLL extends BLL<Usuario> {

    @Autowired
    private UsuarioDAO dao;

    public List<Usuario> findByUsers(Turma turma, Grupo grupo) {
        return dao.findByUsers(turma, grupo);
    }

    public List<Usuario> findByTurma(List<Turma> turmas) {
        return dao.findByTurma(turmas);
    }

    public Usuario findByEmail(String email) {
        return dao.findByEmail(email);
    }
}
