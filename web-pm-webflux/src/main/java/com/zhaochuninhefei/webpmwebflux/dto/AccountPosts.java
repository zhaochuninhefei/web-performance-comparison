package com.zhaochuninhefei.webpmwebflux.dto;

import com.zhaochuninhefei.webpmwebflux.db.po.Accounts;
import com.zhaochuninhefei.webpmwebflux.db.po.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhaochun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountPosts {
    private Accounts account;

    private List<Post> posts;
}
