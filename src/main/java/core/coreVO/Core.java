package core.coreVO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
public class Core implements Serializable {
    @Serial
    private static final long serialVersionUID = 1457755989409740329L;
    private boolean success;
    private String message;
}
