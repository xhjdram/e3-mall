package cn.e3mall.controller;

import cn.e3mall.bean.ItemCatTree;
import cn.e3mall.bean.TaotaoResult;
import cn.e3mall.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoryController {
    @Autowired
    ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<ItemCatTree> getContentegory(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<ItemCatTree> treeNode = contentCategoryService.getTreeNode(parentId);
        return treeNode;
    }

    @RequestMapping("/content/category/create")
    @ResponseBody
    public TaotaoResult addCategory(Long parentId, String name) {
        TaotaoResult taotaoResult = contentCategoryService.addCategory(parentId, name);
        return taotaoResult;
    }

    @RequestMapping("/content/category/update")
    public void renameCategory(Long id, String name) {
        contentCategoryService.rename(id, name);
        return;
    }
}
