/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cndatacom.common.web.action;

import java.util.List;

/**
*
* @author Administrator
*/
public abstract class StatusableActionSupport extends SimpleActionSupport {

   private Long updateStatus;

   /**
    * @return the updateStatus
    */
   public Long getUpdateStatus() {
       return updateStatus;
   }

   /**
    * @param updateStatus the updateStatus to set
    */
   public void setUpdateStatus(Long updateStatus) {
       this.updateStatus = updateStatus;
   }

   public String changeStatus() throws Exception {
       try {
           List pks = getPksByIds();
           getManager().updateStatusByIds(pks, updateStatus);
           addMessage("更新状态成功！");
       } catch (Exception e) {
           addError("更新状态失败！" + e.getMessage(), e);
       }
       return RELOAD;
   }
}
