package br.org.gdt.bll;

import br.org.gdt.dao.ProjetoDAO;
import br.org.gdt.dao.PessoaDAO;
import static br.org.gdt.enumerated.EtapaProjeto.Eap;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.TermoAbertura;
import br.org.gdt.model.Turma;
import br.org.gdt.model.EAP;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Requisito;
import br.org.gdt.model.Stakeholder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedProperty;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projetoBLL")
public class ProjetoBLL extends BLL<Projeto> {

    @Autowired
    private ProjetoDAO dao;
    @Autowired
    private PessoaBLL pessoaBLL;
    @Autowired
    private TurmaBLL turmaBLL;
    @Autowired
    private RequisitoBLL requisitoBLL;
    @Autowired
    private StakeholderBLL stakeholderBLL;
    @Autowired
    private EAPBLL eapbll;
    @Autowired
    private TermoAberturaBLL termoAberturaBLL;
    @Autowired
    private TarefaBLL tarefaBLL;
    @Autowired
    private EtapaBLL etapaBLL;
    @Autowired
    private AvaliacaoBLL avaliacaoBLL;

    public List<Projeto> findbyAluno(Pessoa aluno) {
        List<Projeto> lsProjeto = new ArrayList<>();
        if (aluno != null && aluno.getId() > 0) {
            lsProjeto = dao.findbyAluno(aluno);
        }
        if (lsProjeto.size() > 0) {
            for (Projeto projeto : lsProjeto) {
                projeto = CleanProjeto(projeto);
            }
        }
        return lsProjeto;
    }

    public List<Projeto> findbyGerente(Pessoa gerente) {
        List<Projeto> lsProjeto = new ArrayList<>();
        if (gerente != null && gerente.getId() > 0) {
            lsProjeto = dao.findbyGerente(gerente);
        }
        return lsProjeto;
    }

    public List<Projeto> findbyTurma(Turma turma) {
        List<Projeto> lsProjeto = new ArrayList<>();
        if (turma != null && turma.getId() > 0) {
            lsProjeto = dao.findbyTurma(turma);
        }
        return lsProjeto;
    }

    public Projeto findbyTermoAbertura(TermoAbertura termoabertura) {
        Projeto projeto = null;
        if (termoabertura != null && termoabertura.getId() > 0) {
            projeto = dao.findbyTermoAbertura(termoabertura);
        }
        return projeto;
    }

    public List<Projeto> findbyProfessor(Pessoa professor) {
        List<Projeto> projetos = dao.findbyProfessor(professor);
        for (Projeto projeto : projetos) {
            projeto = CleanProjeto(projeto);
//            List<Pessoa> componentes = pessoaBLL.findbyProjeto(projeto);
//            projeto.setComponentes(componentes);
        }
        return projetos;
    }

    public Projeto findbyRelatorio(Projeto item) {
        Projeto projeto = new Projeto();
        if (item != null && item.getId() > 0) {
            projeto = dao.findbyId(item.getId());
            if (projeto != null) {
                List<Requisito> requisitos = requisitoBLL.findbyProjeto(projeto);
                projeto.setRequisitos(requisitos);
                List<Stakeholder> stakeholders = stakeholderBLL.findbyProjeto(projeto);
                projeto.setStakeholders(stakeholders);
            }
        }
        return projeto;
    }

    public Projeto findProjetoCompleto(int pro_id) {
        if (pro_id > 0) {
            Projeto projeto = dao.findbyId(pro_id);
            if (projeto != null) {
                projeto.getGerente().setLogin(null);
                projeto.getGerente().setProjetos(null);
                projeto.getGerente().setProjetosgerente(null);
                projeto.getGerente().setIndicadoresprofessor(null);
                projeto.getGerente().setTurmasprofessor(null);
                projeto.getGerente().setTurma(null);

                if (projeto.getTurma() != null) {
                    projeto.getTurma().setAcademicos(null);
                    projeto.getTurma().setProjetos(null);
                    projeto.getTurma().setTurmaParametros(null);
                    projeto.getTurma().setEtapas(null);//etapaBLL.findbyTurma(projeto.getTurma()));
                    if (projeto.getTurma().getProfessor() != null) {
                        projeto.getTurma().getProfessor().setIndicadoresprofessor(null);
                        projeto.getTurma().getProfessor().setLogin(null);
                        projeto.getTurma().getProfessor().setProjetos(null);
                        projeto.getTurma().getProfessor().setProjetosgerente(null);
                        projeto.getTurma().getProfessor().setTurma(null);
                        projeto.getTurma().getProfessor().setTurmasprofessor(null);
                    }
                }

                List<EAP> eaps = new ArrayList<>();
                eaps.add(tarefaBLL.findbyEAP(projeto));
                projeto.setEaps(eaps); //eapbll.findbyProjeto(projeto));
                projeto.setRequisitos(requisitoBLL.findbyProjeto(projeto));
                projeto.setStakeholders(stakeholderBLL.findbyProjeto(projeto));
                projeto.setTermoabertura(termoAberturaBLL.findByProjetoCompleto(projeto));
                projeto.setAvaliacoes(avaliacaoBLL.findbyProjeto(projeto));
                projeto.setComentarios(null);

                List<Pessoa> compenentes = pessoaBLL.findbyProjeto(projeto);
                if (compenentes != null) {
                    for (Pessoa com : compenentes) {
                        com.setIndicadoresprofessor(null);
                        com.setLogin(null);
                        com.setProjetos(null);
                        com.setTurma(null);
                        com.setTurmasprofessor(null);
                        com.setProjetos(null);
                        com.setProjetosgerente(null);
                    }
                    projeto.setComponentes(compenentes);
                } else {
                    projeto.setComponentes(null);
                }

                return projeto;
            }
        }
        return null;
    }

    private Projeto CleanProjeto(Projeto projeto) {
        if (projeto.getGerente() != null) {
            projeto.getGerente().setLogin(null);
            projeto.getGerente().setProjetos(null);
            projeto.getGerente().setProjetosgerente(null);
            projeto.getGerente().setIndicadoresprofessor(null);
            projeto.getGerente().setTurmasprofessor(null);
            projeto.getGerente().setTurma(null);
        }
        if (projeto.getTurma() != null) {
            projeto.getTurma().setAcademicos(null);
            projeto.getTurma().setProjetos(null);
            projeto.getTurma().setEtapas(null);
            projeto.getTurma().setTurmaParametros(null);
            projeto.getTurma().setProfessor(null);
        }
        projeto.setComponentes(null);
        projeto.setEaps(null);
        projeto.setRequisitos(null);
        projeto.setStakeholders(null);
        projeto.setTermoabertura(null);
        projeto.setComentarios(null);
        projeto.setAvaliacoes(null);
        return projeto;
    }

    public List<Projeto> findProjetoDate(Date data, Pessoa aluno) {
        if (data != null) {
            List<Projeto> lsProjeto = dao.findbyDate(data, aluno);
            if (lsProjeto != null) {
                for (Projeto projeto : lsProjeto) {
                    if (projeto != null) {
                        projeto.setAvaliacoes(null);
                        projeto.setComentarios(null);
                        projeto.setComponentes(null);
                        projeto.setEaps(null);
                        projeto.setRequisitos(null);
                        projeto.setStakeholders(null);
                        projeto.setTermoabertura(null);
                        projeto.setTurma(null);
                    }
                }
                return lsProjeto;
            }
        }
        return null;
    }
}
