/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eda.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

    public FileUtil() {
    
    }
    
    public String[] read(String path){
       
                 String[] lines = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            int lineCount = 0;
            while (bufferedReader.readLine() != null) {
                lineCount++;
            }
            bufferedReader.close();

            lines = new String[lineCount];

            bufferedReader = new BufferedReader(new FileReader(path));
            int cur_line = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines[cur_line] = line;
                cur_line++;
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return lines;

    }
    
        

    
}
