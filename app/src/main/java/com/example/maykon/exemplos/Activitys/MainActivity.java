package com.example.maykon.exemplos.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.maykon.exemplos.Adapters.TarefasAdapter;
import com.example.maykon.exemplos.Dados.Banco;
import com.example.maykon.exemplos.Modelos.Tarefa;
import com.example.maykon.exemplos.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private RecyclerView recyclerView;
    private TarefasAdapter mAdapter;
    Banco crud = new Banco(this);
    private ArrayList<Tarefa> listaTarefas;
    SearchView search_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        this.listaTarefas = (ArrayList<Tarefa>) crud.obterTarefas();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        search_toolbar = (SearchView) findViewById(R.id.action_search);

        mAdapter = new TarefasAdapter(listaTarefas);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        this.listaTarefas = (ArrayList<Tarefa>) crud.obterTarefas();
        mAdapter = new TarefasAdapter(this.listaTarefas);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void chamaActivityCadastro(View v){
        Intent intent = new Intent(this, CadastroTarefa.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        mAdapter.getFilter().filter(query);
        return false;
    }
}
