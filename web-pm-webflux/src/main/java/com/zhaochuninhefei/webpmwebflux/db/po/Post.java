package com.zhaochuninhefei.webpmwebflux.db.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * @author zhaochun
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {
    @Id
    private Long id;

    private Long actId;

    private String title;

    private String content;
}
