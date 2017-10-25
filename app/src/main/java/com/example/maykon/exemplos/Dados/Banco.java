package com.example.maykon.exemplos.Dados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.maykon.exemplos.Modelos.Tarefa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maykon on 24/10/17.
 */

public class Banco extends SQLiteOpenHelper {

    private static final String TABELA = "TAREFAS";
    private static final String ID = "_ID";
    private static final String TITULO = "TITULO";
    private static final String DATA = "DATA";
    private static final String HORA = "HORA";
    private static final String PERIODICIDADE = "PERIODICIDADE";
    private static final String CRITICIDADE = "CRITICIDADE";

    public Banco(Context context) {
        super(context, TABELA,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABELA + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITULO + " TEXT,"
                + DATA + " TEXT,"
                + HORA + " TEXT,"
                + PERIODICIDADE + " TEXT,"
                + CRITICIDADE + " TEXT"
                +");";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA + ";");
        onCreate(db);
    }

    public String insereDado(String titulo, String data, String hora,String periodicidade,String criticidade){

        ContentValues valores;
        long resultado;

        SQLiteDatabase db = this.getWritableDatabase();
        valores = new ContentValues();

        valores.put(TITULO, titulo);
        valores.put(DATA, data);
        valores.put(HORA, hora);
        valores.put(PERIODICIDADE, periodicidade);
        valores.put(CRITICIDADE, criticidade);

        resultado = db.insert(TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
    }

    public List<Tarefa> obterTarefas(){
        List<Tarefa> listaTarefas = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABELA +";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Tarefa tarefa = new Tarefa();

                tarefa.setId(Integer.parseInt(cursor.getString(0)));
                tarefa.setTitulo(cursor.getString(1));
                tarefa.setData(cursor.getString(2));
                tarefa.setHora(cursor.getString(3));
                tarefa.setPeriodicidade(cursor.getString(4));
                tarefa.setCriticidade(cursor.getString(5));

                listaTarefas.add(tarefa);
            } while (cursor.moveToNext());
        }
        return listaTarefas;
    }

    public void deleteTarefa(Tarefa tarefa) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA, ID + " = ?",
                new String[] { String.valueOf(tarefa.getId()) });
        db.close();
    }

    public int updateTarefa(Tarefa tarefa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITULO, tarefa.getTitulo());
        values.put(DATA, tarefa.getData());
        values.put(HORA, tarefa.getHora());
        values.put(PERIODICIDADE, tarefa.getPeriodicidade());
        values.put(CRITICIDADE, tarefa.getCriticidade());

        // updating row
        return db.update(TABELA, values, ID + " = ?",
                new String[] { String.valueOf(tarefa.getId()) });
    }

}
