package com.wn.wooper.stat.fetch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wn.wooper.stat.common.util.UtilTimeHandler;
import com.wn.wooper.stat.mapper.SejongResultMapper;
import com.wn.wooper.stat.vo.SejongResultVO;

@Service
public class SejongResultTable {

	private Logger log = LoggerFactory.getLogger(SejongResultTable.class);
	
	@Autowired
	private SejongResultMapper sejongResultMapper;
	
	public void runMsgResultTable() {
		
		log.info("SejongResultTable.runMsgResultTable() Start");
		
		// sejong_msg_result_yyyymm 테이블의 정보를 패치 하여 wooper_total_stat에 넣는다.
		List<SejongResultVO> list = null;
		
		try {
			
			list = sejongResultMapper.selectSejongResult(UtilTimeHandler.getyyyymm());
			
			int cnt = 0;
			
			for ( int i=0; i<list.size(); i++ ) {
				
				if ( cnt != 0 && cnt % 30 == 0) {
					Thread.sleep(10);
					log.info("mujimakji sejion sleep");
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				// 대체 발송을 봐야 한다. 
				String szPSEQ = list.get(i).getPseq();
				
				if ( szPSEQ == null ) {
					map.put("mseq", list.get(i).getMseq());
				} else {
					map.put("mseq", szPSEQ);
				}
				
				map.put("channel_id", list.get(i).getExt_col1());
				map.put("receiver_phone", list.get(i).getDstaddr());
				map.put("sender_phone", list.get(i).getCallback());
				map.put("send_type", list.get(i).getExt_col2());
				map.put("message_group", "kakao");
				map.put("message_type", list.get(i).getMsg_type());
				map.put("result", list.get(i).getResult());
				map.put("result_message", "");
				map.put("message", list.get(i).getText());
				map.put("price", list.get(i).getExt_col3());

				if ( szPSEQ == null ) {
					map.put("price_rep", "");
				} else {
					map.put("price_rep", list.get(i).getExt_col3());
				}
				
				map.put("send_time", list.get(i).getRequest_time());
				
				if ( szPSEQ == null ) {
					map.put("replace_yn", "N");
				} else {
					map.put("replace_yn", "Y");
				}
				
				// wooper_total_stat에 넣자...
				int nResult = sejongResultMapper.insertSejongResult(map);
				
				if ( nResult < 0 ) {
					log.error("SejongResultTable insert fail >> " + map);
				}
				
				cnt++;
			}
			
			log.info("SejongResultTable.runMsgResultTable() End");
			
		} catch ( Exception e ) {
			log.error(e.toString());
		}
	} // runMsgResultTable() end
}
