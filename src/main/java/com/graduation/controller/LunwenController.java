package com.graduation.controller;

import com.graduation.model.Lunwen;
import com.graduation.model.Student;
import com.graduation.model.User;
import com.graduation.service.LunwenService;
import com.graduation.service.StudentService;
import com.graduation.service.UserService;
import com.graduation.util.Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@Controller
@RequestMapping("/lunwenController")
public class LunwenController {

	@Autowired
	private UserService userService;
	@Autowired
	private LunwenService lunwenService;
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/gotoTstudentlunwen")
	public String gotoTstudentlunwen(String studentId) {
		return "redirect:http://localhost:8081/#/tstudentlunwen?studentId="+studentId;
	}
	
	@ResponseBody
	@RequestMapping("/getLunwenBySid")
	public Lunwen getLunwenBySid(String studentId,HttpSession session) {
		if(studentId!=null&&!studentId.isEmpty()) {
			Lunwen lunwen = lunwenService.getLunwenBySid(studentId);
			if(lunwen!=null) {
				return lunwen;
			}
		}else {
			User user = (User) session.getAttribute("user");
    		if(user!=null) {
    			Student student = studentService.getStudentByUserId(user.getUserId());
    			Lunwen lunwen = lunwenService.getLunwenBySid(student.getStudentId());
    			if(lunwen!=null) {
    				return lunwen;
    			}
    		}
		}
		return new Lunwen();
	}
	
	@ResponseBody
	@RequestMapping(value="/upLunwenBySid",method=RequestMethod.POST)
	public boolean upLunwenBySid(@RequestBody Lunwen lunwen) {
		return lunwenService.upLunwenBySid(lunwen);
	}
	
	@ResponseBody
	@RequestMapping(value="/upload_lunwen",method=RequestMethod.POST)
    public boolean saveProduct(@RequestParam(value="lunwen",required=true)MultipartFile lunwen,
    							@RequestParam("userId") String userId,HttpServletRequest request) {
		System.out.println("开始上传...");
		System.out.println(lunwen.getSize());
        if(lunwen!=null&&lunwen.getSize()>0) {
        	if(lunwen.getSize()>(10*1024*1024)) {
        		return false;
        	}
			System.out.println("上传中...");
			//获取文件名
        	String filename = lunwen.getOriginalFilename();

			String projectPath = System.getProperty("user.dir");
			System.out.println(projectPath);
			String uploadDir = projectPath + "/WEB-INF/lunwen/";

			//创建上传目录
        	File directory  = new File(uploadDir);
        	if (!directory .exists()) {
				directory .mkdirs();
			}
        	//文件上传
			String filePath = uploadDir + File.separator + filename;
			File destFile = new File(filePath);
        	try {
				lunwen.transferTo(destFile);
				Subject subject = SecurityUtils.getSubject();
				String useraccount = (String) subject.getPrincipal();
				User user = userService.isUser(useraccount);
				//System.out.println(user);
        		if(user!=null) {
        			Student student = studentService.getStudentByUserId(userId);
        			Lunwen lw=new Lunwen();
        			lw.setLunwenDate(new Date());
        			lw.setLunwenName(filename);
        			lw.setLunwenPath("/WEB-INF/lunwen/"+filename);
					//System.out.println(lw);
        			lunwenService.insertOrUpdate(student.getStudentId(),lw);
        		}else {
					return false;
				}
        		return true;
        	} catch (Exception e) {
        		e.printStackTrace();
        	} 
        }
        return false;
    }
	
	 @RequestMapping("/downloadResource")
	 public @ResponseBody void downloadResource(HttpServletResponse response,
			 HttpServletRequest request,@RequestParam String studentId) throws Exception {
	     if(studentId==null||studentId.isEmpty()) {
	    	 return;
	     }
	     Lunwen lunwen = lunwenService.getLunwenBySid(studentId);
	     if(lunwen==null) {
	    	 return;
	     }
		 String dataDir=System.getProperty("user.dir") + lunwen.getLunwenPath();
	     String fileName=lunwen.getLunwenName();
		 Path path=Paths.get(dataDir);
		 if(Files.exists(path)) {
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename="+Utils.filenameEncoding(fileName, request));
			try {
				Files.copy(path, response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
		return;
	  }

}
