package org.jeecg.modules.jcy.importantMsg.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.gzcmcc.ismgclient.db.domain.MsgMtLog;
import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.modules.jcy.importantMsg.mapper.ImportantMsgUserSmsMapper;
import org.jeecg.modules.jcy.msgGroup.entity.MsgGroupMember;
import org.jeecg.modules.jcy.msgGroup.vo.MsgGroupPage;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.jcy.importantMsg.entity.ImportantMsgUserSms;
import org.jeecg.modules.jcy.importantMsg.entity.ImportantMsg;
import org.jeecg.modules.jcy.importantMsg.vo.ImportantMsgPage;
import org.jeecg.modules.jcy.importantMsg.service.IImportantMsgService;
import org.jeecg.modules.jcy.importantMsg.service.IImportantMsgUserSmsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 重要信息通知管理
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
@Api(tags="重要信息通知管理")
@RestController
@RequestMapping("/importantMsg/importantMsg")
@Slf4j
public class ImportantMsgController {
	@Autowired
	private IImportantMsgService importantMsgService;
	@Autowired
	private IImportantMsgUserSmsService importantMsgUserSmsService;

	 @Autowired
	 private ISysBaseAPI sysBaseAPI;

	 @Autowired
	 private ImportantMsgUserSmsMapper importantMsgUserSmsMapper;
	 @Autowired
	 private RedisUtil redisUtil;
	 String listRedisMtToIsmg = "list_mt_wait_to_ismg_851010";

	 /**
	 * 分页列表查询
	 *
	 * @param importantMsg
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "重要信息通知管理-分页列表查询")
	@ApiOperation(value="重要信息通知管理-分页列表查询", notes="重要信息通知管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ImportantMsg importantMsg,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<ImportantMsg> queryWrapper = QueryGenerator.initQueryWrapper(importantMsg, req.getParameterMap());
		Page<ImportantMsg> page = new Page<ImportantMsg>(pageNo, pageSize);
		IPage<ImportantMsg> pageList = importantMsgService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 public void setImportantMsgUserSmsInfo(ImportantMsgPage importantMsgPage) {

		 String[] arrUsernames = new String[importantMsgPage.getImportantMsgUserSmsList().size()];
		 int iIndex = 0;
		 for(ImportantMsgUserSms importantMsgUserSms : importantMsgPage.getImportantMsgUserSmsList()) {
			 arrUsernames[iIndex] = importantMsgUserSms.getSmsUserName();
			 iIndex++;
		 }

		 List<LoginUser> listUsers = sysBaseAPI.queryUserByNames(arrUsernames);
		 for(LoginUser loginUser : listUsers) {
		 	log.info("loginuser " + loginUser );
		 }

		 for(ImportantMsgUserSms importantMsgUserSms : importantMsgPage.getImportantMsgUserSmsList()) {

		 	log.info("初始 importantMsgUserSms" + importantMsgUserSms);

		 	for(LoginUser loginUser : listUsers) {
				 if (loginUser.getUsername().equalsIgnoreCase(importantMsgUserSms.getSmsUserName())) {

				 	 importantMsgUserSms.setSmsUserName(loginUser.getRealname());
					 importantMsgUserSms.setSmsUserDuties(loginUser.getPost());
					 importantMsgUserSms.setSmsUserMobile(loginUser.getPhone());
					 importantMsgUserSms.setSmsMtSrcTerminalId("10658123456");

					 log.info("更新后 importantMsgUserSms" + importantMsgUserSms);
					 break;
				 }
			 }
		 }

	 }//end method

	 /**
	 * 添加
	 *
	 * @param importantMsgPage
	 * @return
	 */
	@AutoLog(value = "重要信息通知管理-添加")
	@ApiOperation(value="重要信息通知管理-添加", notes="重要信息通知管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ImportantMsgPage importantMsgPage) {
		ImportantMsg importantMsg = new ImportantMsg();

		BeanUtils.copyProperties(importantMsgPage, importantMsg);

		//必须在存储重要信息短信之前更新短信相关的用户名、手机号、职务等信息
		//setImportantMsgUserSmsInfo(importantMsgPage);//获取用户的手机号，职务名称等信息

		importantMsgService.saveMain(importantMsg, importantMsgPage.getImportantMsgUserSmsList());

		//这里以 importantMsgPage.getImportantMsgUserSmsList() 中每条消息插入数据库后生成的id属性作为
		// 每条消息的的本地唯一id发送给短信模块，这样短信模块在网关返回短信状态报告后可以根据此id设置每条消息的状态报告

		// 这里将重要信息通知中每个人的消息内容要进行处理，替换其中的模板变量，
		// 例如将职务名称模板替换成每个人对应的职务名称，然后提交给短信模块

		String msgContent = importantMsg.getMeetingSmsInfo() + " #职务名称#，您有重要信息通知！";

		for(ImportantMsgUserSms importantMsgUserSms : importantMsgPage.getImportantMsgUserSmsList()) {

			msgContent = msgContent.replaceAll("#职务名称#", importantMsgUserSms.getSmsUserDuties());
			//String mobile = (listLoginuser.get(0)).getString("phone");

			/*System.out.println("短信发送关键信息 唯一流水号 " + importantMsgUserSms.getId()
					+ " 手机号 " + importantMsgUserSms.getSmsUserMobile() + " 职务信息 " + importantMsgUserSms.getSmsUserDuties()
					+ " 消息内容 " + msgContent);*/

			MsgMtLog msgMtLog = new MsgMtLog();
			msgMtLog.setMsg_id(importantMsgUserSms.getId());
			msgMtLog.setRegistered_delivery(1);
			msgMtLog.setSrc_terminal_id("10658123456");
			msgMtLog.setDest_terminal_id(importantMsgUserSms.getSmsUserMobile());
			msgMtLog.setMsg_content(msgContent);

			redisUtil.lSet(listRedisMtToIsmg, msgMtLog);

			log.info("MsgMtLog 发送短信内容 " + msgMtLog);

		}

		return Result.OK("添加成功!");
	}
	
	/**
	 * 编辑
	 *
	 * @param importantMsgPage
	 * @return
	 */
	@AutoLog(value = "重要信息通知管理-编辑")
	@ApiOperation(value="重要信息通知管理-编辑", notes="重要信息通知管理-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody ImportantMsgPage importantMsgPage) {
		ImportantMsg importantMsg = new ImportantMsg();
		BeanUtils.copyProperties(importantMsgPage, importantMsg);
		importantMsgService.updateMain(importantMsg, importantMsgPage.getImportantMsgUserSmsList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "重要信息通知管理-通过id删除")
	@ApiOperation(value="重要信息通知管理-通过id删除", notes="重要信息通知管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
	    importantMsgService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "重要信息通知管理-批量删除")
	@ApiOperation(value="重要信息通知管理-批量删除", notes="重要信息通知管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.importantMsgService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "重要信息通知管理-通过id查询")
	@ApiOperation(value="重要信息通知管理-通过id查询", notes="重要信息通知管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ImportantMsg importantMsg = importantMsgService.getById(id);
		return Result.OK(importantMsg);
	}
	
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "重要信息通知明细-通过主表ID查询")
	@ApiOperation(value="重要信息通知明细-通过主表ID查询", notes="重要信息通知管理-通过主表ID查询")
	@GetMapping(value = "/queryImportantMsgUserSmsByMainId")
	public Result<?> queryImportantMsgUserSmsListByMainId(@RequestParam(name="id",required=true) String id) {
		List<ImportantMsgUserSms> importantMsgUserSmsList = importantMsgUserSmsService.selectByMainId(id);
		return Result.OK(importantMsgUserSmsList);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param importantMsg
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, ImportantMsg importantMsg) {
      // Step.1 组装查询条件
      QueryWrapper<ImportantMsg> queryWrapper = QueryGenerator.initQueryWrapper(importantMsg, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<ImportantMsgPage> pageList = new ArrayList<ImportantMsgPage>();
      List<ImportantMsg> importantMsgList = importantMsgService.list(queryWrapper);
      for (ImportantMsg temp : importantMsgList) {
          ImportantMsgPage vo = new ImportantMsgPage();
          BeanUtils.copyProperties(temp, vo);
          List<ImportantMsgUserSms> importantMsgUserSmsList = importantMsgUserSmsService.selectByMainId(temp.getId());
          vo.setImportantMsgUserSmsList(importantMsgUserSmsList);
          pageList.add(vo);
      }
      //Step.3 调用AutoPoi导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "重要信息通知管理");
      mv.addObject(NormalExcelConstants.CLASS, ImportantMsgPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("重要信息通知管理数据", "导出人:"+sysUser.getRealname(), "重要信息通知管理"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
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
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<ImportantMsgPage> list = ExcelImportUtil.importExcel(file.getInputStream(), ImportantMsgPage.class, params);
              for (ImportantMsgPage page : list) {
                  ImportantMsg po = new ImportantMsg();
                  BeanUtils.copyProperties(page, po);
                  importantMsgService.saveMain(po, page.getImportantMsgUserSmsList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
  }

}
