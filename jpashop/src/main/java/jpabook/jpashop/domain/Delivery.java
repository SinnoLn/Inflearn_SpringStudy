package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    // ORDINAL 로 쓸 경우 새로운 상태가 중간에 생기면 12에서 123처럼 상태의 숫자가 밀린다.
    // READY 와 COMP 사이에 새로운 상태가 들어갈 경우 망함, 반드시 STRING 로 써주자
    @Enumerated(EnumType.STRING) //Default ORDINAL (숫자로 들어감 EX) READY=1, COMP=2
    private DeliveryStatus status; //READY, C0MP
}
