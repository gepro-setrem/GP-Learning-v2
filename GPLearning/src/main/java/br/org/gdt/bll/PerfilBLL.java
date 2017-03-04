package br.org.gdt.bll;

import br.org.gdt.dao.PerfilDAO;
import br.org.gdt.model.Perfil;
import br.org.gdt.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("perfilBLL")
public class PerfilBLL extends BLL<Perfil> {

    @Autowired
    private PerfilDAO dao;

    public Perfil findByUsuario(Usuario usuario) {
        return dao.findByUsuario(usuario);
    }
}
