public class FacadeTest {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.method();
    }
}


class Facade{
    private SubSystem01 system01 = new SubSystem01();
    private SubSystem02 system02 = new SubSystem02();

    public void method(){
        system01.method();
        system02.method();
    }
}

class SubSystem01{
    public void method(){
        System.out.println("SubSystem01的method方法。");
    }
}

class SubSystem02{
    public void method(){
        System.out.println("SubSystem02的method方法。");
    }
}
