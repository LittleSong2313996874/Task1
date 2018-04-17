package a.b.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import a.b.dao.PersonDao3;
import a.b.entity.Person;

import java.io.InputStream;
public class TestForStruction {
    private SqlSessionFactory sqlSessionFactory;

    // 被该注解申明的方法，功能是：在所有的测试方法之前执行，只执行一次(类加载时执行)
    @BeforeClass // 注意,这里必须是static...因为方法将在类被装载的时候就被调用(那时候还没创建实例)
    public static void before() {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    // 被该注解申明的方法，功能是：在所有的测试方法之后执行，只执行一次（类结束时执行）
    @AfterClass
    public static void after() {
        System.out.println("==============================================================================");
    }

    // 使用了该元数据的方法在每个测试方法执行之前都要执行一次。（方法开始前执行）
    @Before
    public void init() throws Exception {
        // 创建SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 加载SqlMapConfig.xml配置文件
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 创建SqlsessionFactory
        this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
    }

    //方法是在执行每个测试方法之后执行的，方法名最好和tearDown()一样，但不强求，在每一个测试方法之后被执行
    @After
    public void aft() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
    }

    /*@Ignore :让 测试方法 或 测试类 不被执行，让其失去测试的功能*/

    // 表明这是一个测试方法
    @Test
    /*
     * 在junit 4 当中测试类无需继承于 TestCase类，测试方法的名字也无需以test开头
     * 测试代码满足的原则 1）修饰符设为 public 2）返回类型 void 3）没有方法参数 4）方法名称必须以test开头
     */
//    按ID查询
    public void testQueryStudentById() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();

        // 从sqlSession中获取Mapper接口的代理对象
        PersonDao3 persondao = sqlSession.getMapper(PersonDao3.class);
        // 执行查询方法
        Person stu = persondao.findById(14);
        System.out.println(stu);

        // 和spring整合后由spring管理
        sqlSession.close();
    }

    // 表明这是一个测试方法
    @Test
//    增加
    public void testAddUser() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();

        // 从sqlSession中获取Mapper接口的代理对象
        PersonDao3 stuMapper = sqlSession.getMapper(PersonDao3.class);
        // 创建保存对象
        Person stu = new Person();
        stu.setNAME("刘备");
        stu.setId(64);
        // 执行添加方法
        stuMapper.add(stu);;
        System.out.println(stu);

        // 和spring整合后由spring管理
        sqlSession.commit();
        sqlSession.close();
    }

    // 表明这是一个测试方法
    @Test
//    删除
    public void testDeleteStudent() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        // 从sqlSession中获取Mapper接口的代理对象
        PersonDao3 stuMapper = sqlSession.getMapper(PersonDao3.class);
        // 执行删除方法
        Person stu = new Person();
        stuMapper.delete(stu);;

        sqlSession.commit();
        sqlSession.close();

    }

    // 表明这是一个测试方法
    @Test
//    修改
    public void testUpdateStudent() {
        // 获取sqlSession，和spring整合后由spring管理
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        // 从sqlSession中获取Mapper接口的代理对象
        PersonDao3 stuMapper = sqlSession.getMapper(PersonDao3.class);
        // 创建保存对象
        Person stu = new Person();
        stu.setNAME("刘备");
        stu.setId(64);
        stu.setId(14);
        // 执行更新方法
        stuMapper.update(stu);;

        sqlSession.commit();
        sqlSession.close();
    }
}
