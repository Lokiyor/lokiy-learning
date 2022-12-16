package com.lokiy.learning.spring.webflux.dao;

import com.lokiy.learning.spring.webflux.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

/**
 * @author lokiy
 * @date 2022/12/16
 * @description TODO
 */
public interface UserRepository extends ReactiveCrudRepository<User, String> {

    Mono<User> findByUsername(String username);

    Mono<Long> deleteByUsername(String username);
}
