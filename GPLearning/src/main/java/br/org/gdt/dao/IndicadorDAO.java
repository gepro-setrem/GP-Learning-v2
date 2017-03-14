package br.org.gdt.dao;

import br.org.gdt.model.Indicador;
import org.springframework.stereotype.Repository;

@Repository("indicadorDAO")
public class IndicadorDAO extends DAO<Indicador> {

    public IndicadorDAO() {
        classe = Indicador.class;
    }
}
