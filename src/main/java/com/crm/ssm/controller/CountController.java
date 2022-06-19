package com.crm.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.ssm.entity.CstCustomer;
import com.crm.ssm.entity.CstLost;
import com.crm.ssm.entity.CstService;
import com.crm.ssm.service.CstLostService;
import com.crm.ssm.service.CstServiceService;
import com.crm.ssm.service.CustomerService;

@Controller
@RequestMapping("/count")
public class CountController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("findCount")
	public String selectCount(Model model) {
		List<CstCustomer> count = customerService.findCustNameCount(null,null);
		model.addAttribute("list", count);
		return "report/contributeReport";
	}
	@RequestMapping("findCountLike")
	public String selectCountLike(Model model,String custName,String svrCreateDate) {
		if(svrCreateDate=="") {
			List<CstCustomer> count = customerService.findCustNameCount(custName,null);
			model.addAttribute("list", count);
			return "report/contributeReport";
		}else {
			List<CstCustomer> count = customerService.findCustNameCount(custName,svrCreateDate);
			model.addAttribute("list", count);
			return "report/contributeReport";
		}
	}
	
	@RequestMapping("findCountNum")
	public String selectCountNum(Model model) {
		List<CstCustomer> count = customerService.findCustNameCountNum(1);
		model.addAttribute("list", count);
		return "report/makeReport";
	}
	@RequestMapping("findCountNumA")
	public String selectCountNumA(Model model,Integer level) {
		List<CstCustomer> count = customerService.findCustNameCountNum(level);
		model.addAttribute("list", count);
		return "report/makeReport";
	}
	
	//调用客户服务业务层
	@Autowired
	private CstServiceService cstServiceService;
	
	@RequestMapping("findSvrCountNum")
	public String selectSvrCountNum(Model model) {
		List<CstService> count = cstServiceService.selectSvrCountNum(null);
		model.addAttribute("list", count);
		return "report/serverReport";
	}
	@RequestMapping("findSvrCountNumA")
	public String selectSvrCountNumA(Model model,String svrCreateDate) {
		if(svrCreateDate=="") {
			return "redirect:/count/findSvrCountNum";
		}else {
			List<CstService> count = cstServiceService.selectSvrCountNum(svrCreateDate);
			model.addAttribute("list", count);
			return "report/serverReport";
		}
	}
	
	@Autowired
	private CstLostService cstLostService;
	
	@RequestMapping("findLost")
	public String selectLost(Model model) {
		List<CstLost> count = cstLostService.selectCstLostLike("","");
		model.addAttribute("list", count);
		return "report/lostReport";
	}
	
	@RequestMapping("findLostLike")
	public String selectLostLike(Model model,String lstCustName,String lstCustManagerName) {
		List<CstLost> count = cstLostService.selectCstLostLike(lstCustName, lstCustManagerName);
		model.addAttribute("list", count);
		
		return "report/lostReport";
	}
	
}
