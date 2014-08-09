package concorrent;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Alterador extends Thread {
	private static final int NUM_DE_ALTERACOES = 7500;
	private static int[] arrayInt;
	private static LockAdapter[] lockArray;
	private int id;

	public Alterador(int id) {
		this.id = id;
	}

	public static void montaArray(int tamanho, Trava t ) {
		arrayInt = new int[tamanho];
		lockArray = new LockAdapter[tamanho];
		for (int i = 0; i < lockArray.length; i++) {
			lockArray[i] = t.getLock();
		}
	}

	@Override
	public void run() {
		Main.criaBarreira(Main.cbTime, false);
		for (int i = 0; i < NUM_DE_ALTERACOES; i++) {
			Random rnd = new Random();
			int num = rnd.nextInt(arrayInt.length);
			alteraPosicao(num);
		}
		Main.criaBarreira(Main.cb, false);
	}

	private void alteraPosicao(int pos) {
		lockArray[pos].lock(id);
		arrayInt[pos]++;
		//System.out.println("ALTERADOR " + id + " POSICAO " + pos + " == "
			//	+ arrayInt[pos]);
		lockArray[pos].unlock(id);
	}

	public static int[] getArray() {
		return arrayInt;
	}
}
