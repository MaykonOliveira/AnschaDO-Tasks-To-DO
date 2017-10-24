package com.example.maykon.exemplos.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maykon.exemplos.Modelos.Tarefa;
import com.example.maykon.exemplos.R;

import java.util.ArrayList;

/**
 * Created by maykon on 23/10/17.
 */

public class TarefasAdapter extends RecyclerView.Adapter<TarefasAdapter.TarefasViewHolder> {

    private ArrayList<Tarefa> listaTarefa;

    public TarefasAdapter(ArrayList<Tarefa> tarefas){
        this.listaTarefa = tarefas;
    }

    public class TarefasViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo,data;

        public TarefasViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.text_Titulo);
            data = (TextView) itemView.findViewById(R.id.text_Data);
        }
    }

    @Override
    public TarefasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_linha_tarefa,parent,false);

        return new TarefasViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TarefasViewHolder holder, int position) {
        Tarefa tarefa = this.listaTarefa.get(position);
        holder.titulo.setText(tarefa.getTitulo());
        holder.data.setText(tarefa.getData());
    }

    @Override
    public int getItemCount() {
        return this.listaTarefa.size();
    }
}
