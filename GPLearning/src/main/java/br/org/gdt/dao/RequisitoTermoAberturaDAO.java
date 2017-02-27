package br.org.gdt.dao;

import br.org.gdt.model.RequisitoTermoAbertura;
import org.springframework.stereotype.Repository;

@Repository("requisitoTermoAberturaDAO")
public class RequisitoTermoAberturaDAO extends DAO<RequisitoTermoAbertura> {

    public RequisitoTermoAberturaDAO() {
        classe = RequisitoTermoAbertura.class;
    }
}
