import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

import java.io.IOException;

public class Test {
    @org.junit.Test
    public void test() throws IOException, SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://localhost:8085/solr/new_core");
        SolrInputDocument solrInputDocument = new SolrInputDocument();
        solrInputDocument.addField("id", "123");
        solrInputDocument.addField("name", "范冰冰");
        solrServer.add(solrInputDocument);
        solrServer.commit();
    }
}
