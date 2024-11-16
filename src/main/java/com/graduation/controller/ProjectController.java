package com.graduation.controller;

import com.graduation.model.Project;
import com.graduation.model.Teacher;
import com.graduation.model.User;
import com.graduation.service.ProjectService;
import com.graduation.service.StudentService;
import com.graduation.service.TeacherService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.graduation.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/projectController")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	
	@ResponseBody
	@RequestMapping(value="/getProjectListByTeacherId",method=RequestMethod.GET)
	public PageInfo<Project> getProjectListByTeacherId(@RequestParam(required=true)String teacherId,
			  @RequestParam(defaultValue="1")int pageNum,@RequestParam(defaultValue="8")int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Project> list = projectService.getProjectListByTeacherId(teacherId);
		return new PageInfo<>(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/getCountProjectNum",method=RequestMethod.GET)
	public int getCountProjectNum() {
		return projectService.getCountProjectNum();
	}
	
	@ResponseBody
	@RequestMapping(value="/thisTeacherYesProjectNum",method=RequestMethod.GET)
	public int thisTeacherYesProjectNum(HttpSession session) {
		//通过shiro获得当前会话用户信息
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		User user = userService.isUser(username);

		//User user=(User) session.getAttribute("user");
		//System.out.println(user.getUserName());
		if(user!=null) {
			Teacher teacher = teacherService.findTeacherByUserId(user.getUserId());
			if(teacher!=null) {
				String teacherId = teacher.getTeacherId();
				System.out.println(teacherId);
				return projectService.thisTeacherYesProjectNum(teacherId);
			}
		}
		return 0;
	}
	
	@ResponseBody
	@RequestMapping(value="/thisTeacherUndefinedStudentNum",method=RequestMethod.GET)
	public int thisTeacherUndefinedStudentNum(HttpServletRequest request) {
		//通过shiro获得当前会话用户信息
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		User user = userService.isUser(username);

		//User user=(User) request.getSession().getAttribute("user");
		if(user!=null) {
			Teacher teacher = teacherService.findTeacherByUserId(user.getUserId());
			if(teacher!=null) {
				String teacherId = teacher.getTeacherId();
				return studentService.thisTeacherUndefinedStudentNum(teacherId);
			}
		}
		return 0;
	}
	
	/**
	 * 添加与更新方法
	 * @param project
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/insertSelective",method=RequestMethod.POST)
	public boolean insertSelective(Project project,HttpServletRequest request) {
		//通过shiro获得当前会话用户信息
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		User user = userService.isUser(username);
		//User user=(User) request.getSession().getAttribute("user");
		if(user!=null) {
			Teacher teacher = teacherService.findTeacherByUserId(user.getUserId());
			if(teacher!=null) {
				project.setTeacherId(teacher.getTeacherId());
				if(project.getProjectId()!=null) {
					return projectService.updateByPrimaryKeySelective(project);
				}else {
					return projectService.insertSelective(project,teacher.getTeacherId());
				}
			}
		}
		return false;
	}
	
	@ResponseBody
	@RequestMapping(value="/getProjectByTeacherId")
	public List<Project> getProjectByTeacherId(HttpServletRequest request){
		//通过shiro获得当前会话用户信息
		Subject subject = SecurityUtils.getSubject();
		String username = (String) subject.getPrincipal();
		User user = userService.isUser(username);
		//User user=(User) request.getSession().getAttribute("user");
		if(user!=null) {
			Teacher teacher = teacherService.findTeacherByUserId(user.getUserId());
			if(teacher!=null) {
				System.out.println("getProjectByTeacherId：" + projectService.getProjectByTeacherId(teacher.getTeacherId()).toString());
				return projectService.getProjectByTeacherId(teacher.getTeacherId());
			}
		}
		return null;
	}

}
