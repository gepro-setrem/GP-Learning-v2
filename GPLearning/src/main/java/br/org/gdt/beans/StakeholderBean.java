package br.org.gdt.beans;

import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.model.Stakeholder;
import br.org.gdt.bll.StakeholderBLL;
import br.org.gdt.model.Projeto;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean
@SessionScoped
public class StakeholderBean {

    private static final String viewList = "stakeholders";

    private Projeto projeto = new Projeto();
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;

    public Stakeholder stakeholder = new Stakeholder();
    @ManagedProperty("#{stakeholderBLL}")
    private StakeholderBLL stakeholderBLL;
    public DataModel stakeholders;

    public StakeholderBean() {
    }

    public String salvar() {
        if (projeto != null) {
            if (!stakeholder.getNome().isEmpty() && !stakeholder.getContribuicao().isEmpty() && !stakeholder.getPapel().isEmpty()) {
                stakeholder.setProjeto(projeto);
                if (stakeholder.getId() > 0) {
                    stakeholderBLL.update(stakeholder);
                } else {
                    stakeholderBLL.insert(stakeholder);
                }
                stakeholder = new Stakeholder();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parte Interessada salva com sucesso!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher todos os campos!"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Não existe nenhum Projeto selecionado!"));
        }
        return viewList;
    }

    public String editar() {
        stakeholder = (Stakeholder) stakeholders.getRowData();
        stakeholder = stakeholderBLL.findById(stakeholder.getId());
        return viewList;
    }

    public String excluir() {
        stakeholder = (Stakeholder) stakeholders.getRowData();
        stakeholderBLL.delete(stakeholder.getId());
        stakeholder = new Stakeholder();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Parte Interessada excluída com sucesso!"));
        return viewList;
    }

    public Stakeholder getStakeholder() {
        return stakeholder;
    }

    public void setStakeholder(Stakeholder stakeholder) {
        this.stakeholder = stakeholder;
    }

    public StakeholderBLL getStakeholderBLL() {
        return stakeholderBLL;
    }

    public void setStakeholderBLL(StakeholderBLL stakeholderBLL) {
        this.stakeholderBLL = stakeholderBLL;
    }

    public DataModel getStakeholders() {
        if (projeto != null) {
            List<Stakeholder> lsStakeholder = stakeholderBLL.findbyProjeto(projeto);
            stakeholders = new ListDataModel(lsStakeholder);
        }
        return stakeholders;
    }

    public void setStakeholders(DataModel stakeholders) {
        this.stakeholders = stakeholders;
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
