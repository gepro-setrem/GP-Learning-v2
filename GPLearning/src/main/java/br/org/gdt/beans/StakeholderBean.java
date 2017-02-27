package br.org.gdt.beans;

import br.org.gdt.model.Stakeholder;
import br.org.gdt.bll.StakeholderBLL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;

@ManagedBean
@RequestScoped
public class StakeholderBean {

    public Stakeholder stakeholder = new Stakeholder();
    @ManagedProperty("#{stakeholderBLL}")
    private StakeholderBLL service;
    public DataModel partesinteressadas;

    public StakeholderBean() {
    }

    public Stakeholder getParteinteressada() {
        return stakeholder;
    }

    public void setParteinteressada(Stakeholder stakeholder) {
        this.stakeholder = stakeholder;
    }

    public DataModel getPartesinteressadas() {
        return partesinteressadas;
    }

    public void setPartesinteressadas(DataModel partesinteressadas) {
        this.partesinteressadas = partesinteressadas;
    }

    public String salvar() {
        if (stakeholder.getId() > 0) {
            service.update(stakeholder);
        } else {
            service.insert(stakeholder);
        }
        return "PartesInteressadas";
    }

    public String select() {
        stakeholder = (Stakeholder) partesinteressadas.getRowData();
        stakeholder = service.findById(stakeholder.getId());
        return "PartesInteressadas";
    }

    public String delete() {
        stakeholder = (Stakeholder) partesinteressadas.getRowData();
        service.delete(stakeholder.getId());
        partesinteressadas = null;
        return "PartesInteressadas";
    }

    public String NovaStakeholder() {
        stakeholder = new Stakeholder();
        return "PartesInteressadas";
    }

}
