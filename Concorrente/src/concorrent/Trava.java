package concorrent;

public enum Trava {
	REENTRANTLOCK_JUSTO, REENTRANTLOCK_INJUSTO, SPIN_REENTRANTLOCK_JUSTO, SPIN_REENTRANTLOCK_INJUSTO, PADARIA;

	public LockAdapter getLock() {
		if (this.equals(REENTRANTLOCK_INJUSTO)) {
			return new ReentrantLockAdapter(false);
		} else if (this.equals(REENTRANTLOCK_JUSTO)) {
			return new ReentrantLockAdapter(true);
		} else if (this.equals(SPIN_REENTRANTLOCK_INJUSTO)) {
			return new SpinReentrantLockAdapter(false);
		} else if (this.equals(SPIN_REENTRANTLOCK_JUSTO)) {
			return new SpinReentrantLockAdapter(true);
		} else if (this.equals(PADARIA)) {
			return new BakeryLockAdapter();
		}
		return null;
	}

}
