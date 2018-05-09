package cn.e3mall.service.serviceImpl;

import cn.e3mall.bean.SearchBean;
import cn.e3mall.bean.TaotaoResult;
import cn.e3mall.service.SearchItemService;
import cn.e3mall.service.mapper.SearchItemMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchItemMapperImpl implements SearchItemService {
    @Autowired
    SearchItemMapper searchItemMapper;
    @Autowired
    SolrServer solrServer;

    @Override
    public TaotaoResult importItemList() {
        List<SearchBean> itemList = searchItemMapper.findItemList();
        List<SolrInputDocument> solrInputDocumentList = new ArrayList<>();
        for (SearchBean searchBean : itemList) {
            SolrInputDocument solrInputDocument = new SolrInputDocument();
            solrInputDocument.addField("id", searchBean.getId());
            solrInputDocument.addField("item_title", searchBean.getItem_title());
            solrInputDocument.addField("item_sell_point", searchBean.getItem_sell_point());
            solrInputDocument.addField("item_price", searchBean.getItem_price());
            solrInputDocument.addField("item_image", searchBean.getItem_image());
            solrInputDocument.addField("item_category_name", searchBean.getItem_category_name());
            solrInputDocumentList.add(solrInputDocument);
        }
        try {
            solrServer.add(solrInputDocumentList);
            solrServer.commit();
            return TaotaoResult.build(200, "oK");
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, "error");
        }
    }
}
