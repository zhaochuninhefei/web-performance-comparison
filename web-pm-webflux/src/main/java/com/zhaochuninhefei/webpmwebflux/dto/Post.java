package com.zhaochuninhefei.webpmwebflux.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhaochun
 */
@NoArgsConstructor
@Data
public class Post {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}
