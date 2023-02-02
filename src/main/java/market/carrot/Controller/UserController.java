package market.carrot.Controller;

import lombok.Data;
import lombok.Setter;
import market.carrot.DTO.ItemListDTO;
import market.carrot.DTO.UserProfileDto;
import market.carrot.Domain.Item;
import market.carrot.Domain.User;
import market.carrot.Service.InterestService;
import market.carrot.Service.ItemService;
import market.carrot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private InterestService interestService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @GetMapping({ "", "/" })
//    public @ResponseBody String index() {
//        return "인덱스 페이지입니다.";
//    }

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
        if (request.getImage() != user.getImage()) {
            user.setImage(request.getImage());
        }
        if(request.getNickname() != user.getNickname()) {
            user.setNickname(request.getNickname());
        }
        userService.join(user);
        return request;
    }

    /*
    판매내역
     */
    @GetMapping("/v1/user/items")
    public @ResponseBody List<Item> userItemsV1(Principal principal) {
        User user = userService.findOne(principal.getName());
        return itemService.findByUser(user.getId());
    }
    @GetMapping("/v2/user/items")
    public @ResponseBody List<ItemListDTO> userItemsV2(Principal principal) {
        User user = userService.findOne(principal.getName());
        List<Item> items = itemService.findByUser(user.getId());
        return items.stream()
                .map(item -> new ItemListDTO(item))
                .collect(toList());
    }

    /*
    관심 목록 조회
     */
    @GetMapping("/v1/user/interest")
    public @ResponseBody List<Item> userInterestV1(Principal principal) {
        User user = userService.findOne(principal.getName());
        return interestService.findByUser(user.getId());
    }
}