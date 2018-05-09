package cn.e3mall.service.serviceImpl;

import cn.e3mall.IDUtils;
import cn.e3mall.bean.EasyUiPageBean;
import cn.e3mall.bean.TaotaoResult;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceimpl implements ItemService {
    @Autowired
    public TbItemMapper tbItemMapper;
    @Autowired
    public TbItemDescMapper tbItemDescMapper;
    @Override
    public TbItem getItemById(long id) {
        TbItem tbItem = tbItemMapper.selectByPrimaryKey(id);
        return tbItem;
    }

    @Override
    public EasyUiPageBean getPageList(Integer page, Integer rows) {
        Integer index =0;
        if(page!=null&& page>=1){
            index = (page-1)*rows;
        }
        List<TbItem> pageList = tbItemMapper.getPageList(index, rows);
        int count = tbItemMapper.getCount();
        EasyUiPageBean easyUiPageBean =new EasyUiPageBean();
        easyUiPageBean.setTotal(count);
        easyUiPageBean.setRows(pageList);
        return easyUiPageBean;
    }

    @Override
    public TaotaoResult addItem(TbItem item, String desc) {
        //通过工具类获取商品ID
        long l = IDUtils.genItemId();
        // 2、补全TbItem对象的属性
        item.setId(l);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);
        //数据插入商品表
        int insert = tbItemMapper.insert(item);
        //创建商品描述
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setCreated(date);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemId(l);
        //想商品描述表插入数据
        tbItemDescMapper.insert(tbItemDesc);
        return TaotaoResult.ok();
    }
}
