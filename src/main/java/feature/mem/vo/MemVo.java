package feature.mem.vo;

import core.coreVO.Core;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER")
public class MemVo extends Core implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEM_NO", insertable = false, updatable = false)
    private Integer memNo;
    @Column(name = "MEM_NAME")
    private String memName;
    @Column(name = "MEM_PID")
    private String memPid;
    @Column(name = "MEM_GENDER")
    private Byte memGender;
    @Column(name = "MEM_PIC", insertable = false)
    private Byte[] memPic;
    @Column(name = "MEM_EMAIL")
    private String memEmail;
    @Column(name = "MEM_PWD")
    private String memPwd;
    @Column(name = "MEM_PH")
    private String memPh;
    @Column(name = "MEM_ADDRS")
    private String memAddress;
    @Column(name = "MEM_BIRTH")
    private Date memBirth;
    @Column(name = "MEM_AUTH")
    private Byte memAuth;
    @Column(name = "MEM_VIO")
    private Byte memVio;

}
