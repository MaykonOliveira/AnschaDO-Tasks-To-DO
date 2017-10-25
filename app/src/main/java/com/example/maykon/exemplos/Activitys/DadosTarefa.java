package com.example.maykon.exemplos.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.maykon.exemplos.R;

public class DadosTarefa extends AppCompatActivity {

    TextView text_TituloTarefa;
    TextView text_DataTarefa;
    TextView text_HoraTarefa;
    TextView text_PeriodicidadeTarefa;
    TextView textView_CriticidadeTarefa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_tarefa);

        text_TituloTarefa = (TextView) findViewById(R.id.text_TituloTarefa2);
        text_DataTarefa = (TextView) findViewById(R.id.text_DataTarefa2);
        text_HoraTarefa = (TextView) findViewById(R.id.text_HoraTarefa2);
        text_PeriodicidadeTarefa = (TextView) findViewById(R.id.text_Periodicidade2);
        textView_CriticidadeTarefa = (TextView) findViewById(R.id.text_Criticidade2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String titulo = bundle.getString("TITULO");
        String data = bundle.getString("DATA");
        String hora = bundle.getString("HORA");
        String periodicidade = bundle.getString("PERIODICIDADE");
        String criticidade = bundle.getString("CRITICIDADE");

        text_TituloTarefa.setText(titulo);
        text_DataTarefa.setText(data);
        text_HoraTarefa.setText(hora);
        text_PeriodicidadeTarefa.setText(periodicidade);
        textView_CriticidadeTarefa.setText(criticidade);
    }
}
