package market.carrot.Domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter @Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotEmpty
    @Column(name = "EMAIL")
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @Column(name = "PHONENUMBER")
    private String phoneNumber;
    @NotEmpty
    private String nickname;
    private String image;
}
