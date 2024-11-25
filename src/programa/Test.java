/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programa;

import eda.file.FileUtil;
import eda.modelos.Curso;
import eda.modelos.Universidade;
import eda.tads.th.HashTable;
import eda.utils.Interpretador;

public class Test {

   private static String output = "";

    public static void main(String[] args) {

        FileUtil saida = new FileUtil();
        String lines_saida[] = saida.read("contents/fich.out");
        FileUtil entrada = new FileUtil();
        String lines_entrada[] = entrada.read("contents/fich.in");
        Universidade universidade = new Universidade();
         testarAddCursos(lines_entrada, universidade);
        /*print(universidade.getCursos().get("c653"));
        removerCursos(lines_entrada, universidade);
        print(universidade.getCursos().get("c653"));
         */
        
        //print(lines_saida.length);
       
        
        saida.write(output, "contents/fichout.txt");
        
        
    }

    static void testarAddCursos(String[] lines, Universidade universidade) {
        System.out.println("Adicionar cursos:");
        int count = 0;
        int total = 0;
        int num_cursos_duplicados = 0;
        for (String line : lines) {
            if (Interpretador.isAddCourseSyntax(line)) {

                String nome_curso = Interpretador.getCCValue(line);
                //print(nome_curso);
                if (universidade.adicionarCurso(nome_curso)) {
                    count++;
                    
                    appendOutput("Criacao de curso com sucesso."+"\n\n");

                }
                else{
                    num_cursos_duplicados++;
                   appendOutput("Curso existente."+"\n\n");
                }

                total++;

            }

        }

        System.out.println("Fim do Adicionar cursos");
        System.out.println("Cursos adicionados: " + count + " dos " + total + " disponiveis!");
        System.out.println("Detectamos: " + num_cursos_duplicados + " cursos duplicado!");
       // print(universidade.getCursos().size());
    }

    static void removerCursos(String[] lines, Universidade universidade) {
        System.out.println("Remover cursos:");
        int count = 0;
        int total = universidade.getCursos().size();

        for (String line : lines) {
            if (Interpretador.isRemoveCourseSyntax(line)) {

                String nome_curso = Interpretador.getRCValue(line);
                print(nome_curso);
                if (universidade.removerCurso(nome_curso)) {
                    count++;
                }

            }

        }

        System.out.println("Fim do Remover cursos");
        System.out.println("Cursos removidos: " + count + " dos " + total + " anteriormente disponiveis!");

        print(universidade.getCursos().size());
    }

    static void testarAddDisciplia(String[] lines, Universidade universidade) {
        System.out.println("Adicionar disciplinas:");
        int count = 0;
        int total = 0;

        for (String line : lines) {
            if (Interpretador.isAddCourseSyntax(line)) {

                String nome_curso = Interpretador.getCCValue(line);
                //print(nome_curso);
                if (universidade.adicionarCurso(nome_curso)) {
                    count++;
                }

                total++;

            }

        }

        System.out.println("Fim do Adicionar cursos");
        System.out.println("Cursos adicionados: " + count + " dos " + total + " disponiveis!");

        print(universidade.getCursos().size());
    }

    static void print(Object o) {
        System.out.println(o);
    }

    static void appendOutput(String _new) {
        output = output+ _new;
    }
}
