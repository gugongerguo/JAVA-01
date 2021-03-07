package com.example.dynamicds.config;

import com.example.dynamicds.datasource.DataSourceContext;
import com.example.dynamicds.datasource.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.example.dynamicds.mapper",sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {


    @Bean(name = "dataSourcePrimary")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSourceReplica0")
    @ConfigurationProperties(prefix = "spring.datasource.replica0")
    public DataSource replica0DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dataSourceReplica1")
    @ConfigurationProperties(prefix = "spring.datasource.replica1")
    public DataSource replica1DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DataSource(@Qualifier("dataSourcePrimary") DataSource primaryDataSource,
                                        @Qualifier("dataSourceReplica0") DataSource replica0DataSource,
                                        @Qualifier("dataSourceReplica1") DataSource replica1DataSource) {

        //这个地方是比较核心的targetDataSource 集合是我们数据库和名字之间的映射
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceContext.DataBaseType.Primary, primaryDataSource);
        targetDataSource.put(DataSourceContext.DataBaseType.Replica0, replica0DataSource);
        targetDataSource.put(DataSourceContext.DataBaseType.Replica1, replica1DataSource);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(primaryDataSource);//设置默认对象
        return dataSource;
    }

    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory SqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/*/*.xml"));//设置我们的xml文件路径
        return bean.getObject();
    }
}
