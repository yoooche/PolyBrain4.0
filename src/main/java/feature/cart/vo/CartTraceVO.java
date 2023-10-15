package feature.cart.vo;

import feature.item.vo.Item;
import feature.item.vo.ItemImg;
import feature.mem.vo.MemVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="cart")
@IdClass(CartTraceId.class)

public class CartTraceVO {
    @Id
    @Column(name="MEM_NO", updatable = false)
    private Integer memNo;
    @Id
    @Column(name="ITEM_NO", updatable = false)
    private Integer itemNo;
    @Column(name="quantity")
    private Integer quantity;

    public CartTraceVO() {
        super();
    }

    public CartTraceVO(Integer memNo, Integer itemNo, Integer quantity) {
        this.memNo = memNo;
        this.itemNo = itemNo;
        this.quantity = quantity;
    }

    public Integer getMemNo() {
        return memNo;
    }

    public void setMemNo(Integer memNo) {
        this.memNo = memNo;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}



