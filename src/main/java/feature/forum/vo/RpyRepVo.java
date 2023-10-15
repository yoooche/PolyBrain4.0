package feature.forum.vo;

import core.coreVO.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;
import java.time.LocalDateTime;

@Entity(name = "REPLY_REPORT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RpyRepVo extends Core {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "REPLY_REPORT_NO",insertable = false)
    private Integer reprepNo;
    @Column(name = "REPLY_NO")
    private Integer rpyNo;
    @Column(name = "MEM_NO")
    private Integer memNo;
    @Column(name = "REP_WHY")
    private String repWhy;
    @Column(name = "REP_TIME")
    private LocalDateTime repTime;
    @Column(name = "REP_STATE")
    private Byte repState;
    @Column(name = "REP_DONETIME")
    private LocalDateTime repdoneTime;

    }

