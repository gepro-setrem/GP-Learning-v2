package br.org.gdt.dao;

import br.org.gdt.model.EAP;
import br.org.gdt.model.Projeto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("eapDAO")
public class EAPDAO extends DAO<EAP> {

    public EAPDAO() {
        classe = EAP.class;
    }

    public List<EAP> findbyProjeto(Projeto projeto) {
        return entityManager.createQuery("from EAP where projeto = :p and pai is null")
                .setParameter("p", projeto).getResultList();
    }

    public List<EAP> findbyEAP(EAP eap) {
        return entityManager.createQuery("from EAP where pai = :e order by ordem")
                .setParameter("e", eap).getResultList();
    }
}
