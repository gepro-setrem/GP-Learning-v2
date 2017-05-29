package br.org.gdt.bll;

import br.org.gdt.dao.PessoaDAO;
import br.org.gdt.enumerated.Role;
import br.org.gdt.model.Avaliacao;
import br.org.gdt.model.Etapa;
import br.org.gdt.model.Login;
import br.org.gdt.model.Turma;
import br.org.gdt.model.Pessoa;
import br.org.gdt.model.Projeto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pessoaBLL")
public class PessoaBLL extends BLL<Pessoa> {

    @Autowired
    private PessoaDAO dao;
    @Autowired
    private LoginBLL loginBLL;
    @Autowired
    private AvaliacaoBLL avaliacaoBLL;
    @Autowired
    private EtapaBLL etapaBLL;

    public List<Pessoa> findbyProjeto(Projeto projeto) {
        List<Pessoa> lsPessoa = new ArrayList<>();
        if (projeto != null && projeto.getId() > 0) {
            lsPessoa = dao.findbyProjeto(projeto);
        }
        return lsPessoa;
    }

    public List<Pessoa> findbyTurmaUsers(Turma turma, Role role, String search) {
        List<Pessoa> lsPessoa = new ArrayList<>();
        if (turma != null && turma.getId() > 0) {
            lsPessoa = dao.findbyTurmaUsers(turma, role, search);
        }
        return lsPessoa;
    }

    public List<Pessoa> findbyTurma(Turma turma) {
        List<Pessoa> lsPessoa = new ArrayList<>();
        if (turma != null && turma.getId() > 0) {
            lsPessoa = dao.findbyTurma(turma);
        }
        return lsPessoa;
    }

    public Pessoa findbyEmail(String email) {
        if (email != null && !email.isEmpty()) {
            return dao.findbyEmail(email);
        }
        return null;
    }

    public List<Pessoa> findbyProfessor(Pessoa professor) {
        List<Pessoa> lsPessoa = new ArrayList<>();
        if (professor != null && professor.getId() > 0) {
            lsPessoa = dao.findbyProfessor(professor);
            for (Pessoa pessoa : lsPessoa) {
                Login login = loginBLL.findbyPessoa(pessoa);
                pessoa.setLogin(login);
            }
        }
        return lsPessoa;
    }

    public byte[] findbyImagem(int id) {
        byte[] imagem = new byte[0];
        if (id > 0) {
            imagem = dao.findbyImagem(id);
        }
        return imagem;
    }

    public int findNivel(int total) {
        int nivel;
        if (total < 15) {
            nivel = 1;
        } else if (total < 30) {
            nivel = 2;
        } else if (total < 50) {
            nivel = 3;
        } else if (total < 75) {
            nivel = 4;
        } else if (total < 100) {
            nivel = 5;
        } else if (total < 150) {
            nivel = 6;
        } else if (total < 250) {
            nivel = 7;
        } else if (total < 350) {
            nivel = 8;
        } else if (total < 500) {
            nivel = 9;
        } else {
            nivel = 10;
        }
        return nivel;
    }

    public int findPontuacao(Pessoa pessoa) {
        int Pontuacao = 0;
        if (pessoa != null && pessoa.getTurma() != null) {
            List<Etapa> lsEtapa = etapaBLL.findbyTurma(pessoa.getTurma());
            if (lsEtapa != null && lsEtapa.size() > 0) {
                for (Etapa etapa : lsEtapa) {
                    int valor = 0;
                    List<Avaliacao> lsAvaliacao = avaliacaoBLL.findbyEtapa(etapa);
                    if (lsAvaliacao != null && lsAvaliacao.size() > 0) {
                        for (Avaliacao avaliacao : lsAvaliacao) {
                            valor += avaliacao.getValor();
                        }
                        Pontuacao += (valor / lsAvaliacao.size());
                    }
                }
            }
        }
        return Pontuacao;
    }

}
