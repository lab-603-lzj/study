/**
 * 继承为类适配模式
 */
public class Adapter extends Target implements Tmp{
    private Target target;

    public Adapter(){

    }
    // 构造器注入为对象适配器
    public Adapter(Target target){
        this.target = target;
    }
    //
    @Override
    public void request() {
        use();
    }

    public void request01(){
        target.use();
    }

    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        System.out.println("类适配器注入：");
        adapter.request();

        Target target = new Target();
        Adapter adapter1 = new Adapter(target);
        System.out.println("对象适配器注入：");
        adapter1.request01();
    }
}



interface Tmp{
    public void request();
}

class Target{
    public void use(){
        System.out.println("使用中。。。。。");
    }
}
