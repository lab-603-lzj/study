/**
 * @Description: 策略模式
 * 该模式定义了一系列算法，并将每个算法封装起来，使它们可以相互替换，且算法的变化不会影响使用算法的客户
 * @author: lzj
 * @date: 2022年05月27日 9:01
 */
public class StrategyTest {
    public static void main(String[] args) {
        Strategy concreteStrategy1 = new ConcreteStrategy1();
        Context context = new Context();
        context.setStrategy(concreteStrategy1);
        context.strategyMethod();
        System.out.println("==========策略1执行完成");

        Strategy concreteStrategy2 = new ConcreteStrategy2();
        context.setStrategy(concreteStrategy2);
        context.strategyMethod();
        System.out.println("+++++++++++策略2执行完成");
    }
}

interface Strategy{
    void strategyMethod();
}

class ConcreteStrategy1 implements Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("this is ConcreteStrategy1 strategyMethod");
    }
}

class ConcreteStrategy2 implements Strategy{
    @Override
    public void strategyMethod() {
        System.out.println("this is ConcreteStrategy2 strategyMethod");
    }
}

class Context{
    private Strategy strategy;

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void strategyMethod(){
        strategy.strategyMethod();
    }
}
