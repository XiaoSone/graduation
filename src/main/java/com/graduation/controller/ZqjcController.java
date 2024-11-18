package com.graduation.controller;

import com.graduation.model.Student;
import com.graduation.model.User;
import com.graduation.model.Zqjc;
import com.graduation.service.StudentService;
import com.graduation.service.UserService;
import com.graduation.service.ZqjcService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/zqjcController")
public class ZqjcController {

	@Autowired
	private UserService userService;
	@Autowired
	private ZqjcService zqjcService;
	@Autowired
	private StudentService studentService;
	
	@ResponseBody
	@RequestMapping(value="/insertSelective",method=RequestMethod.POST)
	public boolean insertSelective(@RequestBody Zqjc zqjc) {
		if(zqjc.getZqjcId()==null) {
			return zqjcService.insertSelective(zqjc);
		}else {
			return zqjcService.updateByPrimaryKeySelective(zqjc);
		}
	}
	
	@ResponseBody
	@RequestMapping("/getzqjcByStudentId")
	public Zqjc getzqjcByStudentId() {
		Subject currentUser = SecurityUtils.getSubject();
		String useraccount = (String) currentUser.getPrincipal();
		User user = userService.isUser(useraccount);
		String studentId = studentService.getStudentByUserId(user.getUserId()).getStudentId();
		if(studentId!=null&&!studentId.isEmpty()) {
			Zqjc zqjc = zqjcService.getzqjcByStudentId(studentId);
			if(zqjc!=null) {
				return zqjc;
			}
		}else {
			if(user!=null) {
				Student student = studentService.getStudentByUserId(user.getUserId());
				if(student!=null) {
					Zqjc zqjc = zqjcService.getzqjcByStudentId(student.getStudentId());
					if(zqjc!=null) {
						return zqjc;
					}
				}
			}
		}
		return new Zqjc();
	}
	
	@RequestMapping("/gotoTzqjc")
	public String gotoTzqjc(@RequestParam(required=true)String studentId) {
		return "redirect:http://localhost:8081/#/tzqjc?studentId="+studentId;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateByPrimaryKeySelective",method=RequestMethod.POST)
	public boolean updateByPrimaryKeySelective(@RequestBody Zqjc zqjc) {
		return zqjcService.updateByPrimaryKeySelective(zqjc);
	}

}
