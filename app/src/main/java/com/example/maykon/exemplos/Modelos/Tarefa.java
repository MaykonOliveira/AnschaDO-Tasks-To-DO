package com.example.maykon.exemplos.Modelos;

/**
 * Created by maykon on 23/10/17.
 */

public class Tarefa {

    private int id;
    private String titulo;
    private String data;
    private String hora;
    private String periodicidade;
    private String criticidade;

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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    public String getCriticidade() {
        return criticidade;
    }

    public void setCriticidade(String criticidade) {
        this.criticidade = criticidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
