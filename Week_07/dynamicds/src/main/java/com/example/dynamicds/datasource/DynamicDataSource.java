package com.example.dynamicds.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        DataSourceContext.DataBaseType dataSource = DataSourceContext.getDataBaseType();
        System.out.println("determineCurrentLookupKey ===> "+dataSource);
        return dataSource;
    }
}
