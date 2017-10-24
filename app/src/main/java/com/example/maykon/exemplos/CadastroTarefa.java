package com.example.maykon.exemplos;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class CadastroTarefa extends AppCompatActivity {

    private EditText edit_DataCadastro;
    private Spinner spinner_Criticidade;
    private Spinner spinner_Periodicidade;
    private static final String[] mOpcoesCriticidade = { "Alta", "Média", "Baixa" };
    private static final String[] mOpcoesPeriodicidade = { "Diária", "Semanal", "Mensal", "Anual", "Uma vez" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);

        edit_DataCadastro = (EditText) findViewById(R.id.edit_DataCadastro);
        spinner_Criticidade = (Spinner) findViewById(R.id.spinner_Criticidade);
        spinner_Periodicidade = (Spinner) findViewById(R.id.spinner_Periodicidade);

        ArrayAdapter<String> adapterCriticidade = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mOpcoesCriticidade);
        spinner_Criticidade.setAdapter(adapterCriticidade);

        ArrayAdapter<String> adapterPeriodicidade = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mOpcoesPeriodicidade);
        spinner_Periodicidade.setAdapter(adapterPeriodicidade);

        ExibeDataListener listener = new ExibeDataListener();
        edit_DataCadastro.setOnClickListener(listener);
        edit_DataCadastro.setOnFocusChangeListener(listener);
    }

    private void exibeData(){

        Calendar calendar = Calendar.getInstance();

        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dlg = new DatePickerDialog(this, new SelecionaDataListener(), ano, mes, dia);
        dlg.show();
    }

    private class ExibeDataListener implements View.OnClickListener, View.OnFocusChangeListener{

        @Override
        public void onClick(View v) {
            exibeData();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            exibeData();
        }
    }

    private class SelecionaDataListener implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            String dt = com.example.maykon.exemplos.DateUtils.dateToString(year, month, dayOfMonth);

            edit_DataCadastro.setText(dt);
        }
    }

}
