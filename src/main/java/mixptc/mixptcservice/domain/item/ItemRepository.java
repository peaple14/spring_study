package mixptc.mixptcservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    //아이템 아이디1씩 증가
    private static long sequence = 0L;

    public Item save(Item item){
        item.setId(++sequence); //아이디1증가시키고
        store.put(item.getId(), item); //저장소에 아이디와 아이템이름 넣기
        return item;
    }

    //id로 물품찾기
    public Item findById(Long id) {return store.get(id);}

    //저장소에있는 모든것들 꺼내기
    public List<Item> findAll(){return new ArrayList<>(store.values());}

    //수정하는곳
    public void update(Long itemId, Item updateParam){
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        findItem.setGenreCode(updateParam.getGenreCode());
        findItem.setDeliveryType(updateParam.getDeliveryType());
        findItem.setOpen(updateParam.getOpen());

    }

    public void clearStore(){store.clear();}
}
