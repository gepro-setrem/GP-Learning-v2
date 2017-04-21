package br.org.gdt.beans;

import br.org.gdt.bll.EAPBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.model.EAP;
import br.org.gdt.model.Projeto;
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

    private Projeto projeto;
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;

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

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public ProjetoBLL getProjetoBLL() {
        return projetoBLL;
    }

    public void setProjetoBLL(ProjetoBLL projetoBLL) {
        this.projetoBLL = projetoBLL;
    }

}
