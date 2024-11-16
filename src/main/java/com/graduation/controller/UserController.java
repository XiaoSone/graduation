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
import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:8081", allowCredentials = "true")
@Controller
@RequestMapping("/userController")
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
	public @ResponseBody String login(HttpServletRequest request,@RequestParam("randStr")String randStr,@RequestParam("account")String account,@RequestParam("password")String password) {
		Subject subject = SecurityUtils.getSubject();

		String randStr2 = (String) request.getSession().getAttribute("captcha");
		if (randStr2 != null && randStr2.equals(randStr)) {
			password = Utils.md5(password);
			UsernamePasswordToken token  = new UsernamePasswordToken(account,password);
			User user = userService.isUser(account);
			if (user != null) {
				if(2==user.getUserRoles()) {
					subject.login(token);//shiro处理身份认证
					return "/sindex";
					//return "学生登录成功";
				} else if(1==user.getUserRoles()) {
					subject.login(token);//shiro处理身份认证
					return "/tcontent";
					//tindex
					//return "教师登录成功";
				} else {
					return "用户信息有误";
				}
			} else {
				 return "用户不存在";
			}
		} else {
			return "randStrError";
		}
	}
	//登录成功跳转
	public String toUI(HttpSession session,String account,String password) {
		User user = userService.login(account, password);
		if(user!=null) {
			if(2==user.getUserRoles()) {
				session.setAttribute("user", user);
				return "/scontent";
				//return "学生登录成功";
			}
			if(1==user.getUserRoles()) {
				session.setAttribute("user", user);
				return "/tcontent";
				//return "教师登录成功";
			}
		}
		return "用户名或密码错误";
	}
	//退出登录
	@RequestMapping("/logout")
	public @ResponseBody String logout(HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();

		HttpSession session = request.getSession();
		if(currentUser.isAuthenticated()) {
			//session.removeAttribute("user_stu");
			currentUser.logout();
		}else {
			//session.removeAttribute("user_tea");
		}
		return "/login";
	}
	//更新用户信息
	@RequestMapping(value="/updateInfo",method=RequestMethod.POST)
	public @ResponseBody boolean updateStudentInfo(User user,
			@RequestParam(value="portrait",required=false)MultipartFile portrait,HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		String useraccount = (String) currentUser.getPrincipal();
		System.out.println("更新中的用户："+useraccount);
		if(currentUser.isAuthenticated()) {
			user.setUserId((userService.isUser(useraccount)).getUserId());
			System.out.println(user);
			//if(portrait!=null&&portrait.getSize()>0) {
			//	if(portrait.getSize()>(10*1024*1024)) {
			//		return false;
			//	}
			//	String filename=portrait.getOriginalFilename();
			//	String dbPath=request.getServletContext().getContextPath()+"/portrait/"+user.getUserId();
			//	String basePath=request.getServletContext().getRealPath("/portrait/"+user.getUserId());
			//	new File(basePath).mkdir();
			//	File portraitFile=new File(basePath,filename);
			//	try {
			//		portrait.transferTo(portraitFile);
			//		user.setUserPortrait(dbPath+"/"+filename);
			//		return userService.updateUserInfo(user);
			//	} catch (Exception e) {
			//		e.printStackTrace();
			//	}
			//
			//}
			return userService.updateUserInfo(user);
		}
		System.out.println("更新失败");
		return false;
	}
	//更新密码
	@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	public @ResponseBody boolean updateStudentPwd(User user,HttpSession session) {
		Subject currentUser = SecurityUtils.getSubject();
		String useraccount = (String) currentUser.getPrincipal();
		user.setUserId(userService.isUser(useraccount).getUserId());
		String password=user.getUserPassword();
		System.out.println(user);
		if(password!=null && !password.isEmpty() && currentUser.isAuthenticated()) {
			user.setUserPassword(Utils.md5(password));
			System.out.println(user);
			boolean bool = userService.updateUserInfo(user);
			if(bool==true) {
				currentUser.logout();
				System.out.println("已退出登录");
				//session.removeAttribute("user");
				return true;
			}
		}
		return false;
	}
	
	
}
