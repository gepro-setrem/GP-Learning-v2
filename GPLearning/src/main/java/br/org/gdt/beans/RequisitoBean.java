package br.org.gdt.beans;

import br.org.gdt.model.Requisito;
import br.org.gdt.bll.RequisitoBLL;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;

@ManagedBean
@RequestScoped
public class RequisitoBean {

    private Requisito requisito = new Requisito();
    @ManagedProperty("#{requisitoBLL}")
    private RequisitoBLL service;
    private DataModel requisitos;

    public RequisitoBean() {
    }

    public Requisito getRequisito() {
        return requisito;
    }

    public void setRequisito(Requisito requisito) {
        this.requisito = requisito;
    }

    public DataModel getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(DataModel requisitos) {
        this.requisitos = requisitos;
    }

    //quando é clicado o botão inserir
    public String salvar() {
        if (requisito.getId() > 0) {
            service.update(requisito);
        } else {
            service.insert(requisito);
        }
        return "listarrequisitos";
    }

    public String select() {
        requisito = (Requisito) requisitos.getRowData();
        requisito = service.findById(requisito.getId());
        return "manutrequisitos";
    }

    public String delete() {
        requisito = (Requisito) requisitos.getRowData();
        service.delete(requisito.getId());
        requisitos = null;
        return "listarrequisitos";
    }

    public String novoRequisito() {
        requisito = new Requisito();
        return "manutrequisitos";
    }
}
