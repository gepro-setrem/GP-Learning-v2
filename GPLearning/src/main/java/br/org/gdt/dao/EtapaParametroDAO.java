package br.org.gdt.dao;

import br.org.gdt.model.Etapa;
import br.org.gdt.model.EtapaParametro;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("etapaParametroDAO")
public class EtapaParametroDAO extends DAO<EtapaParametro> {

    public EtapaParametroDAO() {
        classe = EtapaParametro.class;
    }

    public List<EtapaParametro> findbyEtapa(Etapa etapa) {
        return entityManager.createQuery("from EtapaParametro where etapa = :e")
                .setParameter("e", etapa).getResultList();
    }
}
