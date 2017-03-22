package br.org.gdt.beans;

import br.org.gdt.bll.EAPBLL;
import br.org.gdt.model.EAP;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;

@ManagedBean
@RequestScoped
public class EapBean {

    private EAP eap = new EAP();
    @ManagedProperty("#{eapBLL}")
    private EAPBLL eapBLL;
    private DataModel eaps;

    public EapBean() {
    }

    public String select() {

        return "EapIndex";
    }

    public EAP getEap() {
        return eap;
    }

    public void setEap(EAP eap) {
        this.eap = eap;
    }

    public EAPBLL getEapBLL() {
        return eapBLL;
    }

    public void setEapBLL(EAPBLL eapBLL) {
        this.eapBLL = eapBLL;
    }

    public DataModel getEaps() {
        return eaps;
    }

    public void setEaps(DataModel eaps) {
        this.eaps = eaps;
    }

}
