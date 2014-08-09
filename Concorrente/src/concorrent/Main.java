package concorrent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {

	static CyclicBarrier cb;
	static CyclicBarrier cbTime;
	static int numero = 1;
	static FileWriter fw;
	static long antes;

	public static void main(String[] args) {

		for (int i = 0; i < 1; i++) {

			File file = new File("saida" + numero + ".txt");
			try {
				fw = new FileWriter(file);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			int tamArray = 4;
			int nThreads = 8;

			executa(tamArray, nThreads, Trava.REENTRANTLOCK_INJUSTO);
			executa(tamArray, nThreads, Trava.REENTRANTLOCK_JUSTO);
			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_INJUSTO);
			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_JUSTO);
			executa(tamArray, nThreads, Trava.PADARIA);

//			tamArray = 8;
//			nThreads = 8;
//
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.PADARIA);
//
//			tamArray = 16;
//			nThreads = 8;
//
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.PADARIA);
//
//			tamArray = 4;
//			nThreads = 4;
//
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.PADARIA);
//
//			tamArray = 8;
//			nThreads = 4;
//
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.PADARIA);
//
//			tamArray = 16;
//			nThreads = 4;
//
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.PADARIA);
//
//			tamArray = 4;
//			nThreads = 2;
//
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.PADARIA);
//
//			tamArray = 8;
//			nThreads = 2;
//
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.PADARIA);
//
//			tamArray = 16;
//			nThreads = 2;
//
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_INJUSTO);
//			executa(tamArray, nThreads, Trava.SPIN_REENTRANTLOCK_JUSTO);
//			executa(tamArray, nThreads, Trava.PADARIA);

		}
		
		
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void executa(int tamArray, int nThreads, Trava trava) {
		// É preciso settar o número de Threads
		BakeryLock.nThreads = nThreads;
		cb = new CyclicBarrier(nThreads + 1);
		cbTime = new CyclicBarrier(nThreads + 1);

		Alterador.montaArray(tamArray, trava);
		for (int i = 0; i < nThreads; i++) {
			new Alterador(i).start();
		}
		criaBarreira(cbTime, true);
		criaBarreira(cb,false);
		try {
			fw.write(// "Configuracoes: " + tamArray + " tam " + nThreads
						// + " threads " + trava.name() + " trava " + " tempo: "
			+(System.currentTimeMillis() - antes)
					+ (System.getProperty("line.separator")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// imprimeArrayFinal(Alterador.getArray());
	}

	private static void imprimeArrayFinal(int[] array) {
		System.out.print("ARRAY FINAL [ ");
		for (int i = 0; i < array.length; i++) {
			System.out.print(" " + array[i] + " ");
		}
		System.out.print(" ]");
	}

	public static void criaBarreira(CyclicBarrier barreira, boolean calcular) {
		try {
			barreira.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		} finally {
			if(calcular){
				antes = System.currentTimeMillis();
			}
		}
	}

}
