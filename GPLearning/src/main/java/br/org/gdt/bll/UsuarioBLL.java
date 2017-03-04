package br.org.gdt.bll;

import br.org.gdt.dao.UsuarioDAO;
import br.org.gdt.model.Grupo;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("usuarioBLL")
public class UsuarioBLL {

    @Autowired
    private UsuarioDAO dao;

    @Transactional
    public void insert(Usuario item) {
        dao.insert(item);
    }

    @Transactional
    public void update(Usuario item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public Usuario findById(long id) {
        return (Usuario) dao.findById(id);
    }

    public List<Usuario> findAll() {
        return dao.findAll();
    }

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
