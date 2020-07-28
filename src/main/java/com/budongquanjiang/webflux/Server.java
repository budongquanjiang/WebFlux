package com.budongquanjiang.webflux;

import com.budongquanjiang.webflux.handler.UserHandler;
import com.budongquanjiang.webflux.service.UserService;
import com.budongquanjiang.webflux.service.impl.UserServiceImpl;

import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

/**
 * @ClassName Server
 * @Description
 * @Author
 * @Date 2020/7/28 1:47
 * @Version V1.0
 **/

public class Server {


    public static void main(String[] args) throws Exception{

        Server server = new Server();

        server.createReactorServer();

        System.out.println("enter to exit");

        System.in.read();

    }



    public RouterFunction<ServerResponse> routerFunction(){

        UserService userService=new UserServiceImpl();

        UserHandler handler = new UserHandler(userService);

        return RouterFunctions.route(
                RequestPredicates.GET("/user/{id}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::getById)
                .andRoute(RequestPredicates.GET("/users")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::getAll);

    }

    //创建服务器完成适配
    public void createReactorServer(){
        //路由和handler适配
        RouterFunction<ServerResponse> routerFunction = this.routerFunction();

        HttpHandler httpHandler = toHttpHandler(routerFunction);

        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

        //创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.port(8083).handle(adapter).bindNow();

    }
}
