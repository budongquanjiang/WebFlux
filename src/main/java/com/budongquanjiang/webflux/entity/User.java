package com.budongquanjiang.webflux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName User
 * @Description
 * @Author
 * @Date 2020/7/28 0:21
 * @Version V1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String name;

    private String gender;

    private int age;
}
