package br.org.gdt.resource;

import br.org.gdt.bll.EtapaBLL;
import br.org.gdt.bll.IndicadorBLL;
import br.org.gdt.bll.PessoaBLL;
import br.org.gdt.bll.ProjetoBLL;
import br.org.gdt.bll.TurmaBLL;
import br.org.gdt.enumerated.EtapaProjeto;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Indicador;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.Turma;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/turma")
public class TurmaResource {
    
    @Autowired
    private TurmaBLL turmaBLL;
    @Autowired
    private ProjetoBLL projetoBLL;
    @Autowired
    private PessoaBLL pessoaBLL;
    @Autowired
    private EtapaBLL etapaBLL;
    @Autowired
    private IndicadorBLL indicadorBLL;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/projeto/{pro_id}")
    public Turma findByProjeto(@PathParam("pro_id") int pro_id) {
        if (pro_id > 0) {
            Projeto projeto = projetoBLL.findById(pro_id);
            if (projeto != null) {
                Turma turma = turmaBLL.findById(projeto.getTurma().getId());
            }
        }
        return null;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{tur_id}")
    public Turma findById(@PathParam("tur_id") int tur_id) {
        if (tur_id > 0) {
            Turma turma = turmaBLL.findById(tur_id);
            if (turma != null) {
                turma.setProjetos(null);
                turma.setTurmaParametros(null);
                turma.setProfessor(null);
                List<Pessoa> lsComponentes = pessoaBLL.findbyTurma(turma);
                if (lsComponentes != null) {
                    for (Pessoa p : lsComponentes) {
                        p.setImagem(null);
                        p.setIndicadoresprofessor(null);
                        p.setLogin(null);
                        p.setProjetos(null);
                        p.setProjetosgerente(null);
                        p.setTurma(null);
                        p.setTurmasprofessor(null);
                    }
                }
                turma.setAcademicos(lsComponentes);
                
                List<Etapa> lsEtapas = etapaBLL.findbyTurma(turma);
                if (lsEtapas != null) {
                    for (Etapa e : lsEtapas) {
                        e.setAtividadeParametros(null);
                        e.setAvaliacoes(null);
                        e.setComentarios(null);
                        e.setEtapaParametros(null);
                        e.setTurma(null);
                        List<Indicador> lsIndicador = indicadorBLL.findbyEtapa(e);
                        if (lsIndicador != null) {
                            for (Indicador i : lsIndicador) {
                                i.setAvaliacoes(null);
                                i.setEtapas(null);
                                i.setProfessor(null);
                            }
                        }
                        e.setIndicadores(lsIndicador);
                    }
                }
                turma.setEtapas(lsEtapas);
            }
            return turma;
        }
        return null;
    }
    
}
