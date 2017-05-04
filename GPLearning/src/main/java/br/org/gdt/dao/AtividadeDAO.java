package br.org.gdt.dao;

import br.org.gdt.model.Etapa;
import org.springframework.stereotype.Repository;

@Repository("atividadeDAO")
public class AtividadeDAO extends DAO<Etapa> {

    public AtividadeDAO() {
        classe = Etapa.class;
    }
}
