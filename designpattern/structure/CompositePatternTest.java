package designPattern.structure;

import java.util.ArrayList;

/**
 * 组合模式适用于部分与整体的结构。
 * 叶子节点
 */
public class CompositePatternTest {
    public static void main(String[] args) {
        Components c1 = new Composite();
        Components c2 = new Composite();
        Components l1 = new Leaf("1");
        Components l2 = new Leaf("2");
        c1.add(l1);
        c2.add(l2);
        l1.operation();
    }

}

interface Components {
    void add(Components c);
    Components getChild(int i);
    void remove(Components c);
    void operation();
}

/**
 * 透明模式
 */
class Leaf implements Components{
    private String name;

    public Leaf(String name){
        this.name = name;
    }
    @Override
    public void add(Components c) {

    }

    @Override
    public Components getChild(int i) {
        return null;
    }

    @Override
    public void remove(Components c) {

    }

    @Override
    public void operation() {
        System.out.println("树叶"+this.name+"被访问。");
    }
}

class Composite implements Components{
    private ArrayList<Components> children = new ArrayList<>();

    @Override
    public void add(Components c) {
        children.add(c);
    }

    @Override
    public Components getChild(int i) {
        return children.get(i);
    }

    @Override
    public void remove(Components c) {
        children.remove(c);
    }

    @Override
    public void operation() {
        for (Components c: children) {
            c.operation();
        }
    }
}




