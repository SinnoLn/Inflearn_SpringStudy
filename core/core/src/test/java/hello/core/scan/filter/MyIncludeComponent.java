package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //class level에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented

//붙어있을 경우 component scan에 추가한다는 의미
public @interface MyIncludeComponent {

}


