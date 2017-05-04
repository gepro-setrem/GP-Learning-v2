package br.org.gdt.dao;

import br.org.gdt.model.Etapa;
import br.org.gdt.model.EtapaParametro;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("atividadeParametroDAO")
public class AtividadeParametroDAO extends DAO<EtapaParametro> {

    public AtividadeParametroDAO() {
        classe = EtapaParametro.class;
    }

    public List<EtapaParametro> findbyAtividade(Etapa atividade) {
        return entityManager.createQuery("from AtividadeParametro where atividade = :a")
                .setParameter("a", atividade).getResultList();
    }
}
