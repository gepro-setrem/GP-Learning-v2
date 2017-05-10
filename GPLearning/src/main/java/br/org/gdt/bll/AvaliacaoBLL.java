package br.org.gdt.bll;

import br.org.gdt.dao.AvaliacaoDAO;
import br.org.gdt.model.Avaliacao;
import br.org.gdt.model.Etapa;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("avaliacaoBLL")
public class AvaliacaoBLL extends BLL<Avaliacao> {

    @Autowired
    private AvaliacaoDAO dao;

    public List<Avaliacao> findbyEtapa(Etapa etapa) {
        List<Avaliacao> lsAvaliacao = new ArrayList<>();
        if (etapa != null && etapa.getId() > 0) {
            lsAvaliacao = dao.findbyEtapa(etapa);
        }
        return lsAvaliacao;
    }
}
