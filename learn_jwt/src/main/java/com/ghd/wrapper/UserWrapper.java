package com.ghd.wrapper;

import com.ghd.model.User;
import lombok.Data;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/7 12:14
 */
@Data
public class UserWrapper extends User {

    /**
     * token失效时间
     */
    private Integer exp;

    /**
     * token创建时间
     */
    private Integer nbf;

}
