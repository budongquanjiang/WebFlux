package com.budongquanjiang.webflux.controller;

import com.budongquanjiang.webflux.entity.User;
import com.budongquanjiang.webflux.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @ClassName UserController
 * @Description
 * @Author
 * @Date 2020/7/28 0:36
 * @Version V1.0
 **/
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/user/{id}")
    public Mono<User> getById(@PathVariable("id")int id){

        return userService.getById(id);
    }@GetMapping("/users")
    public Flux<User> getAll(){

        return userService.getAll();
    }
    @PostMapping("/user")
    public Mono<Void> getById(@RequestBody User user){

        Mono<User> userMono=Mono.just(user);

        return userService.addUser(userMono);
    }
}
