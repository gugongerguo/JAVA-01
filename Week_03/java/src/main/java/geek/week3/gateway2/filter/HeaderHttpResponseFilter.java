package geek.week3.gateway2.filter;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter{
    @Override
    public void filter(FullHttpRequest request,FullHttpResponse response) {
        String uri = request.uri();
        response.headers().setInt("Content-Length", response.content().readableBytes());
        if(uri.contains(".js")){
            response.headers().set("Content-Type", "application/javascript");
        }else if(uri.contains(".css")){
            response.headers().set("Content-Type", "text/css");
        }else if(uri.contains(".png")){
            response.headers().set("Content-Type", "image/png");
        }
        else{
            response.headers().set("Content-Type", "text/html");
        }
    }
}
