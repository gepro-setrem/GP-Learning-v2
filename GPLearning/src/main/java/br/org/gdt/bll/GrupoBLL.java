package br.org.gdt.bll;

import br.org.gdt.dao.GrupoDAO;
import br.org.gdt.model.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("grupoBLL")
public class GrupoBLL extends BLL<Grupo> {

    @Autowired
    private GrupoDAO dao;
}