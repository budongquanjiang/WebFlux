package com.budongquanjiang.webflux.handler;

import com.budongquanjiang.webflux.entity.User;
import com.budongquanjiang.webflux.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @ClassName UserHandler
 * @Description
 * @Author
 * @Date 2020/7/28 1:23
 * @Version V1.0
 **/

public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {

        this.userService=userService;
    }


    public Mono<ServerResponse> getById(ServerRequest request){


        Integer id = Integer.valueOf(request.pathVariable("id"));

        Mono<ServerResponse> notFound=ServerResponse.notFound().build();
        Mono<User> userMono = userService.getById(id);

        return userMono.flatMap(person->
            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(person))
        )
                .switchIfEmpty(notFound);

    }

    public Mono<ServerResponse> getAll(ServerRequest request){

        Flux<User> users = this.userService.getAll();

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(users, User.class);

    }

    public Mono<ServerResponse> addUser(ServerRequest request){

        Mono<User> userMono = request.bodyToMono(User.class);


        return ServerResponse.ok().build(this.userService.addUser(userMono));
    }
}
