/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eda.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    public FileUtil() {
    
    }
    
    public String[] read(String path) {
        String[] lines = new String[10]; // Array inicial com tamanho fixo
        int size = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Redimensionar array se necessário
                if (size == lines.length) {
                    lines = resizeArray(lines, lines.length * 2); // Dobra o tamanho do array
                }
                lines[size] = line;
                size++;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Ajustar o array ao tamanho exato
        return resizeArray(lines, size);
    }

    private String[] resizeArray(String[] original, int newSize) {
        String[] newArray = new String[newSize];
        System.arraycopy(original, 0, newArray, 0, Math.min(original.length, newSize));
        return newArray;
    }
    
    
    public void write(String content, String path){
         try {
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));
           bufferedWriter.write(content);
           bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
        

    
}
