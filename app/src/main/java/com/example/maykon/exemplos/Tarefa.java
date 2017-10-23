package com.example.maykon.exemplos;

/**
 * Created by maykon on 23/10/17.
 */

public class Tarefa {

    String titulo;
    String data;

    public Tarefa(String titulo,String data){
        this.titulo=titulo;
        this.data=data;
    }

    public String getTitulo(){
        return this.titulo;
    }

    public String getData(){
        return this.data;
    }

    public void setTitulo(String titulo){
        this.titulo=titulo;
    }

    public void setData(String data){
        this.data=data;
    }
}
