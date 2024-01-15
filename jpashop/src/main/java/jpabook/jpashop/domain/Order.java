package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //주문 회원

    //Order 엔티티와 관련된 OrderItem 및 Delivery 엔티티에 대한 영속성 작업을 제어
    //Order 저장, 제거시 연관된 엔티티 자동 변경
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery; //배송 정보

    //order_date
    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태 [ORDER, CANCEL]

    //==연관관계 메서드==// 양방향 연관관계를 한번의 매소드 호출로 완전하게 설정
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    //연관관계를 한쪽 방향으로만 설정
/*    public static void main(String[] args){
        Member member = new Member();
        Order order = new Order();

        order.setMember(member);
    }*/

    //==생성 메서드==// (생성을 할 때 부터 create order을 무조건 호출)
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

        //==비즈니스 로직==//
        /**
         * 주문 취소
         */
        public void cancel() {
            if (delivery.getStatus() == DeliveryStatus.COMP) {
                throw new IllegalStateException("이미 배송 완료된 상품은 취소가 불가능 합니다.");
            }

            this.setStatus(OrderStatus.CANCEL);
            for (OrderItem orderItem : orderItems) {
                orderItem.cancel();
            }
        }

        //==조회 로직==//
    /**
     * 전체 주문 가격 조회
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for(OrderItem orderItem :orderItems){
            totalPrice += orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}

