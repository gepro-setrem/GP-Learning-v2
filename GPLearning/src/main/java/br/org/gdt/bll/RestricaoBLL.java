package br.org.gdt.bll;

import br.org.gdt.dao.RestricaoDAO;
import br.org.gdt.model.Restricao;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("restricaoBLL")
public class RestricaoBLL extends BLL<Restricao> {

    @Autowired
    private RestricaoDAO dao;

    public List<Restricao> findbyTermoAbertura(TermoAbertura termoabertura) {
        return dao.findbyTermoAbertura(termoabertura);
    }
}
