package br.org.gdt.beans;

import br.org.gdt.bll.IndicadorBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.model.Indicador;
import br.org.gdt.model.Pessoa;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@RequestScoped
public class IndicadorBean {

    private Indicador indicador = new Indicador();
    @ManagedProperty("#{indicadorBLL}")
    private IndicadorBLL indicadorBLL;
    private DataModel indicadores;

    private Pessoa usuario = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;

    public IndicadorBean() {
    }

    public String salvar() {
        usuario = getUsuario();
        Pessoa user = new Pessoa();
        user.setId(usuario.getId());
        if (!indicador.getNome().equals("")) {
            indicador.setProfessor(user);
            if (indicador.getId() > 0) {
                indicadorBLL.update(indicador);
            } else {
                indicadorBLL.insert(indicador);
            }
            return "indicadorlst";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher o nome do Indicador!"));
            return "indicadorfrm";
        }
    }

    public String editar() {
        indicador = (Indicador) indicadores.getRowData();
        indicador = indicadorBLL.findById(indicador.getId());
        return "indicadorfrm";
    }

    public String excluir() throws Exception {
        try {
            indicador = (Indicador) indicadores.getRowData();
            indicadorBLL.delete(indicador.getId());
            indicadores = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        return "indicadorlst";
    }

    public String novo() {
        indicador = new Indicador();
        return "indicadorfrm";
    }

    public Indicador getIndicador() {
        return indicador;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    public IndicadorBLL getIndicadorBLL() {
        return indicadorBLL;
    }

    public void setIndicadorBLL(IndicadorBLL indicadorBLL) {
        this.indicadorBLL = indicadorBLL;
    }

    public DataModel getIndicadores() {
        usuario = getUsuario();
        indicadores = new ListDataModel(indicadorBLL.findbyProfessor(usuario));
        return indicadores;
    }

    public void setIndicadores(DataModel indicadores) {
        this.indicadores = indicadores;
    }

    public Pessoa getUsuario() {
        ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) external.getRequest();
        String email = request.getRemoteUser();
        usuario = pessoaBLL.findbyEmail(email);
        return usuario;
    }

    public void setUsuario(Pessoa usuario) {
        this.usuario = usuario;
    }

    public PessoaBLL getPessoaBLL() {
        return pessoaBLL;
    }

    public void setPessoaBLL(PessoaBLL pessoaBLL) {
        this.pessoaBLL = pessoaBLL;
    }

}
