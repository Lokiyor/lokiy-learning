package com.lokiy.learning.spring.webflux.service;

import com.lokiy.learning.spring.webflux.dao.UserRepository;
import com.lokiy.learning.spring.webflux.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author lokiy
 * @date 2022/12/16
 * @description TODO
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> save(User user){
        return userRepository.save(user)
                .onErrorResume(e ->
                        userRepository.findByUsername(user.getUsername())
                                .flatMap(ori -> {
                                    user.setId(ori.getId());
                                    return userRepository.save(user);
                                }));
    }

    public Mono<Long> deleteByUsername(String username){
        return userRepository.deleteByUsername(username);
    }

    public Mono<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Flux<User> findAll(){
        return userRepository.findAll();
    }
}
