package feature.wallet.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@Entity(name = "WALLET")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WALLET_HISTORY_NO", insertable = false)
    private Integer walletHistoryNo;
    @Column(name = "MEM_NO")
    private Integer memNo;
    @Column(name = "TRANS_TYPE")
    private Byte transType;
    @Column(name = "TRANS_DATE", insertable = false)
    private Date transDate;
    @Column(name = "TRANS_NOTE")
    private Byte transNote;
    @Column(name = "TRANS_AMOUNT")
    private Integer transAmount;

}
