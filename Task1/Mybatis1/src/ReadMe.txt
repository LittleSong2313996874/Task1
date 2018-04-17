PersonDao是通过Mybatis的sqlSession里提供方法进行增删查改
testPersonDao是对它的测试

PersonDao2是用动态Sql实现的增删查改，它的实现特点体现在/Mybatis/src/PersonMapper.xml里的配置中。
estPersonDao2是对它的单元测试

PersonDao3是直接调用接口的方式实现增删查改，它的测试类testPerson3通过@Before、@After对测试代码进行了精简。

PersonDao4是PersonDao3的注解sql版本。

MybatisUtil是个工具类，主要进行一些初始化操作(如载入配置文件mybatis-config.xml，创建sqlSessionFactory，创建sqlSession等),
以及善后工作(关闭sqlSession，解绑线程)

TestForStruction内含Junit测试的大量教程，代码无关业务，只作为教程作用