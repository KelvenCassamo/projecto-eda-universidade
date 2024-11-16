/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programa;

import eda.modelos.Curso;
import eda.tads.listas.IlligalHeadCallException;
import eda.tads.listas.SimpleLinkedList;
import eda.tads.th.HashTable;
import eda.tads.th.IlligalTailCallException;


/**
 *
 * @author Cassamo
 */
public class Test {
    
    public static void main(String[] args) throws IlligalTailCallException {
         HashTable<String, Curso> cursos = new HashTable<>();
        
        // Adicionando alguns cursos à tabela
        cursos.put("curso1", new Curso("Curso 1"));
        cursos.put("curso2", new Curso("Curso 2"));
        
        // Obtendo e exibindo os valores (cursos) armazenados na tabela
        SimpleLinkedList<Curso> listaDeCursos = cursos.values();
        
        // Exibindo os cursos
        for (int i = 1; i <= listaDeCursos.length(); i++) {
            try {
                Curso curso = listaDeCursos.get(i);
                System.out.println("Curso: " + curso.getNome());
            } catch (IlligalHeadCallException | eda.tads.listas.IlligalTailCallException e) {
                e.printStackTrace();
            }
        }
        
        
        
        
    }
    
}
