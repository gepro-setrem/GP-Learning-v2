package br.org.gdt.bll;

import br.org.gdt.dao.PremissaDAO;
import br.org.gdt.model.Premissa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("premissaBLL")
public class PremissaBLL extends BLL<Premissa> {

    @Autowired
    private PremissaDAO dao;
}
