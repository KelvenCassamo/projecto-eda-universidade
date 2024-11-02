package eda.tads.fila;

public class TestaFila {

	public static void main(String[] args) throws FilaVaziaException, FilaCheiaException {
		TADFila<Integer> fila = new TADFila<>(7);
		
		fila.enqueue(10);
		fila.enqueue(20);
		fila.enqueue(30);
		fila.enqueue(40);
		fila.enqueue(50);
		fila.enqueue(60);
		fila.enqueue(70);
		System.out.println(fila.dequeue());
		System.out.println(fila.dequeue());
		fila.enqueue(80);
		System.out.println(fila.dequeue());
		System.out.println(fila.dequeue());
		System.out.println(fila.dequeue());
		System.out.println(fila.dequeue());
		fila.enqueue(90);
		System.out.println(fila.dequeue());
		System.out.println(fila.dequeue());
		System.out.println(fila.dequeue());
		System.out.println(fila.dequeue());
	}

}
