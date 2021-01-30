package geek.week3.gateway2.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;
import java.util.Map;

public class DomainHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        List<Map.Entry<String, String>> entries = fullRequest.headers().entries();
        String host = "127.0.0.1:8808";
        String referer = "http://127.0.0.1:8808";
        if (!fullRequest.uri().contains("qingyi")) {
            for (Map.Entry<String, String> entry : entries) {
                if (entry.getKey().equals("Host")) {
                    host = entry.getValue();
                }
                if (entry.getKey().equals("Referer")) {
                    referer = entry.getValue();
                }
            }
        }
        referer = referer.replaceAll("http", "https").replaceAll(host, "www.martheus.com");
        fullRequest.headers().set("Domain", referer);
    }
}
