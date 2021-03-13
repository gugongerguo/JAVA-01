package com.hf.multdatabasestables.repository;

import com.hf.multdatabasestables.mapper.OrderMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderRepository extends OrderMapper {


}
