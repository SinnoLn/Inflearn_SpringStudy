package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Getter Setter 메소드를 자동으로 Annotation processing 으로 만들어 줌
//@ToString 어노테이션을 클래스나 필드에 적용하면, 롬복은 해당 클래스의 toString() 메서드를 자동으로 생성
@Getter @Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args){

        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("agrdfs");

        System.out.println("helloLombok = " + helloLombok);
    }
}
