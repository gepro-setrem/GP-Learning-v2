package br.org.gdt.dao;

import br.org.gdt.model.Comentario;
import org.springframework.stereotype.Repository;

@Repository("comentarioDAO")
public class ComentarioDAO extends DAO<Comentario> {

    public ComentarioDAO() {
        classe = Comentario.class;
    }
}
