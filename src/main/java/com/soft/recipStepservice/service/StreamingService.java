package com.soft.recipStepservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class StreamingService {

    public static final String FORMAT = "classpath:static\\videos\\steps\\%s";
    @Autowired
    private ResourceLoader resourceLoader;

    public Mono<Resource> getVideo(String title){
        return Mono.fromSupplier(
                () -> resourceLoader.getResource(String.format(FORMAT,title))
        );
    }
}
