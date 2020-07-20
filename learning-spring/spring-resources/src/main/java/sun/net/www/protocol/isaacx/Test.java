package sun.net.www.protocol.isaacx;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * 测试 {@link IsaacxUrlConnection}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/20
 **/
public class Test {

    public static void main(String[] args) throws IOException {
//        ClassPathResource resource = new ClassPathResource("META-INF/prod.properties");
//        IsaacxUrlConnection connection = new IsaacxUrlConnection(resource.getURL(), resource);
//        System.out.println(IOUtils.toString(connection.getInputStream(), "UTF-8"));

        // 第二种实现
        URL url = new URL("isaacx:///META-INF/prod.properties");
        InputStream inputStream = url.openStream();
        System.out.println(IOUtils.toString(inputStream, Charset.forName("UTF-8")));
    }
}
