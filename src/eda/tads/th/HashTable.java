package eda.tads.th;

import eda.tads.listas.IlligalHeadCallException;
import eda.tads.listas.IlligalTailCallException;
import eda.tads.listas.SimpleLinkedList;

/*
 * Data: 05/11/2024
 * Autores: LDS e LASIR - USTM
 * Tema: Tabela Hash
 */
public class HashTable<K, V> {

    
    private record Entry<K, V>(K key, V value) {

        // método hash para inteiros, números de ponto-flutuante e strings
        public static <T> int rawHash(T key) {
            if (key instanceof Integer) { 
                return (int) key;
            } else if (key instanceof Double) { 
                int k = (int) key / 1;
                k += (int) key % 1;
                return k;
            } else if (key instanceof String) { 
                int k = 0;
                for (int i = 0; i < ((String) key).length(); i++)
                    k += ((String) key).charAt(i) * Math.pow(11, i);
                return k;
            }
            return -1;
        }
    }

    private SimpleLinkedList<Entry<K, V>>[] buckets;
    private final double LOADFACTOR = 0.75;
    private int nSlots, dim;

    public HashTable() {
        nSlots = 0;
        dim = 11;
        buckets = new SimpleLinkedList[dim];
    }

    // método responsável por converter o hash para um valor válido para a tabela
    private int compress(int hash) {
        hash = Math.abs(hash);
        double a = (Math.sqrt(5) - 1) / 2;
        return (int) Math.floor(dim * ((a * hash) % 1));
    }

    // método responsável por crescer a tabela
    private void resizeTable() {
        if (nSlots < dim * LOADFACTOR)
            return;

        nSlots = 0;
        dim = nextPrime(dim * 2);
        SimpleLinkedList<Entry<K, V>>[] newTable = new SimpleLinkedList[dim];

        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                int idx;
                for (int j = 1; j <= buckets[i].length(); j++) {
                    try {
                        Entry<K, V> aux = buckets[i].get(j);
                        idx = compress(Entry.rawHash(aux.key()));
                        if (newTable[idx] == null) {
                            newTable[idx] = new SimpleLinkedList<>();
                            nSlots++;
                        }
                        newTable[idx].insert(aux);
                    } catch (IlligalHeadCallException | IlligalTailCallException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        buckets = newTable;
    }

    // método que procura um número primo próximo ao recebido por parâmetro
    private int nextPrime(int v) {
        int i, f;
        while (true) {
            i = 2;
            f = v - 1;

            while (i <= f) {
                if (v % i == 0)
                    break;
                i++;
            }

            if (i > f)
                return v;
            v++;
        }
    }

    // método para adicionar ou actualizar um valor na tabela
    public void put(K key, V value) {
        resizeTable();
        Entry<K, V> newElement = new Entry<>(key, value);
        int idx = compress(Entry.rawHash(key));
        if (buckets[idx] == null) {
            buckets[idx] = new SimpleLinkedList<>();
            nSlots++;
        }

        try {
            
            for (int i = 1; i <= buckets[idx].length(); i++) {
                Entry<K, V> aux = buckets[idx].get(i);
                if (aux.key().equals(key)) {
                    buckets[idx].remove(i);
                    break;
                }
            }
            // Inserimos a nova entrada
            buckets[idx].insert(newElement);
        } catch (IlligalHeadCallException | IlligalTailCallException e) {
            e.printStackTrace();
        }
    }

    // método para obter um valor associado a uma chave
    public V get(K key) {
        int idx = compress(Entry.rawHash(key));
        if (buckets[idx] == null)
            return null;
        for (int i = 1; i <= buckets[idx].length(); i++) {
            try {
                Entry<K, V> aux = buckets[idx].get(i);
                if (aux.key().equals(key)) {
                    return aux.value();
                }
            } catch (IlligalHeadCallException | IlligalTailCallException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    // método para remover um par chave-valor
    public boolean remove(K key) {
        int idx = compress(Entry.rawHash(key));

        if (buckets[idx] == null)
            return false;

        for (int i = 1; i <= buckets[idx].length(); i++) {
            try {
                Entry<K, V> aux = buckets[idx].get(i);
                if (aux.key().equals(key)) {
                    buckets[idx].remove(i);
                    if (buckets[idx].length() <= 0)
                        nSlots--;
                    return true;
                }
            } catch (IlligalHeadCallException | IlligalTailCallException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // método para verificar se a chave existe
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    
    public int size() {
        return nSlots;
    }
    
    
     
      
     public SimpleLinkedList<V> values() {
    SimpleLinkedList<V> values = new SimpleLinkedList<>();
    
    // Iterar sobre todos os "buckets" da tabela hash
    for (int i = 0; i < buckets.length; i++) {
        if (buckets[i] != null) {
            // Iterar sobre os elementos dentro de cada "bucket"
            for (int j = 1; j <= buckets[i].length(); j++) {
                try {
                  
                    Entry<K, V> entry = buckets[i].get(j);
                    
                    
                    if (entry.value() != null) {
                        values.insert(entry.value());  
                    }
                } catch (IlligalHeadCallException | IlligalTailCallException e) {
                    e.printStackTrace();  
                }
            }
        }
    }

    return values;
}

    
    public void printTable() {
        for (int i = 0; i < buckets.length; i++) {
            System.out.print("[" + i + "] ");
            if (buckets[i] != null) {
                for (int j = 1; j <= buckets[i].length(); j++) {
                    try {
                        Entry<K, V> aux = buckets[i].get(j);
                        System.out.print(aux.key() + " -> " + aux.value() + " | ");
                    } catch (IlligalHeadCallException | IlligalTailCallException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println();
        }
    }
}
