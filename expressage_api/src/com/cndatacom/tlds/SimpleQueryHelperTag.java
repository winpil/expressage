/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cndatacom.tlds;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 *
 * @author Administrator
 */
public class SimpleQueryHelperTag extends TagSupport implements QueryHelper {

    private String name;
    private String id;
    private String type;
    private String itemsValue;
    private String checkItems;
    private List fields;
    //�������ֶ�Ϊ����ʱ����Ҫ�ñ�����������
    private String searchDateRegion = "";

    public SimpleQueryHelperTag() {
        fields = new ArrayList();
    }

    @Override
    public int doStartTag() throws JspException {
        fields.clear();
        if (pageContext.getRequest().getParameter("searchDateRegion") != null && pageContext.getRequest().getParameter("searchDateRegion").length() > 0) {
            searchDateRegion = pageContext.getRequest().getParameter("searchDateRegion");
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        StringBuilder html = new StringBuilder();

        html.append(MessageFormat.format(""
                + "<div id=\"searchInputWarp\" dataType=\"\" style=\"float:left;\"><input type=\"text\" id=\"txtItemsValue\"  name=\"txtItemsValue\" value=\"\" /></div>"
                + "<input type=\"hidden\" id=\"itemsValue\" name=\"itemsValue\" value=\"{1}\" />", id, itemsValue));
        html.append(MessageFormat.format("&nbsp;<select id=\"ddl_{0}\" name=\"ddl_{0}\" >", id));
        html.append(getOptionsHtml());
        html.append("</sellect>");
        //����һ��hidden���ڱ�����Ҫ��ѯ���ֶ�
        html.append(MessageFormat.format("<input type=\"hidden\" id=\"checkItems\" name=\"checkItems\" value=\"{0}\" />", checkItems));
        //����һ��hidden���ڱ�����������ʱ�Ĳ�ѯ����
        html.append(MessageFormat.format("<input type=\"hidden\" id=\"searchDateRegion\" name=\"searchDateRegion\" value=\"{0}\" />", searchDateRegion));
        //����������ť
        html.append(MessageFormat.format(" <input type=\"button\" value=\"����\" id=\"btnSearch_{0}\" id=\"btnSearch{0}\">", id));
        //ע��һ����ʼ���¼�
        html.append(MessageFormat.format("<script>initSimpleSearch(\"{0}\")</script>", id));
        

        try {
            pageContext.getOut().write(html.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        searchDateRegion = "";
        return this.EVAL_PAGE;
    }

    private String getOptionsHtml() {
        StringBuilder html = new StringBuilder();
        for (Object obj : fields) {
            QueryField field = (QueryField) obj;
            String f = MessageFormat.format("<option value=\"\" dataType=\"{1}\" matchType=\"{2}\" fieldName=\"{0}\" >{3}</option> ",
                    field.getFieldname(), field.getDatatype(), field.getMatchtype(), field.getTitle());
            html.append(f);
        }
        return html.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void addField(Object field) {
        fields.add(field);
    }

    /**
     * @param itemsValue the itemsValue to set
     */
    public void setItemsValue(String itemsValue) {
        this.itemsValue = itemsValue;
    }

    /**
     * @param checkItems the checkItems to set
     */
    public void setCheckItems(String checkItems) {
        this.checkItems = checkItems;
    }
}
