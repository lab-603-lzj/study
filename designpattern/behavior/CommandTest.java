/**
 * @Description: 命令模式。常用的controller、server就类似于这样的模式，但其中ConcreteCommand1与ReceiverA的关系更紧密
 * 将一个请求封装为一个对象，使发出请求的责任和执行请求的责任分割开
 * @author: lzj
 * @date: 2022年05月27日 10:05
 */
public class CommandTest {
    private Command command;

    void setCommand(Command command) {
        this.command = command;
    }

    void cell(){
        this.command.execute();
    }

    public static void main(String[] args) {
        System.out.println("开始发送命令cell（）");
        CommandTest commandTest = new CommandTest();
        ConcreteCommand1 command1 = new ConcreteCommand1();
        commandTest.setCommand(command1);
        commandTest.cell();
    }
}

interface Command{
    void execute();
}

class ConcreteCommand1 implements Command{
    private ReceiverA receiverA;

    public ConcreteCommand1() {
        this.receiverA = new ReceiverA();
    }

    @Override
    public void execute() {
        receiverA.action();
    }
}


class ReceiverA{

    public void action(){
        System.out.println("this is receiverA");
    }
}
