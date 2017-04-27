package br.org.gdt.bll;

import br.org.gdt.dao.ProjetoDAO;
import br.org.gdt.dao.PessoaDAO;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.TermoAbertura;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
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

    public List<Projeto> findbyAluno(Pessoa aluno) {
        List<Projeto> lsProjeto = new ArrayList<>();
        if (aluno != null && aluno.getId() > 0) {
            lsProjeto = dao.findbyAluno(aluno);
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

    public Projeto findByIdRelatorio(int id) {
        return dao.findByIdRelatorio(id);
    }
}
