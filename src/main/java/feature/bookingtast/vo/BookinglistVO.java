package feature.bookingtast.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookinglist")
public class BookinglistVO implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "booking_no",insertable = false)
	private Integer BOOKING_NO;

	@Column(name = "table_no")
	private Integer TABLE_NO;

	@Column(name = "table_date")
	private Date TABLE_DATE;

	@Column(name = "booking_check_state",insertable = false)
	private Integer BOOKING_CHECK_STATE;

	@Column(name = "booking_state",insertable = false)
	private Integer BOOKING_STATE;

	@Column(name = "period_time")
	private Integer PERIOD_TIME;
}
