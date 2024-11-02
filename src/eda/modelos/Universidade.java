
package eda.modelos;

import eda.tads.listas.DoubleLinkedList;
import eda.tads.listas.IlligalHeadCallException;
import eda.tads.listas.IlligalTailCallException;
import eda.tads.listas.SimpleLinkedList;
import java.util.HashMap;
import java.util.Map;

public class Universidade {

    private Map<String, Curso> cursos = new HashMap<>();

    public boolean adicionarCurso(String nome) {
        nome = nome.toLowerCase();
        if (!cursos.containsKey(nome)) {
            cursos.put(nome, new Curso(nome));
            return true;
        }
        return false;
    }

    public boolean criarDisciplina(String nomeDisciplina, int ects, DoubleLinkedList<Topico> topicos, String nomeCurso, int semestre, boolean obrigatoria) throws IlligalHeadCallException, IlligalTailCallException {
        nomeCurso = nomeCurso.toLowerCase();
        if (!cursos.containsKey(nomeCurso)) {
            System.out.println("Curso '" + nomeCurso + "' não encontrado!");
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

        for (Curso curso : cursos.values()) {
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
        }
        return resultado;
    }

}