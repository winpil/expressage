/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cndatacom.tlds;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.cndatacom.iptv.enums.utils.EnumList;
import com.cndatacom.iptv.enums.utils.EnumListField;

/**
 *
 * @author Administrator
 */
public class EnumConverterTag extends TagSupport {

    //private String id;
    private String key = "";
    //private String cssClass = "filterField";
    private String enumInstance = "";

    /**
     * Called by the container to invoke this tag. 
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    @Override
    public int doStartTag() throws JspException {
        try {
           
            //获取枚举类型列表
            EnumList enumList = (EnumList) pageContext.findAttribute(enumInstance);
            //枚举类型的输出值
            String value="";            
            if (enumList != null) {
                List<EnumListField> fields = enumList.getEnumList();
                for (Iterator<EnumListField> it = fields.iterator(); it.hasNext();) {
                    EnumListField enumListField = it.next();
                   if(enumListField.getKey().equals(key)){
                       value=enumListField.getValue();
                       break;
                   }
                }
            }
            pageContext.getOut().write(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.EVAL_PAGE;
    }





    /**
     * @param enumInstance the enumInstance to set
     */
    public void setEnumInstance(String enumInstance) {
        this.enumInstance = enumInstance;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }
}
