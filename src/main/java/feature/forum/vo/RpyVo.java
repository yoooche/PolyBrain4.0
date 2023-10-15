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
import java.sql.Timestamp;
import java.util.Date;
import java.time.LocalDateTime;

@Entity(name = "REPLY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RpyVo extends Core {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "REPLY_NO",insertable = false)
    private Integer rpyNo;
    @Id
    @Column(name = "MEM_NO")
    private Integer memNo;
    @Column(name = "ARTICLE_NO")
    private Integer artNo;
    @Column(name = "REPLY_CONTENT")
    private String rpyCon;
    @Column(name = "REPLY_TIME")
    private Timestamp rpyTime;
    @Column(name = "REPLY_STATE")
    private Byte rpyState;
}
