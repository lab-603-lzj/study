import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: lzj
 * @date: 2022年06月07日 15:23
 */
public class MiddlePatternTest {
    public static void main(String[] args) {
        Mediator md = new ConcreteMediator();
        Colleague c1,c2;
        c1 = new ConcreteColleague1();
        c2 = new ConcreteColleague2();
        md.register(c1);
        md.register(c2);
        c1.send("mmm");
        System.out.println("-----------------");
        c2.send("pppp");
    }
}

abstract class Mediator{
    public abstract void register(Colleague colleague);

    public abstract void reply(Colleague colleague,String msg);
}

class ConcreteMediator extends Mediator{
    private List<Colleague> colleagueList = new ArrayList<>();

    @Override
    public void register(Colleague colleague) {
        if (!colleagueList.contains(colleague)){
            colleagueList.add(colleague);
            colleague.setMedium(this);
        }
    }

    @Override
    public void reply(Colleague colleague,String msg) {
        for (Colleague ob: colleagueList){
            if (!ob.equals(colleague)){
                ob.receive(msg);
            }
        }
    }
}

abstract class Colleague {
    protected Mediator mediator;

    public void setMedium(Mediator mediator){
        this.mediator = mediator;
    }

    public abstract void receive(String msg);

    public abstract void send(String msg);
}

class ConcreteColleague1 extends Colleague{

    @Override
    public void receive(String msg) {
        System.out.println("同事1收到消息:"+msg);
    }

    @Override
    public void send(String msg) {
        System.out.println("同事1发送了消息:"+msg);
        mediator.reply(this,msg);
    }
}

class ConcreteColleague2 extends Colleague{
    @Override
    public void receive(String msg) {
        System.out.println("同事2收到消息:"+msg);
    }

    @Override
    public void send(String msg) {
        System.out.println("同事2发送了消息:"+msg);
        mediator.reply(this,msg);
    }
}
