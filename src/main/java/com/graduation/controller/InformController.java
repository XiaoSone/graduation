package com.graduation.controller;

import com.graduation.model.Inform;
import com.graduation.service.InformService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/informController")
public class InformController {
	
	@Autowired
	private InformService informService;
	
	@ResponseBody
	@RequestMapping(value="/getSubInform",method=RequestMethod.GET)
	public PageInfo getSubInform(int pageNum,int pageSize,int navigatePages) {
		//传入当前页和每页记录数
		PageHelper.startPage(pageNum, pageSize);
		List<Inform> list = informService.getAllInfrom();
		PageInfo pageInfo=new PageInfo(list,navigatePages);
		return pageInfo;
	}
	
	@ResponseBody
	@RequestMapping(value="/getSubInformById",method=RequestMethod.GET)
	public Inform getSubInformById(int informId) {
		return informService.getSubInformById(informId);
	}
	
}
