package com.wn.wooper.stat.fetch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wn.wooper.stat.common.util.UtilTimeHandler;
import com.wn.wooper.stat.mapper.RcsResultMapper;
import com.wn.wooper.stat.vo.RcsResultVO;

@Service
public class RcsResultTable {

	private Logger log = LoggerFactory.getLogger(RcsResultTable.class);
	
	@Autowired
	private RcsResultMapper rcsResultMapper;
	
	public void runRcsResultTable() {
		
		log.info("RcsResultTable.runRcsResultTable() Start");
		
		// RCS_RESULT_yyyymm 테이블의 정보를 패치 하여 wooper_total_stat에 넣는다.
		List<RcsResultVO> list = null;
		
		try {
			
			list = rcsResultMapper.selectRcsResult(UtilTimeHandler.getyyyymm());
			
			int cnt = 0;
			
			for ( int i=0; i<list.size(); i++ ) {
				
				if ( cnt != 0 && cnt % 30 == 0) {
					Thread.sleep(10);
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("mseq", list.get(i).getMSEQ());
				map.put("channel_id", list.get(i).getCHANNEL_ID());
				map.put("receiver_phone", list.get(i).getRECEIVER());
				map.put("sender_phone", "");
				map.put("send_type", list.get(i).getSEND_TYPE());
				map.put("message_group", "rcs");
				map.put("message_type", "");
				map.put("result", list.get(i).getRESULT());
				map.put("result_message", list.get(i).getMESSAGE());
				map.put("message", "");
				map.put("price", list.get(i).getPRICE_RCS());
				map.put("price_rep", list.get(i).getPRICE_REP());
				map.put("send_time", list.get(i).getDATE());
				if ( list.get(i).getRESULT() == 0 ) {
					map.put("replace_yn", "Y");
				} else {
					map.put("replace_yn", "N");
				}
				
				// wooper_total_stat에 넣자...
				int nResult = rcsResultMapper.insertRcsResult(map);
				
				if ( nResult < 0 ) {
					log.error("RcsResultTable insert fail >> " + map);
				}
				
				cnt++;
			}
			
			log.info("RcsResultTable.runRcsResultTable() End");
			
		} catch ( Exception e ) {
			log.error(e.toString());
		}
	} // runMsgResultTable() end
	
	
}
