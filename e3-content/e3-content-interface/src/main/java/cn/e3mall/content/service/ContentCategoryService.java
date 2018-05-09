package cn.e3mall.content.service;

import cn.e3mall.bean.ItemCatTree;
import cn.e3mall.bean.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    List<ItemCatTree> getTreeNode(Long parentId);

    TaotaoResult addCategory(Long parentId, String name);

    void rename(Long id, String name);


}
