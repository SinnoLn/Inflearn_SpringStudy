package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
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

    //수동 등록 빈과 자동 등록 빈이 충돌이 나는 경우 수동 등록 빈이 우선권을 가진다.
    //수동 빈이 자동 빈을 오버라이딩
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
    
    //스프링부트 최신 버전은 오류가 수동 빈과 자동 빈의 오류가 나면 에러나도록 바뀜
}
