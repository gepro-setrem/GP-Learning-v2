package com.gplearning.gplearning.Controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gplearning.gplearning.Enums.Fragments;
import com.gplearning.gplearning.R;

public class NivelAcessoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_acesso);
    }

    public void AcessoAluno(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("PAGE", Fragments.projetos.toString());
        SetAcessoAluno();
        startActivity(intent);
        finish();
        overridePendingTransition(R.animator.push_left_in, R.animator.push_left_out);
    }


       public void SetAcessoAluno(){
        SharedPreferences pref;
        pref = getSharedPreferences("modoAcesso",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("modoAcesso","aluno");
        editor.commit();
    }

    public void SetAcessoProfessor(){
        SharedPreferences pref;
        pref = getSharedPreferences("modoAcesso",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("modoAcesso","professor");
        editor.commit();
    }



}
