package br.org.gdt.bll;

import br.org.gdt.dao.EAPDAO;
import br.org.gdt.model.EAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("eapBLL")
public class EAPBLL extends BLL<EAP> {

    @Autowired
    private EAPDAO dao;
}
