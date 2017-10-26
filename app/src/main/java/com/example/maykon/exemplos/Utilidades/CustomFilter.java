package com.example.maykon.exemplos.Utilidades;

import android.widget.Filter;

import com.example.maykon.exemplos.Adapters.TarefasAdapter;
import com.example.maykon.exemplos.Modelos.Tarefa;

import java.util.ArrayList;


public class CustomFilter extends Filter{
    TarefasAdapter adapter;
    ArrayList<Tarefa> filterList;
    public CustomFilter(ArrayList<Tarefa> filterList,TarefasAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();

        if(constraint != null && constraint.length() > 0)
        {

            constraint=constraint.toString().toUpperCase();

            ArrayList<Tarefa> filteredPlayers=new ArrayList<>();
            for (int i=0;i<filterList.size();i++)
            {

                if(filterList.get(i).getTitulo().toUpperCase().contains(constraint)||filterList.get(i).getData().toUpperCase().contains(constraint)||filterList.get(i).getHora().toUpperCase().contains(constraint))
                {
                    filteredPlayers.add(filterList.get(i));
                }
            }
            results.count=filteredPlayers.size();
            results.values=filteredPlayers;
        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }
        return results;
    }
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.setListaTarefa((ArrayList<Tarefa>) results.values);

        adapter.notifyDataSetChanged();
    }
}