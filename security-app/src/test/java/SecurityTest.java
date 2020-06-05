import com.ghd.SecurityAppApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/21 14:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecurityAppApplication.class)
@Slf4j
public class SecurityTest {

    @Test
    public void testSum(){
        int nums[] = {2, 7, 11, 15};
        int target = 9;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                int[] ints = {map.get(complement), i};
                System.out.println(ints.toString());
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
