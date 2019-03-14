package com.a16lao.wyh.bean;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * date:   2018/6/11 0011 上午 10:44
 * author: caoyan
 * description:
 */
@Entity
public class ExampleEntity {
    @Id(assignable = true) long id;
}
