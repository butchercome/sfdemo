import com.cjw.demo.service.log.LogOutPut;
import org.junit.Test;

/**
 * Created by 828471 on 2017/5/29.
 */
public class LogOutPutTest {
    LogOutPut logOutPut = new LogOutPut();

    @Test
    public void soutThisClassMethodTest(){
        logOutPut.getCallerClassMethod();


    }
}
