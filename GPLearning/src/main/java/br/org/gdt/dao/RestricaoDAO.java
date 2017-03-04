package br.org.gdt.dao;

import br.org.gdt.model.Restricao;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("restricaoDAO")
public class RestricaoDAO extends DAO<Restricao> {

    public RestricaoDAO() {
        classe = Restricao.class;
    }

    public List<Restricao> findbytermoabertura(TermoAbertura termoabertura) {
        return entityManager.createQuery("from Restricao where termoabertura = :p")
                .setParameter("p", termoabertura).getResultList();
    }
}
