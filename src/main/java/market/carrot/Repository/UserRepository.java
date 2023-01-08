package market.carrot.Repository;

import market.carrot.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
}
