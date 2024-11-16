package com.graduation.controller;

import com.graduation.model.Mdb;
import com.graduation.model.Student;
import com.graduation.model.User;
import com.graduation.service.MdbService;
import com.graduation.service.StudentService;
import com.graduation.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/mdbController")
public class MdbController {

	@Autowired
	private UserService userService;
	@Autowired
	private MdbService mdbService;
	@Autowired
	private StudentService studentService;
	
	@ResponseBody
	@RequestMapping(value="/insertSelective",method=RequestMethod.POST)
	public boolean insertSelective(Mdb mdb) {
		if(mdb.getMdbId()==null) {
			return mdbService.insertSelective(mdb);
		}else {
			return mdbService.updateColumnById(mdb);
		}
	}
	
	@ResponseBody
	@RequestMapping("/getMdbByStudentId")
	public Mdb getMdbByStudentId(String studentId,HttpSession session) {
		if(studentId!=null&&!"".equals(studentId)) {
			Mdb mdb=mdbService.getMdbByStudentId(studentId);
			if(mdb!=null) {
				return mdb;
			}
		}else {
			//通过shiro获得当前会话用户信息
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		User user = userService.isUser(username);
			if(user!=null) {
				Student student = studentService.getStudentByUserId(user.getUserId());
				if(student!=null) {
					Mdb mdb=mdbService.getMdbByStudentId(student.getStudentId());
					if(mdb!=null) {
						return mdb;
					}
				}
			}
		}
		return new Mdb();
	}
	
	@ResponseBody
	@RequestMapping(value="/updateColumn",method=RequestMethod.PUT)
	public boolean updateColumn(Mdb mdb) {
		return mdbService.updateColumn(mdb);
	}
	
	@ResponseBody
	@RequestMapping(value="/updateColumnById",method=RequestMethod.PUT)
	public boolean updateColumnById(Mdb mdb) {
		return mdbService.updateColumnById(mdb);
	}
	
	@RequestMapping("/gotoTsureMdb")
	public ModelAndView gotoTsureMdb(@RequestParam(required=true)String studentId) {
		return new ModelAndView("/teacher/tsuremdb.html?studentId="+studentId);
	}

}
