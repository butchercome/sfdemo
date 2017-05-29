package com.cjw.demo.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by 828471 on 2016/9/21.
 */
public class SolrUtil {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public <T> List<T> query(Class<T> model, SolrQuery parameters, String zkHost) {

        List<T> beanList = null;
        CloudSolrClient server = new CloudSolrClient(zkHost);
        try {
            server.setParser(new XMLResponseParser());
            server.getZkHost();
            QueryResponse response;
            response = server.query(parameters);
            SolrDocumentList list = response.getResults();
            DocumentObjectBinder binder = new DocumentObjectBinder();
            beanList = binder.getBeans(model, list);

        } catch (SolrServerException e) {
            logger.error("SolrUtil.query  solr SolrServerException", e);
        } catch (IOException e) {
            logger.error("SolrUtil.query  solr IOException", e);
        }finally {
            try {
                server.close();
            } catch (IOException e) {
                logger.error("SolrUtil solr close IOException", e);
            }
        }
        return beanList;
    }
    public long queryCount(SolrQuery parameters, String zkHost) {

        CloudSolrClient server = new CloudSolrClient(zkHost);
        long count=0;
        try {
            server.setParser(new XMLResponseParser());
            server.getZkHost();
            QueryResponse response;
            response = server.query(parameters);
            count=response.getResults().getNumFound();
        } catch (SolrServerException e) {
            logger.error("SolrUtil.query  solr SolrServerException", e);
        } catch (IOException e) {
            logger.error("SolrUtil.query  solr IOException", e);
        }finally {
            try {
                server.close();
            } catch (IOException e) {
                logger.error("SolrUtil solr close IOException", e);
            }
        }
        return count;
    }

}
