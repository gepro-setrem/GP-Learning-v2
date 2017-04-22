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

    public EAP findbyEAP(Projeto projeto) {
        EAP eap = null;
        if (projeto != null && projeto.getId() > 0) {
            List results = entityManager.createQuery("from EAP where projeto = :p and pai is null")
                    .setParameter("p", projeto).getResultList();
            if (!results.isEmpty()) {
                eap = (EAP) results.get(0);
            }
        }
        return eap;
    }

    public List<EAP> findbyChildren(int eap_id) {
        if (eap_id > 0) {
            List<EAP> lsEaps = entityManager.createQuery("from EAP where pai.id = :eap_id order by ordem")
                    .setParameter("eap_id", eap_id).getResultList();
            return lsEaps;
        }
        return null;
    }
}
