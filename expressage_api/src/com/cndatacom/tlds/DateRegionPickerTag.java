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
public class DateRegionPickerTag extends SimpleTagSupport {

    private String id;
    private String cssClass;
    private String iconCssClass;
    private String dateFormat = "yyyy-MM-dd";

    /**
     * Called by the container to invoke this tag. 
     * The implementation of this method is provided by the tag library developer,
     * and handles all tag processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {
            // TODO: insert code to write html before writing the body content.
            // e.g.:
            //
            // out.println("<strong>" + attribute_1 + "</strong>");
            // out.println("    <blockquote>");

            out.println("<input type=\"text\" name=\"" + id + "_begin\" id=\"" + id + "_begin\" class=\"" + cssClass + "\" onclick=\"showWdatePicker(this,'"+dateFormat+"')\" />");
           // out.println("<button   />");
            out.println("жа");
           out.println("<input type=\"text\" name=\"" + id + "_end\" id=\"" + id + "_end\" class=\"" + cssClass + "\" onclick=\"showWdatePicker(this,'"+dateFormat+"')\" />");
           // out.println("<button  name=\"" + id + "_end_icon\" id=\"" + id + "_end_icon\" class=\"" + iconCssClass + "\" onclick=\"WdatePicker({el:'" + id + "_end',isShowClear:false,readOnly:true,highLineWeekDay:true})\" />");
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");

        } catch (java.io.IOException ex) {
            throw new JspException("Error in NewTagHandler tag", ex);
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    /**
     * @param iconCssClass the iconCssClass to set
     */
    public void setIconCssClass(String iconCssClass) {
        this.iconCssClass = iconCssClass;
    }


}
