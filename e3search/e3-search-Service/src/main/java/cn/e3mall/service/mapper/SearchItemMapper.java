package cn.e3mall.service.mapper;

import cn.e3mall.bean.SearchBean;

import java.util.List;

public interface SearchItemMapper {
    List<SearchBean> findItemList();
}
