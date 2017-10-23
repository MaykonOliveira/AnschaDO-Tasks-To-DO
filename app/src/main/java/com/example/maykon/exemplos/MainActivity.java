package com.example.maykon.exemplos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Tarefa> listaTarefas = new ArrayList<>();
    private TarefasAdapter mAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new TarefasAdapter(listaTarefas);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        recyclerView.setHasFixedSize(true);

        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));
        listaTarefas.add(new Tarefa("Maykon","Data"));

        mAdapter.notifyDataSetChanged();
    }

    public void chamaActivityCadastro(View v){
        Intent intent = new Intent(this, CadastroTarefa.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
