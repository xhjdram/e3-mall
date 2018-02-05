package cn.e3mall.service;
import cn.e3mall.bean.EasyUiPageBean;
import cn.e3mall.pojo.TbItem;
public interface ItemService {
    TbItem getItemById(long id);

    EasyUiPageBean getPageList(Integer page, Integer rows);
}
