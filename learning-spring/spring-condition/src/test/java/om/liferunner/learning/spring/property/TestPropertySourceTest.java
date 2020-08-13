package om.liferunner.learning.spring.property;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * {@link TestPropertySource} 测试
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/8/13
 **/
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPropertySourceTest.class) //Spring 注解驱动测试注解
@TestPropertySource(properties = "user.name = Isaac")
public class TestPropertySourceTest {

    // 注入 System properties (System.getProperties())
    @Value("${user.name}")
    private String userName;

    @Autowired
    private ConfigurableEnvironment environment;

    @Test
    public void testUserName() {
        Assert.assertEquals("Isaac", userName);

        for (PropertySource ps : environment.getPropertySources()) {
            System.out.println(ps.getName() + "====" + ps.getProperty("user.name"));
        }
    }
}
