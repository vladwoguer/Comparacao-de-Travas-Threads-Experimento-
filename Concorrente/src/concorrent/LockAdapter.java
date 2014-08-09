package concorrent;

public interface LockAdapter {
	
	public void lock(int id);
	
	public void unlock(int id);

}
