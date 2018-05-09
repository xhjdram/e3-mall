package cn.e3mall.content.serviceImpl;

import cn.JsonUtils;
import cn.e3mall.MyRedis.MyRedis;
import cn.e3mall.content.service.Content;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentImpl implements Content {
    @Value("${LUNBO}")
    String lunBo;
    @Autowired
    MyRedis myRedis;
    @Autowired
    TbContentMapper tbContentMapper;

    @Override
    public List<TbContent> findTbcontentByCategoryId(Long id) {
        //判断当前缓存中是否有当前需要的数据
        String hget = myRedis.hget(lunBo, id.toString());
        if (StringUtils.isNotBlank(hget)) {
            List<TbContent> tbContents = JsonUtils.jsonToList(hget, TbContent.class);
            return tbContents;
        }
        TbContentExample tbContentExample = new TbContentExample();
        tbContentExample.createCriteria().andCategoryIdEqualTo(id);
        List<TbContent> tbContents = tbContentMapper.selectByExampleWithBLOBs(tbContentExample);
        //把查询到的数据缓存到redis中
        myRedis.hset(lunBo, id + "", JsonUtils.objectToJson(tbContents));
        return tbContents;
    }
}
