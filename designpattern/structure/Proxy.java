package designPattern.structure;

public class Proxy {
    private Subject subject;

    public void request(){
        if (subject==null){
            subject = new Subject();
        }
        pre();
        subject.request();
        post();
    }

    public void pre(){
        System.out.println("访问前。");
    }

    public void post(){
        System.out.println("访问后。");
    }

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}

class Subject{
    public void request(){
        System.out.println("访问主体。");
    }
}