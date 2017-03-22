package br.org.gdt.dao;

import br.org.gdt.model.EAP;
import br.org.gdt.model.Projeto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("eapDAO")
public class EAPDAO extends DAO<EAP> {

    public EAPDAO() {
        classe = EAP.class;
    }

    public List<EAP> findbyEAP(Projeto projeto) {
        return entityManager.createQuery("from EAP where projeto = :p")
                .setParameter("p", projeto)
                .getResultList();
    }
}
