package com.wn.wooper.stat.fetch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wn.wooper.stat.mapper.DeleteStatDataMapper;

@Service
public class DeleteStatData {

	private Logger log = LoggerFactory.getLogger(DeleteStatData.class);
	
	@Autowired
	private DeleteStatDataMapper deleteStatDataMapper;
	
	public void rudDeleteStatData() {
		
		try {
			int nResult = deleteStatDataMapper.deleteStatData("100");
			
			if ( nResult < 0 ) {
				log.error("####################################");
				log.error("wooper_total_stat delete fail");
				log.error("####################################");
			}
			
		} catch ( Exception e ) {
			log.error(e.toString());
		}
	}
}
