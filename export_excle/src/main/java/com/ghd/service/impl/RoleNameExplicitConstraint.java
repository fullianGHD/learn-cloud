package com.ghd.service.impl;

import com.ghd.service.ExplicitInterface;
import org.springframework.stereotype.Service;

/**
 * @author ghd-alem
 * @description
 * @date 2020/5/28 11:28
 */
@Service
public class RoleNameExplicitConstraint implements ExplicitInterface {


    @Override
    public String[] source() {
            return new String[]{"角色1","角色2","角色3","角色4","角色5"};
    }
}
