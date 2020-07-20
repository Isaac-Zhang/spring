package sun.net.www.protocol.isaacx;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * 实现 isaacx 协议的处理
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @see URLStreamHandler
 * @since 2020/7/20
 **/
public class Handler extends URLStreamHandler {

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new IsaacxUrlConnection(u);
    }
}
