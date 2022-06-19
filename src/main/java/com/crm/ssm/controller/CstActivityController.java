package com.crm.ssm.controller;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.ssm.entity.CstActivity;
import com.crm.ssm.entity.CstCustomer;
import com.crm.ssm.service.CstActivityService;

@Controller
@RequestMapping("/atv")
public class CstActivityController {

	@Autowired
	private CstActivityService cstActivityService;
	
	@RequestMapping("/findAtvByNo")
	public String findCstActivityByNo(Model model,String custNo) {
		List<CstActivity> list = cstActivityService.findCstActivityByNo(custNo);
		model.addAttribute("list",list);
		return "customer/activitysPage";
	}
	
	@RequestMapping("/findAtvById")
	public String findCstActivityByNo(Model model,int atvId) {
		CstActivity atv = cstActivityService.findCstActivityById(atvId);
		model.addAttribute("activity", atv);
		return "customer/activitysEdit.html";
	}
	
	@RequestMapping("/updateAtv")
	@ResponseBody
	public int updateLinkman(int atvId,Date atvDate,String atvPlace,String atvTitle,String atvDesc) {	
		CstActivity atv = new CstActivity(); 
		atv.setAtvDate(atvDate);
		atv.setAtvPlace(atvPlace);
		atv.setAtvTitle(atvTitle);
		atv.setAtvDesc(atvDesc);
		atv.setAtvId(atvId);
		int updateNum = cstActivityService.updateActivity(atv);
		return updateNum;
	}
	
	@RequestMapping("/deleteAtv")
	@ResponseBody
	public int deleteLinkman(Integer atvId) {	
		return cstActivityService.deleteActivity(atvId);
	}
	
	@RequestMapping("/findCustANoName")
	public String findCustNoAndCustName(Model model,String atvCustNo,String atvCustName) {
		CstCustomer customer = new CstCustomer();
		customer.setCustNo(atvCustNo);
		customer.setCustName(atvCustName);
		model.addAttribute("cust",customer);
		return "customer/activitysAdd";
	}
	
	@RequestMapping("/addActivity")
	@ResponseBody
	public int insertActivity(String atvCustNo,String atvCustName,Date atvDate
							  ,String atvPlace,String atvTitle,String atvDesc) {
		CstActivity atv = new CstActivity();
		atv.setAtvCustNo(atvCustNo);
		atv.setAtvCustName(atvCustName);
		atv.setAtvDate(atvDate);
		atv.setAtvPlace(atvPlace);
		atv.setAtvTitle(atvTitle);
		atv.setAtvDesc(atvDesc);
		return cstActivityService.insertActivity(atv);
	}
	
}
