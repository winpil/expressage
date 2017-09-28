/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cndatacom.tlds;

import java.text.MessageFormat;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * 通用验证控件标签，用于验证表单填写控件内容是否符合要求
 * controlToValidate：需要进行验证的控件的name属性
 * validateFunction：javascript验证脚本，函数签名fn(obj)，obj为当前接受验证的控件对象
 * @author lsh update 20110108 12:30:00
 */
public class CommonValidatorTag extends SimpleTagSupport {

    /**
     * 验证控件id
     */
    private String id;
    /**
     * 需要验证的控件的名称（name属性）
     */
    private String controlToValidate;
    /**
     * 填写规则信息
     */
    private String ruleMessage = "";
    /**
     * 填写错误时的提示（默认："格式错误，请重新填写"）
     */
    private String errorMessage = "格式错误，请重新填写";
    /**
     * 初始化提示div样式（默认为:tip)
     */
    private String cssClass = "tip";
    /**
     * 填写正确时的提示div样式（默认：tip-right）
     */
    private String normalClass = "tip-right";
    /**
     * 焦点移入时的提示div样式（默认:tip-focus)
     */
    private String focusClass = "tip-focus";
    /**
     * 填写错误时的提示div样式（默认：tip-error)
     */
    private String errorClass = "tip-error";
    /**
     * 验证脚本函数
     */
    private String validateFunction;
    /**
     * 是否可空（默认：false)
     */
    private Boolean nullable = false;

    /**
     * 标签处理入口函数，完成标签所有属性的赋值和解释
     * 生成输出最终的html，写入页面     * 
     * @throws JspException
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();

        try {

            StringBuilder html = new StringBuilder();

            //非空提示div
            html.append(getNullableTipDiv());
            html.append("\n\r");

            //验证控件tip div           
            html.append(getValidatorTipHtml());
            html.append("\n\r");

            //输出标签的html
            out.println(html.toString());

            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }

        } catch (java.io.IOException ex) {
            throw new JspException("Error in CommonValidator tag", ex);
        }
    }

    /**
     * 非空字段提示div html
     * @return
     */
    protected String getNullableTipDiv() {
        StringBuilder html = new StringBuilder();
        String nullableLable = nullable ? "&nbsp;" : "*";
        //非空提示div
        return "<div class=\"normal_icon\"  >" + nullableLable + "</div>";
    }

    /**
     * 验证辅助对象div html
     * 验证控件的配置，在页面中通过js分析配置完成验证控件的初始化
     * @return
     */
    protected String getValidatorTipHtml() {
        //创建div对象
        StringBuilder element = new StringBuilder("<div ");
        //添加基础属性
        getCommonAttribute(element);
        //添加附加属性
        getExtendAttribute(element);
        element.append(" ></div");
        //输入div html
        return element.toString();
    }

    /**
     * 验证控件通用属性
     * @param element
     */
    private void getCommonAttribute(final StringBuilder element) {
        addAttribute(element, "id", id);
        addAttribute(element, "name", id);
        addAttribute(element, "class", cssClass);
        addAttribute(element, "type", "validateHelper");
        addAttribute(element, "controlToValidate", controlToValidate);
        addAttribute(element, "rule", ruleMessage);
        addAttribute(element, "error", errorMessage);
        addAttribute(element, "normalClass", normalClass);
        addAttribute(element, "focusClass", focusClass);
        addAttribute(element, "errorClass", errorClass);
    }

    /**
     * 当前验证控件特有的属性（子类可覆盖）
     * @param element
     */
    protected void getExtendAttribute(final StringBuilder element) {
        //验证方式：自定义javascript脚本
        addAttribute(element, "validateType", "jsscript");
        addAttribute(element, "validateFunction", validateFunction);
    }

    /**
     * 添加控件属性
     * @param element
     * @param attributeName
     * @param value
     */
    protected void addAttribute(final StringBuilder element, final String attributeName, final String value) {
        element.append(MessageFormat.format(" {0}=\"{1}\" ", attributeName, value));
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @param controlToValidate
     */
    public void setControlToValidate(String controlToValidate) {
        this.controlToValidate = controlToValidate;
    }

    /**
     *
     * @param ruleMessage
     */
    public void setRuleMessage(String ruleMessage) {
        this.ruleMessage = ruleMessage;
    }

    /**
     *
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     *
     * @param normalClass
     */
    public void setNormalClass(String normalClass) {
        this.normalClass = normalClass;
    }

    /**
     *
     * @param focusClass
     */
    public void setFocusClass(String focusClass) {
        this.focusClass = focusClass;
    }

    /**
     *
     * @param errorClass
     */
    public void setErrorClass(String errorClass) {
        this.errorClass = errorClass;
    }

    /**
     *
     * @param nullable
     */
    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    /**
     *
     * @param validateFunction
     */
    public void setValidateFunction(String validateFunction) {
        this.validateFunction = validateFunction;
    }

    /**
     *
     * @param cssClass
     */
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
}
