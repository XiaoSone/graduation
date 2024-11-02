package com.graduation.service;

import com.graduation.mapper.*;
import com.graduation.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Scope(value="singleton",proxyMode=ScopedProxyMode.TARGET_CLASS)
public class TeacherService {
	
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private YansouTeamMapper yansouTeamMapper;
	@Autowired
	private YansouTeacherMapper yansouTeacherMapper;
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Teacher> getSubTeacher(){
		return teacherMapper.getAllTeacher();
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Teacher> findTeacherByTeacherName(String teacherName){
		return teacherMapper.findTeacherByTeacherName(teacherName);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public Teacher findTeacherAndProject(String teacherId) {
		return teacherMapper.findTeacherAndProject(teacherId);
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public Teacher findTeacherByUserId(String userId) {
		return teacherMapper.findTeacherByUserId(userId);
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Student> getStudentByTeacherId(String teacherId) {
		return studentMapper.getStudentByTeacherId(teacherId);
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public Teacher findTeacherByTeacherId(String teacherId) {
		return teacherMapper.findTeacherByTeacherId(teacherId);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateTeacherInfo(Teacher teacher) {
		int bool1 = teacherMapper.updateByPrimaryKeySelective(teacher);
		int bool2 = userMapper.updateByPrimaryKeySelective(teacher.getUser());
		if(bool1>0&&bool2>0) {
			return true;
		}
		return false;
	}
	
	@Transactional(propagation=Propagation.SUPPORTS)
	public YansouTeam getTeacherYansouInfo(String teacherId) {
		YansouTeacher yansouTeacher = yansouTeacherMapper.selectYansouTeacherByTeacherId(teacherId);
		return yansouTeamMapper.selectYanSouInfoByid(yansouTeacher.getYansouTeamId());
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Student> getStudentByTeacherId2(String teacherId) {
		return studentMapper.getStudentByTeacherId2(teacherId);
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Student> getYansouTeamStu(String teacherId) {
		YansouTeacher yansouTeacher = yansouTeacherMapper.selectYansouTeacherByTeacherId(teacherId);
		return studentMapper.findStudentsByYansouId(yansouTeacher.getYansouTeamId());
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public YansouTeacher thisTeacherisLeader(User user) {
		Teacher teacher = teacherMapper.findTeacherByUserId(user.getUserId());
		if(teacher!=null) {
			return yansouTeacherMapper.selectYansouTeacherByTeacherId(teacher.getTeacherId());
		}
		return null;
	}

}
