package AsynToSyn;

import java.util.Random;

/**
 * @Description: 异步转同步方法1:轮询
 * 线程将反复在休眠与测试条件中切换	，直到超时或状态满足继续往下
 * 超时时间不好控制，休眠时间需要在响应与cpu利用率之间权衡
 * @author: lzj
 * @date: 2022年06月29日 14:06
 */
public class AsynToSynTest1Poll {

	public static void main(String[] args) throws InterruptedException {
		Random random = new Random(System.currentTimeMillis());

		Thread thread = new Thread(() -> {
			System.out.println("发起调用");
			long res = random.nextInt(10);
			try {
				Thread.sleep(res * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("调用结束");
		});

		thread.start();

		while (thread.isAlive()){
			System.out.println("子线程还未结束");
			Thread.sleep(1000);
		}

		System.out.println("主线程执行");

	}

}


