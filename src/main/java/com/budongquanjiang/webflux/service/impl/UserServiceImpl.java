package com.budongquanjiang.webflux.service.impl;

import com.budongquanjiang.webflux.entity.User;
import com.budongquanjiang.webflux.service.UserService;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author
 * @Date 2020/7/28 0:24
 * @Version V1.0
 **/
@Repository
public class UserServiceImpl implements UserService {

    private final Map<Integer,User> map=new HashMap<>();

    public UserServiceImpl() {

        this.map.put(1,new User("lucky","nan",20));
        this.map.put(2,new User("mary","nv",30));
        this.map.put(3,new User("jack","nv",50));
    }

    @Override
    public Mono<User> getById(int id) {

       return Mono.justOrEmpty(this.map.get(id));

    }

    @Override
    public Flux<User> getAll() {
        return Flux.fromIterable(this.map.values());
    }

    @Override
    public Mono<Void> addUser(Mono<User> user) {
        return user.doOnNext(person->{
            int id=this.map.size()+1;
            this.map.put(id,person);
        }).thenEmpty(Mono.empty());
    }
}
