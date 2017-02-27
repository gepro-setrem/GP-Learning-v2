package br.org.gdt.dao;

import br.org.gdt.model.Premissa;
import org.springframework.stereotype.Repository;

@Repository("premissaDAO")
public class PremissaDAO extends DAO<Premissa> {

    public PremissaDAO() {
        classe = Premissa.class;
    }
}
