package designPattern.structure;


/**
 * 桥接模式通过注入接口，从而调用接口中的方法获取对象信息
 */
public abstract class Bridge {
    protected Material material;

    protected Bridge(Material material){
        this.material = material;
    }

    abstract void build();

}

class RailWayBridge extends Bridge{
    @Override
    void build() {
        material.getMaterial();
        System.out.println("建铁路桥。");
    }

    protected RailWayBridge(Material material) {
        super(material);
    }

}

interface Material{
    public void getMaterial();
}

class Wood implements Material{
    @Override
    public void getMaterial() {
        System.out.println("材料是木头。");
    }
}

class Blood implements Material{
    @Override
    public void getMaterial() {
        System.out.println("材料是骨头。");
    }
}

class BridgeTest{
    public static void main(String[] args) {
        Material material = new Blood();
        Bridge bridge1 = new RailWayBridge(material);
        bridge1.build();
    }
}