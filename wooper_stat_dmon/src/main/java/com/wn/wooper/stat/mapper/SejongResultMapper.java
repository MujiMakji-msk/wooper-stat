package com.wn.wooper.stat.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.wn.wooper.stat.vo.SejongResultVO;

@Mapper
public interface SejongResultMapper {
	List<SejongResultVO> selectSejongResult(String yearMonth);
	
	int insertSejongResult(Map inMap);
}
