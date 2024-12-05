

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DisciplinaProcessor {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("contents/fich.in"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("CD")) {
                    // Processar a criação da disciplina
                    processarDisciplina(br, linha);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processarDisciplina(BufferedReader br, String linha) throws IOException {
        // A linha de comando "CD"
        String[] comando = linha.split(" ");
        String semestre = comando[1];
        String tipo = comando[2];
        String curso = comando[3];
        
        // Agora, precisamos ler as próximas 5 linhas
        String numeroDeCreditosENome = br.readLine(); // linha que contém número de créditos e nome da disciplina
        String[] creditoNome = numeroDeCreditosENome.split(" "); // Dividimos para separar número de créditos e nome da disciplina
        String numeroDeCreditos = creditoNome[0]; // Número de créditos
        String nomeDisciplina = creditoNome[1]; // Nome da disciplina
        
        String topico1 = br.readLine();
        String topico2 = br.readLine();
        String topico3 = br.readLine();
        String topico4 = br.readLine();

        // Exibir os dados lidos
        System.out.println("Comando 'CD' encontrado: ");
        System.out.println("Semestre: " + semestre);
        System.out.println("Tipo: " + tipo);
        System.out.println("Curso: " + curso);
        System.out.println("Número de Créditos: " + numeroDeCreditos);
        System.out.println("Nome da Disciplina: " + nomeDisciplina);
        System.out.println("Tópico 1: " + topico1);
        System.out.println("Tópico 2: " + topico2);
        System.out.println("Tópico 3: " + topico3);
        System.out.println("Tópico 4: " + topico4);
    }
}
