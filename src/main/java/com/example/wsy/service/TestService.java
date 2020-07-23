package com.example.wsy.service;

import com.example.wsy.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestService {

    User test01(String id);

}
