package hello.core.scan.filter;
import java.lang.annotation.*;

@Target(ElementType.TYPE) //class level에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented

//붙은것은 component scan에서 제외
public @interface MyExcludeComponent {
}

//annotation 2개 생성 (MyExcludeComponent, MyIncludeComponent)
