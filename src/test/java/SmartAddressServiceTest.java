import com.cjw.demo.address.SmartAddressServiceImpl;
import com.cjw.demo.address.util.DataCache;
import com.cjw.demo.util.JsonUtils;
import com.cjw.demo.util.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 828471 on 2017/5/4.
 */
public class SmartAddressServiceTest {
    @Autowired
    SmartAddressServiceImpl smartAddressService=new SmartAddressServiceImpl();
    @Test
    public void getAddressDividingTest(){
        System.out.println(JsonUtils.toJson(smartAddressService.getAddressDividing("西昌礼州池家文15112362169")));
    }
    @Test
    public void testPath(){
        System.out.println(DataCache.class.getResource("/com/cjw/demo/address/util/citybasedata_v3.config").getPath());
    }
}
