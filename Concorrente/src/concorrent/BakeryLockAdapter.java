package concorrent;

public class BakeryLockAdapter implements LockAdapter{

	private BakeryLock lock;
	
	public BakeryLockAdapter() {
		lock = new BakeryLock();
	}

	@Override
	public void lock(int id) {
		lock.lock(id);
	}

	@Override
	public void unlock(int id) {
		lock.unlock(id);
	}
	
	
	
}
