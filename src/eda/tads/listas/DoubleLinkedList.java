package eda.tads.listas;

public class DoubleLinkedList<T> {

    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node() {
            // head
        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public boolean compareTo(Node obj) {
            return this == obj;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private Node head, tail;
    private int nElements;

    public DoubleLinkedList() {
        head = new Node();
        tail = new Node();
        head.setNext(tail);
        tail.setPrev(head);
    }

    private Node begin() {
        return head;
    }

    private Node end() {
        return tail;
    }
    
    public boolean isEmpty(){
        return length() == 0;
    }

    public T next(int p) throws IlligalTailCallException {
        if (p < 1 || p >= nElements)  // Verificado para <=
            throw new IlligalTailCallException("Chamada de next para uma posição inválida");

        Node aux = nth(p);
        return aux.getNext().getData();
    }

    public T prev(int p) throws IlligalHeadCallException {
        if (p <= 1 || p > nElements)
            throw new IlligalHeadCallException("Chamada de prev para uma posição inválida");

        Node aux = nth(p);
        return aux.getPrev().getData();
    }

    public T get(int p) throws IlligalHeadCallException, IlligalTailCallException {
        if (p < 1 || p > nElements)
            throw new IlligalHeadCallException("Argumento inválido");

        Node aux = nth(p);
        return aux.getData();
    }

    public void set(int p, T data) throws IlligalHeadCallException, IlligalTailCallException {
        if (p < 1 || p > nElements)
            throw new IlligalHeadCallException("Argumento inválido");

        Node aux = nth(p);
        aux.setData(data);
    }

    public void insert(T data, int... p) throws IlligalHeadCallException, IlligalTailCallException {
        if (p.length > 0) {
            if (p[0] < 1 || p[0] > nElements + 1)
                throw new IlligalHeadCallException("Argumento inválido");

            Node newElem = new Node(data);

            if (p[0] == 1) {
                newElem.setNext(head.getNext());
                newElem.setPrev(head);
                head.getNext().setPrev(newElem);
                head.setNext(newElem);
            } else {
                Node aux = nth(p[0] - 1);
                newElem.setNext(aux.getNext());
                newElem.setPrev(aux);
                if (aux.getNext() != null) {
                    aux.getNext().setPrev(newElem);
                }
                aux.setNext(newElem);
            }
        } else {
            Node newElem = new Node(data);
            newElem.setPrev(tail.getPrev());
            tail.getPrev().setNext(newElem);
            newElem.setNext(tail);
            tail.setPrev(newElem);
        }
        nElements++;
    }

    public void remove(int p) throws IlligalHeadCallException, IlligalTailCallException {
        if (p < 1 || p > nElements)
            throw new IlligalHeadCallException("Argumento inválido");

        Node aux = nth(p);
        Node prevNode = aux.getPrev();
        Node nextNode = aux.getNext();

        if (prevNode != null) {
            prevNode.setNext(nextNode);
        }
        if (nextNode != null) {
            nextNode.setPrev(prevNode);
        }

        nElements--;
    }

    public int find(T data) {
        Node aux = head.getNext();
        int idx = 1; // Começar de 1 para a compatibilidade com os métodos públicos

        while (aux != null && aux != tail) {
            if (aux.getData().equals(data))
                return idx;
            aux = aux.getNext();
            idx++;
        }
        return -1;
    }

    private Node nth(int k) {
        if (k < 1 || k > nElements)
            return tail;

        int idx = 0;
        Node aux = head;

        while (++idx <= k)
            aux = aux.getNext();

        return aux;
    }

    public int length() {
        return nElements;
    }
}
