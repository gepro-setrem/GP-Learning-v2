package br.org.gdt.bll;

import br.org.gdt.dao.RequisitoDAO;
import br.org.gdt.model.Requisito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("requisitoBLL")
public class RequisitoBLL extends BLL<Requisito> {

    @Autowired
    private RequisitoDAO dao;
}
