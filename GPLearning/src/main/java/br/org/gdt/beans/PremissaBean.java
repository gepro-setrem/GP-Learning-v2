package br.org.gdt.beans;

import br.org.gdt.model.Premissa;
import br.org.gdt.bll.PremissaBLL;
import br.org.gdt.bll.ProjetoBLL;
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
public class PremissaBean {

    private static final String viewList = "termoaberturapremissas";

    private Projeto projeto = new Projeto();
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;

    private Premissa premissa = new Premissa();
    @ManagedProperty("#{premissaBLL}")
    private PremissaBLL premissaBLL;
    private DataModel premissas;

    public PremissaBean() {
    }

    public String salvar() {
        if (projeto != null && projeto.getTermoabertura() != null) {
            if (!premissa.getDescricao().isEmpty()) {
                premissa.setTermoabertura(projeto.getTermoabertura());
                if (premissa.getId() > 0) {
                    premissaBLL.update(premissa);
                } else {
                    premissaBLL.insert(premissa);
                }
                premissa = new Premissa();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Premissa salva com sucesso!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, você deve preencher a descrição da Premissa!"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Não existe nenhum Projeto selecionado ou Termo de Abertura criado!"));
        }
        return viewList;
    }

    public String editar() {
        premissa = (Premissa) premissas.getRowData();
        premissa = premissaBLL.findById(premissa.getId());
        return viewList;
    }

    public String excluir() {
        premissa = (Premissa) premissas.getRowData();
        premissaBLL.delete(premissa.getId());
        premissa = new Premissa();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Premissa excluída com sucesso!"));
        return viewList;
    }

    public Premissa getPremissa() {
        return premissa;
    }

    public void setPremissa(Premissa premissa) {
        this.premissa = premissa;
    }

    public PremissaBLL getPremissaBLL() {
        return premissaBLL;
    }

    public void setPremissaBLL(PremissaBLL premissaBLL) {
        this.premissaBLL = premissaBLL;
    }

    public DataModel getPremissas() {
        if (projeto != null && projeto.getTermoabertura() != null) {
            List<Premissa> lsPremissa = premissaBLL.findbyTermoAbertura(projeto.getTermoabertura());
            premissas = new ListDataModel(lsPremissa);
        }
        return premissas;
    }

    public void setPremissas(DataModel premissas) {
        this.premissas = premissas;
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
