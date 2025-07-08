package com.hwadee.mybatis.dao;

import com.hwadee.mybatis.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface StudentDao {

    /**
     * 查询所有学生信息
     */
    ArrayList<Student> selectAllStudent();

    /**
     * 通过注解查询所有学生信息
     */
    @Select("select * from student")
    ArrayList<Student> selectAllStudentByAnnotation();

    /**
     * 根据id查询学生信息
     */
    Student selectStudentById(long id);

    /**
     * 实体类入参
     */
    ArrayList<Student> selectStudentByStudent(Student student);

    /**
     * 增加学生信息
     */
    int insertStudent(Student student);

    /**
     * 修改学生信息
     */
    int updateStudent(Student student);

    /**
     * 删除学生信息
     */
    int deleteStudent(long id);

    /**
     * 使用注解@param,修改学生信息
     */
    int updateStudentByParam(@Param("id") long studentId, @Param("address") String studentAddress);

    /**
     * map入参
     */
    ArrayList<Student> selectStudentByMap(Map<String, Object> map);

    /**
     * 返回map
     * @param studentId
     * @return
     */
    Map<String, Object> getStudentMapRes(long studentId);

    /**
     * 返回多个map
     * @param student
     * @return
     */
    List<Map<String, Object>> getStudentMapList(Student student);

}
