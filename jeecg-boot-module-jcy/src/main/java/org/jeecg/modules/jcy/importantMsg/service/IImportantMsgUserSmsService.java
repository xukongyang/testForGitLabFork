package org.jeecg.modules.jcy.importantMsg.service;

import org.jeecg.modules.jcy.importantMsg.entity.ImportantMsgUserSms;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 重要信息通知明细
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
public interface IImportantMsgUserSmsService extends IService<ImportantMsgUserSms> {

	public List<ImportantMsgUserSms> selectByMainId(String mainId);
}
