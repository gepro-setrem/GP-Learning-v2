package br.org.gdt.bll;

import br.org.gdt.dao.RecursoDAO;
import br.org.gdt.model.Recurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("recursoBLL")
public class RecursoBLL extends BLL<Recurso> {

    @Autowired
    private RecursoDAO dao;
}
