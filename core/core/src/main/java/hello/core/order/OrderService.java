package hello.core.order;

public interface OrderService {
    
    //주문 생성에 대한 함수 (회원 id, itemName, itemPrice)
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
