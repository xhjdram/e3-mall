package cn.e3mall.controller;

import cn.e3mall.bean.TaotaoResult;
import cn.e3mall.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    @Autowired
    SearchItemService searchItemService;

    @RequestMapping(value = "/index/item/import")
    @ResponseBody
    public TaotaoResult importIndex() {
        TaotaoResult taotaoResult = searchItemService.importItemList();
        return taotaoResult;
    }
}
