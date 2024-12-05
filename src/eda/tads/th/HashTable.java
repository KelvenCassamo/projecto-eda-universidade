package eda.tads.th;

import eda.tads.listas.IlligalHeadCallException;
import eda.tads.listas.IlligalTailCallException;
import eda.tads.listas.SimpleLinkedList;

public class HashTable<K, V> {
private int totalElements; // Número total de entradas na tabela

    private record Entry<K, V>(K key, V value) {

        public static <T> int rawHash(T key) {
            if (key instanceof Integer) {
                return (int) key;
            } else if (key instanceof Double) {
                int k = (int) key / 1;
                k += (int) key % 1;
                return k;
            } else if (key instanceof String) {
                int k = 0;
                for (int i = 0; i < ((String) key).length(); i++) {
                    k += ((String) key).charAt(i) * Math.pow(11, i);
                }
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
          totalElements = 0;
        buckets = new SimpleLinkedList[dim];
    }

    private int compress(int hash) {
        hash = Math.abs(hash);
        double a = (Math.sqrt(5) - 1) / 2;
        return (int) Math.floor(dim * ((a * hash) % 1));
    }

    private void resizeTable() {
        if (nSlots < dim * LOADFACTOR) {
            return;
        }

        nSlots = 0;
        dim = nextPrime(dim * 2);
        SimpleLinkedList<Entry<K, V>>[] newTable = new SimpleLinkedList[dim];

        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                for (int j = 1; j <= buckets[i].length(); j++) {
                    try {
                        Entry<K, V> aux = buckets[i].get(j);
                        int idx = compress(Entry.rawHash(aux.key()));
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

    private int nextPrime(int v) {
        int i, f;
        while (true) {
            i = 2;
            f = v - 1;

            while (i <= f) {
                if (v % i == 0) {
                    break;
                }
                i++;
            }

            if (i > f) {
                return v;
            }
            v++;
        }
    }

    public void put(K key, V value) {
        if (containsKey(key)) {
            return;
        }

        resizeTable();
        Entry<K, V> newElement = new Entry<>(key, value);
        int idx = compress(Entry.rawHash(key));

        if (buckets[idx] == null) {
            buckets[idx] = new SimpleLinkedList<>();
            nSlots++;
        }

        try {
            buckets[idx].insert(newElement);
            totalElements++; // Incrementa o número total de elementos
        } catch (IlligalHeadCallException | IlligalTailCallException e) {
            e.printStackTrace();
        }
    }

    public V get(K key) {
        Entry<K, V> foundEntry = find(key);
        return (foundEntry != null) ? foundEntry.value() : null;
    }

    private Entry<K, V> find(K key) {
        int idx = compress(Entry.rawHash(key));
        if (buckets[idx] == null) {
            return null;
        }

        for (int i = 1; i <= buckets[idx].length(); i++) {
            try {
                Entry<K, V> aux = buckets[idx].get(i);
                if (aux.key().equals(key)) {
                    return aux;
                }
            } catch (IlligalHeadCallException | IlligalTailCallException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean remove(K key) {
         int idx = compress(Entry.rawHash(key));
    if (buckets[idx] == null)
        return false;

    for (int i = 1; i <= buckets[idx].length(); i++) {
        try {
            Entry<K, V> aux = buckets[idx].get(i);
            if (aux.key().equals(key)) {
                buckets[idx].remove(i);
                totalElements--; // Decrementa o número total de elementos
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

    public boolean containsKey(K key) {
        return find(key) != null;
    }

   public int size() {
    return totalElements; // Retorna o número total de elementos
}

    public SimpleLinkedList<V> values() {
        SimpleLinkedList<V> values = new SimpleLinkedList<>();

        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                for (int j = 1; j <= buckets[i].length(); j++) {
                    try {
                        Entry<K, V> aux = buckets[i].get(j);
                        if (aux.value() != null) {
                            values.insert(aux.value());
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
