package AsynToSyn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: Lock Condition。
 * @author: lzj
 * @date: 2022年06月29日 18:37
 */
public class AsynToSynTest3LockCondition extends BaseDemo {

	private final Lock lock = new ReentrantLock();
	private final Condition cond = lock.newCondition();

	@Override
	public void callback(long response) {
		System.out.println("得到结果");
		System.out.println(response);
		System.out.println("调用结束");

		// 与使用wait的方式相比，这里不用synchronized关键字
		lock.lock();

		//synchronized (lock) {
			try {
				cond.signal();
			} finally {
				lock.unlock();
			}
		//}
	}

	public static void main(String[] args) {
		AsynToSynTest3LockCondition condition = new AsynToSynTest3LockCondition();

		condition.call();

		condition.lock.lock();

		//synchronized (condition.lock) {
			try {
				condition.cond.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				condition.lock.unlock();
			}
		//}

		System.out.println("主线程执行");

	}
}
