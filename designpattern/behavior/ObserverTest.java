import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * @Description: 观察者模式
 * 观察者模式是一种对象行为型模式，其主要优点如下。
 * 降低了目标与观察者之间的耦合关系，两者之间是抽象耦合关系。符合依赖倒置原则。
 * 目标与观察者之间建立了一套触发机制。
 *
 * 它的主要缺点如下。
 * 目标与观察者之间的依赖关系并没有完全解除，而且有可能出现循环引用。
 * 当观察者对象很多时，通知的发布会花费很多时间，影响程序的效率。
 * @author: lzj
 * @date: 2022年06月07日 13:35
 */
public class ObserverTest {
    public static void main(String[] args) {
        Subject1 subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserver1();
        Observer observer2 = new ConcreteObserver2();
        subject.add(observer1);
        subject.add(observer2);
        subject.notifyObserver();
    }
}

abstract class Subject1{
    protected List<Observer> observers = new ArrayList<>();
    void add(Observer observer){
        observers.add(observer);
    };

    void remove(Observer observer){
        observers.remove(observer);
    };

    abstract void notifyObserver();
}

class ConcreteSubject extends Subject1{
    @Override
    public void notifyObserver() {
        System.out.println("目标发生变化。。。。");
        for (Observer observer : observers) {
            observer.response();
        }
    }
}


interface Observer{
    void response();
}

class ConcreteObserver1 implements Observer{
    @Override
    public void response() {
        System.out.println("concreteObserver1 response");
    }
}

class ConcreteObserver2 implements Observer{
    @Override
    public void response() {
        System.out.println("ConcreteObserver2 response");
    }
}


/**
 * 下面两个是java官方提供的观察者模式的实现
 */

class SysConcreteObserver implements java.util.Observer{
    @Override
    public void update(Observable o, Object arg) {

    }
}

class SysConcreteSubject extends java.util.Observable {

}
