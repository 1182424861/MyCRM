package com.crm.ssm.controller;

/**
 * 销售机会
 * 
 */

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.ssm.entity.SalChance;
import com.crm.ssm.entity.SysUser;
import com.crm.ssm.service.SalChanceService;
import com.crm.ssm.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Controller
@RequestMapping("/sale")
public class SalController {
	
	//调用业务层
	@Autowired
	private SalChanceService salService;
	
	//分页查询销售机会管理(页面:saleChance.html)
	@RequestMapping("/chancePage")
	public String findSalChanceByPageNow(HttpSession session,Model model,Integer pageNow) {
		//点击销售机会管理的时候将模糊查询的session全部清空
		session.removeAttribute("chcCustName");
		session.removeAttribute("chcLinkman");
		session.removeAttribute("chcTitle");
		session.removeAttribute("pageNowLike");
		pageNow = pageNow==null?1:pageNow;
		int pageSize = 3;
		//开启分页设置【必须要写在查询结果集之前】
		PageHelper.startPage(pageNow,pageSize);
		//分页
		PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findSalChance());
		model.addAttribute("pageInfo",pageInfo);
		return "sale/saleChance";	
	}
	
	//模糊查询(页面:saleChance.html)分页【点击改变页数的按钮(点击上一页、下一页等按钮)】
	@RequestMapping("/chance")
	public String findSalChanceByPageLike(HttpSession session,Model model,Integer pageNow) {
		if(session.getAttribute("chcCustName")==null && session.getAttribute("chcLinkman")==null && session.getAttribute("chcTitle")==null) {
		//判断是否有模糊查询条件
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//开启分页设置【必须要写在查询结果集之前】
			PageHelper.startPage(pageNow,pageSize);
			//分页
			PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findSalChance());
			model.addAttribute("pageInfo",pageInfo);
			return "sale/saleChance";
		}else {
		//如果有模糊查询条件，保存pageNow(当前模糊查询页数),跳转到/sale/chanceLike方法中去
			session.setAttribute("pageNowLike", pageNow);
			return "redirect:/sale/chanceLike";
		}
	}
	//模糊查询【点击查询按钮】(页面:saleChance.html)
	@RequestMapping("/likeSalChance")
	public String findLikeSalChance(HttpSession session,Model model,Integer pageNow,String chcCustName,String chcLinkman,String chcTitle) {
		if(chcCustName==null && chcLinkman==null && chcTitle==null ) {
		//判断点击模糊查询的时候查询条件是否为空
			session.removeAttribute("chcCustName");
			session.removeAttribute("chcLinkman");
			session.removeAttribute("chcTitle");
			session.removeAttribute("pageNowLike");
			return "redirect:chance";
		}else {
		//查询条件不为空的时候
			session.setAttribute("chcCustName",chcCustName);
			session.setAttribute("chcLinkman",chcLinkman);
			session.setAttribute("chcTitle",chcTitle);
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//开启分页设置【必须要写在查询结果集之前】
			PageHelper.startPage(pageNow,pageSize);
			//分页
			PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findLikeSalChance(chcCustName, chcLinkman, chcTitle,null));
			model.addAttribute("pageInfo",pageInfo);
			return "sale/saleChance";
		}
		
	}
	//【点击改变页数的按钮】(页面:saleChance.html)
	@RequestMapping("/chanceLike")
	public String likePageSalCHance(HttpSession session,Model model,Integer pageNowLike) {
		//得到session中的模糊查询条件以及当前模糊查询页数
		String chcCustName = (String)session.getAttribute("chcCustName");
		String chcLinkman = (String)session.getAttribute("chcLinkman");
		String chcTitle = (String)session.getAttribute("chcTitle");
		//模糊查询后进行页数操作点击的时候进入
		pageNowLike = (Integer) session.getAttribute("pageNowLike");
		int pageSize = 3;
		//开启分页设置【必须要写在查询结果集之前】
		PageHelper.startPage(pageNowLike,pageSize);
		//分页
		PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findLikeSalChance(chcCustName, chcLinkman, chcTitle,null));
		model.addAttribute("pageInfo",pageInfo);
		return "sale/saleChance";	
	}
	
	//由saleChance跳转到allotSale页面【要显示thymeleaf必须用controller跳转页面】
	@RequestMapping("/skipAllotSale")
	public String skipAllotSale(){
		return "sale/addSale";
	}
	
	//新建销售机会【销售机会添加】
	@PostMapping("/addSalChance")
	public String addSalChance(String chcSource,String chcCustName,String chcTitle,
			Integer chcRate,String chcLinkman,String chcTel,String chcDesc,
			Date chcCreateDate,HttpSession session){		
		
		SalChance salChance = new SalChance();
		salChance.setChcSource(chcSource);
		salChance.setChcCustName(chcCustName);
		salChance.setChcTitle(chcTitle);
		salChance.setChcRate(chcRate);
		salChance.setChcLinkman(chcLinkman);
		salChance.setChcTel(chcTel);
		salChance.setChcDesc(chcDesc);
		SysUser sysUser = (SysUser)session.getAttribute("user");
		int chcCreateId = sysUser.getUserId();
		String chcCreateBy = sysUser.getUserName();
		salChance.setChcCreateId(chcCreateId);
		salChance.setChcCreateBy(chcCreateBy);
		salChance.setChcCreateDate(chcCreateDate);
		salChance.setChcStatus(1);
		salService.addSalChance(salChance);
		return "redirect:/sale/chancePage";
	}
	
	//调用用户的业务层
	@Autowired
	private UserService UserService;
	
	//根据chcId查询分配机会
	@RequestMapping("/allotSalChance")
	public String findByChcIdAllot(Model model,int chcId) {
		SalChance salChance = salService.findSalChanceById(chcId);
		model.addAttribute("salChance",salChance);
		List<SysUser> uNameList = UserService.findSalChanceUser(salChance.getChcCreateId());
		model.addAttribute("uNameList",uNameList);
		return "sale/allotSale";
	}
	
	//分配销售机会
	@RequestMapping("/updateAllotUser")
	public String allotSalChance(Integer chcId,String chcDueTo,Date chcDueDate) {
		SysUser sysUserId = UserService.findByName(chcDueTo);
		SalChance salChance = new SalChance();
		salChance.setChcDueId(sysUserId.getUserId());
		salChance.setChcDueTo(chcDueTo);
		salChance.setChcDueDate(chcDueDate);
		salChance.setChcStatus(2);
		salChance.setChcId(chcId);
		salService.updateSalChance(salChance);
		return "redirect:/sale/chancePage";
	}
	
	//根据chcId查询销售机会
	@RequestMapping("findByChcId")
	public String findByChcId(Model model,int chcId) {
		SalChance salChance = salService.findSalChanceById(chcId);
		model.addAttribute("salChance",salChance);
		return "sale/editSale";
	}
	
	//修改销售机会
	@RequestMapping("/updateSalChance")
	public String updateSalChance(int chcId,String chcCustName,String chcSource,Integer chcRate,
			String chcLinkman,String chcTel,String chcTitle,String chcDesc) {
		SalChance salChance = new SalChance();
		salChance.setChcId(chcId);
		salChance.setChcCustName(chcCustName);
		salChance.setChcSource(chcSource);
		salChance.setChcRate(chcRate);
		salChance.setChcLinkman(chcLinkman);
		salChance.setChcTel(chcTel);
		salChance.setChcTitle(chcTitle);
		salChance.setChcDesc(chcDesc);
		salService.updateSalChance(salChance);
		return "redirect:/sale/chancePage";
	}
	
	//删除销售机会【有主外键问题】
	@RequestMapping("/delSalChance")
	public String delSalChance(int chcId) {
		salService.delSalChance(chcId);
		return "redirect:/sale/chancePage";
	}
	
	
	//分页查询客户开发计划(页面:saleManager.html)
	@RequestMapping("/chancePage01")
	public String findSalChanceByPageNow01(HttpSession session,Model model,Integer pageNow) {
		//点击客户开发计划的时候将模糊查询的session全部清空
		session.removeAttribute("chcCustName");
		session.removeAttribute("chcLinkman");
		session.removeAttribute("chcTitle");
		session.removeAttribute("chcStatus");
		session.removeAttribute("pageNowLike");
		pageNow = pageNow==null?1:pageNow;
		int pageSize = 3;
		//开启分页设置【必须要写在查询结果集之前】
		PageHelper.startPage(pageNow,pageSize);
		//分页
		PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findSalChance());
		model.addAttribute("pageInfo",pageInfo);
		return "sale/saleManager";	
	}
	
	//模糊查询【点击查询按钮】(页面:saleManager.html)
	@RequestMapping("/likeSalChance01")
	public String findLikeSalChance01(HttpSession session,Model model,Integer pageNow,String chcCustName,String chcLinkman,String chcTitle,Integer chcStatus) {
		if(chcCustName==null && chcLinkman==null && chcTitle==null && chcStatus==null) {
		//判断点击模糊查询的时候查询条件是否为空
			session.removeAttribute("chcCustName");
			session.removeAttribute("chcLinkman");
			session.removeAttribute("chcTitle");
			session.removeAttribute("chcStatus");
			session.removeAttribute("pageNowLike");
			return "redirect:chance01";
		}else{
		//查询条件不为空的时候
			session.setAttribute("chcCustName",chcCustName);
			session.setAttribute("chcLinkman",chcLinkman);
			session.setAttribute("chcTitle",chcTitle);
			session.setAttribute("chcStatus",chcStatus);
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//开启分页设置【必须要写在查询结果集之前】
			PageHelper.startPage(pageNow,pageSize);
			//分页
			PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findLikeSalChance(chcCustName, chcLinkman, chcTitle, chcStatus));
			model.addAttribute("pageInfo",pageInfo);
			return "sale/saleManager";
		}
	}
	//模糊查询(页面:saleManger.html)分页【点击改变页数的按钮(点击上一页、下一页等按钮)】
	@RequestMapping("/chance01")
	public String findSalChanceByPageLike01(HttpSession session,Model model,Integer pageNow) {
		if(session.getAttribute("chcCustName")==null 
			&& session.getAttribute("chcLinkman")==null 
			&& session.getAttribute("chcTitle")==null 
			&& session.getAttribute("chcStatus")==null) {
		//判断是否有模糊查询条件
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//开启分页设置【必须要写在查询结果集之前】
			PageHelper.startPage(pageNow,pageSize);
			//分页
			PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findSalChance());
			model.addAttribute("pageInfo",pageInfo);
			return "sale/saleManager";
		}else {
		//如果有模糊查询条件，保存pageNow(当前模糊查询页数),跳转到/sale/chanceLike方法中去
			session.setAttribute("pageNowLike", pageNow);
			return "redirect:/sale/chanceLike01";
		}
	}
	//【点击改变页数的按钮】(页面:saleManager.html)
	@RequestMapping("/chanceLike01")
	public String likePageSalChance01(HttpSession session,Model model,Integer pageNowLike) {
		//得到session中的模糊查询条件以及当前模糊查询页数
		String chcCustName = (String)session.getAttribute("chcCustName");
		String chcLinkman = (String)session.getAttribute("chcLinkman");
		String chcTitle = (String)session.getAttribute("chcTitle");
		
		//模糊查询后进行页数操作点击的时候进入
		pageNowLike = (Integer) session.getAttribute("pageNowLike");
		int pageSize = 3;
		//开启分页设置【必须要写在查询结果集之前】
		PageHelper.startPage(pageNowLike,pageSize);
		//分页
		PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findLikeSalChance(chcCustName, chcLinkman, chcTitle, (Integer)session.getAttribute("chcStatus")));
		model.addAttribute("pageInfo",pageInfo);
		return "sale/saleManager";	
	}
	
	//查询开发状态
	@RequestMapping("/findSalChanceStatus")
	@ResponseBody
	public int findSalChanceStatus(Integer chcId) {
		return salService.findSalStatus(chcId);
	}
	
	//修改开发状态
	@RequestMapping("/updateChcStatus")
	@ResponseBody
	public boolean updateChcStatus(Integer chcId,Integer chcStatus) {
		SalChance salChance = new SalChance();
		salChance.setChcStatus(chcStatus);
		salChance.setChcId(chcId);
		return salService.updateSalChance(salChance);
	}
	
	//返回开发状态和chcId
	@RequestMapping("/findStatusChcId")
	@ResponseBody
	public String findStatusChcId(Integer chcId,Integer chcStatus) {
		String a = chcId.toString()+salService.findSalStatus(chcId);
		return a;
	}
	
	
}
