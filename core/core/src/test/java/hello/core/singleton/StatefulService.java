package hello.core.singleton;

//    싱글톤 방식 사용시 여러 클라이언트가  하나의 같은 객체 인스턴스를 공유하기 때문에
//    싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.
//    stateless로 설정해야 한다.

public class StatefulService {

//    private int price; //상태를 유지하는 필드 10000 -> 20000

    public int order(String name, int price){
        System.out.println("name = " + name + ", price = " + price);
//        this.price = price; //여기가 문제! (읽기(참조)만 하는게 아닌 값을 아예 변경)
        return price;
    }

//    public int getPrice(){
//        return price;
//    }


}
