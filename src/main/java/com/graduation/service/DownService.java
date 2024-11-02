package com.graduation.service;

import com.graduation.mapper.DownMapper;
import com.graduation.model.Down;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope(value="singleton",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class DownService {
	
	@Autowired
	private DownMapper downMapper;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Down> getAllDown(){
		return downMapper.selectByExample(null);
	}

}
