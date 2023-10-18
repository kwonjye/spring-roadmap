package hello.core.singleton;

public class StatefulService {

//    private int price; // 상태를 유지하는 필드 10000 -> 20000
    // 스프링 빈은 항상 무상태(stateless)로 설계!

    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
//        this.price = price; // 여기가 문제!
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
