package org.jeecg.modules.jcy.msgGroup.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.system.api.ISysBaseAPI;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.jcy.msgGroup.entity.MsgGroupMember;
import org.jeecg.modules.jcy.msgGroup.entity.MsgGroup;
import org.jeecg.modules.jcy.msgGroup.vo.MsgGroupPage;
import org.jeecg.modules.jcy.msgGroup.service.IMsgGroupService;
import org.jeecg.modules.jcy.msgGroup.service.IMsgGroupMemberService;
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
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 短信群组管理
 * @Author: jeecg-boot
 * @Date:   2022-05-18
 * @Version: V1.0
 */
@Api(tags="短信群组管理")
@RestController
@RequestMapping("/msgGroup/msgGroup")
@Slf4j
public class MsgGroupController {
	@Autowired
	private IMsgGroupService msgGroupService;
	@Autowired
	private IMsgGroupMemberService msgGroupMemberService;

	 @Autowired
	 private ISysBaseAPI sysBaseAPI;


	 /**
	 * 分页列表查询
	 *
	 * @param msgGroup
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "短信群组管理-分页列表查询")
	@ApiOperation(value="短信群组管理-分页列表查询", notes="短信群组管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MsgGroup msgGroup,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MsgGroup> queryWrapper = QueryGenerator.initQueryWrapper(msgGroup, req.getParameterMap());
		Page<MsgGroup> page = new Page<MsgGroup>(pageNo, pageSize);
		IPage<MsgGroup> pageList = msgGroupService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	public void setMsgGroupMemberInfo(MsgGroupPage msgGroupPage) {

		String[] arrUsernames = new String[msgGroupPage.getMsgGroupMemberList().size()];
		int iIndex = 0;
		for(MsgGroupMember msgGroupMember : msgGroupPage.getMsgGroupMemberList()) {
			arrUsernames[iIndex] = msgGroupMember.getUserId();
			iIndex++;
		}

		List<LoginUser> listUsers = sysBaseAPI.queryUserByNames(arrUsernames);

		for(MsgGroupMember msgGroupMember : msgGroupPage.getMsgGroupMemberList()) {
			for(LoginUser loginUser : listUsers) {
				if (loginUser.getUsername().equalsIgnoreCase(msgGroupMember.getUserId())) {
					msgGroupMember.setPhone(loginUser.getPhone());
					msgGroupMember.setDuties(loginUser.getPost());
					break;
				}
			}
		}

	}//end method
	
	/**
	 * 添加
	 *
	 * @param msgGroupPage
	 * @return
	 */
	@AutoLog(value = "短信群组管理-添加")
	@ApiOperation(value="短信群组管理-添加", notes="短信群组管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MsgGroupPage msgGroupPage) {
		MsgGroup msgGroup = new MsgGroup();
		BeanUtils.copyProperties(msgGroupPage, msgGroup);

		//setMsgGroupMemberInfo(msgGroupPage);

		msgGroupService.saveMain(msgGroup, msgGroupPage.getMsgGroupMemberList());
		return Result.OK("添加成功!");
	}
	
	/**
	 * 编辑
	 *
	 * @param msgGroupPage
	 * @return
	 */
	@AutoLog(value = "短信群组管理-编辑")
	@ApiOperation(value="短信群组管理-编辑", notes="短信群组管理-编辑")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<?> edit(@RequestBody MsgGroupPage msgGroupPage) {
		MsgGroup msgGroup = new MsgGroup();
		BeanUtils.copyProperties(msgGroupPage, msgGroup);

		//setMsgGroupMemberInfo(msgGroupPage);

		msgGroupService.updateMain(msgGroup, msgGroupPage.getMsgGroupMemberList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "短信群组管理-通过id删除")
	@ApiOperation(value="短信群组管理-通过id删除", notes="短信群组管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
	    msgGroupService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "短信群组管理-批量删除")
	@ApiOperation(value="短信群组管理-批量删除", notes="短信群组管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.msgGroupService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "短信群组管理-通过id查询")
	@ApiOperation(value="短信群组管理-通过id查询", notes="短信群组管理-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MsgGroup msgGroup = msgGroupService.getById(id);
		return Result.OK(msgGroup);
	}
	
	/**
	 * 通过id查询
     *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "短信群组明细-通过主表ID查询")
	@ApiOperation(value="短信群组明细-通过主表ID查询", notes="短信群组管理-通过主表ID查询")
	@GetMapping(value = "/queryMsgGroupMemberByMainId")
	public Result<?> queryMsgGroupMemberListByMainId(@RequestParam(name="id",required=true) String id) {
		List<MsgGroupMember> msgGroupMemberList = msgGroupMemberService.selectByMainId(id);
		return Result.OK(msgGroupMemberList);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param msgGroup
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, MsgGroup msgGroup) {
      // Step.1 组装查询条件
      QueryWrapper<MsgGroup> queryWrapper = QueryGenerator.initQueryWrapper(msgGroup, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //Step.2 获取导出数据
      List<MsgGroupPage> pageList = new ArrayList<MsgGroupPage>();
      List<MsgGroup> msgGroupList = msgGroupService.list(queryWrapper);
      for (MsgGroup temp : msgGroupList) {
          MsgGroupPage vo = new MsgGroupPage();
          BeanUtils.copyProperties(temp, vo);
          List<MsgGroupMember> msgGroupMemberList = msgGroupMemberService.selectByMainId(temp.getId());
          vo.setMsgGroupMemberList(msgGroupMemberList);
          pageList.add(vo);
      }
      //Step.3 调用AutoPoi导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "短信群组管理");
      mv.addObject(NormalExcelConstants.CLASS, MsgGroupPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("短信群组管理数据", "导出人:"+sysUser.getRealname(), "短信群组管理"));
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
              List<MsgGroupPage> list = ExcelImportUtil.importExcel(file.getInputStream(), MsgGroupPage.class, params);
              for (MsgGroupPage page : list) {
                  MsgGroup po = new MsgGroup();
                  BeanUtils.copyProperties(page, po);
                  msgGroupService.saveMain(po, page.getMsgGroupMemberList());
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
