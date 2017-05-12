package br.org.gdt.dao;

import br.org.gdt.model.Avaliacao;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Projeto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("avaliacaoDAO")
public class AvaliacaoDAO extends DAO<Avaliacao> {

    public AvaliacaoDAO() {
        classe = Avaliacao.class;
    }

    public List<Avaliacao> findbyEtapa(Etapa etapa) {
        return entityManager.createQuery("from Avaliacao where etapa = :e")
                .setParameter("e", etapa).getResultList();
    }

    public List<Avaliacao> findbyProjeto(Projeto projeto) {
        return entityManager.createQuery("from Avaliacao where projeto = :p")
                .setParameter("p", projeto).getResultList();
    }
}
