package br.org.gdt.beans;

import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.model.Restricao;
import br.org.gdt.bll.RestricaoBLL;
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
public class RestricaoBean {

    private static final String viewList = "termoaberturarestricoes";

    private Projeto projeto = new Projeto();
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;

    private Restricao restricao = new Restricao();
    @ManagedProperty("#{restricaoBLL}")
    private RestricaoBLL restricaoBLL;
    private DataModel restricoes;

    public RestricaoBean() {
    }

    public String salvar() {
        if (projeto != null && projeto.getTermoabertura() != null) {
            if (!restricao.getDescricao().isEmpty()) {
                restricao.setTermoabertura(projeto.getTermoabertura());
                if (restricao.getId() > 0) {
                    restricaoBLL.update(restricao);
                } else {
                    restricaoBLL.insert(restricao);
                }
                restricao = new Restricao();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Restrição salva com sucesso!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, você deve preencher a descrição da Restrição!"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Não existe nenhum Projeto selecionado ou Termo de Abertura criado!"));
        }
        return viewList;
    }

    public String editar() {
        restricao = (Restricao) restricoes.getRowData();
        restricao = restricaoBLL.findById(restricao.getId());
        return viewList;
    }

    public String excluir() {
        restricao = (Restricao) restricoes.getRowData();
        restricaoBLL.delete(restricao.getId());
        restricao = new Restricao();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Restrição excluída com sucesso!"));
        return viewList;
    }

    public Restricao getRestricao() {
        return restricao;
    }

    public void setRestricao(Restricao restricao) {
        this.restricao = restricao;
    }

    public RestricaoBLL getRestricaoBLL() {
        return restricaoBLL;
    }

    public void setRestricaoBLL(RestricaoBLL restricaoBLL) {
        this.restricaoBLL = restricaoBLL;
    }

    public DataModel getRestricoes() {
        if (projeto != null && projeto.getTermoabertura() != null) {
            List<Restricao> lsRestricao = restricaoBLL.findbyTermoAbertura(projeto.getTermoabertura());
            restricoes = new ListDataModel(lsRestricao);
        }
        return restricoes;
    }

    public void setRestricoes(DataModel restricoes) {
        this.restricoes = restricoes;
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
