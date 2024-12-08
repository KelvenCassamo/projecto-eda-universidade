/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eda.modelos;

import eda.tads.listas.DoubleLinkedList;
import java.util.List;
import java.util.Objects;

public class Disciplina {

    private String nome;
    private double ects;
    private DoubleLinkedList<Topico> topicos; 
    private int semestre;
    private boolean obrigatoria;

    public Disciplina(String nome, double ects, DoubleLinkedList<Topico> topicos, int semestre, boolean obrigatoria) {
        this.nome = nome.toLowerCase();  
        this.ects = ects;
        this.topicos = topicos;
        this.semestre = semestre;
        this.obrigatoria = obrigatoria;
    }

    public String getNome() {
        return nome;
    }

    public double getEcts() {
        return ects;
    }

    public void setEcts(double ects) {
        this.ects = ects;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public boolean isObrigatoria() {
        return obrigatoria;
    }

    public void setObrigatoria(boolean obrigatoria) {
        this.obrigatoria = obrigatoria;
    }

    public DoubleLinkedList<Topico> getTopicos() { 
        return topicos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Disciplina disciplina = (Disciplina) o;
        return ects == disciplina.ects && semestre == disciplina.semestre
                && obrigatoria == disciplina.obrigatoria
                && Objects.equals(nome, disciplina.nome)
                && Objects.equals(topicos, disciplina.topicos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, ects, semestre, obrigatoria, topicos);
    }

    @Override
    public String toString() {
        return "Disciplina{"
                + "semestre=" + semestre
                + ", tipo=" + (obrigatoria == true ? "Obrigatória" : "Opcional")
                + ", curso='" + nome + '\''
                + ", creditos=" + ects
                + ", nome='" + nome + '\''
                + ", topicos=" + topicos
                + '}';
    }
}
