/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
package com.cndatacom.tlds;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Administrator
 */

public class QueryFieldTag extends TagSupport {
    private String title;
    private String fieldname;
    private String datatype;
    private String matchtype;
    private String value;

    @Override
    public int doEndTag() throws JspException {
        QueryHelper qh=(QueryHelper)getParent();
        QueryField field=new QueryField();
        field.setDatatype(this.datatype);
        field.setFieldname(fieldname);
        field.setMatchtype(matchtype);
        field.setTitle(title);
        qh.addField(field);
        return EVAL_PAGE;
    }

    
   
    public void setTitle(String title) {
        this.title = title;
    }



    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public void setMatchtype(String matchtype) {
        this.matchtype = matchtype;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }



    /**
     * @return the datatype
     */
    public String getDatatype() {
        return datatype;
    }

    /**
     * @return the matchtype
     */
    public String getMatchtype() {
        return matchtype;
    }

    /**
     * @return the fieldname
     */
    public String getFieldname() {
        return fieldname;
    }

    /**
     * @param fieldname the fieldname to set
     */
    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    
}
