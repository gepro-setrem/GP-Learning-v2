package br.org.gdt.dao;

import br.org.gdt.model.AtividadeParametro;
import org.springframework.stereotype.Repository;

@Repository("atividadeParametroDAO")
public class AtividadeParametroDAO extends DAO<AtividadeParametro> {

    public AtividadeParametroDAO() {
        classe = AtividadeParametro.class;
    }
}
