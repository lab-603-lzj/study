package designPattern.establish;

public class AbstractFactory {
    public static void main(String[] args) {
        farm01 farm01 = new farm01();
        farm02 farm02 = new farm02();
        farm01.newAnimal().show();
        farm01.newPlant().show();
    }
}

interface farm{
    Plant newPlant();
    Animal newAnimal();
}


interface Plant{
    public void show();
}

interface Animal{
    public void show();
}

class Fruitage implements Plant{
    @Override
    public void show() {
        System.out.println("this is Fruitage!");
    }
}

class Vegetables implements Plant{
    @Override
    public void show() {
        System.out.println("this is Vegetables!");
    }
}

class Horse implements Animal{
    @Override
    public void show() {
        System.out.println("this is horse!");
    }
}

class Cattle implements Animal{
    @Override
    public void show() {
        System.out.println("this is Cattle");
    }
}


class farm01 implements farm{
    @Override
    public Plant newPlant() {
        return new Vegetables();
    }

    @Override
    public Animal newAnimal() {
        return new Cattle();
    }
}

class farm02 implements farm{
    @Override
    public Plant newPlant() {
        return new Fruitage();
    }

    @Override
    public Animal newAnimal() {
        return new Horse();
    }
}