import com.ghd.Mode23Application;
import com.ghd.observer_pattern.service.impl.*;
import com.ghd.singleton_pattern.service.impl.Singleton;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ghd-alem
 * @description
 * @date 2020/6/17 11:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Mode23Application.class)
public class TestMode23 {

    /**
     * 客户消息随着服务号变化而变化
     */
    @Test
    public void testObserver_pattern(){

        //模拟一个3D的服务号
        ObjectFor3D objectFor3D = new ObjectFor3D();

        //客户
        Observer1 observer1 = new Observer1(objectFor3D);
        Observer2 observer2 = new Observer2(objectFor3D);

        objectFor3D.setMsg("  456789");

    }

    @Test
    public void testObserverJavaUtil(){
        SubjectFor3DJavaUtil subjectFor3DJavaUtil = new SubjectFor3DJavaUtil();
        SubjectForSSQ subjectForSSQ = new SubjectForSSQ();


        ObserverForJava observerForJava = new ObserverForJava();
        observerForJava.registerSubject(subjectFor3DJavaUtil);
        observerForJava.registerSubject(subjectForSSQ);

        subjectFor3DJavaUtil.setMsg("hello 3D");
        subjectForSSQ.setMsg("hello ssq");
    }

    @Test
    public void testSingleTon(){
        Singleton instance = Singleton.getInstance();

    }


}
