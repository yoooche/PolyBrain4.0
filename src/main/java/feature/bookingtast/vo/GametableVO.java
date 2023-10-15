package feature.bookingtast.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "gametable")  // 假設資料庫的表名稱為"Gametable"
public class GametableVO implements java.io.Serializable {

	@Id
	@Column(name = "TABLE_NO",insertable = false)
	private Integer TABLE_NO;

	@Column(name = "TABLE_TYPE")
	private Integer TABLE_TYPE;

	@Column(name = "TABLE_NAME")
	private String TABLE_NAME;

}
