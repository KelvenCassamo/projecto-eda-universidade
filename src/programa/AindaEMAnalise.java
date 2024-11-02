package programa;


//DECIDIMOS DESCARTAR ESTA ABORDAGEM, POIS O TEMPO DE EXECUÇÃO FOI MAIS DEMORADA.


/*
package programa;

import eda.tads.listas.IlligalHeadCallException;
import eda.tads.listas.IlligalTailCallException;
import eda.tads.listas.SimpleLinkedList;
import eda.tads.listas.DoubleLinkedList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


class Topico {
    private String nome;

    public Topico(String nome) {
        this.nome = nome.toLowerCase();  
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topico topico = (Topico) o;
        return Objects.equals(nome, topico.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}


class Disciplina {
    private String nome;
    private int ects;
    private SimpleLinkedList<Topico> topicos;
    private int semestre;
    private boolean obrigatoria;

    public Disciplina(String nome, int ects, SimpleLinkedList<Topico> topicos, int semestre, boolean obrigatoria) {
        this.nome = nome.toLowerCase();  
        this.ects = ects;
        this.topicos = topicos;
        this.semestre = semestre;
        this.obrigatoria = obrigatoria;
    }

    public String getNome() {
        return nome;
    }

    public SimpleLinkedList<Topico> getTopicos() {
        return topicos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
        return nome + " (" + ects + " ECTS, " + (obrigatoria ? "Obrigatória" : "Opcional") + ", Semestre " + semestre + ")";
    }
}


class Curso {
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


class Universidade {
    private Map<String, Curso> cursos = new HashMap<>();

    public boolean adicionarCurso(String nome) {
        nome = nome.toLowerCase();
        if (!cursos.containsKey(nome)) {
            cursos.put(nome, new Curso(nome));
            return true;
        }
        return false;
    }

    public boolean criarDisciplina(String nomeDisciplina, int ects, SimpleLinkedList<Topico> topicos, String nomeCurso, int semestre, boolean obrigatoria) throws IlligalHeadCallException, IlligalTailCallException {
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
        Topico topicoBuscado = new Topico(nomeTopico.toLowerCase());
        SimpleLinkedList<Disciplina> resultado = new SimpleLinkedList<>();

        for (Curso curso : cursos.values()) {
            SimpleLinkedList<Disciplina> disciplinas = curso.getDisciplinas();
            for (int i = 1; i <= disciplinas.length(); i++) {
                try {
                    Disciplina disciplina = disciplinas.get(i);
                    if (disciplina.getTopicos().find(topicoBuscado) != -1) {
                        resultado.insert(disciplina);
                    }
                } catch (Exception e) {
                    System.out.println("Erro ao acessar disciplina na posição " + i + ": " + e.getMessage());
                }
            }
        }
        return resultado;
    }
}


public class Edateste {
    public static void main(String[] args) throws IlligalHeadCallException, IlligalTailCallException {
         System.out.println("Simple!");
        Universidade universidade = new Universidade();

        
        System.out.println("Adicionando cursos:");
        long startTime = System.nanoTime();
        System.out.println(universidade.adicionarCurso("Engenharia Informática")); 
        System.out.println(universidade.adicionarCurso("Engenharia Civil"));       
        System.out.println(universidade.adicionarCurso("Engenharia Informática")); 
        long endTime = System.nanoTime();
        System.out.println("Tempo para adicionar cursos: " + (endTime - startTime) + " ns");

        
        SimpleLinkedList<Topico> topicosProgramacao = new SimpleLinkedList<>();
        topicosProgramacao.insert(new Topico("Programação"));
        topicosProgramacao.insert(new Topico("Estruturas de Dados"));

        SimpleLinkedList<Topico> topicosCalculo = new SimpleLinkedList<>();
        topicosCalculo.insert(new Topico("Cálculo 1"));
        topicosCalculo.insert(new Topico("Matemática"));
        topicosCalculo.insert(new Topico("Programação"));

        
        System.out.println("\nAdicionando disciplinas:");
        startTime = System.nanoTime();
        System.out.println(universidade.criarDisciplina("Programação 1", 6, topicosProgramacao, "Engenharia Informática", 1, true)); 
        System.out.println(universidade.criarDisciplina("Análise 1", 5, topicosCalculo, "Engenharia Civil", 1, true)); 
        endTime = System.nanoTime();
        System.out.println("Tempo para adicionar disciplinas: " + (endTime - startTime) + " ns");

        
        System.out.println(universidade.criarDisciplina("Programação 1", 6, topicosProgramacao, "Engenharia Informática", 1, true)); 

        
        System.out.println("\nConsultando disciplinas por tópico 'Programação':");
        startTime = System.nanoTime();
        SimpleLinkedList<Disciplina> disciplinasProgramacao = universidade.consultarDisciplinasPorTopico("Programação");
        long consultaTime = System.nanoTime() - startTime;
        System.err.println(disciplinasProgramacao.length());
        for (int i = 1; i <= disciplinasProgramacao.length(); i++) {
            try {
                System.out.println(disciplinasProgramacao.get(i));
            } catch (Exception e) {
                System.out.println("Erro ao acessar disciplina: " + e.getMessage());
            }
        }
        System.out.println("Tempo para consultar disciplinas: " + consultaTime + " ns");

        
        System.out.println("\nConsultando disciplinas por tópico 'Física':");
        startTime = System.nanoTime();
        SimpleLinkedList<Disciplina> disciplinasFisica = universidade.consultarDisciplinasPorTopico("Cálculo 1");
        if (disciplinasFisica.length() == 0) {
            System.out.println("Nenhuma disciplina encontrada para o tópico 'Cálculo 1'.");
        }
        endTime = System.nanoTime();
        System.out.println("Tempo para consultar disciplinas 'Física': " + (endTime - startTime) + " ns");
    }
}


*/