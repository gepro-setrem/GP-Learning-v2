package br.org.gdt.dao;

import br.org.gdt.model.Marco;
import org.springframework.stereotype.Repository;

@Repository("marcoDAO")
public class MarcoDAO extends DAO<Marco> {

    public MarcoDAO() {
        classe = Marco.class;
    }
}
