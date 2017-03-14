package br.org.gdt.dao;

import br.org.gdt.model.EAP;
import org.springframework.stereotype.Repository;

@Repository("eapDAO")
public class EAPDAO extends DAO<EAP> {

    public EAPDAO() {
        classe = EAP.class;
    }
}
