package br.org.gdt.beans;

import br.org.gdt.bll.EtapaBLL;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import br.org.gdt.bll.TurmaBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.TurmaParametroBLL;
import br.org.gdt.enumerated.TurmaParametroType;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.TurmaParametro;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class TurmaBean {

    private Turma turma = new Turma();
    @ManagedProperty("#{turmaBLL}")
    private TurmaBLL turmaBLL;
    private DataModel turmas;
    @ManagedProperty("#{turmaParametroBLL}")
    private TurmaParametroBLL turmaParametroBLL;

    private Pessoa usuario = new Pessoa();
    @ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;
    private TurmaParametroType[] turmaParametroTypes;

    @ManagedProperty("#{etapaBLL}")
    private EtapaBLL etapaBLL;

    public TurmaBean() {
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

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public DataModel getTurmas() {
        usuario = getUsuario();
        turmas = new ListDataModel(turmaBLL.findbyProfessor(usuario));
        return turmas;
    }

    public void setTurmas(DataModel turmas) {
        this.turmas = turmas;
    }

    public String salvar() {
        usuario = getUsuario();
        if (!turma.getNome().isEmpty() && turma.getAno() > 0) {
            turma.setProfessor(usuario);
            if (turma.getId() > 0) {
                turmaBLL.update(turma);
            } else {
                turmaBLL.insert(turma);
            }
            return "turmalst";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Para salvar, é necessário preencher o nome e ano da turma!"));
            return "turmafrm";
        }
    }

    public String editar() {
        turma = (Turma) turmas.getRowData();
        turma = turmaBLL.findById(turma.getId());
        initParametros();
        return "turmafrm";
    }

    public String duplicar() {
        Turma copia = new Turma();
        turma = (Turma) turmas.getRowData();
        copia.setNome(turma.getNome() + " CÓPIA");
        copia.setAno(turma.getAno());
        turmaBLL.insert(copia);
        return "turmalst";
    }

    public String excluir() throws Exception {
        try {
            turma = (Turma) turmas.getRowData();
            turmaBLL.delete(turma.getId());
            turmas = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        return "turmalst";
    }

    public String novo() {
        turma = new Turma();
        initParametros();
        return "turmafrm";
    }

    public TurmaBLL getTurmaBLL() {
        return turmaBLL;
    }

    public void setTurmaBLL(TurmaBLL turmaBLL) {
        this.turmaBLL = turmaBLL;
    }

    public PessoaBLL getPessoaBLL() {
        return pessoaBLL;
    }

    public void setPessoaBLL(PessoaBLL pessoaBLL) {
        this.pessoaBLL = pessoaBLL;
    }

    public TurmaParametroBLL getTurmaParametroBLL() {
        return turmaParametroBLL;
    }

    public void setTurmaParametroBLL(TurmaParametroBLL turmaParametroBLL) {
        this.turmaParametroBLL = turmaParametroBLL;
    }

    public String getParameter(TurmaParametro parametro) {
        String chave = parametro.toString();
        Pessoa user = getUsuario();
        TurmaParametro trp = turmaParametroBLL.getParametro(user.getTurma(), chave);
        if (trp != null) {
            return trp.getValor();
        } else {
            return "";
        }
    }

    public TurmaParametroType[] getTurmaParametroTypes() {
        return TurmaParametroType.values();
    }

    public void setTurmaParametroTypes(TurmaParametroType[] turmaParametroTypes) {
        this.turmaParametroTypes = turmaParametroTypes;
    }

    public String getTurmaParametroType(String type) {
        TurmaParametroType trType = TurmaParametroType.valueOf(type);
        return turmaParametroBLL.getTurmaParametroType(trType);
    }

    private void initParametros() {
        List<TurmaParametro> lsTurmaParametro = turmaParametroBLL.findbyTurma(turma);
        TurmaParametroType[] lsTurmaParametroType = getTurmaParametroTypes();
        for (TurmaParametroType type : lsTurmaParametroType) {
            String chave = type.toString();
            Boolean hasKey = false;
            for (TurmaParametro trp : lsTurmaParametro) {
                if (trp.getChave().equals(chave)) {
                    hasKey = true;
                    break;
                }
            }
            if (!hasKey) {
                TurmaParametro turmaParametro = new TurmaParametro();
                turmaParametro.setChave(chave);
                turmaParametro.setTurma(turma);
                lsTurmaParametro.add(turmaParametro);
            }
        }
        turma.setTurmaParametros(lsTurmaParametro);
    }

    private void inirEtapas() {
        List<Etapa> lsEtapa = etapaBLL.findbyTurma(turma);
    }

    public EtapaBLL getEtapaBLL() {
        return etapaBLL;
    }

    public void setEtapaBLL(EtapaBLL etapaBLL) {
        this.etapaBLL = etapaBLL;
    }
    
    
}
