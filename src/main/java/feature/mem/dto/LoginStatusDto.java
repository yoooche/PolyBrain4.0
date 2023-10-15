package feature.mem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginStatusDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer memNo;
    private String memName;
    private boolean loginStatus;
}
