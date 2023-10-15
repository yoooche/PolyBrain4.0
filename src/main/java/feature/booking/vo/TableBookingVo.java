package feature.booking.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tablebooking")
public class TableBookingVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TABLE_BOOK_NO", insertable = false, updatable = false)
    private  Integer tablebookno;
    @Column(name = "TABLE_DATE")
    private Date tabledate;
    @Column(name = "TABLE_NO")
    private  Integer tableno;
    @Column(name = "TABLE_MOR")
    private  Integer tablemor;
    @Column(name = "TABLE_EVE")
    private Integer tableeve;
    @Column(name = "TABLE_NIGHT")
    private Integer tablenight;
}
