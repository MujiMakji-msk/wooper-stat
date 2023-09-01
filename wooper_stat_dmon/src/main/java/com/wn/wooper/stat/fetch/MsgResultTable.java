package com.wn.wooper.stat.fetch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wn.wooper.stat.common.util.UtilTimeHandler;
import com.wn.wooper.stat.mapper.MsgResultMapper;
import com.wn.wooper.stat.vo.MsgResultVO;

@Service
public class MsgResultTable {

	private Logger log = LoggerFactory.getLogger(MsgResultTable.class);
	
	@Autowired
	private MsgResultMapper msgResultMapper;
	
	public void runMsgResultTable() {
		
		log.info("MsgResultTable.runMsgResultTable() Start");
		
		// MSG_RESULT_yyyymm 테이블의 정보를 패치 하여 wooper_total_stat에 넣는다.
		List<MsgResultVO> list = null;
		
		try {
			
			list = msgResultMapper.selectMsgResult(UtilTimeHandler.getyyyymm());
			
			int cnt = 0;
			
			for ( int i=0; i<list.size(); i++ ) {
				
				if ( cnt != 0 && cnt % 30 == 0) {
					Thread.sleep(10);
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("mseq", list.get(i).getMSEQ());
				map.put("channel_id", list.get(i).getEXT_COL1());
				map.put("receiver_phone", list.get(i).getDSTADDR());
				map.put("sender_phone", list.get(i).getCALLBACK());
				map.put("send_type", list.get(i).getEXT_COL2());
				map.put("message_group", "general");
				map.put("message_type", list.get(i).getMSG_TYPE());
				map.put("result", list.get(i).getRESULT());
				map.put("result_message", "");
				map.put("message", list.get(i).getTEXT());
				map.put("price", list.get(i).getEXT_COL3());
				map.put("price_rep", "");
				map.put("send_time", list.get(i).getREQUEST_TIME());
				
				// wooper_total_stat에 넣자...
				int nResult = msgResultMapper.insertMsgResult(map);
				
				if ( nResult < 0 ) {
					log.error("MsgResultTable insert fail >> " + map);
				}
				
				cnt++;
			}
			
			log.info("MsgResultTable.runMsgResultTable() End");
			
		} catch ( Exception e ) {
			log.error(e.toString());
		}
	} // runMsgResultTable() end
	
	
}
