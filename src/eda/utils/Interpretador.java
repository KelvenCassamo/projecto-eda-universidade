/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eda.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Interpretador {
    
    
    //Verifiacar se é um comando  do Curso
   public static boolean isAddCourseSyntax(String line){
       Pattern patt = Pattern.compile("CC\\s*(.*)");
       Matcher matcher = patt.matcher(line);
      
       return matcher.matches();
       
   }
   
   //Obter o nome do curso a partir do comando
   public static String getCourseName(String line){
       Pattern patt = Pattern.compile("CC\\s*(.*)");
       Matcher matcher = patt.matcher(line);
       String result = null;
       if(matcher.matches()){
           result = matcher.group(1);
       }
       
       return result;
       
   }
    
    
    
    
}