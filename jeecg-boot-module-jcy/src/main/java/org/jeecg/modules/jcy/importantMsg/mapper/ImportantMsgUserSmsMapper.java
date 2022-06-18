package org.jeecg.modules.jcy.importantMsg.mapper;

import java.util.List;
import org.jeecg.modules.jcy.importantMsg.entity.ImportantMsgUserSms;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 重要信息通知明细
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
public interface ImportantMsgUserSmsMapper extends BaseMapper<ImportantMsgUserSms> {

	public boolean deleteByMainId(String mainId);
    
	public List<ImportantMsgUserSms> selectByMainId(String mainId);
}
