package com.wn.wooper.stat.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wn.wooper.stat.fetch.BulkMsgResultTable;
import com.wn.wooper.stat.fetch.DeleteStatData;
import com.wn.wooper.stat.fetch.MsgResultTable;
import com.wn.wooper.stat.fetch.RcsResultTable;
import com.wn.wooper.stat.fetch.SejongResultTable;

@Component
public class WooperStatFetcher {

	private Logger log = LoggerFactory.getLogger(WooperStatFetcher.class);
	
	@Autowired
	private MsgResultTable msgResultTable;
	@Autowired
	private BulkMsgResultTable bulkMsgResultTable;
	@Autowired
	private SejongResultTable sejongResultTable;
	@Autowired
	private RcsResultTable rcsResultTable;
	
	@Autowired
	private DeleteStatData deleteStatData;
	
	// 초 - 분 - 시 - 일 - 월 - 요일
	@Scheduled( fixedDelay = 1000 )
	private void runStatFetcher() {
		
		try {
			// 일반 메시지 패치 
			msgResultTable.runMsgResultTable();
			
			Thread.sleep(100);
			
			// 대량 메시지 패치 
			bulkMsgResultTable.runBulkMsgResultTable();;
			
			Thread.sleep(100);
			
			// 세종 알림톡 패치 
			sejongResultTable.runMsgResultTable();
			
			Thread.sleep(100);
			
			// RCS 패치 
			rcsResultTable.runRcsResultTable();
		} catch ( Exception e ) {
			log.error(e.toString() );
		}
		
	}
	
//	@Scheduled( fixedDelay = 1000 * 10 * 60)
	@Scheduled( cron = "0 10 2 1 * *" )
	private void runStatDel() {
		// 6개월 지난 데이터 삭제 처리 .. 
		try {
			deleteStatData.rudDeleteStatData();
		} catch ( Exception e ) {
			log.error(e.toString() );
		}
	}
	
}
