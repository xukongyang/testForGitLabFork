package org.jeecg.modules.jcy.msgGroup.mapper;

import java.util.List;
import org.jeecg.modules.jcy.msgGroup.entity.MsgGroupMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 短信群组明细
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
public interface MsgGroupMemberMapper extends BaseMapper<MsgGroupMember> {

	public boolean deleteByMainId(String mainId);
    
	public List<MsgGroupMember> selectByMainId(String mainId);
}
