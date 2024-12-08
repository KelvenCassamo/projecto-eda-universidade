package eda.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashSet;

public class Interpretador {

    
    public static boolean isAddCourseSyntax(String line) {
        return line.matches("CC\\s+[^\\r\\n]+");
    }

    
    public static boolean isRemoveCourseSyntax(String line) {
        return line.matches("RC\\s+[^\\r\\n]+");
    }

    
    public static boolean isAddDisciplineSyntax(String line) {
        return line.matches("CD\\s+\\d+\\s+[01]\\s+[^\\r\\n]+");
    }

    
    public static boolean isInsertDisciplineSyntax(String line) {
        return line.matches("ID\\s+\\d+\\s+[01]\\s+[^\\r\\n]+");
    }

    
    public static boolean isRemoveDisciplineSyntax(String line) {
        return line.matches("RD\\s+[^\\r\\n]+");
    }

    
    public static boolean isSearchDisciplineSyntax(String line) {
        return line.matches("PD\\s+[^\\r\\n]+");
    }

    
    public static boolean isSearchPlanSyntax(String line) {
        return line.matches("PP\\s+[^\\r\\n]+");
    }

    
    public static boolean isSearchTopicSyntax(String line) {
        return line.matches("DT\\s+[^\\r\\n]+");
    }

    

    
    public static String getCCValue(String line) {
        return extractValue("CC\\s+(.*)", line);
    }

    
    public static String getRCValue(String line) {
        return extractValue("RC\\s+(.*)", line);
    }

    
  /*  public static Disciplina getDisciplineValues(String input) throws IllegalArgumentException {
        String[] lines = input.split("\\R");
        if (lines.length < 6 || !isAddDisciplineSyntax(lines[0])) {
            throw new IllegalArgumentException("Formato inválido para criação de disciplina.");
        }

        
        String[] mainParts = lines[0].split("\\s+");
        int semestre = Integer.parseInt(mainParts[1]);
        int tipo = Integer.parseInt(mainParts[2]);
        String curso = mainParts[3];

        
        String[] creditsAndName = lines[1].split("\\s+", 2);
        double creditos = Double.parseDouble(creditsAndName[0]);
        String disciplina = creditsAndName[1];

        
        HashSet<String> topicos = new HashSet<>();
        for (int i = 2; i < lines.length; i++) {
            String topico = lines[i];
            if (!topicos.add(topico)) {
                throw new IllegalArgumentException("Sequência de tópicos contém repetições.");
            }
        }

        return new Disciplina(semestre, tipo, curso, creditos, disciplina, new ArrayList<>(topicos));
    }*/

    
    private static String extractValue(String pattern, String line) {
        Pattern patt = Pattern.compile(pattern);
        Matcher matcher = patt.matcher(line);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    
}
