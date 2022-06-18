package org.jeecg.modules.jcy.sms.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.PermissionData;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.jcy.sms.entity.MsgMtLog;
import org.jeecg.modules.jcy.sms.service.IMsgMtLogService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.jcy.sms.service.impl.TDEngineServiceImpl;
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
 * @Description: 短信发送日志
 * @Author: jeecg-boot
 * @Date:   2022-04-28
 * @Version: V1.0
 */
@Slf4j
@Api(tags="短信发送日志")
@RestController
@RequestMapping("/sms/msgMtLog")
public class MsgMtLogController extends JeecgController<MsgMtLog, IMsgMtLogService> {
	@Autowired
	private IMsgMtLogService msgMtLogService;

	 @Autowired
	 private TDEngineServiceImpl tdEngineService;
	/**
	 * 分页列表查询
	 *
	 * @param msgMtLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "短信发送日志-分页列表查询")
	@ApiOperation(value="短信发送日志-分页列表查询", notes="短信发送日志-分页列表查询")
	@PermissionData(pageComponent="jcy/sms/MsgMtLogList")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MsgMtLog msgMtLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {

		/*List<Map<String, Object>> listTdengineDemo = tdEngineService.selectAll();
		for(Map<String, Object> mapData : listTdengineDemo) {
			for(String key : mapData.keySet()) {
				log.info("key " + key + " value " + mapData.get(key));
			}
		}*/

		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

		//log.info("sysUser " + sysUser);

		QueryWrapper<MsgMtLog> queryWrapper = QueryGenerator.initQueryWrapper(msgMtLog, req.getParameterMap());

		Page<MsgMtLog> page = new Page<MsgMtLog>(pageNo, pageSize);

		Map<String, String[]> parameterMap = req.getParameterMap();

		String destTerminalId = "";
		String dateStart = "";
		String dateEnd = "";
		for(String key : parameterMap.keySet()) {
			String[] values = parameterMap.get(key);
			for(String value : values) {
				log.info("key " + key + " value " + value);
				if (key.equals("destTerminalId")) {
					destTerminalId = value;
				}
				if (key.equals("icpSubmitTime")) {
					dateStart = value;
				}
				if (key.equals("ismgSubmitTime")) {
					dateEnd = value;
				}
			}
		}

		QueryWrapper<MsgMtLog> queryWrapper2 = new QueryWrapper();

		//注意要发现查询参数不为空后再设置查询条件，否则去查询相关字段等于空白的记录去了，就会导致初次进入页面时执行list提取不到数据
		if (destTerminalId.equals("") == false ) {
			queryWrapper2.eq("dest_terminal_id", destTerminalId);
		}

		if ((dateStart.equals("") == false ) &&  (dateEnd.equals("") == false )) {
			queryWrapper2.between("icp_submit_time", dateStart, dateEnd);
		}

		IPage<MsgMtLog> pageList = msgMtLogService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param msgMtLog
	 * @return
	 */
	@AutoLog(value = "短信发送日志-添加")
	@ApiOperation(value="短信发送日志-添加", notes="短信发送日志-添加")
	@RequiresPermissions("sms:add")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MsgMtLog msgMtLog) {
		//log.info("MsgMtLogController add msgMtLog.getStat " + msgMtLog.getStat());
		msgMtLogService.save(msgMtLog);
		return Result.OK("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param msgMtLog
	 * @return
	 */
	@AutoLog(value = "短信发送日志-编辑")
	@ApiOperation(value="短信发送日志-编辑", notes="短信发送日志-编辑")
	@RequiresPermissions("sms:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody MsgMtLog msgMtLog) {
		msgMtLogService.updateById(msgMtLog);
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "短信发送日志-通过id删除")
	@ApiOperation(value="短信发送日志-通过id删除", notes="短信发送日志-通过id删除")
	@RequiresPermissions("sms:delete")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		msgMtLogService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "短信发送日志-批量删除")
	@ApiOperation(value="短信发送日志-批量删除", notes="短信发送日志-批量删除")
	@RequiresPermissions("sms:delete")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.msgMtLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "短信发送日志-通过id查询")
	@ApiOperation(value="短信发送日志-通过id查询", notes="短信发送日志-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MsgMtLog msgMtLog = msgMtLogService.getById(id);
		return Result.OK(msgMtLog);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param msgMtLog
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, MsgMtLog msgMtLog) {
      return super.exportXls(request, msgMtLog, MsgMtLog.class, "短信发送日志");
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
      return super.importExcel(request, response, MsgMtLog.class);
  }

}
