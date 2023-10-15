package feature.bid.vo;

import core.coreVO.Core;
import feature.item.vo.ItemClass;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "BID_ITEM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BidItemVo extends Core implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BID_ITEM_NO", insertable = false, updatable = false)
    private Integer bidItemNo;

    @Column(name = "BID_ITEM_NAME", updatable = false)
    private String bidItemName;

    @Column(name = "BID_ITEM_DESCRIBE")
    private String bidItemDescribe;

    @Column(name = "ITEM_CLASS_NO", updatable = false)
    private Integer itemClassNo;

    @Column(name = "GAME_PUBLISHER", updatable = false)
    private String gamePublisher;

    @ManyToOne
    @JoinColumn(name = "ITEM_CLASS_NO", insertable = false, updatable = false)
    private ItemClass itemClass;

    @OneToMany
    @JoinColumn(name = "BID_ITEM_NO", insertable = false, updatable = false)
    private List<BidItemPicVo> bidItemPic;
}
