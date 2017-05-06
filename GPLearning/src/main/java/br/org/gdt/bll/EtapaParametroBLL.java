package br.org.gdt.bll;

import br.org.gdt.dao.EtapaParametroDAO;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.EtapaParametro;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("etapaParametroBLL")
public class EtapaParametroBLL extends BLL<EtapaParametro> {

    @Autowired
    private EtapaParametroDAO dao;

    public List<EtapaParametro> findbyEtapa(Etapa etapa) {
        List<EtapaParametro> lsEtapaParametro = new ArrayList<>();
        if (etapa != null && etapa.getId() > 0) {
            lsEtapaParametro = dao.findbyEtapa(etapa);
        }
        return lsEtapaParametro;
    }
}
