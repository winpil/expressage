/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cndatacom.tlds;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Administrator
 */
public class DatePickerTag extends SimpleTagSupport {

    private String id;
    private String cssClass="dateField";
    private Date value;
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

            String html = "<input type=\"text\" name=\"" + id + "\" id=\"" + id + "\" class=\"" + cssClass + "\" onclick=\"showWdatePicker('"+id+"',\'" + dateFormat + "\')\"";
            if (null != value) {
                SimpleDateFormat df = new SimpleDateFormat(dateFormat);
                html = html + " value=\"" + df.format(value) + "\" ";
            }
            html = html + " />";
            out.println(html);
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

            // TODO: insert code to write html after writing the body content.
            // e.g.:
            //
            // out.println("    </blockquote>");

        } catch (java.io.IOException ex) {
            throw new JspException("Error in datapicker tag", ex);
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Date value) {
        this.value = value;
    }

    /**
     * @param dateFormat the dateFormat to set
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
}
