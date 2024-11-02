/**
 * 
 */
package eda.tads.listas;

/**
 * Data: 27/09/2024
 * Autores: Kelven, Enoque e Kespar
 */
public class SimpleLinkedList <T>{

	private class Node{
		private T data;
		private Node next;
		
		public Node() {
			// head
		}
		
		public Node(T data) {
			this.data = data;
		}
		
		public Node(T data, Node next) {
			this.data = data;
			this.next = next;
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
	}
	
	private Node head, tail;
	private int nElements;
	
	public SimpleLinkedList() {
		head = new Node();
		tail = new Node(null, head);
	}
	
	private Node begin() {
		return head;
	}
	
	private Node end() {
		return tail;
	}
	
	public T next(int p) throws IlligalTailCallException {
    if (p >= nElements || p < 1)  // Corrigido para > e <
        throw new IlligalTailCallException("Chamada de next para uma posição invalida");

    Node aux = nth(p);
    return aux.getNext().getData(); // Aqui você pode precisar ajustar a lógica
}
	
	
	public T get(int p) throws IlligalHeadCallException, IlligalTailCallException {
		if(p < 1)
			throw new IlligalHeadCallException("Argumento inválido");
		
		if(p > nElements)
			throw new IlligalTailCallException("Argumento inválido");
		
		
		Node aux = nth(p);
		
		return aux.getData();
	}
	
	public void set(int p, T data) throws IlligalHeadCallException, IlligalTailCallException {
		if(p < 1)
			throw new IlligalHeadCallException("Argumento inválido");
		
		if(p > nElements)
			throw new IlligalTailCallException("Argumento inválido");
		
		Node aux = nth(p);
		
		aux.setData(data);
	}
	/*
	 * Quando passado o dado útil apenas por parâmetro, insere na cauda
	 * Quando passado o dado útil e a posição por parâmetro, insere na posição indicada
	 */
	public void insert(T data, int ...p) throws IlligalHeadCallException, IlligalTailCallException {
		if(p.length > 0) {
			
			if(p[0] < 1)
				throw new IlligalHeadCallException("Argumento inválido");
			
			if(p[0] > nElements+1)
				throw new IlligalTailCallException("Argumento inválido");
			
			
			Node newElem;
			
			if(nElements == 0) {
				newElem = new Node(data);
				head.setNext(newElem);
				tail.setNext(newElem);
			}else {
				if(p[0] == 1) {
					newElem = new Node(data, begin().getNext());
					begin().setNext(newElem);
				}else {
					
					newElem = new Node(data);
					Node aux = nth(p[0]-1);
					
					newElem.setNext(aux.getNext());
					aux.setNext(newElem);
				}
			}
		}else {
			
			Node newElem = new Node(data);
			if(nElements == 0) {
				head.setNext(newElem);
				tail.setNext(newElem);
			}else {
				end().getNext().setNext(newElem);
				end().setNext(newElem);
			}
		}
		nElements++;
	}
	
	public void remove(int p) throws IlligalHeadCallException, IlligalTailCallException {
		if(p < 1)
			throw new IlligalHeadCallException("Argumento inválido");
		
		if(p > nElements)
			throw new IlligalTailCallException("Argumento inválido");
		
		if(p == 1) {
			if(head.getNext().compareTo(tail.getNext()))
				tail.setNext(head);
			head.setNext(head.getNext().getNext());
		}else {
			if(p == nElements) {
				tail.setNext(nth(p-1));
			}else {
				Node aux = nth(p-1);
				Node aux1 = aux.getNext();
				aux.setNext(aux1.getNext());
			}
		}
		nElements--;
	}
	
	public int find(T data) {
    Node aux = head.getNext();
    int idx = 0;

    while (aux != null) {
        idx++;
        if (aux.getData().equals(data))  // Utilize equals ao invés de ==
            return idx;
        aux = aux.getNext();
    }
    return -1;
}
	
	private Node nth(int k){
		if(k < 1 || k > nElements)
			return tail;
		
		int idx = 0;
		Node aux = head;
		
		while(++idx <= k)
			aux = aux.getNext();
		
		return aux;
	}
	
	public int length() {
		return nElements;
	}
}
