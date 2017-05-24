package com.gplearning.gplearning.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gplearning.gplearning.Models.Projeto;
import com.gplearning.gplearning.R;

import java.util.ArrayList;
import java.util.List;


public class ProjetoAdapter extends RecyclerView.Adapter {

    private List<Projeto> lsProjeto = new ArrayList<>();
    private Context context;

    public ProjetoAdapter(List<Projeto> lsProjeto, Context ctx) {
        this.lsProjeto = lsProjeto;
        this.context = ctx;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listview_projeto, parent, false);
        ViewHolderAdapter holder = new ViewHolderAdapter(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ViewHolderAdapter holder = (ViewHolderAdapter) viewHolder;
        Projeto prj = lsProjeto.get(position);
        holder.texto.setText(prj.getNome());
        holder.texto3.setText(prj.getEstado());
        if (prj.getGerente() != null) {
            holder.texto2.setText(prj.getGerente().getNome());
        }
    }

    @Override
    public long getItemId(int i) {
        return lsProjeto.get(i).getId();
    }

    @Override
    public int getItemCount() {
        return lsProjeto.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        final TextView texto;
        final TextView texto2;
        final TextView texto3;

        public ViewHolderAdapter(View view) {
            super(view);
            texto = (TextView) view.findViewById(R.id.itemListviewProjetoTexto1);
            texto2 = (TextView) view.findViewById(R.id.itemListviewProjetoTexto2);
            texto3 = (TextView) view.findViewById(R.id.itemListviewProjetoTexto3);
        }

    }
}
