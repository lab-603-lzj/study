package AsynToSyn;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description: CyclicBarrier
 * CountDownLatch做加法，CyclicBarrier做减法。初始化为2表示主线程也算一个线程，同时，全部await的时候才能继续执行
 * @author: lzj
 * @date: 2022年06月29日 18:47
 */
public class AsynToSynTest5CyclicBarrier extends BaseDemo{

	private final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

	@Override
	public void callback(long response) {
		System.out.println("得到结果");
		System.out.println(response);
		System.out.println("调用结束");

		try {
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		AsynToSynTest5CyclicBarrier barrier = new AsynToSynTest5CyclicBarrier();

		barrier.call();

		try {
			barrier.cyclicBarrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

		System.out.println("主线程运行");
	}
}
