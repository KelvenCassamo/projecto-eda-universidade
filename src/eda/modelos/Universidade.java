
package eda.modelos;

import eda.tads.listas.DoubleLinkedList;
import eda.tads.listas.IlligalHeadCallException;
import eda.tads.listas.IlligalTailCallException;
import eda.tads.listas.SimpleLinkedList;
import eda.tads.th.HashTable;


public class Universidade {

    private HashTable<String, Curso> cursos = new HashTable<>();

    public boolean adicionarCurso(String nome) {
        nome = nome.toLowerCase();
        if (!cursos.containsKey(nome)) {
            cursos.put(nome, new Curso(nome));
            return true;
        }
        return false;
    }
    
     public boolean removerCurso(String nome) {
        nome = nome.toLowerCase();
        if (cursos.containsKey(nome)) {
            cursos.remove(nome);
            return true;
        }
        return false;
    }

    public HashTable<String, Curso> getCursos(){
       
        return this.cursos;
    }
    
    
    public boolean criarDisciplina(String nomeDisciplina, double ects, DoubleLinkedList<Topico> topicos, String nomeCurso, int semestre, boolean obrigatoria) throws IlligalHeadCallException, IlligalTailCallException {
        nomeCurso = nomeCurso.toLowerCase();
      
        if (!cursos.containsKey(nomeCurso)) {
           // System.out.println("Curso '" + nomeCurso + "' não encontrado!");
            return false;
        }
        Disciplina novaDisciplina = new Disciplina(nomeDisciplina, ects, topicos, semestre, obrigatoria);
        Curso curso = cursos.get(nomeCurso);
        return curso.adicionarDisciplina(novaDisciplina);
    }

    public SimpleLinkedList<Disciplina> consultarDisciplinasPorTopico(String nomeTopico) {
        // Normalizar o tópico para a comparação
        Topico topicoBuscado = new Topico(nomeTopico.toLowerCase()); 
        SimpleLinkedList<Disciplina> resultado = new SimpleLinkedList<>();

     /*   for (Curso curso : cursos.values()) {
            SimpleLinkedList<Disciplina> disciplinas = curso.getDisciplinas();
            // Começamos de 1 pois a SimpleLinkedList começa na posição 1
            for (int i = 1; i <= disciplinas.length(); i++) {  
                try {
                    Disciplina disciplina = disciplinas.get(i); 
                    if (disciplina.getTopicos().find(topicoBuscado) != -1) {
                        // Aqui inserimos a disciplina encontrada no resultado
                        resultado.insert(disciplina); 
                    }
                } catch (IlligalHeadCallException | IlligalTailCallException e) {
                    System.out.println("Erro ao acessar disciplina na posição " + i + ": " + e.getMessage());
                }
            }
        }*/
     for (int i = 1; i <= cursos.values().length(); i++) {
            try {
                Curso curso = cursos.values().get(i);
                 SimpleLinkedList<Disciplina> disciplinas = curso.getDisciplinas();
                   for (int j = 1; j <= disciplinas.length(); j++) {  
                try {
                    Disciplina disciplina = disciplinas.get(j); 
                    if (disciplina.getTopicos().find(topicoBuscado) != -1) {
                        // Aqui inserimos a disciplina encontrada no resultado
                        resultado.insert(disciplina); 
                    }
                } catch (IlligalHeadCallException | IlligalTailCallException e) {
                    System.out.println("Erro ao acessar disciplina na posição " + j + ": " + e.getMessage());
                }
            }
               // System.out.println("Curso: " + curso.getNome());
            } catch (IlligalHeadCallException | eda.tads.listas.IlligalTailCallException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

}