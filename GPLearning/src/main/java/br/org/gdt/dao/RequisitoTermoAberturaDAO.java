package br.org.gdt.dao;

import br.org.gdt.model.RequisitoTermoAbertura;
import br.org.gdt.model.TermoAbertura;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("requisitoTermoAberturaDAO")
public class RequisitoTermoAberturaDAO extends DAO<RequisitoTermoAbertura> {

    public RequisitoTermoAberturaDAO() {
        classe = RequisitoTermoAbertura.class;
    }

    public List<RequisitoTermoAbertura> findbytermoabertura(TermoAbertura termoabertura) {
        return entityManager.createQuery("from RequisitoTermoAbertura where termoabertura =:p")
                .setParameter("p", termoabertura).getResultList();
    }
}
