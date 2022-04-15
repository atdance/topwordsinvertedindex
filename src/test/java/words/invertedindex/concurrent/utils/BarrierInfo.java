package words.invertedindex.concurrent.utils;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * Collects info at defined barrier points
 */
final public class BarrierInfo {

	private static final AtomicBoolean firstToExecute = new AtomicBoolean(true);
	private static final AtomicBoolean firstToDone = new AtomicBoolean(true);
	private static final AtomicBoolean firstConstructed = new AtomicBoolean(true);
	private static final AtomicBoolean firstToRun = new AtomicBoolean(true);

	private static AtomicLong time;

	private final static AtomicReference<StringBuffer> sb = new AtomicReference<>(new StringBuffer());

	public static final void firstConstructed() {
		if (firstConstructed.get()) {
			firstConstructed.set(false);

			time = new AtomicLong(System.nanoTime());
		}
	}

	public static final void firstToRun() {
		if (firstToRun.get()) {
			firstToRun.set(false);

			saveInfo(" firstToRun ", 0);

			time.set(System.nanoTime());
		}
	}

	public static final void phaseOne(int inBarrierCount) {
		if (firstToExecute.get()) {
			firstToExecute.set(false);

			saveInfo(" Phase 1   ", inBarrierCount);

			time.set(System.nanoTime());

			MyThreadInfo.printInfo();
		}
	}

	public static final void phaseTwo(int inBarrierCount) {
		if (firstToDone.get()) {
			firstToDone.set(false);
			saveInfo(" Phase 2   ", inBarrierCount);
		}
	}

	private static final void saveInfo(String msg, int inBarrierCount) {
		sb.get().append(Thread.currentThread().getName() + msg + " in barrier " + inBarrierCount + " time "
				+ (System.nanoTime() - time.longValue()) / 1_000_000);
	}

	public static final String getInfo(String msg, int inBarrierCount) {
		return sb.get().toString();
	}

}