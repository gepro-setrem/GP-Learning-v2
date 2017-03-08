package br.org.gdt.dao;

import br.org.gdt.model.Grupo;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Usuario;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("usuarioDAO")
public class UsuarioDAO extends DAO<Usuario> {

    public UsuarioDAO() {
        classe = Usuario.class;
    }

    public List<Usuario> findByUsers(Turma turma, Grupo grupo) {
        return entityManager.createQuery("from Usuario as p where p.turma = :p and p.grupo = :q")
                .setParameter("p", turma)
                .setParameter("q", grupo).getResultList();
    }

    public List<Usuario> findByTurma(List<Turma> turmas) {
        return entityManager.createQuery("from Usuario as u where u.turma in (:p) ")
                .setParameter("p", turmas).getResultList();
    }

    public Usuario findByEmail(String email) {
        List<Usuario> lsUsers = entityManager.createQuery("from Usuario where email=:p")
                .setParameter("p", email).getResultList();
        Usuario user = new Usuario();
        if (lsUsers.size() > 0) {
            user = (Usuario) lsUsers.get(0);
        }
        return user;
    }
}
