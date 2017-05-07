package br.org.gdt.bll;

import br.org.gdt.dao.EAPDAO;
import br.org.gdt.model.EAP;
import br.org.gdt.model.Projeto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("eapBLL")
public class EAPBLL extends BLL<EAP> {

    @Autowired
    private EAPDAO dao;

    public List<EAP> findbyProjeto(Projeto projeto) {
        List<EAP> lsEAP = new ArrayList<>();
        if (projeto != null && projeto.getId() > 0) {
            lsEAP = dao.findbyProjeto(projeto);
            if(lsEAP!=null){
                for(EAP eap:lsEAP){
                    eap.setProjeto(null);
                }
            }
        }
        return lsEAP;
    }

    public EAP getEAP(Projeto projeto) {
        List<EAP> lsEAP = findbyProjeto(projeto);
        EAP eap = null;
        if (lsEAP.size() > 0) {
            eap = lsEAP.get(0);
            List<EAP> eaps = findbyEAP(eap);
            eap.setEaps(eaps);
        }
        clearRecursive(eap);
        return eap;
    }

    public List<EAP> findbyEAP(EAP eap) {
        if (eap != null && eap.getId() > 0) {
            List<EAP> eaps = dao.findbyEAP(eap);
            if (eaps != null) {
                for (EAP child : eaps) {
                    List<EAP> eaps2 = findbyEAP(child);
                    child.setEaps(eaps2);
                }
            }
            return eaps;
        } else {
            return null;
        }
    }

    private void clearRecursive(EAP eap) {
        if (eap != null) {
            eap.setProjeto(null);
            eap.setTarefas(null);
            if (eap.getPai() != null) {
                eap.getPai().setProjeto(null);
                eap.getPai().setTarefas(null);
                eap.getPai().setEaps(null);
                eap.getPai().setPai(null);
            }
            if (eap.getEaps() != null) {
                for (EAP eap_son : eap.getEaps()) {
                    clearRecursive(eap_son);
                }
            }
        }
    }
}
