package br.org.gdt.bll;

import br.org.gdt.dao.UsuarioDAO;
import br.org.gdt.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("usuarioBLL")
public class UsuarioBLL extends BLL<Usuario> {

    @Autowired
    private UsuarioDAO dao;
}
