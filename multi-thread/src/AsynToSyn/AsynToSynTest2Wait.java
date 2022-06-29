package AsynToSyn;

/**
 * @Description: wait/notify组合使用
 * 对于线程来说，阻塞与唤醒是被动的，当多个线程基于不同条件在同一个队列上等待时，使用notify很容易出现信号丢失的问题，需要谨慎使用
 * @author: lzj
 * @date: 2022年06月29日 14:47
 */
public class AsynToSynTest2Wait extends BaseDemo{
	private final Object lock = new Object();

	@Override
	public void callback(long response) {
		System.out.println("得到结果");
		System.out.println(response);
		System.out.println("调用结束");

		synchronized (lock) {
			lock.notifyAll();
		}
	}

	public static void main(String[] args) {
		AsynToSynTest2Wait wait  = new AsynToSynTest2Wait();

		wait.call();

		synchronized (wait.lock){
			try {
				wait.lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("主线程执行");
	}
}
