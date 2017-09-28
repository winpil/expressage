package com.cndatacom.rbac.dao;

import java.util.List;

import com.cndatacom.common.orm.IBaseDAO;
import com.cndatacom.rbac.pojo.ExpressageNote;

public interface ExpressageNoteDAO extends IBaseDAO<ExpressageNote, String> {
	
	public List<ExpressageNote> getMessagesList(String courierId,String startDate,String endDate);

}
