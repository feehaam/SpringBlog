package com.feeham.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class UtilConfig {
    /*
     * There are several entities in the project that has
     * multiple DTOs. Some of those can be mapped by any auto
     * mapper, some by a custom mapper. ModelMapper is used
     * for auto mapping.
     *  -
     * This configuration method is used to configure ModelMapper.
    */
    @Bean(name = "modelMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
