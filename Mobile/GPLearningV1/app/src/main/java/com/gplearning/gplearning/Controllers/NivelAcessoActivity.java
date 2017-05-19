package com.gplearning.gplearning.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gplearning.gplearning.Enums.Fragments;
import com.gplearning.gplearning.R;
import com.gplearning.gplearning.Utils.MetodosPublicos;

public class NivelAcessoActivity extends AppCompatActivity {

    private boolean atualizaHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_acesso);
    }

    public void AcessoAluno(View view) {
        SetAcessoAluno();
        ChangeView(view);
    }

    public void AcessoProfessor(View view) {
        SetAcessoProfessor();
        ChangeView(view);
    }

    private void ChangeView(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("PAGE", Fragments.projetos.toString());
        if (atualizaHeader)
            intent.putExtra("LOGIN", "");
        startActivity(intent);
        finish();
        overridePendingTransition(R.animator.push_left_in, R.animator.push_left_out);
    }

    public void SetAcessoAluno() {
        MetodosPublicos.SalvaModoAcessoAluno(this);
    }

    public void SetAcessoProfessor() {
        MetodosPublicos.SalvaModoAcessoProfessor(this);
    }


}
