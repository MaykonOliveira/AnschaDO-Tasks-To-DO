package com.example.maykon.exemplos;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

public class CadastroTarefa extends AppCompatActivity {

    private EditText edit_DataCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tarefa);

        edit_DataCadastro = (EditText) findViewById(R.id.edit_DataCadastro);

        ExibeDataListener listener = new ExibeDataListener();
        edit_DataCadastro.setOnClickListener(listener);
        edit_DataCadastro.setOnFocusChangeListener(listener);
    }

    public static void hideKeyboard(Context context, View editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
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
//            Date data = com.example.maykon.exemplos.DateUtils.getDate(year, month, dayOfMonth);

            edit_DataCadastro.setText(dt);
        }
    }

}
