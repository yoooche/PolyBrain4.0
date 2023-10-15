package feature.bookingtast.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tablebooking")  //假設資料庫的表名是"TableBooking"
public class TablebookingVO implements java.io.Serializable {

	@Id //編號
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TABLE_BOOK_NO", insertable = false)
	private Integer TABLE_BOOK_NO;

	//日期
	@Column(name = "TABLE_DATE",insertable = false)
	private java.sql.Date TABLE_DATE;

	//桌號
	@Column(name = "TABLE_NO",insertable = false)
	private Integer TABLE_NO;

	//時段
	@Column(name = "TABLE_MOR")
	private Integer TABLE_MOR;

	@Column(name = "TABLE_EVE")
	private Integer TABLE_EVE;

	@Column(name = "TABLE_NIGHT")
	private Integer TABLE_NIGHT;


}
