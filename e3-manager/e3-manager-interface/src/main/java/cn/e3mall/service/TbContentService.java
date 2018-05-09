package cn.e3mall.service;

import cn.e3mall.bean.EasyUiPageBean;
import cn.e3mall.bean.TaotaoResult;
import cn.e3mall.pojo.TbContent;

import java.util.List;

public interface TbContentService {
    EasyUiPageBean lsitContent(Long categoryId, int pages, int rows);

    TaotaoResult save(TbContent tbContent);
}
