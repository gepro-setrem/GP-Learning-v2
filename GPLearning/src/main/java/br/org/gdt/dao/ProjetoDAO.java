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

    public List<Projeto> findbyAluno(Pessoa aluno) {
        return entityManager.createQuery("from Projeto as p left join p.componentes as c where c = :a")
                .setParameter("a", aluno).getResultList();
    }

    public List<Projeto> findbyGerente(Pessoa gerente) {
        return entityManager.createQuery("from Projeto where gerente = :g")
                .setParameter("g", gerente).getResultList();
    }

    public List<Projeto> findbyTurma(Turma turma) {
        return entityManager.createQuery("from Projeto where turma = :t")
                .setParameter("t", turma).getResultList();
    }
    
    public List<Projeto> findbyProfessor(Pessoa professor) {
        return entityManager.createQuery("from Projeto where turma.professor = :p")
                .setParameter("p", professor).getResultList();
    }
    
    public Projeto findbyTermoAbertura(TermoAbertura termoabertura) {
        List<Projeto> lsProjeto = entityManager.createQuery("from Projeto where termoabertura = :p")
                .setParameter("p", termoabertura).getResultList();
        Projeto projeto = null;
        if (lsProjeto.size() > 0) {
            projeto = lsProjeto.get(0);
        }
        return projeto;
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
