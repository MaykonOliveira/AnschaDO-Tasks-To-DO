package com.example.maykon.exemplos.Activitys;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.maykon.exemplos.Dados.Banco;
import com.example.maykon.exemplos.R;
import com.example.maykon.exemplos.Utilidades.DateUtils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class AtualizaDadosTarefa extends AppCompatActivity {

    private EditText edit_TituloAtualizacao;
    private EditText edit_DataAtualizacao;
    private EditText edit_HoraAtualizacao;

    private Spinner spinner_CriticidadeAtualizacao;
    private Spinner spinner_PeriodicidadeAtualizacao;

    FloatingActionButton fab_Atalizacao;

    String id;

    private static final String[] mOpcoesCriticidade = { "Alta", "Media", "Baixa" };
    private static final String[] mOpcoesPeriodicidade = { "Diaria", "Semanal", "Mensal", "Anual", "Uma vez" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualiza_tarefa);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_atualizacao);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        edit_TituloAtualizacao = (EditText) findViewById(R.id.edit_TituloAtualizacao);
        edit_DataAtualizacao = (EditText) findViewById(R.id.edit_DataAtualizacao);
        edit_HoraAtualizacao = (EditText) findViewById(R.id.edit_HoraAtualizacao);
        spinner_CriticidadeAtualizacao = (Spinner) findViewById(R.id.spinner_CriticidadeAtualizacao);
        spinner_PeriodicidadeAtualizacao = (Spinner) findViewById(R.id.spinner_PeriodicidadeAtualizacao);
        fab_Atalizacao = (FloatingActionButton) findViewById(R.id.fab_AtualizarTarefa);

        ArrayAdapter<String> adapterCriticidade = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mOpcoesCriticidade);
        ArrayAdapter<String> adapterPeriodicidade = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mOpcoesPeriodicidade);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        id =  bundle.getString("ID");
        String titulo = bundle.getString("TITULO");
        String data = bundle.getString("DATA");
        String hora = bundle.getString("HORA");
        String periodicidade = bundle.getString("PERIODICIDADE");
        String criticidade = bundle.getString("CRITICIDADE");

        int idCriticidade = adapterCriticidade.getPosition(criticidade);
        int idPeriodicidade = adapterPeriodicidade.getPosition(periodicidade);

        edit_TituloAtualizacao.setText(titulo);
        edit_DataAtualizacao.setText(data);
        edit_HoraAtualizacao.setText(hora);
        spinner_PeriodicidadeAtualizacao.setSelection(idPeriodicidade);
        spinner_CriticidadeAtualizacao.setSelection(idCriticidade);

        spinner_CriticidadeAtualizacao.setAdapter(adapterCriticidade);
        spinner_PeriodicidadeAtualizacao.setAdapter(adapterPeriodicidade);

        fab_Atalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atualizaTarefa(view);
            }
        });
    }

    public void atualizaTarefa(View v){

        Banco crud = new Banco(AtualizaDadosTarefa.this);
        Boolean faltaCampos = false;
        String erros = "Você não nos informou ";

        String titulo = edit_TituloAtualizacao.getText().toString();
        String data = edit_DataAtualizacao.getText().toString();
        String hora = edit_HoraAtualizacao.getText().toString();
        String periodicidade = spinner_PeriodicidadeAtualizacao.getSelectedItem().toString();
        String criticidade = spinner_CriticidadeAtualizacao.getSelectedItem().toString();

        if(titulo.isEmpty()){
            if(!faltaCampos)
                erros = erros + "o Titulo de sua tarefa";
            else
                erros = erros + ", Titulo da tarefa";

            faltaCampos = true;
        }

        if (data.isEmpty()){
            if(!faltaCampos)
                erros = erros + "a Data de sua tarefa";
            else
                erros = erros + ", Data da tarefa";

            faltaCampos = true;
        }

        if(hora.isEmpty()){
            if(!faltaCampos)
                erros = erros + "a Hora de sua tarefa";
            else
                erros = erros + ", Hora da tarefa";

            faltaCampos = true;
        }

        if(!faltaCampos) {
            int resultado = crud.updateTarefa(id,titulo,data,hora,criticidade,periodicidade);
            if (resultado != 0)
                Toast.makeText(getApplicationContext(), "Registro atualizado com sucesso!", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Ocorreu um erro durante a atualização do registro!", Toast.LENGTH_LONG).show();
            finish();

        } else {
            Toast.makeText(getApplicationContext(), erros, Toast.LENGTH_LONG).show();
        }
    }

    public void capturaHora(View v){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AtualizaDadosTarefa.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String horaFormatada = String.valueOf(selectedHour);
                String minutoFormatado = String.valueOf(selectedMinute);

                if (String.valueOf(selectedHour).length() == 1){
                    NumberFormat formatter = new DecimalFormat("00");
                    horaFormatada = formatter.format(selectedHour);
                }

                if (String.valueOf(selectedMinute).length() == 1){
                    NumberFormat formatter = new DecimalFormat("00");
                    minutoFormatado = formatter.format(selectedMinute);
                }

                edit_HoraAtualizacao.setText(horaFormatada + ":" + minutoFormatado);
            }
        }, hour, minute, true);
        mTimePicker.setTitle("Selecione a Hora");
        mTimePicker.show();
    }

    public void capturaData(View v){
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker;
        mDatePicker = new DatePickerDialog(AtualizaDadosTarefa.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                selectedmonth = selectedmonth + 1;
                String formatado = DateUtils.dateToString(selectedyear,selectedmonth,selectedday);
                edit_DataAtualizacao.setText(formatado);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Selecione a Data");
        mDatePicker.show();
    }
}
