package feature.bid.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "BID_EVENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidEventVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BID_EVENT_NO", insertable = false, updatable = false)
    private Integer bidEventNo;

    @Column(name = "BID_ITEM_NO", insertable = false, updatable = false)
    private Integer bidItemNo;

    @Column(name = "FLOOR_PRICE")
    private Integer floorPrice;

    @Column(name = "LEAST_OFFERS")
    private Integer leastOffers;

    @Column(name = "START_TIME")
    private Timestamp startTime;

    @Column(name = "CLOSE_TIME")
    private Timestamp closeTime;

    @Column(name = "DIRECTIVE_PRICE")
    private Integer directivePrice;

    @OneToOne
    @JoinColumn(name = "BID_ITEM_NO", referencedColumnName = "BID_ITEM_NO")
    private BidItemVo bidItemVo;

}
