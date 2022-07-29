package other;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Description:
 * @author: lzj
 * @date: 2022年07月01日 9:30
 */
public class testThreadLocalMpa {
	static ThreadLocal<Map<String,String>> localMap = new ThreadLocal<>();
	static volatile Map<String,Integer> map = new HashMap<>();


	public static void main(String[] args) throws InterruptedException {

		Runnable runnable = () -> {
			map.put("1", Optional.ofNullable(map.get("1")).orElse(0)+1);
		};
		Runnable runnable2 = () -> {
			map.put("2", Optional.ofNullable(map.get("2")).orElse(0)+1);
		};

		Thread thread11 = new Thread(runnable);
		Thread thread12 = new Thread(runnable);
		Thread thread13 = new Thread(runnable);

		Thread thread21 = new Thread(runnable2);
		Thread thread22 = new Thread(runnable2);
		Thread thread23 = new Thread(runnable2);

		thread11.start();
		thread21.start();
		Thread.sleep(1000);
		map.forEach((key,value) -> {
			System.out.println(key + ":" +value);
		});

		System.out.println("--------------------");

		thread12.start();
		thread22.start();
		Thread.sleep(1000);
		map.forEach((key,value) -> {
			System.out.println(key + ":" +value);
		});
		System.out.println("---------------------");

		thread13.start();
		thread23.start();
		Thread.sleep(1000);
		map.forEach((key,value) -> {
			System.out.println(key + ":" +value);
		});
	}
}
