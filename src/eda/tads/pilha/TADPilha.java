/**
 * 
 */
package eda.tads.pilha;

/**
 * Data: 06/09/2024
 * Autores: Kelven, Enoque e Kespar
 * Tema: TAD Pilha
 */
public class TADPilha <T>{
	private T[] data;
	private int nElements, size;
	
	public TADPilha(int size) {
		this.size=size;
		nElements = 0;
		data = (T[])new Object[size];
	}
	
	public boolean isEmpty() {
		return nElements == 0;
	}
	
	public boolean isFull() {
		return nElements >= size;
	}
	
	public void makeEmpty() {
		data = (T[])new Object[size];
		nElements = 0;
	}
	
	public void push(T element) throws PilhaCheiaException {
		if(isFull())
			throw new PilhaCheiaException("A Pilha está cheia!!");
		data[nElements++]=element;
	}
	
	public T pop() throws PilhaVaziaException {
		if(isEmpty())
			throw new PilhaVaziaException("A Pilha está vazia!!");
		return data[--nElements];
	}
}
