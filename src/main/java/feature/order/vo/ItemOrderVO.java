package feature.order.vo;

import core.coreVO.Core;
import feature.mem.vo.MemVo;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "item_order")
public class ItemOrderVO extends Core {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //若資料庫 PK欄位名稱與VO名稱不一致,仍須@Column
    @Column(name="ORDER_NO")
    private Integer orderNo;
    @Column(name="MEM_NO")
    private Integer memNo;
    //資料庫有預設資料, insertable就要是false(跳過此欄位)
    @Column(name="TRAN_TIME",insertable = false)
    private Date tranTime;
    @Column(name="ORDER_TOTAL",updatable = false)
    private Integer orderTotal;
    @Column(name="ORDER_STATE")
    private Integer orderState;
    @Column(name="RECEIVER_NAME")
    private String receiverName;
    @Column(name="RECEIVER_ADDRESS")
    private String receiverAddress;
    @Column(name="RECEIVER_PHONE")
    private String receiverPhone;
    @Column(name="RECEIVER_METHOD")
    private Integer receiverMethod;
    @OneToOne
    @JoinColumn(name="MEM_NO", insertable = false, updatable = false)
    private MemVo memVo;
}
