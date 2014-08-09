package concorrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SpinReentrantLockAdapter implements LockAdapter{

	private Lock lock;
	
	public SpinReentrantLockAdapter(boolean fair) {
		lock = new ReentrantLock(fair);
	}
	
	@Override
	public void lock(int id) {
		while(!lock.tryLock());
	}

	@Override
	public void unlock(int id) {
		lock.unlock();
	}
	
	

}
