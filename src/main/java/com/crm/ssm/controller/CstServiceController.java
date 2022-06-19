package com.crm.ssm.controller;

/**
 * 客户服务
 * 
 */

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.crm.ssm.entity.CstCustomer;
import com.crm.ssm.entity.CstService;
import com.crm.ssm.entity.SysUser;
import com.crm.ssm.service.CstServiceService;
import com.crm.ssm.service.CustomerService;
import com.crm.ssm.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/ser")
public class CstServiceController {

	@Autowired
	private CstServiceService cstServiceService;
	
	@Autowired
	private CustomerService CustomerService;
	
	
	@RequestMapping("/insertCstSer")
	@ResponseBody
	public int insertCstService(String svrType,String svrTitle,
				String svrCustName,String svrRequest,HttpSession session) {
		CstService cstService = new CstService();
		cstService.setSvrType(svrType);
		cstService.setSvrTitle(svrTitle);
		cstService.setSvrCustName(svrCustName);
		List<CstCustomer> custs = CustomerService.findCustNoByName(svrCustName);
		if(custs!=null) {
			cstService.setSvrCustNo(custs.get(0).getCustNo());
			cstService.setSvrStatus("新创建");
			cstService.setSvrRequest(svrRequest);
			SysUser sysUser = (SysUser)session.getAttribute("user");
			cstService.setSvrCreateId(sysUser.getUserId());
			cstService.setSvrCreateBy(sysUser.getUserName());
			java.util.Date date = new java.util.Date();
			cstService.setSvrCreateDate(date);
			return cstServiceService.insertCstService(cstService);
		}else {
			return -1;
		}
	}
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/servicePage")
	public String findCstServiceByPageNow(HttpSession session,Model model,Integer pageNow,Integer a) {
		session.removeAttribute("svrCustName");
		session.removeAttribute("svrTitle");
		session.removeAttribute("svrType");
		session.removeAttribute("createDate1");
		session.removeAttribute("createDate2");
		session.removeAttribute("svrStatus");
		session.removeAttribute("pageNowLike");
		pageNow = pageNow==null?1:pageNow;
		int pageSize = 3;
		PageHelper.startPage(pageNow,pageSize);
		PageInfo<CstService> pageInfo = new PageInfo<CstService>(cstServiceService.findAllCstService());
		model.addAttribute("pageInfo",pageInfo);
		if(a==1) {
				SysUser sysUser = (SysUser) session.getAttribute("user");
				List<SysUser> uNameList = userService.findSalChanceUser(sysUser.getUserId());
				model.addAttribute("uNameList",uNameList);
			return "customerService/serviceAllot";	
		}else if(a==2){
			return "customerService/serviceDispose";	
		}else if(a==3){
			return "customerService/serviceResult";
		}else {
			return "customerService/serviceDetail";
		}
	}
	
	@RequestMapping("/likeService")
	public String findLikeCstCust(HttpSession session,Model model,Integer pageNow,String svrCustName,String svrTitle
			,String svrType,String createDate1,String createDate2,String svrStatus,Integer a) {
		if(svrCustName==null && svrTitle==null && svrType=="" && createDate1==null && createDate2==null && svrStatus=="") {
			session.removeAttribute("svrCustName");
			session.removeAttribute("svrTitle");
			session.removeAttribute("svrType");
			session.removeAttribute("createDate1");
			session.removeAttribute("createDate2");
			session.removeAttribute("svrStatus");
			session.removeAttribute("pageNowLike");
			return "redirect:/ser/serviceLike";
		}else{
			session.setAttribute("svrCustName",svrCustName);
			session.setAttribute("svrTitle",svrTitle);
			session.setAttribute("svrType",svrType);
			session.setAttribute("createDate1",createDate1);
			session.setAttribute("createDate2",createDate2);
			session.setAttribute("svrStatus",svrStatus);
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			PageHelper.startPage(pageNow,pageSize);
			//分页
			model.addAttribute("pageInfo",new PageInfo<CstService>(cstServiceService.findLikeCstService(svrCustName, svrTitle, svrType, createDate1, createDate2,svrStatus)));
			if(a==1) {
					SysUser sysUser = (SysUser) session.getAttribute("user");
					List<SysUser> uNameList = userService.findSalChanceUser(sysUser.getUserId());
					model.addAttribute("uNameList",uNameList);
				return "customerService/serviceAllot";	
			}else if(a==2){
				return "customerService/serviceDispose";	
			}else if(a==3){
				return "customerService/serviceResult";
			}else {
				return "customerService/serviceDetail";
			}
		}
	}
	@RequestMapping("/cstService")
	public String findServiceByPageLike(HttpSession session,Model model,Integer pageNow,Integer a) {
		if(session.getAttribute("svrCustName")==null 
			&& session.getAttribute("svrTitle")==null 
			&& session.getAttribute("svrType")==null 
			&& session.getAttribute("createDate1")==null 
			&& session.getAttribute("createDate2")==null
			&& session.getAttribute("svrStatus")==null) {
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			PageHelper.startPage(pageNow,pageSize);
			//分页
			model.addAttribute("pageInfo",new PageInfo<CstService>(cstServiceService.findAllCstService()));
			if(a==1) {
					SysUser sysUser = (SysUser) session.getAttribute("user");
					List<SysUser> uNameList = userService.findSalChanceUser(sysUser.getUserId());
					model.addAttribute("uNameList",uNameList);
				return "customerService/serviceAllot";	
			}else if(a==2){
				return "customerService/serviceDispose";	
			}else if(a==3){
				return "customerService/serviceResult";
			}else {
				return "customerService/serviceDetail";
			}
		}else {
			session.setAttribute("pageNowLike", pageNow);
			return "redirect:/ser/serviceLike?a="+a;
		}
	}
	@RequestMapping("/serviceLike")
	public String likePageService(HttpSession session,Model model,Integer pageNowLike,Integer a) {
		String svrCustName = (String)session.getAttribute("svrCustName");
		String svrTitle = (String)session.getAttribute("svrTitle");
		String svrType = (String)session.getAttribute("svrType");
		String createDate1 = (String)session.getAttribute("createDate1");
		String createDate2 = (String)session.getAttribute("createDate2");
		String svrStatus = (String)session.getAttribute("svrStatus");
		pageNowLike = (Integer) session.getAttribute("pageNowLike");
		int pageSize = 3;
		PageHelper.startPage(pageNowLike,pageSize);
		PageInfo<CstService> pageInfo = new PageInfo<CstService>(cstServiceService.findLikeCstService(svrCustName, svrTitle, svrType, createDate1, createDate2, svrStatus));
		model.addAttribute("pageInfo",pageInfo);
		if(a==1) {
				SysUser sysUser = (SysUser) session.getAttribute("user");
				List<SysUser> uNameList = userService.findSalChanceUser(sysUser.getUserId());
				model.addAttribute("uNameList",uNameList);
			return "customerService/serviceAllot";	
		}else if(a==2){
			return "customerService/serviceDispose";	
		}else if(a==3){
			return "customerService/serviceResult";
		}else {
			return "customerService/serviceDetail";
		}	
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder,WebRequest request) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,true));//CustomDateEditor为自定义日期编辑器
	}
	
	@RequestMapping("/updateService")
	@ResponseBody
	public int updateServiceAllot(Integer svrId,String svrDueTo) {
		CstService cstService = new CstService();
		cstService.setSvrId(svrId);
		cstService.setSvrDueTo(svrDueTo);
		SysUser sysUser = userService.findByName(svrDueTo);
		cstService.setSvrDueId(sysUser.getUserId());
		cstService.setSvrDueDate(new java.util.Date());//当前时间
		cstService.setSvrStatus("已分配");
		return cstServiceService.updateCstService(cstService);
	}
	
	@RequestMapping("/deleteService")
	@ResponseBody
	public int dateleService(Integer svrId) {
		return cstServiceService.deleteCstService(svrId);
	}
	
	@RequestMapping("/findServiceBySvrId")
	public String findServiceBySvrId(Model model,Integer svrId,Integer num) {
		CstService cstService = cstServiceService.findCstServiceBySvrId(svrId);
		model.addAttribute("service", cstService);
		if(num==2) {
			return "customerService/serviceDisposeDialog";
		}else if(num==3){
			return "customerService/serviceResultDialog";
		}else {
			return "customerService/serviceDetailDialog";
		}
		
	}
	
	@RequestMapping("/updateSvrDeal")
	@ResponseBody
	public int updateServiceSvrDeal(HttpSession session,Integer svrId,String svrDeal) {
		CstService cstService = new CstService();
		SysUser sysUser = (SysUser)session.getAttribute("user");
		cstService.setSvrDealId(sysUser.getUserId());
		cstService.setSvrDealBy(sysUser.getUserName());
		cstService.setSvrDealDate(new java.util.Date());
		cstService.setSvrStatus("已处理");
		cstService.setSvrDeal(svrDeal);
		cstService.setSvrId(svrId);
		return cstServiceService.updateCstService(cstService);
	}
	
	@RequestMapping("/updateSvrResult")
	@ResponseBody
	public int updateServiceSvrResult(HttpSession session,Integer svrId,Integer svrSatisfy,String svrResult) {
		CstService cstService = new CstService();
		cstService.setSvrSatisfy(svrSatisfy);
		cstService.setSvrResult(svrResult);
		cstService.setSvrStatus("已反馈");
		cstService.setSvrId(svrId);
		return cstServiceService.updateCstService(cstService);
	}
	
	@RequestMapping("/updateSvr")
	@ResponseBody
	public int updateServiceSvr(HttpSession session,Integer svrId,String svrStatus) {
		CstService cstService = new CstService();
		if(svrStatus.equals("已反馈")){
			cstService.setSvrStatus("已归档");
			cstService.setSvrId(svrId);
			return cstServiceService.updateCstService(cstService);
		}else {
			return 0;
		}
	}
	
}
