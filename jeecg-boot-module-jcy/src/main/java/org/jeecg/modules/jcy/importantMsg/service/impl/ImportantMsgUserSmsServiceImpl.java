package org.jeecg.modules.jcy.importantMsg.service.impl;

import org.jeecg.modules.jcy.importantMsg.entity.ImportantMsgUserSms;
import org.jeecg.modules.jcy.importantMsg.mapper.ImportantMsgUserSmsMapper;
import org.jeecg.modules.jcy.importantMsg.service.IImportantMsgUserSmsService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 重要信息通知明细
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
@Service
public class ImportantMsgUserSmsServiceImpl extends ServiceImpl<ImportantMsgUserSmsMapper, ImportantMsgUserSms> implements IImportantMsgUserSmsService {
	
	@Autowired
	private ImportantMsgUserSmsMapper importantMsgUserSmsMapper;
	
	@Override
	public List<ImportantMsgUserSms> selectByMainId(String mainId) {
		return importantMsgUserSmsMapper.selectByMainId(mainId);
	}
}
