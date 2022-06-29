package excutor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @author: lzj
 * @date: 2022年06月27日 16:20
 */
public class ScheduleExcutorTest {
	private static ScheduledExecutorService executorService;

	public static void main(String[] args) {
		int abc = 1;

		executorService = Executors.newScheduledThreadPool(1, new ThreadFactory() {
			private AtomicInteger count = new AtomicInteger(0);
			@Override
			public Thread newThread(Runnable r) {
				int counter = count.incrementAndGet();
				System.out.println("线程创建counter = "+counter);

				Thread thread = new Thread(r);
				thread.setName("测试线程"+counter);
				return thread;
			}
		});

		executorService.scheduleAtFixedRate(((
				new Runnable() {
					@Override
					public void run() {

						System.out.println("开始thead = " + Thread.currentThread().getId()
								+ "threadname = " + Thread.currentThread().getName()
								+ "时间" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					}
				}
				)),1,5, TimeUnit.SECONDS);

	}
}
