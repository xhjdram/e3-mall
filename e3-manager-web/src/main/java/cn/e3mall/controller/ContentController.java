package cn.e3mall.controller;

import cn.e3mall.bean.EasyUiPageBean;
import cn.e3mall.bean.TaotaoResult;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class ContentController {
    @Autowired
    TbContentService tbContentService;

    @RequestMapping(value = "/content/query/list", method = RequestMethod.GET)
    @ResponseBody
    public EasyUiPageBean listContent(Long categoryId, int page, int rows) {
        EasyUiPageBean easyUiPageBean = tbContentService.lsitContent(categoryId, page, rows);
        return easyUiPageBean;
    }

    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult save(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        TaotaoResult save = tbContentService.save(tbContent);
        return save;
    }
}
