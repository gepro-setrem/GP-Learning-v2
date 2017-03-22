package br.org.gdt.bll;

import br.org.gdt.dao.EAPDAO;
import br.org.gdt.model.EAP;
import br.org.gdt.model.Projeto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("eapBLL")
public class EAPBLL extends BLL<EAP> {

    @Autowired
    private EAPDAO dao;

    public List<EAP> findbyEAP(Projeto projeto) {
        return dao.findbyEAP(projeto);
    }
}
