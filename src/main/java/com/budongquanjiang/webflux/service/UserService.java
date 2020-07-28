package com.budongquanjiang.webflux.service;

import com.budongquanjiang.webflux.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<User> getById(int id);

    Flux<User>  getAll();

    Mono<Void> addUser(Mono<User> user);
 }
