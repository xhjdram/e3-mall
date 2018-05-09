package cn.e3mall.content.service;

import cn.e3mall.pojo.TbContent;

import java.util.List;

public interface Content {
    List<TbContent> findTbcontentByCategoryId(Long id);
}
