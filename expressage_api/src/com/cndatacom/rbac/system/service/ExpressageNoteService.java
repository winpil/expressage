package com.cndatacom.rbac.system.service;

import java.util.List;
import com.cndatacom.common.service.IBaseService;
import com.cndatacom.rbac.pojo.ExpressageNote;

public interface ExpressageNoteService extends IBaseService<ExpressageNote, String> {
	public List<ExpressageNote> getMessagesRecorder(String courierId,String startDate,String endDate);
}
