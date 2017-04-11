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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("projetoBLL")
public class ProjetoBLL extends BLL<Projeto> {

    @Autowired
    private ProjetoDAO dao;

    @Autowired
    //@ManagedProperty("#{pessoaBLL}")
    private PessoaBLL pessoaBLL;

    public List<Projeto> findMyProjects(Pessoa usuario) {
        Pessoa user = pessoaBLL.findById(usuario.getId());
        List<Projeto> projetos = user.getProjetos();
        return projetos;
    }

    public List<Projeto> findbyturma(Turma turma) {
        return dao.findbyturma(turma);
    }

    public Projeto findByTermoAbertura(TermoAbertura termoabertura) {
        return dao.findByTermoAbertura(termoabertura);
    }

    public List<Projeto> findbyturmasprofessor(Pessoa pessoa) {
        Pessoa user = pessoaBLL.findById(pessoa.getId());
        List<Projeto> projetos = new ArrayList<>();
        for (Turma turma : user.getTurmasprofessor()) {
            projetos.addAll(turma.getProjetos());
        }
        return projetos;
    }

    public Projeto findByIdRelatorio(int id) {
        return dao.findByIdRelatorio(id);
    }
}
