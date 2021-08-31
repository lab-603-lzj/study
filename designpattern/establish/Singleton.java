// 饿汉模式是在类初始化时便实例化，懒汉模式是第一次调用getInstance时实例化
// 双重校验模式相对与懒汉模式，没有线程安全问题

public class Singleton{
    // 静态属性，volatile保证可见性和禁止指令重排序
    private volatile static Singleton instance = null;
    // 私有化构造器
    private Singleton(){}

    public static  Singleton getInstance(){
        // 第一重检查锁定
        if(instance==null){
            // 同步锁定代码块
            synchronized(Singleton.class){
                // 第二重检查锁定
                if(instance==null){
                    // 注意：非原子操作
                    instance=new Singleton();
                }
            }
        }
        return instance;
    }
}

class LazySingleton {
    private static volatile LazySingleton instance = null;    //保证 instance 在所有线程中同步
    private LazySingleton() {
    }    //private 避免类在外部被实例化
    public static synchronized LazySingleton getInstance() {
        //getInstance 方法前加同步
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}


class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();
    private HungrySingleton() {
    }
    public static HungrySingleton getInstance() {
        return instance;
    }
}
