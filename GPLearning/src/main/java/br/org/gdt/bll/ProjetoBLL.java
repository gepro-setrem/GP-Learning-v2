package br.org.gdt.bll;

import br.org.gdt.dao.ProjetoDAO;
import br.org.gdt.model.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projetoBLL")
public class ProjetoBLL extends BLL<Projeto> {

    @Autowired
    private ProjetoDAO dao;
}
