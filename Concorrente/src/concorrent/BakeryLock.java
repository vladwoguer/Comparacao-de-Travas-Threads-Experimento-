package concorrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class BakeryLock {

	static int nThreads;

	private static AtomicInteger tickets = new AtomicInteger(0);
	private int[] num;

	public BakeryLock() {
		num = new int[nThreads];
	}

	public void lock(int id) {
		System.out.println(id);
		// Porta de acesso...
		num[id] = tickets.getAndIncrement();

		// Espere a sua vez...
		for (int j = 0; j < nThreads; j++) {
			while ((num[j] != 0) && ((num[j] < num[id])))
				;
		}
	}

	public void unlock(int id) {
		num[id] = 0;
	}
}
