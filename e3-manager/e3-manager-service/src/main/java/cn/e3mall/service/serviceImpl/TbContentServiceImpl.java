package cn.e3mall.service.serviceImpl;

import cn.e3mall.MyRedis.MyRedis;
import cn.e3mall.bean.EasyUiPageBean;
import cn.e3mall.bean.TaotaoResult;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    TbContentMapper tbContentMapper;
    @Value("${LUNBO}")
    String lunBO;
    @Autowired
    MyRedis myRedis;

    @Override
    public EasyUiPageBean lsitContent(Long categoryId, int pages, int rows) {
        int start = (pages - 1) * rows;
        List<cn.e3mall.pojo.TbContent> tbContents = tbContentMapper.selectbyCategreyIdPagelist(categoryId, start, rows);
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<cn.e3mall.pojo.TbContent> tbContents1 = tbContentMapper.selectByExample(tbContentExample);
        int total = tbContents1.size();
        EasyUiPageBean easyUiPageBean = new EasyUiPageBean();
        easyUiPageBean.setRows(tbContents);
        easyUiPageBean.setTotal(total);
        return easyUiPageBean;

    }

    @Override
    public TaotaoResult save(TbContent tbContent) {
        int insert = tbContentMapper.insert(tbContent);
        //插入成功需要删除缓存内容更新redis缓存内容
        if (insert != 0) {
            myRedis.hdel(lunBO, tbContent.getCategoryId().toString());
        }
        return TaotaoResult.ok();
    }
}
