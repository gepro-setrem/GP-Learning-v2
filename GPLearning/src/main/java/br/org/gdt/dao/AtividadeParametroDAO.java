package br.org.gdt.dao;

import br.org.gdt.model.Atividade;
import br.org.gdt.model.AtividadeParametro;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("atividadeParametroDAO")
public class AtividadeParametroDAO extends DAO<AtividadeParametro> {

    public AtividadeParametroDAO() {
        classe = AtividadeParametro.class;
    }

    public List<AtividadeParametro> findbyAtividade(Atividade atividade) {
        return entityManager.createQuery("from AtividadeParametro where atividade = :a")
                .setParameter("a", atividade).getResultList();
    }
}
