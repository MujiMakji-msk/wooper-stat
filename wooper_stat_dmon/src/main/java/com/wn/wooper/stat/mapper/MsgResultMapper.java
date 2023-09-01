package com.wn.wooper.stat.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.wn.wooper.stat.vo.MsgResultVO;

@Mapper
public interface MsgResultMapper {
	List<MsgResultVO> selectMsgResult(String yearMonth);
	
	int insertMsgResult(Map inMap);
}
