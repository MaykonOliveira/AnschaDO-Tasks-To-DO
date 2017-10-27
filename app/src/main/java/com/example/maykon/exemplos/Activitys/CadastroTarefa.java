package com.example.maykon.exemplos.Activitys;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class CadastroTarefa extends AppCompatActivity {

    private EditText edit_TituloCadastro;
    private EditText edit_DataCadastro;
    private EditText edit_HoraCadastro;

    private Spinner spinner_Criticidade;
    private Spinner spinner_Periodicidade;

    private static final String[] mOpcoesCriticidade = { "Alta", "Media", "Baixa" };
    private static final String[] mOpcoesPeriodicidade = { "Diaria", "Semanal", "Mensal", "Anual", "Uma vez" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cadastro);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);

        edit_DataCadastro = (EditText) findViewById(R.id.edit_DataCadastro);
        edit_TituloCadastro = (EditText) findViewById(R.id.edit_TituloCadastro);
        edit_HoraCadastro = (EditText) findViewById(R.id.edit_HoraCadastro);

        spinner_Criticidade = (Spinner) findViewById(R.id.spinner_Criticidade);
        spinner_Periodicidade = (Spinner) findViewById(R.id.spinner_Periodicidade);

        ArrayAdapter<String> adapterCriticidade = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mOpcoesCriticidade);
        spinner_Criticidade.setAdapter(adapterCriticidade);

        ArrayAdapter<String> adapterPeriodicidade = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mOpcoesPeriodicidade);
        spinner_Periodicidade.setAdapter(adapterPeriodicidade);
    }

    public void capturaHora(View v){
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(CadastroTarefa.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                edit_HoraCadastro.setText(selectedHour + ":" + selectedMinute);
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
        mDatePicker = new DatePickerDialog(CadastroTarefa.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                selectedmonth = selectedmonth + 1;
                String formatado = DateUtils.dateToString(selectedyear,selectedmonth,selectedday);
                edit_DataCadastro.setText(formatado);
            }
        }, mYear, mMonth, mDay);
        mDatePicker.setTitle("Selecione a Data");
        mDatePicker.show();
    }

    public void adicionarTarefa(View v){

        Banco crud = new Banco(CadastroTarefa.this);
        Boolean faltaCampos = false;
        String erros = "Você não nos informou ";

        String titulo = edit_TituloCadastro.getText().toString();
        String data = edit_DataCadastro.getText().toString();
        String hora = edit_HoraCadastro.getText().toString();
        String periodicidade = spinner_Periodicidade.getSelectedItem().toString();
        String criticidade = spinner_Criticidade.getSelectedItem().toString();

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
            String resultado = crud.insereDado(titulo, data, hora, periodicidade, criticidade);
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), erros, Toast.LENGTH_LONG).show();
        }
    }
}
