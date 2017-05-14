package br.org.gdt.beans;

import br.org.gdt.bll.EtapaBLL;
import br.org.gdt.bll.IndicadorBLL;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import br.org.gdt.bll.TurmaBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.TurmaParametroBLL;
import br.org.gdt.enumerated.EtapaProjeto;
import br.org.gdt.enumerated.TurmaParametroType;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Indicador;
import br.org.gdt.model.TurmaParametro;
import java.util.ArrayList;
import java.util.Date;
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
    
    @ManagedProperty("#{etapaBLL}")
    private EtapaBLL etapaBLL;
    
    @ManagedProperty("#{indicadorBLL}")
    private IndicadorBLL indicadorBLL;
    private List<Indicador> indicadores;
    
    public TurmaBean() {
    }
    
    public Pessoa getUsuario() {
        if (usuario == null || usuario.getId() == 0) {
            ExternalContext external = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) external.getRequest();
            String email = request.getRemoteUser();
            usuario = pessoaBLL.findbyEmail(email);
        }
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
        getUsuario();
        if (turmas == null) {
            turmas = new ListDataModel(turmaBLL.findbyProfessor(usuario));
        }
        return turmas;
    }
    
    public void setTurmas(DataModel turmas) {
        this.turmas = turmas;
    }
    
    public String salvar() {
        usuario = getUsuario();
        if (!turma.getNome().isEmpty() && turma.getAno() > 0) {
            turma.setProfessor(usuario);
            if (turma.getEtapas() != null) {
                for (Etapa etapa : turma.getEtapas()) {
                    if (etapa.getId() == 0 || etapa.getCriacao() == null) {
                        etapa.setCriacao(new Date());
                    }
                }
            }
            if (turma.getId() > 0) {
                turmaBLL.update(turma);
            } else {
                turmaBLL.insert(turma);
            }
            turmas = null;
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
        initEtapas();
        return "turmafrm";
    }
    
    public String duplicar() {
        turma = (Turma) turmas.getRowData();
        Turma copia = new Turma();
        List<TurmaParametro> lsTurmaParametro = turmaParametroBLL.findbyTurma(turma);
        List<Etapa> lsEtapa = etapaBLL.findbyTurma(turma);
        copia.setNome(turma.getNome() + " CÓPIA");
        copia.setAno(turma.getAno());
        copia.setCriacao(new Date());
        copia.setAlteracao(new Date());
        copia.setProfessor(turma.getProfessor());
        
        List<TurmaParametro> TurmaParametros = new ArrayList<>();
        
        if (lsTurmaParametro != null) {
            for (TurmaParametro turmaParametro : lsTurmaParametro) {
                TurmaParametro tp = new TurmaParametro();
                tp.setChave(turmaParametro.getChave());
                tp.setValor(turmaParametro.getValor());
                tp.setTurma(copia);
                TurmaParametros.add(tp);
            }
        }
        copia.setTurmaParametros(TurmaParametros);
        
        List<Etapa> Etapas = new ArrayList<>();
        
        if (lsEtapa != null) {
            for (Etapa etapa : lsEtapa) {
                Etapa et = new Etapa();
                et.setCriacao(new Date());
                et.setEtapa(etapa.getEtapa());
                et.setConclusao(et.getConclusao());
                et.setNome(etapa.getNome());
                et.setTermino(etapa.getTermino());
                et.setTurma(copia);
                et.setIndicadores(etapa.getIndicadores());
                Etapas.add(et);
            }
        }
        copia.setEtapas(Etapas);
        turmaBLL.insert(copia);
        turmas = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Turma copiada com sucesso!"));
        return "turmalst";
    }
    
    public String excluir() throws Exception {
        try {
            turma = (Turma) turmas.getRowData();
            turmaBLL.delete(turma.getId());
            turmas = null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Não foi possível exlcuir a turma!"));
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }
        return "turmalst";
    }
    
    public String novo() {
        turma = new Turma();
        initParametros();
        initEtapas();
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
    
    public String getTurmaParametroType(String type) {
        TurmaParametroType trType = TurmaParametroType.valueOf(type);
        return turmaParametroBLL.getTurmaParametroType(trType);
    }
    
    public String getEtapaProjeto(String type) {
        EtapaProjeto etType = EtapaProjeto.valueOf(type);
        return etapaBLL.getEtapaProjeto(etType);
    }
    
    private void initParametros() {
        List<TurmaParametro> lsTurmaParametro = turmaParametroBLL.findbyTurma(turma);
        TurmaParametroType[] lsTurmaParametroType = TurmaParametroType.values();
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
    
    private void initEtapas() {
        List<Etapa> lsEtapa = etapaBLL.findbyTurma(turma);
        EtapaProjeto[] lsEtapaProjeto = EtapaProjeto.values();
        for (EtapaProjeto type : lsEtapaProjeto) {
            Boolean hasKey = false;
            for (Etapa eta : lsEtapa) {
                if (eta.getEtapa().equals(type)) {
                    hasKey = true;
                    break;
                }
            }
            if (!hasKey) {
                Etapa etapa = new Etapa();
                etapa.setEtapa(type);
                etapa.setTurma(turma);
                lsEtapa.add(etapa);
            }
        }
        turma.setEtapas(lsEtapa);
    }
    
    public EtapaBLL getEtapaBLL() {
        return etapaBLL;
    }
    
    public void setEtapaBLL(EtapaBLL etapaBLL) {
        this.etapaBLL = etapaBLL;
    }
    
    public IndicadorBLL getIndicadorBLL() {
        return indicadorBLL;
    }
    
    public void setIndicadorBLL(IndicadorBLL indicadorBLL) {
        this.indicadorBLL = indicadorBLL;
    }
    
    public List<Indicador> getIndicadores() {
        getUsuario();
        indicadores = indicadorBLL.findbyProfessor(usuario);
        return indicadores;
    }
    
    public void setIndicadores(List<Indicador> indicadores) {
        this.indicadores = indicadores;
    }
}
