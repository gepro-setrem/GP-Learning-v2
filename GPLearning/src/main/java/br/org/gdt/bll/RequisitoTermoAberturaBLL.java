package br.org.gdt.bll;

import br.org.gdt.dao.RequisitoTermoAberturaDAO;
import br.org.gdt.model.RequisitoTermoAbertura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("requisitoTermoAberturaBLL")
public class RequisitoTermoAberturaBLL extends BLL<RequisitoTermoAbertura> {

    @Autowired
    private RequisitoTermoAberturaDAO dao;
}
