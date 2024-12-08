
package eda.modelos;

import java.util.Objects;

public class Topico {

    private String nome;

    public Topico(String nome) {
        this.nome = nome.toLowerCase();  
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
              System.out.println("numm");
            return false;
          
        }
        Topico topico = (Topico) o;
        
        return Objects.equals(nome, topico.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}