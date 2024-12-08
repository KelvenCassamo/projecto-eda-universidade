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

public class Main {

    public static void main(String[] args) {
        
        final String ARQUIVO_DE_ENTRADA = "contents/fich.in";
        final String ARQUIVO_DE_SAIDA = "contents/fichout.txt";
        
        FileUtil saida = new FileUtil();
        FileUtil entrada = new FileUtil();

        
        entrada.openFile(ARQUIVO_DE_ENTRADA);

        
        Universidade universidade = new Universidade();

        
        StringBuilder output = new StringBuilder();

        
        String linha;
        int line = 0;
        while ((linha = entrada.readNextLine()) != null) {
            line++;
            if (linha.trim().isEmpty()) {
                continue; 
            }

            String[] comando = linha.split(" ");  

            switch (comando[0]) {
                case "CC":  // Criação de curso
                    //System.out.println(comando[0]+" "+comando[1]);
                    String nomeCurso = comando[1].toLowerCase();

                    //System.err.println("existe na linha ("+line+")"+linha+" ="+universidade.getCursos().get(nomeCurso));
                    if (universidade.adicionarCurso(nomeCurso.trim())) {
                        // System.err.println(nomeCurso);
                        //output.append("Curso ").append(nomeCurso).append(" criado.\n");
                        output.append("Criacao de curso com sucesso.\n\n");
                        // System.err.println("Criacao de curso '"+nomeCurso+"' com sucesso. existe ="+universidade.getCursos().get(nomeCurso));

                    } else {
                        // System.err.println("Curso "+nomeCurso+" existente.");
                        output.append("Curso existente.\n\n");
                        //output.append("Curso ").append(nomeCurso).append(" já existe.\n");
                    }
                    break;

                /*case "RC":  // Remoção de curso
                    String nomeCursoRemover = comando[1].toLowerCase();
                    if (universidade.removerCurso(nomeCursoRemover)) {
                        //output.append("Remocao de curso com sucesso.\n\n");
                     //  System.err.println("Remocao de curso " + nomeCursoRemover + " com sucesso. na linha: " + line);
                        //output.append("Curso ").append(nomeCursoRemover).append(" removido.\n");
                    } else {
                     //   System.err.println("curso " + nomeCursoRemover + " nãe ãxistente. na linha: " + line);
                        output.append("Curso inexistente.\n\n");
                        //output.append("Curso ").append(nomeCursoRemover).append(" não encontrado.\n");
                    }
                    break;*/
                case "CD":  // Criação de disciplina
                    
                    if (comando.length < 4) {
                        output.append("Comando 'CD' inválido. Parâmetros insuficientes na linha inicial.\n");
                        break;
                    }

                    
                    int semestre = Integer.parseInt(comando[1]);
                    boolean obrigatoria = comando[2].equals("0") ? false : true;
                    String nomeCursoDisciplina = comando[3].toLowerCase();

                    
                    String linhaECTS = entrada.readNextLine().trim();

                    if (linhaECTS.isEmpty()) {
                        output.append("Erro: Linha de ECTS não encontrada.\n");
                        break;
                    }

                    String[] parts = linhaECTS.split(" ");

                    double ects = 0;
                    try {
                        ects = Double.parseDouble(parts[0]);  
                    } catch (NumberFormatException e) {
                        output.append("Erro: Valor de ECTS inválido: ").append(parts[0]).append("\n");
                        break;
                    }

                    String nomeDisciplina = parts[1];  

                    
                    DoubleLinkedList<Topico> topicos = new DoubleLinkedList<>();
                    //   System.err.println("Topicos "+ nomeDisciplina);
                    for (int i = 0; i < 4; i++) {
                        try {

                            String linhaTopico = entrada.readNextLine().trim();
                            if (linhaTopico.isEmpty()) {
                                output.append("Erro: Falta de tópico após a linha do comando 'CD'.\n");

                                break;
                            }

                            if (topicos.isEmpty() || topicos.find(new Topico(linhaTopico)) == -1) {
                                //  System.err.println(linhaTopico);
                                // Adicionat o tópico à lista
                                topicos.insert(new Topico(linhaTopico));
                            } else {
                                output.append("Sequencia de topicos com repeticoes.\n\n");
                                break;
                            }

                        } catch (IlligalHeadCallException | IlligalTailCallException ex) {
                          
                        }
                    }
                    // System.err.println("Fim Topicos "+ nomeDisciplina);

                    
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
                    
                    if (comando.length < 2) {
                        output.append("Comando 'RD' inválido. Parâmetro do curso ausente.\n");
                        break;
                    }

                    
                    String nomeCursoRemoverDisciplina = comando[1].toLowerCase();

                    
                    String nomeDisciplinaRemover = entrada.readNextLine().trim();

                    
                    if (nomeDisciplinaRemover.isEmpty()) {
                        output.append("Comando 'RD' inválido. Nome da disciplina ausente.\n");
                        break;
                    }

                    // Remover disciplina do curso
                    Curso cursoParaRemoverDisciplina = universidade.getCursos().get(nomeCursoRemoverDisciplina);
                    if (cursoParaRemoverDisciplina != null) {
                        SimpleLinkedList<Disciplina> disciplinas = cursoParaRemoverDisciplina.getDisciplinas();
                        boolean disciplinaEncontrada = false;

                        
                        for (int i = 1; i <= disciplinas.length(); i++) {
                            try {
                                Disciplina disciplina = disciplinas.get(i);
                                if (disciplina.getNome().equals(nomeDisciplinaRemover)) {
                                    disciplinas.remove(i);  
                                    disciplinaEncontrada = true;
                                    output.append("Remocao de disciplina com sucesso.\n");
                                    break;
                                }
                            } catch (IlligalHeadCallException | IlligalTailCallException e) {
                                output.append("Erro ao acessar disciplina na posição ").append(i).append(": ").append(e.getMessage()).append("\n");
                            }
                        }

                        
                        if (!disciplinaEncontrada) {
                            output.append("Disciplina inexistente.\n\n");
                            //output.append("Disciplina ").append(nomeDisciplinaRemover).append(" não encontrada no curso ").append(nomeCursoRemoverDisciplina).append(".\n");
                        }
                    } else {
                        output.append("Curso inexistente.\n\n");
                        //output.append("Curso ").append(nomeCursoRemoverDisciplina).append(" não encontrado.\n");
                    }
                    break;

                case "PP":  // Plano curricular (exibir disciplinas de um curso)
                    String nomeCursoPlano = comando[1].toLowerCase();
                    Curso cursoPlano = universidade.getCursos().get(nomeCursoPlano);
                    if (cursoPlano != null) {
                        SimpleLinkedList<Disciplina> disciplinas = cursoPlano.getDisciplinas();
                        // output.append("Plano curricular do curso ").append(nomeCursoPlano).append(":\n");
                        if (disciplinas.length() > 0) {
                            for (int i = 1; i <= disciplinas.length(); i++) {
                                try {
                                    Disciplina disciplina = disciplinas.get(i);

                                    output.append(disciplina.getSemestre()).append("  ").append((disciplina.isObrigatoria() ? 1 : 0)).append(" ").append(String.valueOf(disciplina.getEcts()).replaceAll("\\.", ",")).append("  ").append(disciplina.getNome().toUpperCase()).append("\n");

                                } catch (IlligalHeadCallException | IlligalTailCallException e) {
                                    output.append("Erro ao acessar disciplina na posição ").append(i).append(": ").append(e.getMessage()).append("\n");
                                }
                            }
                            output.append("\n");
                        } else {
                            output.append("Disciplinas inexistentes.\n\n");
                        }

                    } else {
                        output.append("Curso inexistente.\n\n");

                    }
                    break;

                case "PD":  // Pesquisa da Informação Associada a uma Disciplina
                    String nomeDisciplinaInformacao = comando[1].toLowerCase();
                    SimpleLinkedList<Curso> _cursos = universidade.getCursos().values();
                    boolean disciplinaEncontrada = false;  

                    
                    for (int c = 0; c < _cursos.length(); c++) {
                        try {
                            Curso cursoD = _cursos.get(c);  
                            if (cursoD != null) {
                                SimpleLinkedList<Disciplina> disciplinas = cursoD.getDisciplinas();

                                if (disciplinas != null && disciplinas.length() > 0) {  
                                    for (int i = 0; i < disciplinas.length(); i++) {  
                                        try {
                                            Disciplina disciplina = disciplinas.get(i);  

                                            
                                            if (disciplina.getNome().toLowerCase().equals(nomeDisciplinaInformacao)) {
                                                
                                                disciplinaEncontrada = true;

                                                
                                                output.append(disciplina.getSemestre())
                                                        .append(" ")
                                                        .append(disciplina.isObrigatoria() ? 1 : 0)
                                                        .append(" ")
                                                        .append(cursoD.getNome())
                                                        .append("\n");

                                                
                                                output.append(String.valueOf(disciplina.getEcts()).replaceAll("\\.", ","))
                                                        .append("\n");

                                                
                                                DoubleLinkedList<Topico> _topicos = disciplina.getTopicos();
                                                for (int t = 1; t <= _topicos.length(); t++) {
                                                    try {
                                                        Topico topico = _topicos.get(t);  
                                                        output.append(topico.getNome()).append("\n");
                                                    } catch (IlligalHeadCallException | IlligalTailCallException e) {
                                                        
                                                    }
                                                }
                                                output.append("\n"); 

                                                
                                                break; 
                                            }
                                        } catch (IlligalHeadCallException | IlligalTailCallException e) {
                                            
                                        }
                                    }
                                }
                            }

                            
                            if (disciplinaEncontrada) {
                                break; 
                            }

                        } catch (IlligalHeadCallException | IlligalTailCallException ex) {
                            
                        }
                    }

                    
                    if (!disciplinaEncontrada) {
                        output.append("Disciplina inexistente.\n");
                    }

                    
                    //System.out.println(output.toString());
                    break;

                case "DT":  // Consultar disciplinas por tópico
                    String nomeTopico = comando[1].toLowerCase(); 
                    SimpleLinkedList<Disciplina> disciplinasPorTopico = universidade.consultarDisciplinasPorTopico(nomeTopico);

                    if (disciplinasPorTopico.length() == 0) {
                        output.append("Topico inexistente.\n\n"); 
                    } else {
                        for (int i = 1; i <= disciplinasPorTopico.length(); i++) {
                            try {
                                Disciplina disciplina = disciplinasPorTopico.get(i);
                                
                                output.append(String.valueOf(disciplina.getEcts()).replaceAll("\\.", ",")).append(" ").append(disciplina.getNome().toUpperCase()).append("\n");
                            } catch (IlligalHeadCallException | IlligalTailCallException e) {
                                output.append("Erro ao acessar disciplina na posição ").append(i).append(": ").append(e.getMessage()).append("\n");
                            }
                        }
                        output.append("\n");
                    }
                    break;

                default:

                    //output.append("Comando desconhecido: ").append(linha).append("\n");
                    break;
            }
        }

        
        entrada.closeFile();

        
        saida.write(output.toString(), ARQUIVO_DE_SAIDA);
    }
}

/*
 case "PD":  // Pesquisa da Informação Associada a uma Disciplina
                    String nomeDisciplinaInformacao = comando[1].toLowerCase();
                  SimpleLinkedList<Curso> _cursos = universidade.consultarCursosPorDisciplina(nomeDisciplinaInformacao);
                    
                    if (_cursos.length() > 0) {
                        
                          for (int c = 0; c < _cursos.length(); c++) {
                            
                            try {
                                Curso _curso = _cursos.get(c);
                                
                                SimpleLinkedList<Disciplina> disciplinas = _curso.getDisciplinas();
                                for (int d = 1; d < disciplinas.length(); d++) {
                                   
                                    Disciplina _disciplina = disciplinas.get(d);
                                   //  System.out.println(_curso.getNome());
                                    output.append(_disciplina.getSemestre()).append(" Cassamo ").append((_disciplina.isObrigatoria() ? 1 : 0)).append(" ").append(_curso.getNome());
                                }

                                for (int dd = 1; dd < disciplinas.length(); dd++) {
                                 //  System.err.println("chegou");
                                    Disciplina _disciplina = disciplinas.get(dd);
                                    output.append(_String.valueOf(disciplina.getEcts()).replaceAll("\\.", ",")).append("\n");
                                    DoubleLinkedList<Topico> _topicos = _disciplina.getTopicos();
                                    for (int t = 0; t < 4; t++) {
                                         
                                        output.append(_topicos.get(t).getNome());
                                    }
                                    
                                }

                            } catch (IlligalHeadCallException | IlligalTailCallException ex) {
                              //  System.err.println(ex.toString());
                               
                            }
                        }
                        output.append("\n");
                        

                    } else {
                        output.append("Disciplina inexistente.\n\n");
                    }
                    break;
 */
