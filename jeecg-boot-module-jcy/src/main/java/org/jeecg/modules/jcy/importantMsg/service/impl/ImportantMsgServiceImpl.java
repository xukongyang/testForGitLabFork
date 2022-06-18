package org.jeecg.modules.jcy.importantMsg.service.impl;

import org.jeecg.modules.jcy.importantMsg.entity.ImportantMsg;
import org.jeecg.modules.jcy.importantMsg.entity.ImportantMsgUserSms;
import org.jeecg.modules.jcy.importantMsg.mapper.ImportantMsgUserSmsMapper;
import org.jeecg.modules.jcy.importantMsg.mapper.ImportantMsgMapper;
import org.jeecg.modules.jcy.importantMsg.service.IImportantMsgService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 重要信息通知管理
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
@Service
public class ImportantMsgServiceImpl extends ServiceImpl<ImportantMsgMapper, ImportantMsg> implements IImportantMsgService {

	@Autowired
	private ImportantMsgMapper importantMsgMapper;
	@Autowired
	private ImportantMsgUserSmsMapper importantMsgUserSmsMapper;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveMain(ImportantMsg importantMsg, List<ImportantMsgUserSms> importantMsgUserSmsList) {
		importantMsgMapper.insert(importantMsg);
		for(ImportantMsgUserSms entity:importantMsgUserSmsList) {
			//外键设置
			entity.setMeetingId(importantMsg.getId());
			importantMsgUserSmsMapper.insert(entity);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateMain(ImportantMsg importantMsg,List<ImportantMsgUserSms> importantMsgUserSmsList) {
		importantMsgMapper.updateById(importantMsg);
		
		//1.先删除子表数据
		importantMsgUserSmsMapper.deleteByMainId(importantMsg.getId());
		
		//2.子表数据重新插入
		for(ImportantMsgUserSms entity:importantMsgUserSmsList) {
			//外键设置
			entity.setMeetingId(importantMsg.getId());
			importantMsgUserSmsMapper.insert(entity);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delMain(String id) {
		importantMsgUserSmsMapper.deleteByMainId(id);
		importantMsgMapper.deleteById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			importantMsgUserSmsMapper.deleteByMainId(id.toString());
			importantMsgMapper.deleteById(id);
		}
	}
	
}
