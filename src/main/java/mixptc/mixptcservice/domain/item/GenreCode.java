package mixptc.mixptcservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DICTIONARY : 사전
 * WORK BOOK: 문제집
 * NOVEL : 소설
 * LITERATURE : 문학
 * HISTORY : 역사
 * ETC : 기타
 */
@Data
@AllArgsConstructor
public class GenreCode {

    private String code; //시스템에서 전달하는값 ex)proxy
    private String displayName; //고객에게 보여주는값 ex)쇼핑몰 배송
}
