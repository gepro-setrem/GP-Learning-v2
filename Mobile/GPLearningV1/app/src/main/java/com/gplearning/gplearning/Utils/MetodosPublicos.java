package com.gplearning.gplearning.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.gplearning.gplearning.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


public class MetodosPublicos {
    private static String key_nome = "user_nome";
    private static String key_email = "user_email";
    private static String key_image = "user_imagem";
    private static String key_login = "login";
    private static String key_id = "user_id";
    private static String key_idExterno = "user_id_externo";
    private static String key_modoAcesso = "user_modo_acesso";
    private static String key_acessoAluno = "user_acesso_aluno";
    private static String key_acessoProfessor = "user_acesso_professor";

    private static String key_imagemUser = "imagem_user_";

    private static String key_sync = "last_sync";
    private static String key_sync_comentario = "last_sync_comentario";
    private static String key_sync_atividade = "last_sync_atividade";
    private static String key_sync_projeto = "last_sync_projeto";


    public static final String key_idEtapa = "IdEtapa";
    public static final String key_idProjeto = "IdProjeto";

    /// metodos de Dados do usuário/Sessão
    public static void SalvaSessao(Context context, Long id, String nome, String email, int idExterno) {
        SharedPreferences pref = context.getSharedPreferences(key_login, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        Log("sessao", "idI:" + id + " idEx:" + idExterno + " nome:" + nome + " email:" + email);
        if (id != null)
            editor.putLong(key_id, id);
        else
            editor.putLong(key_id, 0);

        editor.putString(key_nome, nome);
        editor.putString(key_email, email);
        // editor.putString(key_image, imageBytes.toString());

        if (idExterno > 0)
            editor.putInt(key_idExterno, idExterno);
        editor.commit();
    }

    public static void SalvaSessaoId(Context context, Long id) {
        SharedPreferences pref = context.getSharedPreferences(key_login, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key_id, id);
        editor.commit();
    }


    public static String SelecionaSessaoNome(Context context) {
        SharedPreferences shared = context.getSharedPreferences(key_login, MODE_PRIVATE);
        return shared.getString(key_nome, "--");
    }

    public static String SelecionaSessaoEmail(Context context) {
        SharedPreferences shared = context.getSharedPreferences(key_login, MODE_PRIVATE);
        return shared.getString(key_email, "--");
    }

    public static Long SelecionaSessaoId(Context context) {
        SharedPreferences shared = context.getSharedPreferences(key_login, MODE_PRIVATE);
        return shared.getLong(key_id, 0);
    }

    public static int SelecionaSessaoidExterno(Context context) {
        SharedPreferences shared = context.getSharedPreferences(key_login, MODE_PRIVATE);
        int user_idExterno = shared.getInt(key_idExterno, 0);
        return user_idExterno;
    }

    public static void SaveImageUser(Context context, Long id, byte[] imagem) {
        String nomeImagem = key_imagemUser + id + ".jpg";
        saveArrayToSDCard(context, nomeImagem, imagem);
    }

    public static void CarregaimagemPerfil(Context context, ImageView imageView, Long idPessoa) {
        File path = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(path, key_imagemUser + idPessoa + ".jpg");
        if (file != null && file.exists()) {
            MetodosPublicos.Log("IMage", "image :" + file.getAbsolutePath());
            Picasso.with(context).load(file).transform(new CircleTransform()).into(imageView);
        } else {
            Picasso.with(context).load(R.drawable.user_default).transform(new CircleTransform()).into(imageView);
        }
    }

    public static String SelecionaCaminhoImagem(Context context, Long idPessoa) {
        File path = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(path, key_imagemUser + idPessoa + ".jpg");
        if (file != null && file.exists())
            return file.getAbsolutePath();
        return null; // Uri.parse("android.resource://com.gplearning.gplearning/" + R.drawable.user_default).getPath();
    }

    private static void saveArrayToSDCard(Context context, String fileName, byte[] imagem) {
        File path = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File file = new File(path, fileName);
        try {
            OutputStream os = new FileOutputStream(file);
            os.write(imagem);
            os.close();
        } catch (IOException e) {
            Log.w("ExternalStorage", "Error writing", e);
        }
        Log("IMAGE", " salvou a imagem em:" + file.getAbsolutePath());
    }

    private static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    public static boolean ExisteSessao(Context context) {
        SharedPreferences shared = context.getSharedPreferences(key_login, MODE_PRIVATE);
        String string_temp = shared.getString(key_email, null);
        boolean sesssao = string_temp != null;
        return sesssao;
    }

    public static boolean ModoAcessoAluno(Context context) {
        SharedPreferences shared = context.getSharedPreferences(key_modoAcesso, MODE_PRIVATE);
        String modoAcesso = shared.getString(key_modoAcesso, null);
        boolean acessoAluno = modoAcesso != null && modoAcesso.equals(key_acessoAluno);
        return acessoAluno;
    }

    public static void SalvaModoAcessoAluno(Context context) {
        SharedPreferences pref = context.getSharedPreferences(key_modoAcesso, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key_modoAcesso, key_acessoAluno);
        editor.commit();
    }

    public static boolean ExisteModoAcesso(Context context) {
        SharedPreferences shared = context.getSharedPreferences(key_modoAcesso, MODE_PRIVATE);
        String modoAcesso = shared.getString(key_modoAcesso, null);
        boolean acesso = modoAcesso != null;
        return acesso;
    }

    public static void SalvaModoAcessoProfessor(Context context) {
        SharedPreferences pref = context.getSharedPreferences(key_modoAcesso, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key_modoAcesso, key_acessoProfessor);
        editor.commit();
    }


    public static void SalvaUltimaSincronizacao(Context context, Date date) {
        java.text.DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        SharedPreferences pref = context.getSharedPreferences(key_sync, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
//        if (recurso == RecursosEnum.Comentario) {
//            editor.putString(key_sync_comentario, dateFormat.format(date));
//        } else if (recurso == RecursosEnum.atividades) {
//            editor.putString(key_sync_atividade, dateFormat.format(date));
//        }
        editor.putString(key_sync, dateFormat.format(date));
        MetodosPublicos.Log("Data", " new Date:" + dateFormat.format(date));

        editor.commit();
    }

    public static Date SelecionaUltimaSincronizacao(Context context) throws ParseException {
        SharedPreferences shared = context.getSharedPreferences(key_sync, MODE_PRIVATE);
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//        if (recursos == RecursosEnum.Comentario) {
//            String data = shared.getString(key_sync_comentario, null);
//            if (data != null)
//                return formato.parse(data);
//        } else if (recursos == RecursosEnum.Atividades) {
        String data = shared.getString(key_sync, null);
        if (data != null)
            return formato.parse(data);
        return null;
    }

//    private String RetornaKeyRecurso(RecursosEnum recurso) {
//        if (recurso == RecursosEnum.Comentario) {
//            return key_sync_comentario;
//        } else if (recurso == RecursosEnum.Atividades) {
//            return key_sync_atividade;
//        } else if (recurso == RecursosEnum.Projetos) {
//            return key_sync_projeto;
//        }
//        return "";
//    }

    public static final boolean IsConnected(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            Boolean wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            Boolean mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected || mobileConnected) {
                return true;
            }
        }
        return false;
    }


    public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
        private OnItemClickListener mListener;

        public interface OnItemClickListener {
            public void onItemClick(View view, int position);

            public void onLongItemClick(View view, int position);
        }

        GestureDetector mGestureDetector;

        public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
            mListener = listener;
            mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && mListener != null) {
                        mListener.onLongItemClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
            View childView = view.findChildViewUnder(e.getX(), e.getY());
            if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
                mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
                return true;
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        }
    }


//    public DaoSession NovaInstanciaBanco(Context context){
//       return  ((App)context.getApplication()).getDaoSession();
//    }

    public static DateFormat GetDateFormatDefault() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    }

    public static String DateToString(Date data) {
        if (data != null) {
            DateFormat format = GetDateFormatDefault();
            return format.format(data);
        }
        return "";
    }


    public static void Log(String lg, String valor) {
        Log.i(lg + " gpla ", valor);
    }
}

