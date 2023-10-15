package feature.emp.vo;

import core.coreVO.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.util.Arrays;

@Entity(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpVo extends Core {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_NO", insertable = false)
    private Integer empNo;
    @Column(name = "EMP_NAME")
    private String empName;
    @Column(name = "EMP_GENDER")
    private Byte empGender;
    @Column(name = "EMP_EMAIL")
    private String empEmail;
    @Column(name = "EMP_PWD")
    private String empPwd;
    @Column(name = "EMP_PH")
    private String empPh;
    @Column(name = "EMP_PIC")
    private Byte[] empPic;
    @Column(name = "EMP_STATE")
    private Byte empState;
}
