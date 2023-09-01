package com.wn.wooper.stat.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeleteStatDataMapper {
	int deleteStatData(String szPeriod);
}
