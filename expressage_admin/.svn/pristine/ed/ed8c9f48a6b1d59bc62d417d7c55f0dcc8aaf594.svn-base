/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cndatacom.common.web.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import com.cndatacom.common.bean.ValidateInfo;
import com.cndatacom.common.orm.Page;
import com.cndatacom.common.orm.PropertyFilter;
import com.cndatacom.common.orm.hibernate.HibernateUtils;
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.common.web.struts2.Struts2Utils;

/**
*
* @author Administrator
*/
public abstract class SimpleActionSupport extends CrudActionSupport {

   private Page page = new Page();
   private String id;//为预处理传过来的隐藏式id
   private String ids;
   private String checkItems;
   private String itemsValue;
   private ValidateInfo validateInfo = new ValidateInfo();
   private List<PropertyFilter> filters = null;
   public static final String LIST = "list";
   

   // <editor-fold defaultstate="collapsed" desc="抽象方法">
   protected abstract IBaseService getManager();

   //创建新的实体对象
   protected abstract Object createNewInstance();

   //获取已经存在的实体对象
   public abstract Object getModel();

   //设置实体对象
   public abstract void setModel(Object obj);
   // </editor-fold>

   //构造方法进行初始化,默认按10条记录分页
   public SimpleActionSupport() {
       limit = 10;
       page.pageSize(limit);
   }

   //////////////////////////////////////////基础业务start////////////////////////////////////////////////
   /**
    * 查询列表
    * @return
    * @throws Exception
    */
   @SuppressWarnings("unchecked")
	@Override
   public String list() throws Exception {
       try {
           //在list前会默认调用prepareList
           //分页查询
           setPage(getManager().findPage(getPage(), getFilters()));
       } catch (Exception e) {
           addError("数据查询失败！", e);
       }
       return LIST;
   }

   /**
    * 新增、更新实体
    * @return
    * @throws Exception
    */
   @Override
   public String save() throws Exception {
       try {
           getManager().save(getModel());
           addMessage("数据保存成功！");
       } catch (Exception e) {
           addError("数据保存失败！", e);
       }

       return RELOAD;
   }

   /**
    * 删除实体
    * @return
    * @throws Exception
    */
   @Override
   public String delete() throws Exception {
       try {
           List pks = getPksByIds();
           getManager().deleteByIds(pks);
           addMessage("数据删除成功！");
       } catch (Exception e) {
           addError("数据删除失败！", e);
       }
       return RELOAD;
   }



   /**
    * 编辑实体
    * @return
    * @throws Exception
    */
   @Override
   public String input() throws Exception {
       return INPUT;
   }
   //////////////////////////////////////////基础业务end////////////////////////////////////////////////

   /**
    * 查询列表前的预处理
    */
   public void prepareList() {
       //设置过滤条件
       getRequestFilter();
       //设置分页参数
       setPageConfig();
   }

   /**
    * 准备实体
    * @throws Exception
    */
   @Override
   protected void prepareModel() throws Exception {
       try {
           if (id != null&&!"".equals(id)) {
               setModel(getManager().getAndInitEntity(id));
           } else {
               setModel(createNewInstance());
           }
       } catch (Exception e) {
           addError("记录查询失败！", e);
       }
   }

   /**
    * 获取页面上的查询过滤条件，将其转化为PropertyFilter列表
    * @return 过滤条件列表
    */
   protected List<PropertyFilter> getRequestFilter() {
       filters = HibernateUtils.buildPropertyFilters(Struts2Utils.getRequest());
       if (getItemsValue() != null && !"".equals(itemsValue.trim()) && getCheckItems() != null && !"".equals(checkItems.trim())) {
           getFilters().add(new PropertyFilter(getCheckItems(), getItemsValue()));
       }
       return getFilters();
   }

   /**
    * 获取选中状态的实体id
    * @return
    */
   protected List getPksByIds() {
       List pks = new ArrayList();
       String[] idsValue = ids.split("\\,");
       for (String delId : idsValue) {
    	   System.out.println(delId);
           pks.add(delId);
       }
       return pks;
   }

   /**
    * 记录异常信息
    * @param e
    */
   protected void logException(Exception e) {
       e.printStackTrace();
       logger.error(e.getMessage());
   }

   /**
    * 操作成功后在页面上显示信息
    * @param aMessage
    */
   protected void addMessage(String aMessage) {
       addActionMessage("[message]" + aMessage);
   }

   /**
    * 操作失败，在页面显示错误信息；记录异常信息
    * @param anErrorMessage
    * @param e
    */
   protected void addError(String anErrorMessage, Exception e) {
       addActionMessage("[error]" + anErrorMessage);
       logException(e);
   }

   /**
    * 刷新页面
    * @param frameName
    * @param url
    */
   protected void refreshFrame(String frameName, String url) {
       addActionMessage("[refresh]" + frameName + "$" + url);
   }

   /**
    * 查询前设置分页配置
    */
   protected void setPageConfig() {
       getPage().setLimit(limit);
       getPage().setStart(start);
   }

   /**
    * @return the page
    */
   public Page getPage() {
       return page;
   }

   /**
    * @param page the page to set
    */
   public void setPage(Page page) {
       this.page = page;
   }

   /**
    * @return the id
    */
   public String getId() {
       return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(String id) {
       this.id = id;
   }

   /**
    * @return the ids
    */
   public String getIds() {
       return ids;
   }

   /**
    * @param ids the ids to set
    */
   public void setIds(String ids) {
       this.ids = ids;
   }

   /**
    * @return the checkItems
    */
   public String getCheckItems() {
       return checkItems;
   }

   /**
    * @param checkItems the checkItems to set
    */
   public void setCheckItems(String checkItems) {
       this.checkItems = checkItems;
   }

   /**
    * @return the itemsValue
    */
   public String getItemsValue() {
       return itemsValue;
   }

   /**
    * @param itemsValue the itemsValue to set
    */
   public void setItemsValue(String itemsValue) {
       this.itemsValue = itemsValue;
   }

   /**
    * @return the validateInfo
    */
   public ValidateInfo getValidateInfo() {
       return validateInfo;
   }

   /**
    * @param validateInfo the validateInfo to set
    */
   public void setValidateInfo(ValidateInfo validateInfo) {
       this.validateInfo = validateInfo;
   }

   /**
    * @return the filters
    */
   protected List<PropertyFilter> getFilters() {
       return filters;
   }

   /**
    * 显示提示信息
    * @param message
    */
   // <editor-fold defaultstate="collapsed" desc="工具函数">
   protected void alert(String message) {
       try {
           HttpServletResponse response = Struts2Utils.getResponse();
           response.getWriter().print("<script>alert('" + message + "');</script>");
           response.flushBuffer();
       } catch (IOException ex) {
           Logger.getLogger(SimpleActionSupport.class.getName()).log(Level.SEVERE, null, ex);
       }
   } 

   // </editor-fold>
}