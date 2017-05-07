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

    public List<Projeto> findbyAluno(Pessoa aluno) {
        List<Projeto> lsProjeto = new ArrayList<>();
        if (aluno != null && aluno.getId() > 0) {
            lsProjeto = dao.findbyAluno(aluno);
        }
        if (lsProjeto.size() > 0) {
            for (Projeto projeto : lsProjeto) {
                if (projeto.getGerente() != null) {
                    projeto.getGerente().setLogin(null);
                    projeto.getGerente().setProjetos(null);
                    projeto.getGerente().setProjetosgerente(null);
                    projeto.getGerente().setIndicadoresprofessor(null);
                    projeto.getGerente().setTurmasprofessor(null);
                    projeto.getGerente().setTurma(null);
                    projeto.setComponentes(null);
                    projeto.setEaps(null);
                    projeto.setRequisitos(null);
                    projeto.setStakeholders(null);
                    projeto.setTermoabertura(null);
                }
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
            List<Pessoa> componentes = pessoaBLL.findbyProjeto(projeto);
            projeto.setComponentes(componentes);
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
                // projeto.getGerente().setTurma(null);
                projeto.setEaps(eapbll.findbyProjeto(projeto));
                projeto.setRequisitos(requisitoBLL.findbyProjeto(projeto));
                projeto.setStakeholders(stakeholderBLL.findbyProjeto(projeto));
                projeto.setTermoabertura(termoAberturaBLL.findByProjetoCompleto(projeto));
                projeto.setComponentes(null);
                
                return projeto;
            }
        }
        return null;
    }

}
