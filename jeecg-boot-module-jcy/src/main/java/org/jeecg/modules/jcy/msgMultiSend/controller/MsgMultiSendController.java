package org.jeecg.modules.jcy.msgMultiSend.controller;

import java.util.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gzcmcc.ismgclient.db.domain.MsgMtLog;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.jcy.importantMsg.entity.ImportantMsgUserSms;
import org.jeecg.modules.jcy.msgMultiSend.entity.MsgMultiSend;
import org.jeecg.modules.jcy.msgMultiSend.service.IMsgMultiSendService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: 群发短信
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
@Slf4j
@Api(tags="群发短信")
@RestController
@RequestMapping("/msgMultiSend/msgMultiSend")
public class MsgMultiSendController extends JeecgController<MsgMultiSend, IMsgMultiSendService> {
	@Autowired
	private IMsgMultiSendService msgMultiSendService;

	 @Autowired
	 private RedisUtil redisUtil;
	 String listRedisMtToIsmg = "list_mt_wait_to_ismg_851010";

	 //获取随机不重复字符串
	 public String getRandomUUID() {
		 return UUID.randomUUID().toString().replace("-", "");
	 }

	 /**
	 * 分页列表查询
	 *
	 * @param msgMultiSend
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "群发短信-分页列表查询")
	@ApiOperation(value="群发短信-分页列表查询", notes="群发短信-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MsgMultiSend msgMultiSend,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MsgMultiSend> queryWrapper = QueryGenerator.initQueryWrapper(msgMultiSend, req.getParameterMap());
		Page<MsgMultiSend> page = new Page<MsgMultiSend>(pageNo, pageSize);
		IPage<MsgMultiSend> pageList = msgMultiSendService.page(page, queryWrapper);

		/*for(MsgMultiSend msgMultiSend1 : pageList.getRecords()) {
			System.out.println("群发短信-分页列表查询 " + msgMultiSend1.getDestTerminalId() + " 消息内容 " + msgMultiSend1.getMsgContent());
		}*/
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param msgMultiSend
	 * @return
	 */
	@AutoLog(value = "群发短信-添加")
	@ApiOperation(value="群发短信-添加", notes="群发短信-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MsgMultiSend msgMultiSend) {
		//msgMultiSendService.save(msgMultiSend);

		//System.out.println("MsgMultiSend " + msgMultiSend);

		String[] arrMobile = msgMultiSend.getDestTerminalId().replaceAll("\r","").split("\n");


		for(String mobile : arrMobile) {
			System.out.println("MsgMultiSend mobile " + mobile);

			String msgContent = msgMultiSend.getMsgContent();

			MsgMtLog msgMtLog = new MsgMtLog();
			msgMtLog.setMsg_id(getRandomUUID());
			msgMtLog.setRegistered_delivery(1);
			msgMtLog.setSrc_terminal_id("10658123456");
			msgMtLog.setDest_terminal_id(mobile);
			msgMtLog.setMsg_content(msgContent);

			System.out.println("短信发送关键信息 唯一流水号 " + msgMtLog.getMsg_id()
					+ " 手机号 " + mobile
					+ " 消息内容 " + msgContent);

			redisUtil.lSet(listRedisMtToIsmg, msgMtLog);

		}


		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param msgMultiSend
	 * @return
	 */
	@AutoLog(value = "群发短信-编辑")
	@ApiOperation(value="群发短信-编辑", notes="群发短信-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody MsgMultiSend msgMultiSend) {
		msgMultiSendService.updateById(msgMultiSend);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "群发短信-通过id删除")
	@ApiOperation(value="群发短信-通过id删除", notes="群发短信-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		msgMultiSendService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "群发短信-批量删除")
	@ApiOperation(value="群发短信-批量删除", notes="群发短信-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.msgMultiSendService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "群发短信-通过id查询")
	@ApiOperation(value="群发短信-通过id查询", notes="群发短信-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MsgMultiSend msgMultiSend = msgMultiSendService.getById(id);
		return Result.OK(msgMultiSend);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param msgMultiSend
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, MsgMultiSend msgMultiSend) {
      return super.exportXls(request, msgMultiSend, MsgMultiSend.class, "群发短信");
  }

  /**
   * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      return super.importExcel(request, response, MsgMultiSend.class);
  }

}
