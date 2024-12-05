package eda.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashSet;

public class Interpretador {

    // 1. Verificar se é um comando de adicionar curso (CC)
    public static boolean isAddCourseSyntax(String line) {
        return line.matches("CC\\s+[^\\r\\n]+");
    }

    // 2. Verificar se é um comando de remover curso (RC)
    public static boolean isRemoveCourseSyntax(String line) {
        return line.matches("RC\\s+[^\\r\\n]+");
    }

    // 3. Verificar se é um comando de adicionar disciplina (CD)
    public static boolean isAddDisciplineSyntax(String line) {
        return line.matches("CD\\s+\\d+\\s+[01]\\s+[^\\r\\n]+");
    }

    // 4. Verificar se é um comando de inserir disciplina no plano curricular (ID)
    public static boolean isInsertDisciplineSyntax(String line) {
        return line.matches("ID\\s+\\d+\\s+[01]\\s+[^\\r\\n]+");
    }

    // 5. Verificar se é um comando de remover disciplina do plano curricular (RD)
    public static boolean isRemoveDisciplineSyntax(String line) {
        return line.matches("RD\\s+[^\\r\\n]+");
    }

    // 6. Verificar se é um comando de pesquisar disciplina (PD)
    public static boolean isSearchDisciplineSyntax(String line) {
        return line.matches("PD\\s+[^\\r\\n]+");
    }

    // 7. Verificar se é um comando de pesquisar plano curricular (PP)
    public static boolean isSearchPlanSyntax(String line) {
        return line.matches("PP\\s+[^\\r\\n]+");
    }

    // 8. Verificar se é um comando de pesquisar disciplinas associadas a um tópico (DT)
    public static boolean isSearchTopicSyntax(String line) {
        return line.matches("DT\\s+[^\\r\\n]+");
    }

    // Extração de valores dos comandos:

    // Obter valor do comando CC
    public static String getCCValue(String line) {
        return extractValue("CC\\s+(.*)", line);
    }

    // Obter valor do comando RC
    public static String getRCValue(String line) {
        return extractValue("RC\\s+(.*)", line);
    }

    // Obter valores do comando CD (criar disciplina)
  /*  public static Disciplina getDisciplineValues(String input) throws IllegalArgumentException {
        String[] lines = input.split("\\R");
        if (lines.length < 6 || !isAddDisciplineSyntax(lines[0])) {
            throw new IllegalArgumentException("Formato inválido para criação de disciplina.");
        }

        // Primeira linha: CD semestre tipo curso
        String[] mainParts = lines[0].split("\\s+");
        int semestre = Integer.parseInt(mainParts[1]);
        int tipo = Integer.parseInt(mainParts[2]);
        String curso = mainParts[3];

        // Segunda linha: Número-de-créditos disciplina
        String[] creditsAndName = lines[1].split("\\s+", 2);
        double creditos = Double.parseDouble(creditsAndName[0]);
        String disciplina = creditsAndName[1];

        // Demais linhas: tópicos
        HashSet<String> topicos = new HashSet<>();
        for (int i = 2; i < lines.length; i++) {
            String topico = lines[i];
            if (!topicos.add(topico)) {
                throw new IllegalArgumentException("Sequência de tópicos contém repetições.");
            }
        }

        return new Disciplina(semestre, tipo, curso, creditos, disciplina, new ArrayList<>(topicos));
    }*/

    // Métodos auxiliares
    private static String extractValue(String pattern, String line) {
        Pattern patt = Pattern.compile(pattern);
        Matcher matcher = patt.matcher(line);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    
}
