package br.org.gdt.beans;

import br.org.gdt.model.Marco;
import br.org.gdt.bll.MarcoBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.model.Projeto;
import java.util.List;
import java.util.TimeZone;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

@ManagedBean
@SessionScoped
public class MarcoBean {

    private static final String viewList = "termoaberturamarcos";

    private Projeto projeto = new Projeto();
    @ManagedProperty("#{projetoBLL}")
    private ProjetoBLL projetoBLL;

    private Marco marco = new Marco();
    @ManagedProperty("#{marcoBLL}")
    private MarcoBLL marcoBLL;
    private DataModel marcos;

    private TimeZone timeZone = TimeZone.getDefault();

    public MarcoBean() {
    }

    public String salvar() {
        if (projeto != null && projeto.getTermoabertura() != null) {
            if (!marco.getObjetivo().isEmpty()) {
                marco.setTermoabertura(projeto.getTermoabertura());
                if (marco.getId() > 0) {
                    marcoBLL.update(marco);
                } else {
                    marcoBLL.insert(marco);
                }
                marco = new Marco();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Marco salvo com sucesso!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, você deve preencher o objetivo do Marco!"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Não existe nenhum Projeto selecionado ou Termo de Abertura criado!"));
        }
        return viewList;
    }

    public String editar() {
        marco = (Marco) marcos.getRowData();
        marco = marcoBLL.findById(marco.getId());
        return viewList;
    }

    public String excluir() {
        marco = (Marco) marcos.getRowData();
        marcoBLL.delete(marco.getId());
        marco = new Marco();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Marco excluído com sucesso!"));
        return viewList;
    }

    public Marco getMarco() {
        return marco;
    }

    public void setMarco(Marco marco) {
        this.marco = marco;
    }

    public MarcoBLL getMarcoBLL() {
        return marcoBLL;
    }

    public void setMarcoBLL(MarcoBLL marcoBLL) {
        this.marcoBLL = marcoBLL;
    }

    public DataModel getMarcos() {
        if (projeto != null && projeto.getTermoabertura() != null) {
            List<Marco> lsMarco = marcoBLL.findbyTermoAbertura(projeto.getTermoabertura());
            marcos = new ListDataModel(lsMarco);
        }
        return marcos;
    }

    public void setMarcos(DataModel marcos) {
        this.marcos = marcos;
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

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

}
