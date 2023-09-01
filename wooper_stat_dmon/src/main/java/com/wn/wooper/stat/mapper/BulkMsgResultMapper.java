package com.wn.wooper.stat.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.wn.wooper.stat.vo.BulkMsgResultVO;

@Mapper
public interface BulkMsgResultMapper {
	List<BulkMsgResultVO> selectBulkMsgResult(String yearMonth);
	
	int insertBulkMsgResult(Map inMap);
}
