package cn.e3mall.controller;

import cn.e3mall.bean.TaotaoResult;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    @Autowired
    ItemService itemService;

    @RequestMapping("/item/save")
    @ResponseBody
    public TaotaoResult addItem(TbItem item, String desc) {
        TaotaoResult taotaoResult = itemService.addItem(item, desc);
        return taotaoResult;

    }
}
