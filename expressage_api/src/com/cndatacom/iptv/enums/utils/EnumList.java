/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cndatacom.iptv.enums.utils;

import java.util.List;

/**
 *
 * @author Administrator
 */
public interface EnumList {

    /**
     * 获取枚举列表（key=数值，value=中文说明）
     * @return
     */
    public List<EnumListField> getEnumList();

    /**
     * 枚举值说明
     * @return
     */
    public String getDescription();

    /**
     * 枚举值
     * @return
     */
    public Object getEnumValue();
}
