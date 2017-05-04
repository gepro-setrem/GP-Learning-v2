package br.org.gdt.bll;

import br.org.gdt.dao.AtividadeParametroDAO;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.EtapaParametro;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("aividadeParametroBLL")
public class AtividadeParametroBLL extends BLL<EtapaParametro> {

    @Autowired
    private AtividadeParametroDAO dao;

    public List<EtapaParametro> findbyAtividade(Etapa atividade) {
        List<EtapaParametro> lsAtividadeParametro = new ArrayList<>();
        if (atividade != null && atividade.getId() > 0) {
            lsAtividadeParametro = dao.findbyAtividade(atividade);
        }
        return lsAtividadeParametro;
    }
}
