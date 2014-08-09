package concorrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockAdapter implements LockAdapter {

	private Lock lock;
	
	public ReentrantLockAdapter(boolean fair) {
		lock = new ReentrantLock(fair);
	}
	
	@Override
	public void lock(int id) {
		lock.lock();
	}

	@Override
	public void unlock(int id) {
		lock.unlock();
	}

}
