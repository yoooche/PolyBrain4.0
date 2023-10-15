package feature.cart.vo;

import lombok.Data;

@Data
public class CartItemImgDTO {
    Integer memNo;
    Integer itemNo;
    Integer quantity;
    String itemName;
    String itemImg;
    Integer itemQty;
    Integer itemPrice;
    Integer quantityXitemPrice;
}
