package programa;

import eda.file.FileUtil;
import eda.modelos.Curso;
import eda.modelos.Disciplina;
import eda.modelos.Topico;
import eda.modelos.Universidade;
import eda.tads.listas.DoubleLinkedList;
import eda.tads.listas.IlligalHeadCallException;
import eda.tads.listas.IlligalTailCallException;
import eda.tads.listas.SimpleLinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewClass {

    public static void main(String[] args) {
        // Criação dos objetos para manipular arquivos
        FileUtil saida = new FileUtil();
        FileUtil entrada = new FileUtil();

        // Abre o arquivo de entrada
        entrada.openFile("contents/fich.in");

        // Criação de uma instância da Universidade
        Universidade universidade = new Universidade();

        // Variável para armazenar o conteúdo a ser escrito no arquivo de saída
        StringBuilder output = new StringBuilder();

        // Processamento de cada linha do arquivo de entrada
        String linha;
        int num_cursos = 0;
        while ((linha = entrada.readNextLine()) != null) {
            if (linha.trim().isEmpty()) {
                continue; // Ignora linhas em branco
            }

            String[] comando = linha.split(" ");  // Separar o comando e seus parâmetros

            switch (comando[0]) {
                case "CC":  // Criação de curso
                    String nomeCurso = comando[1].toLowerCase();
                    if (universidade.adicionarCurso(nomeCurso)) {
                        // System.err.println(nomeCurso);
                        //output.append("Curso ").append(nomeCurso).append(" criado.\n");
                        output.append("Criacao de curso com sucesso.\n\n");

                    } else {
                        output.append("Curso existente.\n\n");
                        //output.append("Curso ").append(nomeCurso).append(" já existe.\n");
                    }
                    break;

                case "RC":  // Remoção de curso
                    String nomeCursoRemover = comando[1].toLowerCase();
                    if (universidade.removerCurso(nomeCursoRemover)) {
                        output.append("Remocao de curso com sucesso.\n\n");
                        //output.append("Curso ").append(nomeCursoRemover).append(" removido.\n");
                    } else {
                        output.append("Curso inexistente.\n\n");
                        //output.append("Curso ").append(nomeCursoRemover).append(" não encontrado.\n");
                    }
                    break;

                case "CD":  // Criação de disciplina
                    // Verifica se há parâmetros suficientes para o comando "CD"
                    if (comando.length < 4) {
                        output.append("Comando 'CD' inválido. Parâmetros insuficientes na linha inicial.\n");
                        break;
                    }

                    // Parâmetros principais da primeira linha (comando 'CD')
                    int semestre = Integer.parseInt(comando[1]);
                    boolean obrigatoria = comando[2].equals("0") ? false : true;
                    String nomeCursoDisciplina = comando[3].toLowerCase();

                    // Lê a linha seguinte com número de créditos e nome da disciplina
                    String linhaECTS = entrada.readNextLine().trim();

                    if (linhaECTS.isEmpty()) {
                        output.append("Erro: Linha de ECTS não encontrada.\n");
                        break;
                    }

                    String[] parts = linhaECTS.split(" ");

                    double ects = 0;
                    try {
                        ects = Double.parseDouble(parts[0]);  // Primeiro valor é o ECTS
                    } catch (NumberFormatException e) {
                        output.append("Erro: Valor de ECTS inválido: ").append(parts[0]).append("\n");
                        break;
                    }

                    String nomeDisciplina = parts[1];  // Nome da disciplina (segundo valor da linha)

                    // Agora, vamos ler os tópicos (permitindo mais de 4 tópicos, se necessário)
                    DoubleLinkedList<Topico> topicos = new DoubleLinkedList<>();
                    for (int i = 0; i < 4; i++) {
                        try {
                            String linhaTopico = entrada.readNextLine().trim();
                            if (linhaTopico.isEmpty()) {
                                output.append("Erro: Falta de tópico após a linha do comando 'CD'.\n");
                                break;
                            }
                            if (topicos.find(new Topico(linhaTopico)) != -1) {
                                // Adiciona o tópico à lista
                                topicos.insert(new Topico(linhaTopico));
                            } else {
                                output.append("Sequencia de topicos com repeticoes.\n\n");
                                break;
                            }

                        } catch (IlligalHeadCallException | IlligalTailCallException ex) {
                            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    // Tenta criar a disciplina na universidade
                    try {
                        if (universidade.criarDisciplina(nomeDisciplina, (double) ects, topicos, nomeCursoDisciplina, semestre, obrigatoria)) {
                            //System.err.println(true);

                            output.append("Criacao de disciplina com sucesso.\n\n");
//  output.append("Disciplina ").append(nomeDisciplina).append(" criada no curso ").append(nomeCursoDisciplina).append(".\n");
                        } else {
                            //System.err.println(universidade.getCursos().size());
                            if (!universidade.getCursos().containsKey(nomeCursoDisciplina)) {
                                output.append("Curso inexistente.\n\n");
                            } else if (universidade.getCursos().get(nomeCursoDisciplina).existeDisciplina(new Disciplina(nomeDisciplina, ects, topicos, semestre, obrigatoria))) {
                                output.append("Disciplina existente.\n\n");
                            }

//output.append("Falha ao criar disciplina ").append(nomeDisciplina).append(" no curso ").append(nomeCursoDisciplina).append(".\n");
                        }
                    } catch (IlligalHeadCallException | IlligalTailCallException e) {
                        System.err.println(e);
                        output.append("Erro ao criar disciplina: ").append(e.getMessage()).append("\n");
                    }
                    break;

                case "RD":  // Remoção de disciplina
                    // Verifica se o comando RD tem o curso na primeira linha
                    if (comando.length < 2) {
                        output.append("Comando 'RD' inválido. Parâmetro do curso ausente.\n");
                        break;
                    }

                    // Lê o nome do curso da primeira linha
                    String nomeCursoRemoverDisciplina = comando[1].toLowerCase();

                    // Lê o nome da disciplina da próxima linha
                    String nomeDisciplinaRemover = entrada.readNextLine().trim();

                    // Verifica se a linha da disciplina está vazia
                    if (nomeDisciplinaRemover.isEmpty()) {
                        output.append("Comando 'RD' inválido. Nome da disciplina ausente.\n");
                        break;
                    }

                    // Remover disciplina do curso
                    Curso cursoParaRemoverDisciplina = universidade.getCursos().get(nomeCursoRemoverDisciplina);
                    if (cursoParaRemoverDisciplina != null) {
                        SimpleLinkedList<Disciplina> disciplinas = cursoParaRemoverDisciplina.getDisciplinas();
                        boolean disciplinaEncontrada = false;

                        // Verifica se a disciplina existe no curso
                        for (int i = 1; i <= disciplinas.length(); i++) {
                            try {
                                Disciplina disciplina = disciplinas.get(i);
                                if (disciplina.getNome().equals(nomeDisciplinaRemover)) {
                                    disciplinas.remove(i);  // Remove disciplina
                                    disciplinaEncontrada = true;
                                    output.append("Remocao de disciplina com sucesso.\n");
                                    break;
                                }
                            } catch (IlligalHeadCallException | IlligalTailCallException e) {
                                output.append("Erro ao acessar disciplina na posição ").append(i).append(": ").append(e.getMessage()).append("\n");
                            }
                        }

                        // Se a disciplina não foi encontrada
                        if (!disciplinaEncontrada) {
                            output.append("Disciplina inexistente.\n\n");
                            //output.append("Disciplina ").append(nomeDisciplinaRemover).append(" não encontrada no curso ").append(nomeCursoRemoverDisciplina).append(".\n");
                        }
                    } else {
                        output.append("Curso inexistente.\n\n");
                        output.append("Curso ").append(nomeCursoRemoverDisciplina).append(" não encontrado.\n");
                    }
                    break;

                case "PP":  // Plano curricular (exibir disciplinas de um curso)
                    String nomeCursoPlano = comando[1].toLowerCase();
                    Curso cursoPlano = universidade.getCursos().get(nomeCursoPlano);
                    if (cursoPlano != null) {
                        SimpleLinkedList<Disciplina> disciplinas = cursoPlano.getDisciplinas();
                        output.append("Plano curricular do curso ").append(nomeCursoPlano).append(":\n");
                        for (int i = 1; i <= disciplinas.length(); i++) {
                            try {
                                Disciplina disciplina = disciplinas.get(i);
                                output.append(disciplina).append("\n");
                            } catch (IlligalHeadCallException | IlligalTailCallException e) {
                                output.append("Erro ao acessar disciplina na posição ").append(i).append(": ").append(e.getMessage()).append("\n");
                            }
                        }
                    } else {
                        output.append("Curso inexistente.\n\n");
                    }
                    break;

                case "DT":  // Consultar disciplinas por tópico
                    String nomeTopico = comando[1];
                    SimpleLinkedList<Disciplina> disciplinasPorTopico = universidade.consultarDisciplinasPorTopico(nomeTopico);
                    output.append("Disciplinas com o tópico ").append(nomeTopico).append(":\n");
                    if (disciplinasPorTopico.length() == 0) {
                        output.append("Topico inexistente.\n\n");
                    } else {
                        for (int i = 1; i <= disciplinasPorTopico.length(); i++) {
                            try {
                                Disciplina disciplina = disciplinasPorTopico.get(i);
                                output.append(disciplina).append("\n");
                            } catch (IlligalHeadCallException | IlligalTailCallException e) {
                                output.append("Erro ao acessar disciplina na posição ").append(i).append(": ").append(e.getMessage()).append("\n");
                            }
                        }
                    }
                    break;

                default:
                    
                    //output.append("Comando desconhecido: ").append(linha).append("\n");
                    break;
            }
        }

        // Fecha o arquivo de entrada
        entrada.closeFile();

        // Escreve a saída no arquivo de destino
        saida.write(output.toString(), "contents/fichout.txt");
    }
}
