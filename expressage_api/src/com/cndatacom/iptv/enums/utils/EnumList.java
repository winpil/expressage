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
     * ��ȡö���б�key=��ֵ��value=����˵����
     * @return
     */
    public List<EnumListField> getEnumList();

    /**
     * ö��ֵ˵��
     * @return
     */
    public String getDescription();

    /**
     * ö��ֵ
     * @return
     */
    public Object getEnumValue();
}
