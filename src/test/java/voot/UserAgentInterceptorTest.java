package voot;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserAgentInterceptorTest {

    private final UserAgentInterceptor subject = new UserAgentInterceptor("voot/1.2.3");

    @Test
    void interceptSetsUserAgentHeader() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        HttpRequest request = mock(HttpRequest.class);
        when(request.getHeaders()).thenReturn(headers);

        ClientHttpRequestExecution execution = mock(ClientHttpRequestExecution.class);
        ClientHttpResponse response = mock(ClientHttpResponse.class);
        when(execution.execute(any(), any())).thenReturn(response);

        byte[] body = new byte[0];
        ClientHttpResponse result = subject.intercept(request, body, execution);

        assertEquals("voot/1.2.3", headers.getFirst(HttpHeaders.USER_AGENT));
        assertSame(response, result);
        verify(execution).execute(request, body);
    }

    @Test
    void interceptOverwritesExistingUserAgentHeader() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.USER_AGENT, "some-other-agent");
        HttpRequest request = mock(HttpRequest.class);
        when(request.getHeaders()).thenReturn(headers);

        ClientHttpRequestExecution execution = mock(ClientHttpRequestExecution.class);
        when(execution.execute(any(), any())).thenReturn(mock(ClientHttpResponse.class));

        subject.intercept(request, new byte[0], execution);

        assertEquals(1, headers.get(HttpHeaders.USER_AGENT).size());
        assertEquals("voot/1.2.3", headers.getFirst(HttpHeaders.USER_AGENT));
    }
}
