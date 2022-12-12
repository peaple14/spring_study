package mixptc.service.web.basic;

import lombok.RequiredArgsConstructor;
import mixptc.service.domain.item.Item;
import mixptc.service.domain.item.ItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/basic/items") //중복되는 주소들
@RequiredArgsConstructor //생성자 주입용
public class BasicItemController {
    //생성자만들기
    private final ItemRepository itemRepository;

    //가장처음 들어왔을때 상품목록뜨게하기
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    //목록에서 id를 클릭시
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item,item");
        return "basic/item";
    }
}
