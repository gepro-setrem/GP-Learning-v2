package com.gplearning.gplearning.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.Models.DaoSession;

import static android.content.Context.MODE_PRIVATE;


public class MetodosPublicos {


    /// metodos de Dados do usuário/Sessão
    public static void SalvaSessao(Context context, String email, Long idExterno) {
        SharedPreferences pref;
        pref = context.getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("user_email", email);
        if (idExterno > 0)
            editor.putLong("user_idExterno", idExterno);
        editor.commit();
    }

    public static void SalvaSessaoId(Context context, Long id) {
        SharedPreferences pref;
        pref = context.getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong("user_id", id);
        editor.commit();
    }


    public static Long SelecionaSessaoId(Context context) {
        SharedPreferences shared = context.getSharedPreferences("login", MODE_PRIVATE);
        Long user_id = shared.getLong("user_id", 0);
        return user_id;
    }

    public static Long SelecionaSessaoidExterno(Context context) {
        SharedPreferences shared = context.getSharedPreferences("login", MODE_PRIVATE);
        Long user_idExterno = shared.getLong("user_idExterno", 0);
        return user_idExterno;
    }

    public static boolean ExisteSessao(Context context) {
        SharedPreferences shared = context.getSharedPreferences("login", MODE_PRIVATE);
        String string_temp = shared.getString("user", null);
        boolean sesssao = string_temp != null;
        return sesssao;
    }

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

}

