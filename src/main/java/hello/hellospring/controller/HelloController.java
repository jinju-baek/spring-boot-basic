package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    private final MemberService memberService;

    public HelloController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        // API 방식(반환타입 : Spring)
        // @ResponseBody를 이용해 http body에 데이터를 담아 전송한다.
        // ViewResolver가 아닌 HttpMessageConverter가 작동한다.
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        // API 방식(반환타입 : 객체)
        // 반환타입이 "String"일 경우 HttpMessageConverter(StringHttpMessageConverter)이 작동한다.
        // 반환타입이 "객체"일 경우 HttpMessageConverter(MappingJackson2HttpMessageConverter)이 작동한다.
        // ** 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보 둘을 조합하여 HttpMessageConverter가 선택된다.
        Hello hello = new Hello();
        hello.setName("spring!!");
        return hello;
    }

    static class Hello {
        private String name;

        // 프로퍼티 방식
        // getter/setter를 설정하여 값을 넣거나 얻는다.
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
