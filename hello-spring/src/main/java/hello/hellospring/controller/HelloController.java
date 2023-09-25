package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    //http://localhost:8080/hello-mvc?name=jinys(여기서 name이 변수)
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";

    }

    @GetMapping("hello-string")
    //이부분 중요! http의 바디부에서 이 데이터를 직접 넣겠다는 의미
    //페이지에서 소스코드를 보면 html태그가 없고 name에 넣은 데이터만 그대로 보여짐
    @ResponseBody //json으로 반환하는게 기본
    public String helloString(@RequestParam("name") String name){

        return "hello " + name; //"hello spring!"
    }

    @GetMapping("hello-api") //json형태로 반환 ex){"name" : "spring!!!"}
    @ResponseBody //더 자세히 알아두기
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //객체가 오면 json방식으로 넘겨주는게 default
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;
        //private라 직접 값을 꺼낼 수 없음 그러므로 get set 메소드를 통해 값을 꺼냄
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
