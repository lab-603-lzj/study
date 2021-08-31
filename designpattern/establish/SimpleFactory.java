package designPattern.establish;

public class SimpleFactory {
    public static Produce makeProduce(String kind){
        switch (kind){
            case "apple": return new AppleProduce();
            case "banana": return new BananaProduce();
        }
        return null;
    }

    public static void main(String[] args) {
        Produce produce = makeProduce("apple");
        produce.shell();
    }


}

interface Produce{
    public void shell();
}

class AppleProduce implements Produce{
    @Override
    public void shell() {
        System.out.println("this is a apple!");
    }
}

class BananaProduce implements Produce{
    @Override
    public void shell() {
        System.out.println("this is a banana!");
    }
}


