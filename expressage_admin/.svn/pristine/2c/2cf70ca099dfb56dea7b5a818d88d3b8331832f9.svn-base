/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cndatacom.tlds;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Administrator
 */
public class TableHeadTag extends SimpleTagSupport {

    private String title;
    private String sortField;

    /**
     *
     * @throws JspException
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            StringBuilder html = new StringBuilder();
            html.append("<div id='sortField_");
            html.append(sortField);
            html.append("' class='sort-n'");            
            html.append(" sortField='");
            html.append(sortField);
            html.append("' />");
            html.append(title);
            html.append("</div>");

            out.println(html.toString());
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

        } catch (java.io.IOException ex) {
            throw new JspException("Error in TableHead tag", ex);
        }
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @param sortField
     */
    public void setSortField(String sortField) {
        this.sortField = sortField;
    }
}
