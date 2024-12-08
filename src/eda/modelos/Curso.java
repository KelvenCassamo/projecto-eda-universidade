package eda.modelos;

import eda.tads.listas.IlligalHeadCallException;
import eda.tads.listas.IlligalTailCallException;
import eda.tads.listas.SimpleLinkedList;
import java.util.ArrayList;
import java.util.List;

public class Curso {

    private String nome;
    private SimpleLinkedList<Disciplina> disciplinas;

    public Curso(String nome) {
        this.nome = nome.toLowerCase();
        this.disciplinas = new SimpleLinkedList<>();
    }

    public String getNome() {
        return nome;
    }

    public boolean adicionarDisciplina(Disciplina disciplina) throws IlligalHeadCallException, IlligalTailCallException {
        // Verificar se a disciplina já existe
        if (disciplinas.find(disciplina) == -1) {
            disciplinas.insert(disciplina);
            return true;
        }
        return false;
    }

   
    public boolean existeDisciplina(Disciplina disciplina) throws IlligalHeadCallException, IlligalTailCallException {
        // Verificar se a disciplina já existe
        if (disciplinas.find(disciplina) == -1) {
           
            return true;
        }
        return false;
    }

    /*  public boolean removerDisciplina(Disciplina disciplina) {
        return disciplinas.remove(disciplina);
    }*/
    public SimpleLinkedList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; 
        }
        Curso curso = (Curso) obj;
        return nome.equals(curso.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode(); 
    }

}
