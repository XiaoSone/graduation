package com.graduation.controller;

import com.graduation.model.User;
import com.graduation.service.UserService;
import com.graduation.util.Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
//@RequestMapping("/userController")
public class UserController {
	
	@Autowired
	private UserService userService;

	//确认用户
	@PostMapping("/isuser")
	public @ResponseBody User isUser(@RequestParam String account) {
		//boolean test = userService.isUser(account);
		return userService.isUser(account);

	}
	//确认密码
	@PostMapping("/ispassword")
	public @ResponseBody boolean ispassword(@RequestParam("password")String password,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user!=null) {
			return userService.ispassword(Utils.md5(password),user.getUserId());
		}
		return false;
	}
	//登录
	@PostMapping("/login")
	public @ResponseBody String login(HttpSession session,String randStr,String account,String password) {
		String randStr2=(String) session.getAttribute("randStr");
			//if(randStr2!=null&&randStr2.equals(randStr)) {
			if(randStr != null) {
				password=Utils.md5(password);
				Subject currentUser = SecurityUtils.getSubject();
				if (!currentUser.isAuthenticated()) {
					//把用户名和密码封装为UsernamePasswordToken
		            UsernamePasswordToken token = new UsernamePasswordToken(account, password);
		            try {
		            	//执行登录
		                currentUser.login(token);
		                return toUI(session,account,password);
						//return "登录成功";
		            }
		            //用户不存在
		            catch (UnknownAccountException ae) {
		            	return "用户不存在";
		            }
		            //用户被锁定
		            catch (LockedAccountException e) {
		            	return "用户名或密码错误";
					}
				}else {
					return toUI(session,account,password);
				}
			}else {
				return "验证码错误";
			}
	}
	//登录成功跳转
	private String toUI(HttpSession session,String account,String password) {
		User user = userService.login(account, password);
		if(user!=null) {
			if(2==user.getUserRoles()) {
				session.setAttribute("user", user);
				//return "student/sindex.html";
				return "学生登录成功";
			}
			if(1==user.getUserRoles()) {
				session.setAttribute("user", user);
				//return "teacher/tindex.html";
				return "教师登录成功";
			}
		}
		return "用户名或密码错误";
	}
	//退出登录
	@RequestMapping("/logout")
	public @ResponseBody String logout(HttpSession session) {
		session.removeAttribute("user");
		//return "redirect:http://localhost:8080/graduation/login.html";
		return "退出成功";
	}
	//更新用户信息
	@RequestMapping(value="/updateInfo",method=RequestMethod.POST)
	public @ResponseBody boolean updateStudentInfo(User user,
			@RequestParam(value="portrait",required=false)MultipartFile portrait,HttpServletRequest request) {
		if(portrait!=null&&portrait.getSize()>0) {
			if(portrait.getSize()>(10*1024*1024)) {
				return false;
			}
			String filename=portrait.getOriginalFilename();
			String dbPath=request.getServletContext().getContextPath()+"/portrait/"+user.getUserId();
			String basePath=request.getServletContext().getRealPath("/portrait/"+user.getUserId());
			new File(basePath).mkdir();
        	File portraitFile=new File(basePath,filename);
        	try {
				portrait.transferTo(portraitFile);
				user.setUserPortrait(dbPath+"/"+filename);
				return userService.updateUserInfo(user);
			} catch (Exception e) {
				e.printStackTrace();
			} 
        	
		}
		return userService.updateUserInfo(user);
	}
	//更新密码
	@RequestMapping(value="/updatePwd",method=RequestMethod.PUT)
	public @ResponseBody boolean updateStudentPwd(User user,HttpSession session) {
		String password=user.getUserPassword();
		if(password!=null&&!password.isEmpty()) {
			user.setUserPassword(Utils.md5(password));
			boolean bool = userService.updateUserInfo(user);
			if(bool==true) {
				session.removeAttribute("user");
				return true;
			}
		}
		return false;
	}
	
	
}
