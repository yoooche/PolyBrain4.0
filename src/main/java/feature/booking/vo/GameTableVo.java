package feature.booking.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity(name = "GAMETABLE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameTableVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TABLE_NO")
    private Integer tableno;
    @Column(name = "TABLE_TYPE")
    private Integer tabletype;

}
