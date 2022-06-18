package org.jeecg.modules.jcy.importantMsg.service;

import org.jeecg.modules.jcy.importantMsg.entity.ImportantMsgUserSms;
import org.jeecg.modules.jcy.importantMsg.entity.ImportantMsg;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 重要信息通知管理
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
public interface IImportantMsgService extends IService<ImportantMsg> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(ImportantMsg importantMsg,List<ImportantMsgUserSms> importantMsgUserSmsList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ImportantMsg importantMsg,List<ImportantMsgUserSms> importantMsgUserSmsList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
