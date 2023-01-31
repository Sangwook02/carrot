package market.carrot.Controller;

import market.carrot.Domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @Autowired
    private SimpMessagingTemplate template;
    @MessageMapping("/hello")
    public void message(Message message) throws Exception {
        template.convertAndSend("/queue/"+message.getRoom_id(),message.getName());
    }
}
