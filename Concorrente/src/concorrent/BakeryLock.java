package concorrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

public class BakeryLock {

	static int nThreads;

	private static AtomicInteger tickets = new AtomicInteger(0);
	private AtomicIntegerArray numeros;

	public BakeryLock() {
		numeros=new AtomicIntegerArray(nThreads);
		for(int i=0; i<nThreads;i++)
			numeros.set(i,0);
	}

	public void lock(int id) {
		// Porta de acesso...
		numeros.set(id, tickets.getAndIncrement());
		// Espere a sua vez...
		for (int j = 0; j < nThreads-1; j++) {
			while ((numeros.get(j) != 0) && ((numeros.get(j) < numeros.get(id))))
				;
		}
	}

	public void unlock(int id) {
		numeros.set(id, 0);
	}
}
