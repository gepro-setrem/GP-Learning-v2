package br.org.gdt.bll;

import br.org.gdt.dao.ProjetoDAO;
import br.org.gdt.dao.UsuarioDAO;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.TermoAbertura;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("projetoBLL")
public class ProjetoBLL {

    @Autowired
    private ProjetoDAO dao;

    @Transactional
    public void insert(Projeto item) {
        dao.insert(item);
    }

    @Transactional
    public void update(Projeto item) {
        dao.update(item);
    }

    @Transactional
    public void delete(long id) {
        dao.delete(id);
    }

    public Projeto findById(long id) {
        return (Projeto) dao.findById(id);
    }

    public List<Projeto> findAll() {
        return dao.findAll();
    }

    public List<Projeto> findbyaluno(Usuario usuario) {
        Usuario user = new UsuarioDAO().findById(usuario.getId());
        List<Projeto> projetos = user.getProjetos();
        return projetos;
    }

    public List<Projeto> findbyturma(Turma turma) {
        return dao.findbyturma(turma);
    }

    public Projeto findByTermoAbertura(TermoAbertura termoabertura) {
        return dao.findByTermoAbertura(termoabertura);
    }

    public List<Projeto> findbyturmasprofessor(Usuario usuario) {
        return dao.findbyturmasprofessor(usuario);
    }

    public Projeto findByIdRelatorio(int id) {
        return dao.findByIdRelatorio(id);
    }
}
