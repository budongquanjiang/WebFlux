package com.budongquanjiang.webflux;

import com.budongquanjiang.webflux.entity.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @ClassName Client
 * @Description
 * @Author
 * @Date 2020/7/28 14:19
 * @Version V1.0
 **/

public class Client {


    public static void main(String[] args) {

        WebClient webClient = WebClient.create("http://localhost:8083");

        String id="1";

        User user = webClient.get().uri("/user/{id}", id).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(User.class).block();
        System.out.println(user.getName());


        webClient.get().uri("/users").accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToFlux(User.class)
                .map(stu->stu.getName()).buffer().doOnNext(System.out::println).blockFirst();
    }
}
