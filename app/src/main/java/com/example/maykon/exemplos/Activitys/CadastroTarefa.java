package com.example.maykon.exemplos.Activitys;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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

    private FloatingActionButton fab;
    private static final String[] mOpcoesCriticidade = { "Alta", "Media", "Baixa" };
    private static final String[] mOpcoesPeriodicidade = { "Diaria", "Semanal", "Mensal", "Anual", "Uma vez" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);

        edit_DataCadastro = (EditText) findViewById(R.id.edit_DataCadastro);
        edit_TituloCadastro = (EditText) findViewById(R.id.edit_TituloCadastro);
        edit_HoraCadastro = (EditText) findViewById(R.id.edit_HoraCadastro);

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

    public void adicionarTarefa(View v){

        Banco crud = new Banco(CadastroTarefa.this);

        String titulo = edit_TituloCadastro.getText().toString();
        String data = edit_DataCadastro.getText().toString();
        String hora = edit_HoraCadastro.getText().toString();
        String periodicidade = spinner_Periodicidade.getSelectedItem().toString();
        String criticidade = spinner_Criticidade.getSelectedItem().toString();

        String resultado = crud.insereDado(titulo,data,hora,periodicidade,criticidade);

        Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
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

            String dt = DateUtils.dateToString(year, month, dayOfMonth);

            edit_DataCadastro.setText(dt);
        }
    }

}
