public class DecoratorTest {
    public static void main(String[] args) {
        ConcreteComponent component = new ConcreteComponent();
        System.out.println("--------------------------");
        component.operation();
        System.out.println("----------------");
        ConcreteDecorator decorator = new ConcreteDecorator(component);
        System.out.println("-------------------");
        decorator.operation();
    }
}


interface Component{
    public void operation();
}

class ConcreteComponent implements Component{
    public ConcreteComponent(){
        System.out.println("创建构建。");
    }

    @Override
    public void operation() {
        System.out.println("调用构建方法。");
    }
}

class Decorator implements Component{
    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}

class ConcreteDecorator extends Decorator{
    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        addFunction();
    }

    public void addFunction(){
        System.out.println("构建新增功能。");
    }
}
