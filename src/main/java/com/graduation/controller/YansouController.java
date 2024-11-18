package com.graduation.controller;

import com.graduation.model.Student;
import com.graduation.model.User;
import com.graduation.model.YansouTeam;
import com.graduation.service.StudentService;
import com.graduation.service.UserService;
import com.graduation.service.YansoouService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/yansouController")
public class YansouController {

	@Autowired
	private UserService userService;
	@Autowired
	private YansoouService yansoouService;
	@Autowired
	private StudentService studentService;
	
	@ResponseBody
	@RequestMapping("/selectYanSouInfoByid")
	public YansouTeam selectYanSouInfoByid(HttpSession session) {
		//通过shiro获得当前会话用户信息
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		User user = userService.isUser(username);
		if(user!=null) {
			Student student = studentService.getStudentByUserId(user.getUserId());
			System.out.println(student);
			if(student!=null) {
				if(student.getYansouTeamId()!=null && !student.getYansouTeamId().isEmpty()) {
					return yansoouService.selectYanSouInfoByid(Integer.parseInt(student.getYansouTeamId()));
				}
			}
		}
		return null;
	}

}
