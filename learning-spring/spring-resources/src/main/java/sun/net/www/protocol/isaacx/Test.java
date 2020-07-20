package sun.net.www.protocol.isaacx;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * 测试 {@link IsaacxUrlConnection}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/20
 **/
public class Test {

    public static void main(String[] args) throws IOException {
        ClassPathResource resource = new ClassPathResource("META-INF/prod.properties");
        IsaacxUrlConnection connection = new IsaacxUrlConnection(resource.getURL(), resource);
        System.out.println(IOUtils.toString(connection.getInputStream(), "UTF-8"));
    }
}
