package cn.e3mall.service.serviceImpl;

import cn.e3mall.bean.EasyUiPageBean;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceimpl implements ItemService {
    @Autowired
    public TbItemMapper tbItemMapper;
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
}
