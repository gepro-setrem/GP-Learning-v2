package br.org.gdt.bll;

import br.org.gdt.dao.IndicadorEtapaDAO;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Indicador;
import br.org.gdt.model.IndicadorEtapa;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("indicadorEtapaBLL")
public class IndicadorEtapaBLL extends BLL<IndicadorEtapa> {

    @Autowired
    private IndicadorEtapaDAO dao;

    public List<IndicadorEtapa> findbyIndicador(Indicador indicador) {
        List<IndicadorEtapa> lsIndicadorEtapa = new ArrayList<>();
        if (indicador != null && indicador.getId() > 0) {
            lsIndicadorEtapa = dao.findbyIndicador(indicador);
        }
        return lsIndicadorEtapa;
    }

    public List<IndicadorEtapa> findbyEtapa(Etapa etapa) {
        List<IndicadorEtapa> lsIndicadorEtapa = new ArrayList<>();
        if (etapa != null && etapa.getId() > 0) {
            lsIndicadorEtapa = dao.findbyEtapa(etapa);
        }
        return lsIndicadorEtapa;
    }
}
