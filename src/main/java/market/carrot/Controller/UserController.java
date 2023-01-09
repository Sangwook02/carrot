package market.carrot.Controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import market.carrot.Domain.User;
import market.carrot.Repository.UserRepository;
import market.carrot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Map;

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

    /**
     * 로그인
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login/failure")
    public String loginFailure() {
        return "redirect:/login";
    }

    /** 
     * 회원 가입
    */
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

    /**
     * 마이페이지 GET 요청
     */
    @GetMapping("user/profile")
    public @ResponseBody UserProfileDto myProfile(Principal principal) {
        User user = userService.findOne(principal.getName());
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setNickname(user.getNickname());
        userProfileDto.setImage(user.getImage());
        return userProfileDto;
    }

    /**
     * 마이페이지 PUT 요청
     */
    @PatchMapping ("user/profile")
    public @ResponseBody UserProfileDto updateMyProfile(Principal principal, @RequestBody UserProfileDto request) {
        User user = userService.findOne(principal.getName());
        if (request.image != user.getImage()) {
            user.setImage(request.image);
        }
        if(request.nickname != user.getNickname()) {
            user.setNickname(request.nickname);
        }
        userService.join(user);
        return request;
    }

    @Data
    @Setter
    static class UserProfileDto {
        private String image;
        private String nickname;
    }

    @Data
    static class UpdateUserRequest {
        private String image;
        private String nickname;
    }
}