package br.org.gdt.bll;

import br.org.gdt.dao.DAO;
import br.org.gdt.dao.ProjetoDAO;
import br.org.gdt.model.Projeto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("projetoBLL")
public class ProjetoBLL extends BLL<Projeto> {

    @Autowired
    private ProjetoDAO dao;
}
