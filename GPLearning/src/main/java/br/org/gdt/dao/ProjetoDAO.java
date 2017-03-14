package br.org.gdt.dao;

import br.org.gdt.model.Projeto;
import br.org.gdt.model.Requisito;
import br.org.gdt.model.TermoAbertura;
import br.org.gdt.model.Stakeholder;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("projetoDAO")
public class ProjetoDAO extends DAO<Projeto> {

    public ProjetoDAO() {
        classe = Projeto.class;
    }

    public List<Projeto> findbyaluno(Pessoa pessoa) {
        Pessoa user = new PessoaDAO().findById(pessoa.getId());
        List<Projeto> projetos = user.getProjetos();
        return projetos;
    }

    public List<Projeto> findbyturma(Turma turma) {
        return entityManager.createQuery("from Projeto where turma = :p")
                .setParameter("p", turma).getResultList();
    }

    public Projeto findByTermoAbertura(TermoAbertura termoabertura) {
        List<Projeto> projetos = entityManager.createQuery("from Projeto where termoabertura = :p")
                .setParameter("p", termoabertura).getResultList();
        Projeto projeto = projetos.get(0);
        return projeto;
    }

    public List<Projeto> findbyturmasprofessor(Pessoa pessoa) {
        PessoaDAO usuDAO = new PessoaDAO();
        Pessoa user = usuDAO.findById(pessoa.getId());
        //Temos a lista de turmas daquele professor, agora precisamos listas os alunos que pertencem aquelas turmas
        List<Projeto> projetosdaturma = new ArrayList<>();
        System.out.println("Turmas do Usuário" + user.getNome() + " são" + user.getTurmasprofessor());

        for (Turma turminha : user.getTurmasprofessor()) {
            projetosdaturma.addAll(turminha.getProjeto());
        }
        List<Projeto> projetos = projetosdaturma;
//        for (Projeto pr : projetos) {
//            Hibernate.initialize(pr.getComponentes());
//        }
        return projetos;
    }

    public Projeto findByIdRelatorio(int id) {

        Projeto projeto = (Projeto) entityManager.createQuery("from Projeto as p where p.idProjeto = :p")
                .setParameter("p", id)
                .getSingleResult();

        System.out.println("Buscando por relatórios, entrando em requisitos" + projeto.getRequisitos());
        List<Requisito> reqtos = new ArrayList<Requisito>();
        for (Requisito r : projeto.getRequisitos()) {
            reqtos.add(r);
        }
        projeto.setRequisitos(reqtos);

        System.out.println("Projeto: " + projeto.getNome());
        System.out.println("Partes do Projeto" + projeto.getStakeholders().size());

        System.out.println("Buscando por relatórios, entrando em partes interessadas" + projeto.getStakeholders());
        List<Stakeholder> partesinteressadas = new ArrayList<Stakeholder>();

        //partesinteressadas = 
        for (Stakeholder p : projeto.getStakeholders()) {
            partesinteressadas.add(p);
            System.out.println("item" + p);
        }
        projeto.setStakeholders(partesinteressadas);
        System.out.println("Partes " + projeto.getStakeholders());

        System.out.println("Buscando por relatórios, concluído");
        return projeto;
    }
}
