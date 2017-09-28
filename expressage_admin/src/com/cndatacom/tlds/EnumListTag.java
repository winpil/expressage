 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cndatacom.tlds;

import java.text.MessageFormat;
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
public class EnumListTag extends TagSupport {

    private String id;
    private String value = "";
    private String cssClass = "filterField";
    private String enumInstance = "";

    /**
     * Called by the container to invoke this tag. 
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    @Override
    public int doStartTag() throws JspException {
        try {
            //获取post回来的数据
            if (pageContext.getRequest().getParameter(id) != null) {
                value = pageContext.getRequest().getParameter(id);
            }
            //获取枚举类型列表
            EnumList enumList = (EnumList) pageContext.findAttribute(enumInstance);
            StringBuilder html = new StringBuilder();
            int index = 1;
            if (enumList != null) {
                html.append(MessageFormat.format("<input type=\"radio\" id=\"{0}{1}\" name=\"{0}\"  value=\"{2}\" class=\"{3}\" />", id, index, "", cssClass));
                html.append(MessageFormat.format("<label for=\"{0}{1}\">{2}</label>", id, index, "全部"));

                index++;
                List<EnumListField> fields = enumList.getEnumList();
                for (Iterator<EnumListField> it = fields.iterator(); it.hasNext();) {
                    EnumListField enumListField = it.next();
                    html.append(MessageFormat.format("<input type=\"radio\" id=\"{0}{1}\" name=\"{0}\"  value=\"{2}\" class=\"{3}\" />", id, index, enumListField.getKey(), cssClass));
                    html.append(MessageFormat.format("<label for=\"{0}{1}\">{2}</label>", id, index, enumListField.getValue()));
                    index++;
                }
            }
            html.append(MessageFormat.format("<input type=\"hidden\" id=\"hd_{0}\" value=\"{1}\" />", id, value));
            value="";
            pageContext.getOut().write(html.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.EVAL_PAGE;
    }

     public void setId(String id) {
        this.id = id;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param cssClass the cssClass to set
     */
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    /**
     * @param enumInstance the enumInstance to set
     */
    public void setEnumInstance(String enumInstance) {
        this.enumInstance = enumInstance;
    }
}
