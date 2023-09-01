package com.wn.wooper.stat.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.wn.wooper.stat.vo.RcsResultVO;

@Mapper
public interface RcsResultMapper {
	List<RcsResultVO> selectRcsResult(String yearMonth);
	
	int insertRcsResult(Map inMap);
}
