package br.org.gdt.bll;

import br.org.gdt.dao.IndicadorDAO;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Indicador;
import br.org.gdt.model.Pessoa;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("indicadorBLL")
public class IndicadorBLL extends BLL<Indicador> {

    @Autowired
    private IndicadorDAO dao;

    public List<Indicador> findbyProfessor(Pessoa professor) {
        List<Indicador> lsIndicador = new ArrayList<>();
        if (professor != null && professor.getId() > 0) {
            lsIndicador = dao.findbyProfessor(professor);
        }
        return lsIndicador;
    }

    public List<Indicador> findbyEtapa(Etapa etapa) {
        List<Indicador> lsIndicador = new ArrayList<>();
        if (etapa != null && etapa.getId() > 0) {
            lsIndicador = dao.findbyEtapa(etapa);
            if(lsIndicador!=null){
                for(Indicador indicador:lsIndicador){
                    indicador.setEtapas(null);
                    indicador.setProfessor(null);
                    indicador.setAvaliacoes(null);
                }
            }
        }
        return lsIndicador;
    }
}
