package AsynToSyn;

import java.util.Random;

/**
 * @Description:
 * @author: lzj
 * @date: 2022年06月29日 19:14
 */
public abstract class BaseDemo {
	protected AsyncCall asyncCall = new AsyncCall();

	public abstract void callback(long response);

	public void call(){
		System.out.println("发起调用");
		asyncCall.call(this);
		System.out.println("调用结束");
	}

	class AsyncCall{
		private Random random = new Random(System.currentTimeMillis());
		public void call(BaseDemo demo){
			new Thread(() -> {
				long res = random.nextInt(10);

				try {
					Thread.sleep(res * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				demo.callback(res);
			}).start();
		}
	}
}
