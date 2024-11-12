package com.graduation.controller;

import com.graduation.model.Inform;
import com.graduation.service.InformService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/informController")
public class InformController {
	
	@Autowired
	private InformService informService;
	
	@ResponseBody
	//@RequestMapping(value="/getSubInform",method=RequestMethod.GET)
	@GetMapping("/getSubInform")
	public PageInfo getSubInform(@RequestParam("pageNum")int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("navigatePages") int navigatePages) {
		//传入当前页和每页记录数
		PageHelper.startPage(pageNum, pageSize);
		List<Inform> list = informService.getAllInfrom();
		PageInfo pageInfo=new PageInfo(list,navigatePages);
		System.out.println(pageInfo);
		return pageInfo;
	}
	
	@ResponseBody
	@RequestMapping(value="/getSubInformById",method=RequestMethod.GET)
	public Inform getSubInformById(int informId) {
		return informService.getSubInformById(informId);
	}
	
}
