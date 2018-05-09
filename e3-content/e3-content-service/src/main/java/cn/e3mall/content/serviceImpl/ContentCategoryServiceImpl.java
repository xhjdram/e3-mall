package cn.e3mall.content.serviceImpl;

import cn.e3mall.bean.ItemCatTree;
import cn.e3mall.bean.TaotaoResult;
import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    /**
     * 程序执行是对的
     */
    @Autowired
    TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<ItemCatTree> getTreeNode(Long parentId) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.selectByExample
                (tbContentCategoryExample);
        ArrayList<ItemCatTree> itemCatTrees = new ArrayList<>();
        for (TbContentCategory tb : tbContentCategories) {
            ItemCatTree itemCatTree = new ItemCatTree();
            itemCatTree.setId(tb.getId());
            itemCatTree.setState(tb.getIsParent() ? "closed" : "open");
            itemCatTree.setText(tb.getName());
            itemCatTrees.add(itemCatTree);
        }
        return itemCatTrees;
    }

    @Override
    public TaotaoResult addCategory(Long parentId, String name) {

        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setIsParent(false);
        tbContentCategory.setName(name);
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setStatus(1);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setSortOrder(1);
        int insert = tbContentCategoryMapper.insert(tbContentCategory);

        //判断父节点是否是叶子结点
        TbContentCategory tbContentCategory1 = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if (!tbContentCategory1.getIsParent()) {
            tbContentCategory1.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory1);
        }
        return TaotaoResult.ok(tbContentCategory);
    }

    @Override
    public void rename(Long id, String name) {
        TbContentCategory tbContentCategory = tbContentCategoryMapper.selectByPrimaryKey(id);
        tbContentCategory.setName(name);
        tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory);
        return;
    }
}
