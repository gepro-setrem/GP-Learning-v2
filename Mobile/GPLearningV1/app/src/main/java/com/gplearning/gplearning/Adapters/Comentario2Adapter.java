package com.gplearning.gplearning.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gplearning.gplearning.Models.Comentario;
import com.gplearning.gplearning.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class Comentario2Adapter extends BaseAdapter {

    private List<Comentario> lsComentario = new ArrayList<>();
    private Context context;

    public Comentario2Adapter(List<Comentario> lsComentario, Context ctx) {
        this.lsComentario = lsComentario;
        this.context = ctx;
    }

    @Override
    public int getCount() {
        return lsComentario.size();
    }

    @Override
    public Object getItem(int i) {

        return lsComentario.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lsComentario.get(i).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view;
        ViewHolder holder;

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_listview_comentario, viewGroup, false);///.getLayoutInflater().inflate(R.layout.item_listview_comentario, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context.getApplicationContext());

        Comentario comentario = (Comentario) getItem(position);
        if (comentario != null) {
            holder.texto.setText(comentario.getDescricao());
            holder.data.setText(dateFormat.format(comentario.getCriacao()));
        }

        return view;
    }

    public class ViewHolder {

        final TextView texto;
        final TextView usuario;
        final TextView data;

        public ViewHolder(View view) {
            texto = (TextView) view.findViewById(R.id.itemListviewComentTexto);
            usuario = (TextView) view.findViewById(R.id.itemListviewComentNome);
            data = (TextView) view.findViewById(R.id.itemListviewComentData);
        }

    }
}
