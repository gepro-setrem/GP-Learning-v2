package br.org.gdt.bll;

import br.org.gdt.dao.AvaliacaoDAO;
import br.org.gdt.model.Avaliacao;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Projeto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("avaliacaoBLL")
public class AvaliacaoBLL extends BLL<Avaliacao> {
    
    @Autowired
    private AvaliacaoDAO dao;
    
    public List<Avaliacao> findbyEtapa(Etapa etapa) {
        List<Avaliacao> lsAvaliacao = new ArrayList<>();
        if (etapa != null && etapa.getId() > 0) {
            lsAvaliacao = dao.findbyEtapa(etapa);
        }
        return lsAvaliacao;
    }
    
    public List<Avaliacao> findbyProjeto(Projeto projeto) {
        List<Avaliacao> lsAvaliacao = new ArrayList<>();
        if (projeto != null && projeto.getId() > 0) {
            lsAvaliacao = dao.findbyProjeto(projeto);
            if (lsAvaliacao != null) {
                for (Avaliacao avaliacao : lsAvaliacao) {
                    avaliacao.setEtapaParametro(null);
                    if(avaliacao.getIndicador()!=null){
                        avaliacao.getIndicador().setEtapas(null);
                        avaliacao.getIndicador().setProfessor(null);
                    }
                    if(avaliacao.getEtapa()!=null){
                        avaliacao.getEtapa().setAtividadeParametros(null);
                        avaliacao.getEtapa().setAvaliacoes(null);
                        avaliacao.getEtapa().setComentarios(null);
                        avaliacao.getEtapa().setEtapaParametros(null);
                        avaliacao.getEtapa().setTurma(null);
                        avaliacao.getEtapa().setIndicadores(null);
                    }
                    if (avaliacao.getProjeto() != null) {
                        avaliacao.getProjeto().setComponentes(null);
                        avaliacao.getProjeto().setEaps(null);
                        avaliacao.getProjeto().setGerente(null);
                        avaliacao.getProjeto().setRequisitos(null);
                        avaliacao.getProjeto().setStakeholders(null);
                        avaliacao.getProjeto().setTermoabertura(null);
                        avaliacao.getProjeto().setTurma(null);
                    }
                }
            }
        }
        return lsAvaliacao;
    }
}
