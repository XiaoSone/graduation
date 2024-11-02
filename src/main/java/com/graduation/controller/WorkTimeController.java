package com.graduation.controller;


import com.graduation.model.WorkTime;
import com.graduation.service.WorkTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/workTimeController")
public class WorkTimeController {
	
	@Autowired
	private WorkTimeService workTimeService;
	
	@ResponseBody
	@RequestMapping("/getWorkTime")
	public WorkTime getWorkTime(String date,HttpServletResponse response) {
		response.setContentType("application/json;charset=utf-8");
		WorkTime workTime = workTimeService.getWorkTime(date);
		return workTime;
	}

}
