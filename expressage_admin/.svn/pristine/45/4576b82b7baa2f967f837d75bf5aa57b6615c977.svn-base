/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cndatacom.tlds;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Administrator
 */
public class ImageThumbTag extends SimpleTagSupport {

    private String id;
    private String src;
    private String gallyName;
    private String cssStyle;
    private String cssClass;

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

            StringBuilder html = new StringBuilder();
            html.append("<a href='");
            html.append(src);
           
            html.append("' class='nyroModal' >");
            html.append("<img src='");
            html.append(src);
            html.append("' ");
            html.append(" id='");
            html.append(id);
            html.append("' ");
            html.append(" name='");
            html.append(id);
            html.append("' ");
            if (StringUtils.isNotEmpty(cssStyle)) {
                html.append(" style='");
                html.append(cssStyle);
                html.append("' ");
            }
            if (StringUtils.isNotEmpty(cssClass)) {
                html.append(" class='");
                html.append(cssClass);
                html.append("' ");
            }
            html.append(" /></a>");
            out.println(html.toString());
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");

        } catch (java.io.IOException ex) {
            throw new JspException("Error in ImageThumb tag", ex);
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setGallyName(String gallyName) {
        this.gallyName = gallyName;
    }

    /**
     * @param cssStyle the cssStyle to set
     */
    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    /**
     * @param cssClass the cssClass to set
     */
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
}
