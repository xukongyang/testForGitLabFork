package org.jeecg.modules.jcy.msgGroup.service;

import org.jeecg.modules.jcy.msgGroup.entity.MsgGroupMember;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 短信群组明细
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
public interface IMsgGroupMemberService extends IService<MsgGroupMember> {

	public List<MsgGroupMember> selectByMainId(String mainId);
}
