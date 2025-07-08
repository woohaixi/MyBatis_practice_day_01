import com.hwadee.mybatis.dao.StudentDao;
import com.hwadee.mybatis.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test_01 {

    //将sqlSessionFactory声明为静态变量
    private static SqlSessionFactory sqlSessionFactory;

    //在静态代码块里使用sqlSessionFactoryBuilder创建sqlSessionFactory
    static {
        //2.1使用Resources类加载mybatis核心配置文件
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            //2.2将读取的输入流放到sqlSessionFactoryBuilder里创建sqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //测试方法

    //1、查询所有学生信息
    @Test
    public void test_01() {
        //3.1使用sqlSessionFactory创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.2使用sqlSession执行sql语句
        List<Object> objects = sqlSession.selectList("com.hwadee.mybatis.dao.StudentDao.selectAllStudent");
        for (Object object : objects) {
            System.out.println(object);
        }
        //3.3关闭sqlSession
        sqlSession.close();

    }

    //2、dao层映射查询所有学生信息
    @Test
    public void test_02() {
        //3.1使用sqlSessionFactory创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.2使用sqlSession执行sql语句
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        ArrayList<Student> students = studentDao.selectAllStudent();
        for (Student student : students) {
            System.out.println(student);
        }
        //3.3关闭sqlSession
        sqlSession.close();
    }

    //3、通过注解查询所有学生信息
    @Test
    public void test_03() {
        //3.1使用sqlSessionFactory创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.2使用sqlSession执行sql语句
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        ArrayList<Student> students = studentDao.selectAllStudentByAnnotation();
        for (Student student : students) {
            System.out.println(student);
        }
        //3.3关闭sqlSession
        sqlSession.close();
    }

    //4、一级缓存
    @Test
    public void test_04() {
        //3.1使用sqlSessionFactory创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.2使用sqlSession执行sql语句
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        ArrayList<Student> students = studentDao.selectAllStudent();
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("=======================");
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);
        ArrayList<Student> students2 = studentDao1.selectAllStudent();
        for (Student student : students2) {
            System.out.println(student);
        }
        //3.3关闭sqlSession
        sqlSession.close();
    }

    //5、二级缓存
    @Test
    public void test_05() {
        //3.1使用sqlSessionFactory创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.2使用sqlSession执行sql语句
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        ArrayList<Student> students = studentDao.selectAllStudent();
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("=======================");
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        StudentDao studentDao1 = sqlSession1.getMapper(StudentDao.class);
        ArrayList<Student> students2 = studentDao1.selectAllStudent();
        for (Student student : students2) {
            System.out.println(student);
        }
        //3.3关闭sqlSession
        sqlSession.close();
    }

    //6、实体类入参
    @Test
    public void test_06() {
        //3.1使用sqlSessionFactory创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.2使用sqlSession执行sql语句
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        Student student = new Student();
        student.setStudentName("赵萧萧");
        ArrayList<Student> student1 = studentDao.selectStudentByStudent(student);
        for (Student student2 : student1) {
            System.out.println(student2);
        }
        //3.3关闭sqlSession
        sqlSession.close();
    }

    //7、 增加学生信息
    @Test
    public void test_07() {
        //3.1使用sqlSessionFactory创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.2使用sqlSession执行sql语句
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        //封装参数
        Student student = new Student();
        student.setStudentName("赵萧萧");
        student.setStudentBirth("2000-02-02");
        student.setStudentAddress("长沙大道一号");
        student.setStudentNo(130001);
        student.setStudentAge(23);
        student.setGradeId(2);
        //执行sql语句
        int i = studentDao.insertStudent(student);
        if (i > 0) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
        //提交事务
        sqlSession.commit();
        //3.3关闭sqlSession
        sqlSession.close();

    }

    //8、修改学生信息
    @Test
    public void test_08() {
        //3.1使用sqlSessionFactory创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.2使用sqlSession执行sql语句
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        //封装参数
        Student student = new Student();
        student.setStudentId(100183);
        student.setStudentAddress("长沙大道二号");
        int i = studentDao.updateStudent(student);
        if (i > 0) {
            System.out.println("修改成功");
        }      else {
            System.out.println("修改失败");
        }
        //提交事务
        sqlSession.commit();
        //3.3关闭sqlSession
        sqlSession.close();
    }

    //9、删除学生信息
    @Test
    public void test_09() {
        //3.1使用sqlSessionFactory创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.2使用sqlSession执行sql语句
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);

        int i = studentDao.deleteStudent(100183);
        if (i > 0) {
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
        //提交事务
        sqlSession.commit();
        //3.3关闭sqlSession
        sqlSession.close();
    }

    //10、使用注解@param,修改学生信息
    @Test
    public void test_10() {
        //3.1使用sqlSessionFactory创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.2使用sqlSession执行sql语句
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        int i = studentDao.updateStudentByParam(100184, "长沙大道三号");
        if (i > 0) {
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
        //提交事务
        sqlSession.commit();
        //3.3关闭sqlSession
        sqlSession.close();

    }

    //11、map入参
    @Test
    public void test_11() {
        //3.1使用sqlSessionFactory创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.2使用sqlSession执行sql语句
        StudentDao studentDao = sqlSession.getMapper(StudentDao.class);
        //封装参数
        Map<String, Object> map = new HashMap<>();
        map.put("studentName", "赵");
        ArrayList<Student> students = studentDao.selectStudentByMap(map);
        for (Student student : students) {
            System.out.println(student);
        }
        //3.3关闭sqlSession
        sqlSession.close();
    }

    //12、返回map
    @Test
    public void test_12(){
        //1.创建会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.使用反射方式获取dao层
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        //3.调用
        Map<String, Object> studentMapRes = mapper.getStudentMapRes(100184);
        //4.打印
        System.out.println(studentMapRes);
        //5.关闭
        sqlSession.close();
    }

    //13、返回多个map
    @Test
    public void test_13(){
        //1.创建会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.使用反射方式获取dao层
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        //3.调用
        Student student = new Student();
        student.setStudentName("赵");
        List<Map<String, Object>> studentMapList = mapper.getStudentMapList(student);
        //4.打印
        studentMapList.forEach(map -> {
            System.out.println(map);
        });
        //5.关闭
        sqlSession.close();
    }

}
