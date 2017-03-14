package com.gplearning.gplearning.Adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gplearning.gplearning.Models.Projeto;
import com.gplearning.gplearning.R;

import java.util.List;

public class ProjetoAdapter extends BaseAdapter{

    private final List<Projeto> lsProjetos;
    private final Context context;

    public ProjetoAdapter(List<Projeto>projetos, Context context){
        this.lsProjetos = projetos;
        this.context = context;
    }


    @Override
    public int getCount() {
        return lsProjetos.size();
    }

    @Override
    public Object getItem(int i) {
        return lsProjetos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return lsProjetos.get(i).get_id();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        View view;
        ViewHolder holder;

        if( convertView == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.item_listview1, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

      //  TextView nome = (TextView) view.findViewById(R.id.itemListviewText1);
      //  TextView email = (TextView) view.findViewById(R.id.itemListviewText2);

        Projeto prj = (Projeto) getItem(i);
        holder.nome.setText(prj.getNome());
        holder.empresa.setText(prj.getEmpresa());

        return view;
    }


    public class ViewHolder {

        final TextView nome;
        final TextView empresa;

        public ViewHolder(View view) {
            nome = (TextView) view.findViewById(R.id.itemListviewText1);
            empresa = (TextView) view.findViewById(R.id.itemListviewText2);
        }

    }
}
