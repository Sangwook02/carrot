package market.carrot.Service;

import lombok.RequiredArgsConstructor;
import market.carrot.Domain.User;
import market.carrot.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long join(User user) {
        userRepository.save(user);
        return user.getId();
    }

    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }
}
