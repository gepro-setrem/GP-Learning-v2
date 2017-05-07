package br.org.gdt.bll;

import br.org.gdt.dao.TermoAberturaDAO;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.TermoAbertura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("termoAberturaBLL")
public class TermoAberturaBLL extends BLL<TermoAbertura> {

    @Autowired
    private TermoAberturaDAO dao;

    public TermoAbertura findByIdRelatorio(int id) {
        return dao.findByIdRelatorio(id);
    }

    public TermoAbertura findByProjeto(Projeto projeto) {
        return dao.findByProjeto(projeto);
    }
}
