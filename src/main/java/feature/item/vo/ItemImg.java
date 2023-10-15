package feature.item.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;

@Entity
@Table(name = "ITEM_IMAGE")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemImg {
	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_ImgNo")
	private int itemImgNo;
	@Column(name = "item_No")
	private int itemNo;
	@Column(name = "item_Img")
	private String itemImg;

}
