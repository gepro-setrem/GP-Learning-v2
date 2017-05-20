package br.org.gdt.beans;

import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.bll.RequisitoTermoAberturaBLL;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.RequisitoTermoAbertura;
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
public class RequisitoTermoAberturaBean {

    private static final String viewList = "termoaberturarequisitos";

    private Projeto projeto = new Projeto();
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;

    private RequisitoTermoAbertura requisito = new RequisitoTermoAbertura();
    @ManagedProperty("#{requisitoTermoAberturaBLL}")
    private RequisitoTermoAberturaBLL requisitoBLL;
    private DataModel requisitos;

    public RequisitoTermoAberturaBean() {
    }

    public String salvar() {
        if (projeto != null && projeto.getTermoabertura() != null) {
            if (!requisito.getDescricao().isEmpty() && !requisito.getNome().isEmpty()) {
                requisito.setTermoabertura(projeto.getTermoabertura());
                if (requisito.getId() > 0) {
                    requisitoBLL.update(requisito);
                } else {
                    requisitoBLL.insert(requisito);
                }
                requisito = new RequisitoTermoAbertura();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Requisito salvo com sucesso!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, você deve preencher todos os campos!"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Não existe nenhum Projeto selecionado ou Termo de Abertura criado!"));
        }
        return viewList;
    }

    public String editar() {
        requisito = (RequisitoTermoAbertura) requisitos.getRowData();
        requisito = requisitoBLL.findById(requisito.getId());
        return viewList;
    }

    public String excluir() {
        requisito = (RequisitoTermoAbertura) requisitos.getRowData();
        requisitoBLL.delete(requisito.getId());
        requisito = new RequisitoTermoAbertura();
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

    public RequisitoTermoAbertura getRequisito() {
        return requisito;
    }

    public void setRequisito(RequisitoTermoAbertura requisito) {
        this.requisito = requisito;
    }

    public RequisitoTermoAberturaBLL getRequisitoBLL() {
        return requisitoBLL;
    }

    public void setRequisitoBLL(RequisitoTermoAberturaBLL requisitoBLL) {
        this.requisitoBLL = requisitoBLL;
    }

    public DataModel getRequisitos() {
        if (projeto != null && projeto.getTermoabertura() != null) {
            List<RequisitoTermoAbertura> lsRequisito = requisitoBLL.findbyTermoAbertura(projeto.getTermoabertura());
            requisitos = new ListDataModel(lsRequisito);
        }
        return requisitos;
    }

    public void setRequisitos(DataModel requisitos) {
        this.requisitos = requisitos;
    }

}
