package market.carrot.Controller;

import market.carrot.Domain.User;
import market.carrot.Repository.UserRepository;
import market.carrot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({ "", "/" })
    public @ResponseBody String index() {
        return "인덱스 페이지입니다.";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login/failure")
    public String loginFailure() {
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String join() {
        return "register";
    }

    @PostMapping("/register-process")
    public String joinProc(User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        System.out.println("회원가입 진행 : " + user);
        userService.join(user);
        return "redirect:/login";
    }
}
