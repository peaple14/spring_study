package mixptc.mixptcservice.web.item;

import lombok.RequiredArgsConstructor;
import mixptc.mixptcservice.domain.item.DeliveryType;
import mixptc.mixptcservice.domain.item.GenreCode;
import mixptc.mixptcservice.domain.item.Item;
import mixptc.mixptcservice.domain.item.ItemRepository;
import mixptc.mixptcservice.web.item.form.ItemSaveForm;
import mixptc.mixptcservice.web.item.form.ItemUpdateForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    //RequiredArgsConstructor: final이 붙은 멤버변수만 사용해서 생성자를 자동으로 만들어준다
    //1개만 있을경우 @Autowired로 의존관계주입
    private final ItemRepository itemRepository;


    @ModelAttribute("deliveryTypes")
    public DeliveryType[] deliveryTypes() {return DeliveryType.values();}

    @ModelAttribute("GenreCodes")
    public List<GenreCode> genreCodes(){
        List<GenreCode> genreCodes = new ArrayList<>();
        genreCodes.add(new GenreCode("DICTIONARY", "사전"));
        genreCodes.add(new GenreCode("WORK BOOK", "문제집"));
        genreCodes.add(new GenreCode("NOVEL", "소설"));
        genreCodes.add(new GenreCode("LITERATURE", "문학"));
        genreCodes.add(new GenreCode("HISTORY", "역사"));
        genreCodes.add(new GenreCode("ETC", "기타"));
        return genreCodes;
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "items/items";
    }

    //목록에서 id를 클릭시
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "items/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "items/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(form.getPrice() !=null && form.getQuantity() !=null){
            int resultPrice = form.getPrice() * form.getQuantity();
            if(resultPrice < 10000){
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null); //bindingResult는 model에 저절로 담김
            }
        }
        //검증실패시
        if(bindingResult.hasErrors()){
            return "items/addForm";
        }

        //폼 객체를 item으로 변환
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());
        item.setGenreCode(form.getGenreCode());
        item.setDeliveryType(form.getDeliveryType());
        item.setOpen(form.getOpen());

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/items/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "items/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) {

        //특정 필드가 아닌 복합 룰 검증
        if (form.getPrice() != null && form.getQuantity() != null) {
            int resultPrice = form.getPrice() * form.getQuantity();
            if (resultPrice < 10000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        if (bindingResult.hasErrors()) {//오류가 있을시
            return "items/editForm";
        }

        Item itemParam = new Item();
        itemParam.setItemName(form.getItemName());
        itemParam.setPrice(form.getPrice());
        itemParam.setQuantity(form.getQuantity());
        itemParam.setGenreCode(form.getGenreCode());
        itemParam.setDeliveryType(form.getDeliveryType());
        itemParam.setOpen(form.getOpen());

        itemRepository.update(itemId, itemParam);
        return "redirect:/items/{itemId}";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
