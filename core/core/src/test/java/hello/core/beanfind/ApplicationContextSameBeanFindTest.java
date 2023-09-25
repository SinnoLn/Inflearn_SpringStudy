package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 오류 발생")
    void findBeanByTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, ()->
                ac.getBean(MemoryMemberRepository.class)); //반드시 이 예외가 나와야 성공임
    }

    //static을 사용한다는건 이 안에서만 사용하겠다는 의미

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 하나는 빈 이름을 지정")
    void findBeanByName(){
        MemoryMemberRepository bean = ac.getBean("memberRepository1", MemoryMemberRepository.class);
        assertThat(bean).isInstanceOf(MemoryMemberRepository.class);

    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) { //iter
            System.out.println("key = " + key + "value= " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemoryMemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }
        @Bean
        public MemoryMemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }

    }
}
