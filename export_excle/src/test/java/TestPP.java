import com.ghd.ExportExcelApplication;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/29 17:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExportExcelApplication.class)
public class TestPP {

    @Test
    public void testSplit(){
        String str = "霍伟轩-Darcy[fe57364a40ba4bea8062ccd266cc7c43]";
        String s = StringUtils.substringBetween(str, "[", "]");
        System.out.println(s);

        String s1 = StringUtils.substringBefore(str, "[");
        System.out.println(s1);

    }


    @Test
    public void testHH(){
        String time = "12：30";
        if(time.matches("(0\\d{1}|1\\d{1}|2[0-3]):([0-5]\\d{1})")){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
    }

    @Test
    public void testDate8(){
        LocalDate now = LocalDate.now();
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);

        String str = "2020-08-06";
        LocalDate parse = LocalDate.parse(str);
        System.out.println("时间为："+parse);

    }



}
