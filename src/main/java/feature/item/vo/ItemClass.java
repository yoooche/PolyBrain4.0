package feature.item.vo;


import core.coreVO.Core;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;

@Entity
@Table(name = "ITEM_CLASS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemClass extends Core {
	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_Class_No")
	private Integer itemClassNo;
	@Column(name = "ITEM_CLASS_NAME")
	private String itemClassName;

}
