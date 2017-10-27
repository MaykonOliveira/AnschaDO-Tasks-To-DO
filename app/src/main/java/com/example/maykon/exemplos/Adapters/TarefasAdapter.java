package com.example.maykon.exemplos.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.test.mock.MockContext;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maykon.exemplos.Activitys.AtualizaDadosTarefa;
import com.example.maykon.exemplos.Activitys.MainActivity;
import com.example.maykon.exemplos.Dados.Banco;
import com.example.maykon.exemplos.Modelos.Tarefa;
import com.example.maykon.exemplos.R;
import com.example.maykon.exemplos.Utilidades.CustomFilter;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by maykon on 23/10/17.
 */

public class TarefasAdapter extends RecyclerView.Adapter<TarefasAdapter.TarefasViewHolder> implements Filterable {

    private ArrayList<Tarefa> listaTarefa,filterList;
    CustomFilter filter;

    public TarefasAdapter(ArrayList<Tarefa> tarefas){
        this.listaTarefa = tarefas;
        this.filterList = tarefas;
    }

    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter(filterList,this);
        }
        return filter;
    }

    public ArrayList<Tarefa> getListaTarefa(){
        return this.listaTarefa;
    }

    public ArrayList<Tarefa> getListaFiltrada(){
        return this.filterList;
    }

    public void setListaTarefa(ArrayList<Tarefa> tarefa){
        this.listaTarefa = tarefa;
    }

    public void setListaFiltrada(ArrayList<Tarefa> tarefa){
        this.filterList = tarefa;
    }

    public class TarefasViewHolder extends ViewHolder{
        public TextView titulo,data;
        public ImageButton delete;

        public TarefasViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.text_Titulo);
            data = itemView.findViewById(R.id.text_Data);
            delete = (ImageButton) itemView.findViewById(R.id.button_Remove);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delete(getAdapterPosition(),v);
                }
            });

            titulo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    atualizaDadosActivity(v,getAdapterPosition());
                }
            });

            data.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    atualizaDadosActivity(v,getAdapterPosition());
                }
            });
        }
    }

    public void atualizaDadosActivity(View v,int position){

        Bundle bundle = new Bundle();
        Tarefa tarefa = listaTarefa.get(position);
        bundle.putString("ID", String.valueOf(tarefa.getId()));
        bundle.putString("TITULO", tarefa.getTitulo());
        bundle.putString("DATA", tarefa.getData());
        bundle.putString("HORA", tarefa.getHora());
        bundle.putString("PERIODICIDADE", tarefa.getPeriodicidade());
        bundle.putString("CRITICIDADE", tarefa.getCriticidade());
        Intent intent = new Intent(v.getContext(), AtualizaDadosTarefa.class);
        intent.putExtras(bundle);
        v.getContext().startActivity(intent);
    }

    public void delete(int position,View v) {

        Banco crud = new Banco(v.getContext());
        crud.deleteTarefa(listaTarefa.get(position));
        listaTarefa.remove(position);
        notifyItemRemoved(position);

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
        holder.data.setText(tarefa.getData() + ", " + tarefa.getHora());
        holder.getAdapterPosition();
    }

    @Override
    public int getItemCount() {
        return this.listaTarefa.size();
    }
}
