package br.org.gdt.bll;

import br.org.gdt.dao.AtividadeParametroDAO;
import br.org.gdt.model.Atividade;
import br.org.gdt.model.AtividadeParametro;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("aividadeParametroBLL")
public class AtividadeParametroBLL extends BLL<AtividadeParametro> {

    @Autowired
    private AtividadeParametroDAO dao;

    public List<AtividadeParametro> findbyAtividade(Atividade atividade) {
        List<AtividadeParametro> lsAtividadeParametro = new ArrayList<>();
        if (atividade != null && atividade.getId() > 0) {
            lsAtividadeParametro = dao.findbyAtividade(atividade);
        }
        return lsAtividadeParametro;
    }
}
