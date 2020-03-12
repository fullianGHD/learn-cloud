import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Ghd
 * @Date: 2019/12/30 15:36
 */
@SpringBootTest
public class TestApplication {

    @Test
    public void testDouble(){
        String str = "1";
        System.out.println(Double.valueOf(str) == Double.valueOf(str));
        System.out.println(Integer.valueOf(str) == Integer.valueOf(str));
        System.out.println(Long.valueOf(str) == Long.valueOf(str));

    }
}
