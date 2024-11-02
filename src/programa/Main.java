package programa;

import eda.modelos.Disciplina;
import eda.modelos.Topico;
import eda.modelos.Universidade;
import eda.tads.listas.DoubleLinkedList;
import eda.tads.listas.IlligalHeadCallException;
import eda.tads.listas.IlligalTailCallException;
import eda.tads.listas.SimpleLinkedList;

public class Main {

    public static void main(String[] args) throws IlligalHeadCallException, IlligalTailCallException {
        System.out.println("Hora da verdade! Será que vai funcionar? kk!\n\n");
        Universidade universidade = new Universidade();

        // Adiciona cursos
        System.out.println("Adicionar cursos:");
        long startTime = System.nanoTime();
        System.out.println(universidade.adicionarCurso("Licenciatura em Desenvolvimento de Software")); // true
        System.out.println(universidade.adicionarCurso("Contabilidade"));       // true
        System.out.println(universidade.adicionarCurso("Licenciatura em Desenvolvimento de Software")); // false, porque está duplicado
        long endTime = System.nanoTime();
        System.out.println("Tempo para adicionar os cursos: " + (endTime - startTime) + " ns");

        // Criar tópicos para as disciplinas
        DoubleLinkedList<Topico> topicosLDS = new DoubleLinkedList<>();
        topicosLDS.insert(new Topico("Fluxogramas"));
        topicosLDS.insert(new Topico("Sistemas de numeração"));
        
        
        DoubleLinkedList<Topico> topicosContabilidade = new DoubleLinkedList<>();
        topicosContabilidade.insert(new Topico("Probabilidades"));
        topicosContabilidade.insert(new Topico("Introdução a estátistica"));

        // Adicionar disciplinas aos cursos
        System.out.println("\nAdicionando disciplinas:");
        startTime = System.nanoTime();
        //Licenciatura em Desenvolvimento de Software
        System.out.println(universidade.criarDisciplina("Lógica de Programação", 6, topicosLDS, "Licenciatura em Desenvolvimento de Software", 1, true)); // true
        //Contabilidade
        System.out.println(universidade.criarDisciplina("Estatística", 5, topicosContabilidade, "Contabilidade", 1, true)); // true
           // Tentar adicionar a mesma disciplina novamente (esperado: false)
        System.out.println(universidade.criarDisciplina("Lógica de Programação", 6, topicosLDS, "Licenciatura em Desenvolvimento de Software", 1, true)); // false

        endTime = System.nanoTime();
        System.out.println("Tempo para adicionar disciplinas: " + (endTime - startTime) + " ns");

  
        // Consultar disciplinas por tópico
        System.out.println("\nConsultando disciplinas por topico 'Fluxogramas':");
        startTime = System.nanoTime();
        SimpleLinkedList<Disciplina> disciplinasProgramacao = universidade.consultarDisciplinasPorTopico("Fluxogramas");
        long consultaTime = System.nanoTime() - startTime;
        System.out.println("%s%d%s".formatted("Topicos encontrados em ", disciplinasProgramacao.length(), " disciplinas."));
        for (int i = 1; i <= disciplinasProgramacao.length(); i++) { // Acesso em 1 a n
            try {
                System.out.println(disciplinasProgramacao.get(i));
            } catch (IlligalHeadCallException | IlligalTailCallException e) {
                System.out.println("Erro ao acessar disciplina: " + e.getMessage());
            }
        }
        System.out.println("Tempo para consultar disciplinas: " + consultaTime + " ns");

        // Consultar disciplinas por tópico que não existe
        System.out.println("\nConsultando disciplinas por topico 'Matrizes':");
        startTime = System.nanoTime();
        SimpleLinkedList<Disciplina> disciplinasFisica = universidade.consultarDisciplinasPorTopico("Matrizes");
        if (disciplinasFisica.length() == 0) {
            System.out.println("Nenhuma disciplina encontrada para o topico 'Matrizes'.");
        }
        endTime = System.nanoTime();
        System.out.println("Tempo para consultar disciplinas por topico 'Matrizes': " + (endTime - startTime) + " ns");
    }
}
