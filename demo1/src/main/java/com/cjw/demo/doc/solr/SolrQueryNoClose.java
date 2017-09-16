package com.cjw.demo.doc.solr;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.List;

/**
 * Created by 828471 on 2017/5/24.
 */
public class SolrQueryNoClose {


    public static <T> List<T> querySolr(Class<T> model, SolrQuery parameters, String zkHost) {
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
            System.out.println("e");
        } catch (IOException e) {
            System.out.println("e");
        }
        return beanList;
    }

    public static void main(String[] args) {
        String zkHost = "10.202.13.196:2181,10.202.13.197:2181,10.202.13.198:2181";



        SolrQuery parameters2 = new SolrQuery();
        parameters2.set("q", "MEMBERID:C53EE3CAE3A0420B9840BF0CED61CB55");
        parameters2.set("q", "ADDRESS:软件产业");
        parameters2.set("qt", "/select");
        parameters2.set("collection", "address");
        long time4=System.currentTimeMillis();
        System.out.println(time4);
        List<AddressBookResp> beanList2 = querySolr(AddressBookResp.class, parameters2, zkHost);
        long time5=System.currentTimeMillis();
        System.out.println(time5);
        System.out.println(time5-time4);
//        System.out.println(JsonUtils.toJson(beanList2));

        SolrQuery parameters = new SolrQuery();
        parameters.set("q", "MEMBERID:847072C9EB6340E09B1A1A00670C9117");
        parameters.set("q", "ADDRESS:刚盛机械厂");
        parameters.set("qt", "/select");
        parameters.set("collection", "address");
        long time2=System.currentTimeMillis();
        System.out.println(time2);
        List<AddressBookResp> beanList = querySolr(AddressBookResp.class, parameters, zkHost);
        long time3=System.currentTimeMillis();
        System.out.println(time3);
        System.out.println(time3-time2);
//        System.out.println(JsonUtils.toJson(beanList));
    }
}
