package market.carrot.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "itemImages")
public class ItemImages {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    private String path1;
    private String path2;
    private String path3;
    private String path4;
    private String path5;
    private String path6;
    private String path7;
    private String path8;
    private String path9;
    private String path10;
}
