/**
 * 相同的方法，不同的执行顺序，产生不同的结果。
 * 多个部件或零件，都可以装配到一个对象中，但是产生的结果又不相同。
 * 产品类非常复杂，或者产品类中不同的调用顺序产生不同的作用。
 * 初始化一个对象特别复杂，参数多，而且很多参数都具有默认值。
 */
public class BuilderTest {
    private String property01;
    private String property02;
    private String property03;

    private BuilderTest(Builder builder){
        this.property01 = builder.property01;
        this.property02 = builder.property02;
        this.property03 = builder.property03;
    }

    public static class Builder{
        private String property01;
        private String property02;
        private String property03;

        public Builder set01(String property01){
            this.property01 = property01;
            return this;
        }

        public Builder set02(String property02){
            this.property02 = property02;
            return this;
        }

        public Builder set03(String property03){
            this.property03 = property03;
            return this;
        }

        public BuilderTest builder(){
            return new BuilderTest(this);
        }

    }

    public void show(){
        System.out.println("proper01:"+property01+"\nproper02:"+property02+"\nproper03:"+property03);
    }

    public String getProperty01() {
        return property01;
    }

    public void setProperty01(String property01) {
        this.property01 = property01;
    }

    public String getProperty02() {
        return property02;
    }

    public void setProperty02(String property02) {
        this.property02 = property02;
    }

    public String getProperty03() {
        return property03;
    }

    public void setProperty03(String property03) {
        this.property03 = property03;
    }

    public static void main(String[] args) {
        String p01 = "属性01";
        String p02 = "属性02";
        String p03 = "属性03";

        Builder builder = new Builder();
        builder.set01(p01).set02(p02).set03(p03).builder().show();

    }
}
