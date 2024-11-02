package com.graduation.service;

import com.graduation.mapper.InformMapper;
import com.graduation.model.Inform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope(value="singleton",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class InformService {
	
	@Autowired
	private InformMapper informMapper;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Inform> getAllInfrom(){
		return informMapper.selectTitleAndId();
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public Inform getSubInformById(int informId) {
		return informMapper.selectByPrimaryKey(informId);
	}

}
