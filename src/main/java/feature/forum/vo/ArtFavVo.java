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

@Entity(name = "ARTICLE_FAVORITES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtFavVo extends Core {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ARTICLE_NO")
    private Integer artNo;
    @Id
    @Column(name = "MEM_NO")
    private Integer memNo;
}
