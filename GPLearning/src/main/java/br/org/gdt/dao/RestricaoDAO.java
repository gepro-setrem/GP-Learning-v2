package br.org.gdt.dao;

import br.org.gdt.model.Restricao;
import org.springframework.stereotype.Repository;

@Repository("restricaoDAO")
public class RestricaoDAO extends DAO<Restricao> {

    public RestricaoDAO() {
        classe = Restricao.class;
    }
}
