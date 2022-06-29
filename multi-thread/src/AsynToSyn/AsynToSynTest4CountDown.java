package AsynToSyn;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: 利用CountDownLatch的await阻塞当前线程，在其他方法中使用countDown来使被阻塞的继续运行
 * 最常用的异步转同步的方法
 * @author: lzj
 * @date: 2022年06月29日 18:46
 */
public class AsynToSynTest4CountDown  extends BaseDemo{

	private final CountDownLatch countDownLatch = new CountDownLatch(1);

	@Override
	public void callback(long response) {
		System.out.println("得到结果");
		System.out.println(response);
		System.out.println("调用结束");

		countDownLatch.countDown();
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		AsynToSynTest4CountDown countDown = new AsynToSynTest4CountDown();

		AsynToSynTest4CountDown countDown2 = new AsynToSynTest4CountDown();

		countDown.call();
		countDown2.call();

		try {
			countDown.countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
		System.out.println("子线程执行"+(end-start)/1000);

		System.out.println("主线程执行");
	}
}
