import com.cjw.demo.solr.SolrQueryClose;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 828471 on 2017/5/24.
 */
public class SolrQueryTest {
    SolrQueryClose solrQuery = new SolrQueryClose();

    @Test
    public void testSolrCloseQuery() {


        for (int i = 0; i < 3; i++) {
            solrQuery.queryAddress();
        }
    }

    public void SolrQuery(int i) {
        System.out.println("this is thread "+i);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                solrQuery.queryAddress();
            }
        });
    }
    @Test
    public void testThreadQuery(){
        for (int i = 0; i < 2; i++) {
            System.out.println("this is request i"+i);
            solrQuery.queryAddressInThread();
        }
    }


}
