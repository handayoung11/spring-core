package hello.core.singleton;

public class StatefulService {

    private int price; //문제: 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //문제: 값이 변경되는 필드
    }

    public int getPrice() {
        return price; //문제: 변경되는 필드에 의존
    }
}
