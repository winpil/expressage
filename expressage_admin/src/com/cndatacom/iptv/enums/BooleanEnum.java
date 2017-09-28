/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cndatacom.iptv.enums;

import java.util.ArrayList;
import java.util.List;

import com.cndatacom.iptv.enums.utils.EnumList;
import com.cndatacom.iptv.enums.utils.EnumListField;

/**
 * 布尔枚举类型
 * @author Administrator
 */
public enum BooleanEnum implements EnumList {

    YES {

        public String getDescription() {
            return "是";
        }

        public Long getEnumValue() {
            return 1L;
        }
    },
    NO {

        public String getDescription() {
            return "否";
        }

        public Long getEnumValue() {
            return 0L;
        }
    };
    public List<EnumListField> getEnumList() {
        List enums = new ArrayList<EnumListField>();
        for (BooleanEnum statu : BooleanEnum.values()) {
            EnumListField field = new EnumListField();
            field.setKey(statu.getEnumValue().toString());
            field.setValue(statu.getDescription());
            enums.add(field);
        }
        return enums;
    }
}
