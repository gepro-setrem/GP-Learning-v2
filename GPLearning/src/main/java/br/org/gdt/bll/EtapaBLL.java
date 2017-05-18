package br.org.gdt.bll;

import br.org.gdt.dao.EtapaDAO;
import br.org.gdt.enumerated.EtapaProjeto;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Indicador;
import br.org.gdt.model.Projeto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("etapaBLL")
public class EtapaBLL extends BLL<Etapa> {

    @Autowired
    private EtapaDAO dao;
    @Autowired
    private IndicadorBLL indicadorBLL;

    public List<Etapa> findbyTurma(Turma turma) {
        List<Etapa> lsEtapa = new ArrayList<>();
        if (turma != null && turma.getId() > 0) {
            lsEtapa = dao.findbyTurma(turma);
            for (Etapa etapa : lsEtapa) {
                List<Indicador> lsIndicador = indicadorBLL.findbyEtapa(etapa);
                etapa.setIndicadores(lsIndicador);
                etapa.setTurma(null);
                etapa.setAtividadeParametros(null);
                etapa.setAvaliacoes(null);
                etapa.setComentarios(null);
                etapa.setEtapaParametros(null);
            }
        }
        return lsEtapa;
    }

    public Etapa findbyTurmaEtapa(Turma turma, EtapaProjeto etapaProjeto) {
        Etapa etapa = null;
        if (turma != null && turma.getId() > 0 && etapaProjeto != null) {
            etapa = dao.findbyTurmaEtapa(turma, etapaProjeto);
        }
        return etapa;
    }
    

    public String getEtapaProjeto(EtapaProjeto type) {
        switch (type) {
            case DescricaoProjeto:
                return "Descrição do Projeto";
            case JustificativaProjeto:
                return "Justificativa do Projeto";
            case Premissas:
                return "Listagem de Premissas";
            case Restricoes:
                return "Listagem de Restricões";
            case Marcos:
                return "Cronograma de Marcos";
            case RequisitosTermoAbertura:
                return "Listagem de Requisitos do Termo de Abertura";
            case Stakeholders:
                return "Identificação das Partes Interessadas";
            case PlanoGerenciamentoEscopo:
                return "Planejar o Gerenciamento do Escopo";
            case Requisitos:
                return "Coleta de Requisitos";
            case Escopo:
                return "Definir o Escopo";
            case Eap:
                return "Criar a EAP";
            case Cronograma:
                return "Criar o Cronograma";
            case PlanoGerenciamentoProjeto:
                return "Criar o Plano de Gerenciamento de Projeto";
            default:
                return "";
        }
    }
}
