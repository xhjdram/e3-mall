package cn.e3mall.controller;

import cn.e3mall.bean.ItemCatTree;
import cn.e3mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ItemCatController {
    @Autowired
    ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    private List<ItemCatTree> getItemCatTree(@RequestParam(name = "id", defaultValue = "0") Long id) {
//       if (id == null || id == 0) {
//            id = 0L;
//        }
        List<ItemCatTree> itemCatTree = itemCatService.getItemCatTree(id);
        return itemCatTree;
    }

}
