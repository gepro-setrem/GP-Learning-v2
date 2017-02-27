package br.org.gdt.dao;

import br.org.gdt.model.TermoAbertura;
import org.springframework.stereotype.Repository;

@Repository("termoAberturaDAO")
public class TermoAberturaDAO extends DAO<TermoAbertura> {

    public TermoAberturaDAO() {
        classe = TermoAbertura.class;
    }
}
