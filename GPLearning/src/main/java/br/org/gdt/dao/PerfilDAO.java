package br.org.gdt.dao;

import br.org.gdt.model.Perfil;
import br.org.gdt.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository("perfilDAO")
public class PerfilDAO extends DAO<Perfil> {

    public PerfilDAO() {
        classe = Perfil.class;
    }

    public Perfil findByUsuario(Usuario usuario) {
        return (Perfil) entityManager.createQuery("from Perfil where usuario= :p")
                .setParameter("p", usuario)
                .getSingleResult();
    }
}
