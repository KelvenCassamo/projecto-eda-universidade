/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eda.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Interpretador {
    
    
    //Verifiacar se é um comando  de adicionar Curso
   public static boolean isAddCourseSyntax(String line){
       Pattern patt = Pattern.compile("CC\\s*(.*)");
       Matcher matcher = patt.matcher(line);
      
       return matcher.matches();
       
   }
   public static boolean isAddDisciplineSyntax(String line){
       Pattern patt = Pattern.compile("CC\\s*(.*)");
       Matcher matcher = patt.matcher(line);
      
       return matcher.matches();
       
   }
   
   
   //Verifiacar se é um comando  de renover Curso
   public static boolean isRemoveCourseSyntax(String line){
       Pattern patt = Pattern.compile("RC\\s*(.*)");
       Matcher matcher = patt.matcher(line);
      
       return matcher.matches();
       
   }
   
   //Obter valor do comando CC
   public static String getCCValue(String line){
       Pattern patt = Pattern.compile("CC\\s*(.*)");
       Matcher matcher = patt.matcher(line);
       String result = null;
       if(matcher.matches()){
           result = matcher.group(1);
       }
       
       return result;
       
   }
   
   //Obter valor do comando RC
   public static String getRCValue(String line){
       Pattern patt = Pattern.compile("RC\\s*(.*)");
       Matcher matcher = patt.matcher(line);
       String result = null;
       if(matcher.matches()){
           result = matcher.group(1);
       }
       
       return result;
       
   }
    
    
    
    
}
