package feature.item.dto;

import feature.item.vo.Item;
import lombok.Data;

import java.util.List;

@Data
public class AddItemDTO {
    List<String> itemImageList;
    Item item;
}
