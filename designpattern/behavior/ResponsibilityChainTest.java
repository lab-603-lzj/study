/**
 * @Description: 责任链模式
 *为了避免请求发送者与多个请求处理者耦合在一起，于是将所有请求的处理者通过前一对象记住其下一个对象的引用而连成一条链；当有请求发生时，可将请求沿着这条链传递，直到有对象处理它为止。
 * 多个对象可以处理一个请求，但具体由哪个对象处理该请求在运行时自动确定。
 * 可动态指定一组对象处理请求，或添加新的处理者。
 * 需要在不明确指定请求处理者的情况下，向多个处理者中的一个提交请求。
 * @author: lzj
 * @date: 2022年05月27日 10:36
 */
public class ResponsibilityChainTest {
    // 客户类
    public static void main(String[] args) {
        ConcreteHandler1 handler1 = new ConcreteHandler1();
        ConcreteHandler2 handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);
        handler1.handleRequest("two");
    }
}

abstract class Handler{
    private Handler next;

    public void setNext(Handler next) {
        this.next = next;
    }

    public Handler getNext() {
        return next;
    }

    public abstract void handleRequest(String request);
}

class ConcreteHandler1 extends Handler{
    @Override
    public void handleRequest(String request) {
        if (request.equals("one")){
            System.out.println("handler1 处理了该请求");
        }else {
            if (getNext()!=null){
                getNext().handleRequest(request);
            }else {
                System.out.println("无人处理该请求");
            }
        }
    }
}

class ConcreteHandler2 extends Handler{
    @Override
    public void handleRequest(String request) {
        if (request.equals("two")){
            System.out.println("handler2 处理了该请求");
        }else {
            if (getNext()!=null){
                getNext().handleRequest(request);
            }else {
                System.out.println("无人处理该请求");
            }
        }
    }
}
