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
    private ProjetoBLL projetoBLL;

    public EAP findbyEAP(Projeto projeto) {
        if (projeto != null) {
            EAP eap = dao.findbyEAP(projeto);
            if (eap != null) {
                List<EAP> eaps = findbyChildren(eap);
                eap.setEaps(eaps);
            }
            clearRecursive(eap);
            return eap;
        }
        return null;
    }

    public List<EAP> findbyChildren(EAP eap) {
        if (eap != null) {
            List<EAP> eaps = dao.findbyChildren(eap.getId());
            if (eaps != null) {
                for (EAP child : eaps) {
                    List<EAP> eaps2 = findbyChildren(child);
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
