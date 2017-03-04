package br.org.gdt.dao;

import br.org.gdt.model.Marco;
import br.org.gdt.model.Premissa;
import br.org.gdt.model.Projeto;
import br.org.gdt.model.RequisitoTermoAbertura;
import br.org.gdt.model.Restricao;
import br.org.gdt.model.Stakeholder;
import br.org.gdt.model.TermoAbertura;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("termoAberturaDAO")
public class TermoAberturaDAO extends DAO<TermoAbertura> {

    public TermoAberturaDAO() {
        classe = TermoAbertura.class;
    }

    public TermoAbertura findByIdRelatorio(int id) {
        TermoAbertura tabertura = (TermoAbertura) entityManager.createQuery("from TermoAbertura as ta where ta.idTermoAbertura = :p")
                .setParameter("p", id).getSingleResult();

        System.out.println("Buscando por relatórios, entrando em marcos" + tabertura.getMarcos());

        List<Marco> marcos = new ArrayList<Marco>();
        for (Marco u : tabertura.getMarcos()) {
            marcos.add(u);
        }
        tabertura.setMarcos(marcos);

        System.out.println("Buscando por relatórios, entrando em premissas" + tabertura.getPremissas());
        List<Premissa> premissas = new ArrayList<Premissa>();
        for (Premissa u : tabertura.getPremissas()) {
            premissas.add(u);
        }
        tabertura.setPremissas(premissas);

        System.out.println("Buscando por relatórios, entrando em restrições" + tabertura.getRestricoes());
        List<Restricao> restricoes = new ArrayList<Restricao>();
        for (Restricao u : tabertura.getRestricoes()) {
            restricoes.add(u);
        }
        tabertura.setRestricoes(restricoes);

        System.out.println("Buscando por relatórios, entrando em requisitos" + tabertura.getRequisitosTermoAberturas());
        List<RequisitoTermoAbertura> reqta = new ArrayList<RequisitoTermoAbertura>();
        for (RequisitoTermoAbertura u : tabertura.getRequisitosTermoAberturas()) {
            reqta.add(u);
        }
        tabertura.setRequisitosTermoAberturas(reqta);

        Projeto projeto = (Projeto) entityManager.createQuery("from Projeto where termoabertura=:p")
                .setParameter("p", tabertura).getSingleResult();

        System.out.println("Projeto: " + projeto.getNome());
        System.out.println("Partes do Projeto" + projeto.getStakeholders().size());

        System.out.println("Buscando por relatórios, entrando em requisitos" + tabertura.getProjeto().getStakeholders());
        List<Stakeholder> partesinteressadas = new ArrayList<Stakeholder>();

        for (Stakeholder p : projeto.getStakeholders()) {
            partesinteressadas.add(p);
            System.out.println("item" + p);
        }
        projeto.setStakeholders(partesinteressadas);
        tabertura.setProjeto(projeto);
        System.out.println("Partes " + projeto.getStakeholders());

        System.out.println("Buscando por relatórios, concluído");
        return tabertura;
    }
}
