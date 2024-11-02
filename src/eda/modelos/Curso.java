
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

    public SimpleLinkedList<Disciplina> getDisciplinas() {
        return disciplinas;
    }
}
