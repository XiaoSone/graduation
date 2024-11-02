package com.graduation.service;

import com.graduation.mapper.ZqjcMapper;
import com.graduation.model.Zqjc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope(value="singleton",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ZqjcService {
	
	@Autowired
	private ZqjcMapper zqjcMapper;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean insertSelective(Zqjc zqjc) {
		if(zqjcMapper.insertSelective(zqjc)>0){
			return true;
		}
		return false;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public Zqjc getzqjcByStudentId(String studentId) {
		return zqjcMapper.getzqjcByStudentId(studentId);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateByPrimaryKeySelective(Zqjc zqjc) {
		if(zqjcMapper.updateByPrimaryKeySelective(zqjc)>0) {
			return true;
		}
		return false;
	}

}
