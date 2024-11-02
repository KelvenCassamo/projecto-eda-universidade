/**
 * 
 */
package eda.tads.pilha;

/**
 * 
 */
public class TestaPilha {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		TADPilha<Integer> pilha = new TADPilha<>(4);
		
		try {
			pilha.push(7);
			pilha.push(9);
			pilha.push(3);
			pilha.push(16);
			//pilha.push(19);
			System.out.println(pilha.pop());
			System.out.println(pilha.pop());
			System.out.println(pilha.pop());
			System.out.println(pilha.pop());
			System.out.println(pilha.pop());
		} catch (PilhaVaziaException _t) {
			System.out.println("Erro, a pilha é vazia!!");
		} catch (PilhaCheiaException _w) {
			System.out.println("Erro, a pilha é cheia!!");
		}
	}

}
