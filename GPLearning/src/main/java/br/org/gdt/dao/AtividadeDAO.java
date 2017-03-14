package br.org.gdt.dao;

import br.org.gdt.model.Atividade;
import org.springframework.stereotype.Repository;

@Repository("atividadeDAO")
public class AtividadeDAO extends DAO<Atividade> {

    public AtividadeDAO() {
        classe = Atividade.class;
    }
}
