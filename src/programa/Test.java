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

    public static void main(String[] args) {
        FileUtil fileUtil = new FileUtil();
        String lines[] = fileUtil.read("contents/fich.in.txt");
        Universidade universidade = new Universidade();
        testarAddCursos(lines, universidade);
        print(universidade.getCursos().get("c653"));
        removerCursos(lines, universidade);
        print(universidade.getCursos().get("c653"));
       
        


// universidade.getCursos().printTable();
        
        
    }

    static void testarAddCursos(String[] lines, Universidade universidade) {
        System.out.println("Adicionar cursos:");
        int count = 0;
        int total = 0;

        for (String line : lines) {
            if (Interpretador.isAddCourseSyntax(line)) {

                String nome_curso = Interpretador.getCourseName(line);
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
    static void removerCursos(String[] lines, Universidade universidade) {
        System.out.println("Remover cursos:");
        int count = 0;
        int total = universidade.getCursos().size();

        for (String line : lines) {
            if (Interpretador.isAddCourseSyntax(line)) {

                String nome_curso = Interpretador.getCourseName(line);
                //print(nome_curso);
                if (universidade.adicionarCurso(nome_curso)) {
                    count++;
                }

               

            }

        }

        System.out.println("Fim do Remover cursos");
        System.out.println("Cursos removidos: " + count + " dos " + total + " anteriormente disponiveis!");

        print(universidade.getCursos().size());
    }
    static void testarAddDisciplia(String[] lines, Universidade universidade) {
        System.out.println("Adicionar cursos:");
        int count = 0;
        int total = 0;

        for (String line : lines) {
            if (Interpretador.isAddCourseSyntax(line)) {

                String nome_curso = Interpretador.getCourseName(line);
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

}
