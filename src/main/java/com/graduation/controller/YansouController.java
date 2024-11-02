package com.graduation.controller;

import com.graduation.model.Student;
import com.graduation.model.User;
import com.graduation.model.YansouTeam;
import com.graduation.service.StudentService;
import com.graduation.service.YansoouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/yansouController")
public class YansouController {
	
	@Autowired
	private YansoouService yansoouService;
	@Autowired
	private StudentService studentService;
	
	@ResponseBody
	@RequestMapping("/selectYanSouInfoByid")
	public YansouTeam selectYanSouInfoByid(HttpSession session) {
		User user=(User) session.getAttribute("user");
		if(user!=null) {
			Student student = studentService.getStudentByUserId(user.getUserId());
			if(student!=null) {
				if(student.getYansouTeamId()!=null&&!student.getYansouTeamId().isEmpty()) {
					return yansoouService.selectYanSouInfoByid(Integer.parseInt(student.getYansouTeamId()));
				}
			}
		}
		return null;
	}

}
