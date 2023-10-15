package feature.bid.dto;

import feature.bid.vo.BidItemVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidItemDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private BidItemVo bidItemVo;
    private List<String> bidItemPic;
}
