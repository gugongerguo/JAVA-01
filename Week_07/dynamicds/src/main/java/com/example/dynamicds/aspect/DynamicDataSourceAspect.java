package com.example.dynamicds.aspect;

import com.example.dynamicds.annotation.DataSource;
import com.example.dynamicds.datasource.DataSourceContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class DynamicDataSourceAspect {

    @Before("@annotation(dataSource)")//拦截我们的注解
    public void changeDataSource(JoinPoint point, DataSource dataSource) throws Throwable {
        String value = dataSource.value();
        if (value.equals("primary")){
            DataSourceContext.setDataBaseType(DataSourceContext.DataBaseType.Primary);
        }else if (value.equals("replica")){
            List<DataSourceContext.DataBaseType> dataBaseTypes = new ArrayList<>();
            dataBaseTypes.add(DataSourceContext.DataBaseType.Replica0);
            dataBaseTypes.add(DataSourceContext.DataBaseType.Replica1);
            DataSourceContext.setDataBaseType(dataBaseTypes.get((int)(10*Math.random())%2));
        }else {
            DataSourceContext.setDataBaseType(DataSourceContext.DataBaseType.Primary);//默认使用主数据库
        }

    }

    @After("@annotation(dataSource)") //清除数据源的配置
    public void restoreDataSource(JoinPoint point, DataSource dataSource) {
        DataSourceContext.clearDataBaseType();
    }
}
