package words.invertedindex.concurrent.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

public class MyThreadInfo {

	/**
	 * Collects selected Thread information
	 *
	 *
	 * This is to be called with caution as it can be resource intensive if
	 * ManagementFactory.getThreadMXBean().dumpAllThreads is called many times
	 */
	static final void printInfo() {

		ThreadInfo[] infos = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);

		if (infos.length < 1) {
			return;
		}

		boolean canContinue = true;

		for (ThreadInfo info : infos) {

			String name = info.getThreadName();

			if (name.equalsIgnoreCase("Finalizer") || name.equalsIgnoreCase("Reference Handler")
					|| name.equalsIgnoreCase("Attach Listener") || name.equalsIgnoreCase("Signal Dispatcher")) {

				canContinue = false;

				break;
			}
		}

		if (!canContinue) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		for (ThreadInfo info : infos) {
			String name = info.getThreadName();

			if (info.getBlockedCount() > 0) {
				sb.append(name + " blocked " + info.getBlockedCount());
			}

			if (info.getWaitedCount() > 0) {
				sb.append(name + "   wait " + info.getWaitedCount());
			}

			if (info.getLockedMonitors().length > 0) {
				sb.append(name + " monitors " + info.getLockedMonitors().length);
			}

			if (info.getLockedSynchronizers().length > 0) {
				sb.append(name + " synchro " + info.getLockedSynchronizers().length);
			}

			if (info.getLockInfo() != null) {
				sb.append(name + " lockinfo " + info.getLockInfo().getClassName());
			}
		}
		if (sb.length() > 0) {
			System.out.println(sb.toString());
		}
	}

}