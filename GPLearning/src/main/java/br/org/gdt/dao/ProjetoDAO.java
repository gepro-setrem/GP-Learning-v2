package br.org.gdt.dao;

import br.org.gdt.model.Projeto;
import org.springframework.stereotype.Repository;

@Repository("projetoDAO")
public class ProjetoDAO extends DAO<Projeto> {

    public ProjetoDAO() {
        classe = Projeto.class;
    }

}
