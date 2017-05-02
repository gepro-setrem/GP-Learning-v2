package br.org.gdt.bll;

import br.org.gdt.dao.RequisitoDAO;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Requisito;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("requisitoBLL")
public class RequisitoBLL extends BLL<Requisito> {

    @Autowired
    private RequisitoDAO dao;

    public List<Requisito> findbyProjeto(Projeto projeto) {
        return dao.findbyProjeto(projeto);
    }
}
