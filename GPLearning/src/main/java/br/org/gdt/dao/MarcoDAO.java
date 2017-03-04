package br.org.gdt.dao;

import br.org.gdt.model.Marco;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("marcoDAO")
public class MarcoDAO extends DAO<Marco> {

    public MarcoDAO() {
        classe = Marco.class;
    }

    public List<Marco> findbytermoabertura(TermoAbertura termoabertura) {
        return entityManager.createQuery("from Marco where termoabertura = :p")
                .setParameter("p", termoabertura)
                .getResultList();
    }
}
