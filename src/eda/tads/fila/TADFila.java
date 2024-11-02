
package eda.tads.fila;

import java.util.Arrays;

/**
 * Data: 06/09/2024
 * Autores: Kelven, Enoque e Kespar
 * Tema: TAD Fila
 */
public class TADFila <T>{

	private T[] data;
	private int nElements, size;
	private byte[] info;
	
	public TADFila(int size) {
		this.size=size;
		makeEmpty();
	}
	
	public boolean isEmpty() {
		return nElements == 0;
	}
	
	public boolean isFull() {
		return nElements >= size;			
	}
	
	public void makeEmpty() {
		nElements = 0;
		data =(T[])new Object[size];
		info = new byte[size];
		Arrays.fill(info, (byte)0);
	}
	
	public void enqueue(T element) throws FilaCheiaException {
		if(isFull())
			throw new FilaCheiaException("Fila está cheia!!");
		
		if(isEmpty()) { 
			info[nElements] = 2;
			data[nElements] = element;
		}
		else {
			int i = 0;
			while(info[i] != 2)
				i++;
			boolean resp = false;
			int j;
			for (j = i + 1; j < info.length; j++)
				if(info[j] == 0) {
					resp = true;
					break;
				}
			if(resp) {
				info[j] = 1;
				data[j] = element;
			}else {
				int j2 = 0;
				while(info[j2] != 0)
					j2++;
				
				info[j2] = 1;
				data[j2] = element;
			}
		}
		nElements ++;
	}
	
	public T dequeue() throws FilaVaziaException {
		if(isEmpty())
			throw new FilaVaziaException("Fila está vazia!!");
		int i = 0;
		while(info[i] != 2)
			i++;
		if(i + 1 < size) {
			info[i + 1] = 2;
		}else {
			info[0] = 2;
		}
		info[i] = 0;
		nElements--;
		return data[i];
	}
}
