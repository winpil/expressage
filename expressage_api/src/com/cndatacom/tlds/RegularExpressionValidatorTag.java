/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cndatacom.tlds;

/**
 * ������ʽ��֤�ؼ�
 * 
 */
public class RegularExpressionValidatorTag extends CommonValidatorTag {

    private String validationExpression;

    @Override
    protected void getExtendAttribute(StringBuilder element) {
       
        //��֤��ʽ��������ʽ
        addAttribute(element, "validateType", "regularexpression");
        addAttribute(element, "validationExpression", validationExpression);
    }

    /**
     * @param validationExpression the validationExpression to set
     */
    public void setValidationExpression(String validationExpression) {
        this.validationExpression = validationExpression;
    }
}
