package br.org.gdt.bll;

import br.org.gdt.dao.TurmaParametroDAO;
import br.org.gdt.enumerated.TurmaParametroType;
import br.org.gdt.model.TurmaParametro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.org.gdt.model.Turma;
import java.util.ArrayList;
import java.util.List;

@Service("turmaParametroBLL")
public class TurmaParametroBLL extends BLL<TurmaParametro> {

    @Autowired
    private TurmaParametroDAO dao;

    public TurmaParametro getParametro(Turma turma, String chave) {
        if (turma != null && turma.getId() > 0 && chave != null && !chave.isEmpty()) {
            return dao.getParametro(turma.getId(), chave);
        } else {
            return null;
        }
    }

    public List<TurmaParametro> findbyTurma(Turma turma) {
        List<TurmaParametro> lsTurmaParametro = new ArrayList<>();
        if (turma != null && turma.getId() > 0) {
            lsTurmaParametro = dao.findbyTurma(turma);
        }
        return lsTurmaParametro;
    }

    public String getTurmaParametroType(TurmaParametroType type) {
        switch (type) {
            case descricaoTelaInicialGerenciamentoProjetos:
                return "Descrição da Tela Inicial de Gerenciamento de Projetos";
            case descricaoTelaGrupoProcessosIniciacao:
                return "Descrição da Tela de Grupo de Processos Iniciação";
            case descricaoTelaTermoAberturaDescricao:
                return "Descrição da Tela de Descrição do Termo de Abertura";
            case descricaoTelaTermoAberturaJustificativa:
                return "Descrição da Tela de Justificativa do Termo de Abertura";
            case descricaoTelaTermoAberturaPremissas:
                return "Descrição da Tela de Premissas do Termo de Abertura";
            case descricaoTelaTermoAberturaRestricoes:
                return "Descrição da Tela de Restrições do Termo de Abertura";
            case descricaoTelaTermoAberturaCronogramaMarcos:
                return "Descrição da Tela do Cronograma de Marcos do Termo de Abertura";
            case descricaoTelaTermoAberturaRequisitos:
                return "Descrição da Tela dos Requisitos do Termo de Abertura";
            case descricaoTelaPartesInteressadas:
                return "Descrição da Tela do Processo de Identificação das Partes Interessadas";
            case descricaoTelaGrupoProcessosPlanejamento:
                return "Descrição da Tela do Grupo de Processos de Planejamento";
            case descricaoTelaCriarPlanoGerenciamentoProjeto:
                return "Descrição da Tela do Processo Criar o Plano de Gerenciamento de Projeto";
            case descricaoTelaCriarPlanoGerenciamentoEscopo:
                return "Descrição da Tela do Processo Criar o Plano de Gerenciamento de Escopo";
            case descricaoTelaColetarRequisitos:
                return "Descrição da Tela do Processo de Coletar os Requisitos";
            case descricaoTelaDefinirEscopo:
                return "Descrição da Tela do Processo de Definir o Escopo";
            case descricaoTelaCriarEAP:
                return "Descrição da Tela do Processo de Criar a EAP";
            case descricaoTelaCriarCronograma:
                return "Descrição da Tela do Processo de Criar o Cronograma";
            default:
                return "";
        }
    }
}
