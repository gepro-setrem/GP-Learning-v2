package br.org.gdt.dao;

import br.org.gdt.model.Premissa;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("premissaDAO")
public class PremissaDAO extends DAO<Premissa> {

    public PremissaDAO() {
        classe = Premissa.class;
    }

    public List<Premissa> findbytermoabertura(TermoAbertura termoabertura) {
        return entityManager.createQuery("from Premissa where termoabertura = :p")
                .setParameter("p", termoabertura).getResultList();
    }
}
