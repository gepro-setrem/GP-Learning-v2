package br.org.gdt.dao;

import br.org.gdt.model.Etapa;
import br.org.gdt.model.Indicador;
import br.org.gdt.model.IndicadorEtapa;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("indicadorEtapaDAO")
public class IndicadorEtapaDAO extends DAO<IndicadorEtapa> {

    public IndicadorEtapaDAO() {
        classe = IndicadorEtapa.class;
    }

    public List<IndicadorEtapa> findbyIndicador(Indicador indicador) {
        return entityManager.createQuery("from IndicadorEtapa where indicador = :i")
                .setParameter("i", indicador).getResultList();
    }

    public List<IndicadorEtapa> findbyEtapa(Etapa etapa) {
        return entityManager.createQuery("from IndicadorEtapa where etapa = :e")
                .setParameter("e", etapa).getResultList();
    }
}
