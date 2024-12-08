package eda.modelos;

import eda.tads.listas.DoubleLinkedList;
import eda.tads.listas.IlligalHeadCallException;
import eda.tads.listas.IlligalTailCallException;
import eda.tads.listas.SimpleLinkedList;
import eda.tads.th.HashTable;

public class Universidade {

    private HashTable<String, Curso> cursos = new HashTable<>();
    private HashTable<String, Disciplina> disciplinas = new HashTable<>();
    private HashTable<String, Topico> topicos = new HashTable<>();

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

    public HashTable<String, Curso> getCursos() {

        return this.cursos;
    }
    
      public HashTable<String, Disciplina> getDisciplinas() {

        return this.disciplinas;
    }

    public boolean criarDisciplina(String nomeDisciplina, double ects, DoubleLinkedList<Topico> topicos, String nomeCurso, int semestre, boolean obrigatoria) throws IlligalHeadCallException, IlligalTailCallException {
        nomeCurso = nomeCurso.toLowerCase();

        if (!cursos.containsKey(nomeCurso)) {
            // System.out.println("Curso '" + nomeCurso + "' não encontrado!");

            return false;
        }
        Disciplina novaDisciplina = new Disciplina(nomeDisciplina, ects, topicos, semestre, obrigatoria);
        Curso curso = cursos.get(nomeCurso);
        
        if (curso.adicionarDisciplina(novaDisciplina)) {
            disciplinas.put(nomeDisciplina.toLowerCase(), novaDisciplina);
            //topicos.put(nomeDisciplina, novaDisciplina);
            return true;
        } else {
            return false;
        }

    }

    public SimpleLinkedList<Disciplina> consultarDisciplinasPorTopico(String nomeTopico) {
        
        Topico topicoBuscado = new Topico(nomeTopico.toLowerCase());
        SimpleLinkedList<Disciplina> resultado = new SimpleLinkedList<>();

        for (int i = 1; i <= disciplinas.values().length(); i++) {
            try {
                Disciplina disciplina = disciplinas.values().get(i);
               
                //System.err.println(disciplina.getTopicos().length());
                if (disciplina.getTopicos().find(topicoBuscado) != -1) {
                    
                    // Aqui inserimos a disciplina encontrada no resultado
                    resultado.insert(disciplina);
                }
            } catch (IlligalHeadCallException | IlligalTailCallException e) {
               // System.out.println("Erro ao acessar disciplina na posição " + i + ": " + e.getMessage());
            }
        }
        return resultado;
    }

    public SimpleLinkedList<Curso> consultarCursosPorDisciplina(String nomeDisciplina) {
        SimpleLinkedList<Curso> cursosAssociados = new SimpleLinkedList<>();
        SimpleLinkedList<Curso> cursos_somente_valores = cursos.values();
        
        for (int i = 1; i <= cursos_somente_valores.length(); i++) {
            try {
                Curso curso = cursos_somente_valores.get(i);  
                SimpleLinkedList<Disciplina> disciplinas = curso.getDisciplinas();

                
                for (int j = 1; j <= disciplinas.length(); j++) {
                    Disciplina disciplina = disciplinas.get(j);
                    if (disciplina.getNome().equalsIgnoreCase(nomeDisciplina)) {
                        cursosAssociados.insert(curso);  
                        break;  
                    }
                }
            } catch (IlligalHeadCallException | IlligalTailCallException e) {
                //System.out.println("Erro ao acessar curso ou disciplina: " + e.getMessage());
            }
        }

        return cursosAssociados;
    }

    public Disciplina consultarDisciplina(String nomeDisciplina) {
        
        for (int i = 1; i <= cursos.size(); i++) {
            try {
                Curso curso = cursos.values().get(i); 
                for (int j = 1; j <= curso.getDisciplinas().length(); j++) {
                    Disciplina disciplina = curso.getDisciplinas().get(j); 
                    if (disciplina.getNome().equalsIgnoreCase(nomeDisciplina)) {
                        return disciplina; 
                    }
                }
            } catch (IlligalHeadCallException | IlligalTailCallException e) {
                
               // System.err.println("Erro ao acessar a lista de cursos ou disciplinas: " + e.getMessage());
            }
        }
        return null; 
    }

}


/*

public SimpleLinkedList<Disciplina> consultarDisciplinasPorTopico(String nomeTopico) {
        
        Topico topicoBuscado = new Topico(nomeTopico.toLowerCase());
        SimpleLinkedList<Disciplina> resultado = new SimpleLinkedList<>();

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
 */
