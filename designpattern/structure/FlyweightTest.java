import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式类似于工厂模式，但是为享元单位添加了缓存
 */
public class FlyweightTest {
    public static void main(String[] args) {
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight flyweight = factory.getFlyweight("a");
        Flyweight flyweight1 = factory.getFlyweight("a");
        Flyweight flyweight2 = factory.getFlyweight("b");
        Flyweight flyweight3 = factory.getFlyweight("b");
        flyweight.operation(new UnFlyweight("第一次调用a"));
        flyweight1.operation(new UnFlyweight("第二次调用a"));
        flyweight2.operation(new UnFlyweight("第一次调用b"));
        flyweight3.operation(new UnFlyweight("第二次调用b"));
    }
}

class UnFlyweight{
    private String info;

    public UnFlyweight(String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

interface Flyweight{
    public void operation(UnFlyweight unFlyweight);
}

class ConcreteFlyweight implements Flyweight{

    private String key;

    public ConcreteFlyweight(String key){
        this.key = key;
        System.out.println("key为："+key);
    }

    @Override
    public void operation(UnFlyweight unFlyweight) {
        System.out.println("key为："+this.key);
        System.out.println("非享元为："+unFlyweight.getInfo());
    }
}

class FlyweightFactory{
    private Map<String,Flyweight> map = new HashMap<>();

    public Flyweight getFlyweight(String key){
        Flyweight flyweight = map.get(key);
        if (flyweight != null){
            System.out.println(key+"key已存在,被成功获取。");
        }else {
            flyweight = new ConcreteFlyweight(key);
            map.put(key,flyweight);
        }
        return flyweight;
    }
}
