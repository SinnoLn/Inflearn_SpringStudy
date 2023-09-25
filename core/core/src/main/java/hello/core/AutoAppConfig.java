package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basepackages 에 적힌 것만 component Scan 의 대상
        //만약 hello.core.member 라면 그 파일 안에 있는 것만 Component Scan의 대상이 됨
        basePackages = "hello.core",
        basePackageClasses = AutoAppConfig.class, //지정한 패키지 안에서 부터 찾아 들어감
        //만약 위에처럼 package를 지정해주지 않는다면?
        //AutoConfig부터 시작하여 얘랑 얘 하위 패키지를 전부 뒤짐
        //즉 AutoAppConfig의 위치가 hello.core이므로 hello.core 패키지를 전부 뒤짐
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
