package br.org.gdt.dao;

import br.org.gdt.model.Etapa;
import br.org.gdt.model.Indicador;
import br.org.gdt.model.Pessoa;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("indicadorDAO")
public class IndicadorDAO extends DAO<Indicador> {

    public IndicadorDAO() {
        classe = Indicador.class;
    }

    public List<Indicador> findbyProfessor(Pessoa pessoa) {
        List<Indicador> lsIndicador = entityManager.createQuery("from Indicador as t where t.professor = :p")
                .setParameter("p", pessoa).getResultList();
        return lsIndicador;
    }

    public List<Indicador> findbyEtapa(Etapa etapa) {
        return entityManager.createQuery("select i from Indicador as i join i.etapas as eta where etap = :e")
                .setParameter("e", etapa).getResultList();
    }
}
