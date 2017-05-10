package com.gplearning.gplearning.DAO;


import android.os.Environment;
import android.util.Log;

import com.gplearning.gplearning.Controllers.MainActivity;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.Pessoa;
import com.gplearning.gplearning.Models.PessoaDao;
import com.gplearning.gplearning.Models.Turma;
import com.gplearning.gplearning.Models.TurmaDao;
import com.gplearning.gplearning.Utils.MetodosPublicos;

import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends DefaultDAO {

    String url = UrlDefault;

    public Pessoa Login(DaoSession session, String email, String senha) {
        url += "/login/login/" + email + "/" + senha;
        RestTemplate restTemplate = getResTemplateDefault();

        MetodosPublicos.Log("login", "Url: " + url);
        Pessoa user = restTemplate.getForObject(url, Pessoa.class); //("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);

        //  Pessoa user = null;// = response.
        if (user != null && user.getId() > 0) {
            PessoaDao dao = session.getPessoaDao();
            TurmaDao daoTurma = session.getTurmaDao();
            if (user.getTurma() != null) {
                Turma turma = user.getTurma();
                long idTurma = daoTurma.insert(turma);
                user.setIdTurma(idTurma);
            }

            dao.insert(user);

            return user;
        }
        return null;
    }


    public List<Pessoa> Select() {

        List<Pessoa> lsUsuario = new ArrayList<>();

        // String url = UrlDefau;
        return lsUsuario;
    }









}
