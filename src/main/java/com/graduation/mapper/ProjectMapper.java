package com.graduation.mapper;

import com.graduation.model.Project;
import com.graduation.model.ProjectExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {
    long countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Integer projectId);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Integer projectId);
    
    Project selectProjectAndFromByPid(Integer projectId);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
    
    int noChooseProject(String teacherId);
    
    List<Project> getProjectListByTeacherId(String teacherId);
    
    Project getProjectByStudentId(String studentId);
    
    boolean updateProjectByStudentId(@Param("studentId")String studentId,@Param("projectId")String projectId);
    
    List<Project> getProjectAndStudent(String teacher_id);

	boolean updateNoStudent(String projectId);

	int getCountProjectNum();

	int thisTeacherYesProjectNum(String teacherId);

	List<Project> getProjectByTeacherId(String teacherId);

}