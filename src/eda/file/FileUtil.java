package eda.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class FileUtil {
    private BufferedReader bufferedReader;

    public FileUtil() {
    }

    
    public void openFile(String path) {
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (IOException e) {
            System.err.println("Erro ao abrir o arquivo: " + e.getMessage());
        }
    }

    
    public String readNextLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }
    }

    
    public void closeFile() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        } catch (IOException e) {
            System.err.println("Erro ao fechar o arquivo: " + e.getMessage());
        }
    }

    
    public void write(String content, String path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            bufferedWriter.write(content);
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}
