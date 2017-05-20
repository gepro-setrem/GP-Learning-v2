package br.org.gdt.beans;

import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.model.Requisito;
import br.org.gdt.bll.RequisitoBLL;
import br.org.gdt.model.Projeto;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean
@SessionScoped
public class RequisitoBean {

    private static final String viewList = "requisitos";

    private Projeto projeto = new Projeto();
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;

    private Requisito requisito = new Requisito();
    @ManagedProperty("#{requisitoBLL}")
    private RequisitoBLL requisitoBLL;
    private DataModel requisitos;

    public RequisitoBean() {
    }

    public String novo() {
        requisito = new Requisito();
        return viewList;
    }

    public String salvar() {
        if (projeto != null) {
            if (!requisito.getNome().isEmpty() && !requisito.getDescricao().isEmpty()) {
                requisito.setProjeto(projeto);
                if (requisito.getId() > 0) {
                    requisitoBLL.update(requisito);
                } else {
                    requisitoBLL.insert(requisito);
                }
                requisito = new Requisito();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Requisito salvo com sucesso!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Não existe nenhum Projeto selecionado!"));
        }
        return viewList;
    }

    public String editar() {
        requisito = (Requisito) requisitos.getRowData();
        requisito = requisitoBLL.findById(requisito.getId());
        return viewList;
    }

    public String excluir() {
        requisito = (Requisito) requisitos.getRowData();
        requisitoBLL.delete(requisito.getId());
        requisito = new Requisito();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Requisito excluído com sucesso!"));
        return viewList;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        if (projeto != null && projeto.getId() > 0) {
            projeto = projetoBLL.findById(projeto.getId());
        }
        this.projeto = projeto;
    }

    public ProjetoBLL getProjetoBLL() {
        return projetoBLL;
    }

    public void setProjetoBLL(ProjetoBLL projetoBLL) {
        this.projetoBLL = projetoBLL;
    }

    public Requisito getRequisito() {
        return requisito;
    }

    public void setRequisito(Requisito requisito) {
        this.requisito = requisito;
    }

    public RequisitoBLL getRequisitoBLL() {
        return requisitoBLL;
    }

    public void setRequisitoBLL(RequisitoBLL requisitoBLL) {
        this.requisitoBLL = requisitoBLL;
    }

    public DataModel getRequisitos() {
        if (projeto != null) {
            List<Requisito> lsRequisito = requisitoBLL.findbyProjeto(projeto);
            requisitos = new ListDataModel(lsRequisito);
        }
        return requisitos;
    }

    public void setRequisitos(DataModel requisitos) {
        this.requisitos = requisitos;
    }

}
