package market.carrot.Controller;



import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import market.carrot.Domain.Chat;
import market.carrot.Domain.User;
import market.carrot.Service.ChatService;
import market.carrot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    /*
    채팅방 목록 조회
     */
    @GetMapping("/chat/rooms")
    public List<Chat> roomList(Principal principal) {
        User user = userService.findOne(principal.getName());
        return chatService.findByUser(user.getId());
    }

    /*
    채팅방 입장
     */
    @GetMapping("/chat/room")
    public String room(Principal principal, Model model, @RequestParam(value = "id") Long id) {
        model.addAttribute("room_id", id);
        return "chat/room";
    }
}