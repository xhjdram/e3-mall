package cn.e3mall.service.serviceImpl;

import cn.e3mall.bean.ItemCatTree;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    TbItemCatMapper itemCatMapper;
    @Override
    public List<ItemCatTree> getItemCatTree(Long id) {
        List<TbItemCat> tbItemCats = itemCatMapper.selectByParentId(id);
        List<ItemCatTree> list =new ArrayList<ItemCatTree>();
        for(TbItemCat tbItemCat : tbItemCats){
            ItemCatTree itemCatTree = new ItemCatTree();
            itemCatTree.setId(tbItemCat.getId());
            itemCatTree.setText(tbItemCat.getName());
            itemCatTree.setState(tbItemCat.getIsParent()==1?"closed":"open");
            list.add(itemCatTree);
        }

        return list;
    }
}
