remoteCom是对基本JDBC插入远程数据库的测试。用的con.createStatement()

remoteCon3 是百万数据库插入实现

remoteCon4 是插入完后返回主键的实现。prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)

remoteCon5 是remoteCon3的使用String版本。