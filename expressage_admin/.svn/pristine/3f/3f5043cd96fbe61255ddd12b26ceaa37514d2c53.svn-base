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
public class PaginationTag extends SimpleTagSupport {

    private String id;
    private int pageSize = 10;
    private int totalCount;
    private int totalPage;
    private int currentPageNo;

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


            if (totalPage < 1) {
                totalPage = 1;
            }

            StringBuilder html = new StringBuilder();
            html.append("<div class='ZB_PageL'>");
            html.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
            html.append("<tr>");
            html.append("<td  class='ZB_PageLine'><a href='javascript:jumpFirstPage()'>首页</a>&nbsp;&nbsp;<a href='javascript:pageUp()'>上一页</a>&nbsp;&nbsp;<a href='javascript:pageDown()'>下一页</a>&nbsp;&nbsp;<a href='javascript:jumpLastPage()'>尾页</a></td>");
            html.append("</tr></table></div>");
            html.append("<div class='ZB_PageR'>");
            html.append("<input type='hidden' id='pageTotalPages' name='pageTotalPages' value='");
            html.append(totalPage);
            html.append("' />");
            html.append("<input type='hidden' id='limit' name='limit' value='10' />");
            html.append("<input type='hidden' id='start' name='start' value='0 '/>");
            html.append("<input type='hidden' id='page.pageNo' name='page.pageNo' value='");
            html.append(currentPageNo);
            html.append("' />");
            html.append("<font color='red'>总记录");
            html.append(totalCount);
            html.append("条</font> 每页:");
            html.append("<select id='page.pageSize' name='page.pageSize' value='10' onchange='changePageSize()'>"
                    + "<option value='10' >10</option>"
                    + "<option value='20' >20</option>"
                    + "<option value='40' >40</option>"
                    + "<option value='50' >50</option>"
                    + "<option value='99' >99</option>"
                    + "</select>条");
            html.append(" 当前第：<font color='red'>");
            html.append(currentPageNo);
            html.append("/");
            html.append(totalPage);
            html.append("页</font> ");

            html.append("<a href='javascript:jumpPage()'>跳转到</a>");
            html.append(" <input type='text' value='' name='txtPageNo' id='txtPageNo' style='width:20px; height:14px;'/>");
            html.append("页</div>");
            html.append("<script>document.getElementById('page.pageSize').value=");
            html.append(pageSize);
            html.append(";document.getElementById('limit').value=");
            html.append(pageSize);
            html.append(";</script>");
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
            throw new JspException("Error in Pagination tag", ex);
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setCurrentPageNo(int currentPageNo) {
        this.currentPageNo = currentPageNo;
    }
}
