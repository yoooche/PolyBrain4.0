package feature.item.vo;

import core.coreVO.Core;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Data

public class Item extends Core {
	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ITEM_NO" , insertable = false, updatable = false)
	private Integer itemNo;
	@Column(name = "ITEM_CLASS_NO", updatable = false)
	private Integer itemClassNo;
	@Column(name = "ITEM_NAME" , updatable = false)
	private String itemName;
	@Column(name = "ITEM_PRICE")
	private Integer itemPrice;
	@Column(name = "ITEM_STATE")
	private Integer itemState;
	@Column(name = "ITEM_QTY")
	private Integer itemQty;
	@Column(name = "ITEM_SALES", insertable = false, updatable = false)
	private Integer itemSales;
	@Column(name = "MIN_PLAYER", updatable = false)
	private Integer minPlayer;
	@Column(name = "MAX_PLAYER", updatable = false)
	private Integer maxPlayer;
	@Column(name = "GAME_TIME",updatable = false)
	private Integer gameTime;
	@Column(name = "ITEM_PROD_DESCRIPTION")
	private String itemProdDescription;
	@ManyToOne
	@JoinColumn(name ="ITEM_CLASS_NO", insertable = false, updatable = false)
	private ItemClass itemClass;
	@OneToMany
	@JoinColumn(name ="ITEM_NO", insertable = false, updatable = false)
	private List<ItemImg> itemImg;
}
