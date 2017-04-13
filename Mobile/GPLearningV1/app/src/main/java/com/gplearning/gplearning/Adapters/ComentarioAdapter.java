package com.gplearning.gplearning.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.R;

import java.util.ArrayList;
import java.util.List;


public class ComentarioAdapter extends RecyclerView.Adapter {

    private List<Comentario> lsComentario = new ArrayList<>();
    private Context context;
    java.text.DateFormat dateFormat;

    public ComentarioAdapter(List<Comentario> lsComentario, Context ctx) {
        this.lsComentario = lsComentario;
        this.context = ctx;
        this.dateFormat = android.text.format.DateFormat.getDateFormat(context.getApplicationContext());
    }

//    @Override
//    public int getCount() {
//        return lsComentario.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//
//        return lsComentario.get(i);
//    }

    public void swap(List<Comentario> comentarios){
        lsComentario.clear();
        lsComentario.addAll(comentarios);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listview_comentario, parent, false);
        ViewHolderAdapter holder = new ViewHolderAdapter(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolderAdapter holder = (ViewHolderAdapter) viewHolder;
        Comentario comentario = lsComentario.get(position);
        holder.texto.setText(comentario.getTexto());
        holder.data.setText(dateFormat.format(comentario.getCriacao()));
    }

    @Override
    public long getItemId(int i) {
        return lsComentario.get(i).get_id();
    }

    @Override
    public int getItemCount() {
        return lsComentario.size();
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup viewGroup) {
//        View view;
//        ViewHolder holder;
//
//        if (convertView == null) {
//            view = LayoutInflater.from(context).inflate(R.layout.item_listview_comentario, viewGroup, false);///.getLayoutInflater().inflate(R.layout.item_listview_comentario, viewGroup, false);
//            holder = new ViewHolder(view);
//            view.setTag(holder);
//        } else {
//            view = convertView;
//            holder = (ViewHolder) view.getTag();
//        }
//        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context.getApplicationContext());
//
//        Comentario comentario = (Comentario) getItem(position);
//        if (comentario != null) {
//            holder.texto.setText(comentario.getTexto());
//            holder.data.setText(dateFormat.format(comentario.getCriacao()));
//        }
//
//        return view;
//    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        final TextView texto;
        final TextView usuario;
        final TextView data;

        public ViewHolderAdapter(View view) {
            super(view);
            texto = (TextView) view.findViewById(R.id.itemListviewComentTexto);
            usuario = (TextView) view.findViewById(R.id.itemListviewComentNome);
            data = (TextView) view.findViewById(R.id.itemListviewComentData);
        }

    }
}
