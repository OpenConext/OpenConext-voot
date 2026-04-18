package voot.provider;

import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.impl.auth.BasicScheme;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.HttpHost;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Default HttpClient does not support Preemptive authentication. Spring has added a hook to
 * support this: https://jira.spring.io/browse/SPR-8367
 */
public class PreemptiveAuthenticationHttpComponentsClientHttpRequestFactory extends HttpComponentsClientHttpRequestFactory {

    public PreemptiveAuthenticationHttpComponentsClientHttpRequestFactory(CloseableHttpClient httpClient, String url,
                                                                          UsernamePasswordCredentials credentials) throws MalformedURLException {
        super(httpClient);
        URL parsedUrl = new URL(url);
        HttpHost targetHost = new HttpHost(parsedUrl.getProtocol(), parsedUrl.getHost(), parsedUrl.getPort());
        setHttpContextFactory((method, uri) -> {
            BasicScheme basicScheme = new BasicScheme();
            basicScheme.initPreemptive(credentials);
            HttpClientContext context = HttpClientContext.create();
            context.resetAuthExchange(targetHost, basicScheme);
            return context;
        });
    }
}
