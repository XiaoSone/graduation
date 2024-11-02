package com.graduation.service;

import com.graduation.mapper.WorkTimeMapper;
import com.graduation.model.WorkTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope(value="singleton",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class WorkTimeService {
	
	@Autowired
	private WorkTimeMapper workTimeMapper;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public WorkTime getWorkTime(String thisDate) {
		return workTimeMapper.getWorkTime(thisDate);
	}

}
