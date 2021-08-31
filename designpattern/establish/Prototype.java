package designPattern.establish;

public class Prototype implements Cloneable {

    public String tmp = "test";


    public Prototype clone() throws CloneNotSupportedException {
        return (Prototype) super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Prototype prototype = new Prototype();
        Prototype prototype1 = prototype.clone();
        System.out.println(prototype1.tmp);
    }
}
