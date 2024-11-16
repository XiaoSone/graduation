package com.graduation.controller;

import com.graduation.model.DaBian;
import com.graduation.model.Student;
import com.graduation.model.User;
import com.graduation.service.DabianService;
import com.graduation.service.StudentService;
import com.graduation.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dabianController")
public class DabianController {

    @Autowired
    private UserService userService;
    @Autowired
    private DabianService dabianService;
    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping("/getThisStudentScore")
    public Integer getThisStudentScore(HttpSession session) {
        //通过shiro获得当前会话用户信息
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		User user = userService.isUser(username);
        if(user!=null) {
            Student student = studentService.getStudentByUserId(user.getUserId());
            if(student!=null) {
                return dabianService.getThisStudentScore(student.getStudentId());
            }
        }
        return -1;
    }

    @ResponseBody
    @RequestMapping(value="/insertOrUpdate",method= RequestMethod.POST)
    public boolean insertOrUpdate(DaBian daBian) {
        if(daBian.getId()==null) {
            return dabianService.insertDabian(daBian);
        }else {
            return dabianService.updateDabian(daBian);
        }
    }

}
