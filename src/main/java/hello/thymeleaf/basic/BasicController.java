package hello.thymeleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello Spring!");
        model.addAttribute("jimin", "itemJimin");
        return "basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String textUnescaped(Model model){
        model.addAttribute("data", "<b>Hello Spring!</b>");
        model.addAttribute("jimin", "itemJimin");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model){
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA" , userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users",list);
        model.addAttribute("userMap",map);

        return "basic/variable";
    }

    //http session 은 유저가 종료하기 전까지 유지된다. 뒤에서 좀더 자세히 설명해준다.
    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session){
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }

    //컴포넌트 보여주기 위한 예제. Bean 등록
    @Component("helloBean")
    public class HelloBean{
        public String hello(String data){
            return "Hello " + data;
        }
    }

    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("link")
    public String link(Model model){
        model.addAttribute("param1", "data1");
        model.addAttribute("param2","data1");
        return "basic/link";
    }

    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data","Spring!");
        return "basic/literal";
    }

    @Data
    static class User{
        private String username;
        private int age;

        public User(String username, int age){
            this.username = username;
            this.age = age;
        }
    }
}
