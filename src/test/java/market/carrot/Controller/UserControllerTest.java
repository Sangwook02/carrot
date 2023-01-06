package market.carrot.Controller;

import market.carrot.Domain.User;
import market.carrot.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class UserControllerTest {
    @Autowired
    UserService userService;

    @Test
    public void 회원가입() throws Exception {
        //given
        User user = new User();
        user.setEmail("abc@abc.com");
        user.setPassword("1234");
        user.setName("홍길동");
        user.setPhoneNumber("01011111111");
        user.setNickname("chamsae");
        //when
        userService.join(user);
        //then
        assertEquals(user, userService.findOne("abc@abc.com"));
    }
    @Test
    public void 중복_회원가입() throws Exception {
        //given
        User user1 = new User();
        user1.setEmail("abc@abc.com");
        user1.setPassword("1234");
        user1.setName("홍길동");
        user1.setPhoneNumber("01011111111");
        user1.setNickname("chamsae");

        User user2 = new User();
        user2.setEmail("abc@abc.com");
        user2.setPassword("1234");
        user2.setName("홍길동");
        user2.setPhoneNumber("01011111111");
        user2.setNickname("chamsae");
        //when
        userService.join(user1);
        userService.join(user2);
        //then
        fail("중복된 회원");
    }
}