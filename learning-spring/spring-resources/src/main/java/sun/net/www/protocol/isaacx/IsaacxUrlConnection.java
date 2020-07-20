package sun.net.www.protocol.isaacx;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * isaacx 协议的 {@link URLConnection} 实现
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2020/7/20
 **/
public class IsaacxUrlConnection extends URLConnection {

    private final ClassPathResource resource;

    /**
     * Constructs a URL connection to the specified URL. A connection to
     * the object referenced by the URL is not created.
     *
     * @param url      the specified URL.
     * @param resource
     */
    protected IsaacxUrlConnection(URL url, ClassPathResource resource) {
        super(url);
        this.resource = resource;
    }

    @Override
    public void connect() throws IOException {

    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.resource.getInputStream();
    }
}
