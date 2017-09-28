package com.cndatacom.rbac.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cndatacom.common.exception.ServiceException;
import com.cndatacom.common.service.impl.BaseServiceImpl;
import com.cndatacom.common.utils.Constants;
import com.cndatacom.rbac.dao.ISysGroupDAO;
import com.cndatacom.rbac.pojo.SysGroup;
import com.cndatacom.rbac.system.service.ISysGroupService;

/**
 * ��֯�ܹ�Service��ӿڶ�Ӧʵ����
 * @author yab
 */
@Service("sysGroupService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class SysGroupServiceImpl extends BaseServiceImpl<SysGroup,String> implements ISysGroupService {
	
	@Resource(name="sysGrupHibernate")
	private ISysGroupDAO sysGroupDao;

	public ISysGroupDAO getBaseDao() {
		return sysGroupDao;
	}
	
	
	public synchronized void setTheSortValue(String id, String orderType) {
		SysGroup ownGroup = sysGroupDao.getAndInitEntity(id);

		SysGroup parentGroup = ownGroup.getParent();

		Long ownOrderId = ownGroup.getOrderId();

		// �����ƶ�
		if (Constants.ORDER_UP.equals(orderType)) {
			if (ownGroup.getOrderId().longValue() != 1L) {
				SysGroup upGroup = sysGroupDao.findUnique(
						"from SysGroup where parent = ? and orderId = ?",
						new Object[] { parentGroup, ownOrderId - 1 });

				upGroup.setOrderId(ownOrderId);

				ownGroup.setOrderId(ownOrderId - 1);
			}
		// �����ƶ�
		} else if (Constants.ORDER_DOWN.equals(orderType)) {
			long size = sysGroupDao.getChildrenSize(parentGroup.getGroupId());

			if (ownOrderId.longValue() != size) {
				SysGroup downGroup = sysGroupDao.findUnique(
						"from SysGroup where parent = ? and orderId = ?",
						new Object[] { parentGroup, ownOrderId + 1 });
				downGroup.setOrderId(ownOrderId);

				ownGroup.setOrderId(ownOrderId + 1);
			}
		}

	}

	public synchronized void deleteSysGroup(String id) {
		try {
			SysGroup sysGroup = sysGroupDao.getAndInitEntity(id);
			
			if(!sysGroup.isLeaf()){
				throw new ServiceException("��֯�ܹ�����Ҷ�ӽ�㣬���ܱ�ɾ����");
			}
			
			if(sysGroup.getUsers().size() > 0){
				throw new ServiceException("��֯�ܹ���������û������ܱ�ɾ����");
			}
			


			List<SysGroup> results = sysGroupDao.find(
					"from SysGroup where parent = ? and orderId > ?",
					new Object[] { sysGroup.getParent(), sysGroup.getOrderId() });
			
			for(SysGroup group : results){
				group.setOrderId(group.getOrderId() - 1);
			}
			
			sysGroupDao.delete(sysGroup);
		}catch (ServiceException se) {
			throw se;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new ServiceException("ɾ����֯�ܹ�ʧ�ܣ�������ϢΪ��" + e.getMessage());
		}
	}

	public synchronized void saveSysGroup(SysGroup sysGroup) {
		try {
			long size = sysGroupDao.getChildrenSize(sysGroup.getParent().getGroupId());
			
			if(null == sysGroup.getGroupId()) {
				sysGroup.setOrderId(size + 1);
			}
			
			sysGroupDao.save(sysGroup);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new ServiceException(e.getMessage());
		}
	}


	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public List<HashMap<String, Object>> getSysGroupByParentId(String parentId) {

		List<SysGroup> results = sysGroupDao.find(
				"from SysGroup where parent.id = ? order by orderId asc,id asc", parentId);

		List<HashMap<String, Object>> subGroups = new ArrayList<HashMap<String, Object>>();

		for (SysGroup sysGroup : results) {
			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("id", sysGroup.getGroupId());

			map.put("text", sysGroup.getGroupName());
			map.put("leaf", sysGroup.isLeaf());

			subGroups.add(map);
		}

		return subGroups;
	}

	@Transactional(propagation = Propagation.REQUIRED,readOnly=true)
	public SysGroup findSysGroupByUsername(String username) {
		
		try{
			String hql = "select g from SecuritySysUser u inner join u.sysGroup g where u.username = ?";
			List<SysGroup> groups = this.getBaseDao().find(hql,username);
			if(null != groups && groups.size() > 0){
				return groups.get(0);
			}
		}catch(Exception e){
			logger.error(e.toString());
			throw new ServiceException("ͨ���û�ID���ض�Ӧ����ʧ�ܣ�");
		}
		return null;
	}

}
