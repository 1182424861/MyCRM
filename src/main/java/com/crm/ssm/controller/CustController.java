package com.crm.ssm.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.ssm.entity.CstCustomer;
import com.crm.ssm.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/cust")
public class CustController {

	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/cstCustomPage")
	public String findCstCustByPageNow(HttpSession session,Model model,Integer pageNow) {
		session.removeAttribute("custName");
		session.removeAttribute("custNo");
		session.removeAttribute("custManagerName");
		session.removeAttribute("custRegion");
		session.removeAttribute("custLevel");
		session.removeAttribute("pageNowLike");
		pageNow = pageNow==null?1:pageNow;
		int pageSize = 3;
		PageHelper.startPage(pageNow,pageSize);
		PageInfo<CstCustomer> pageInfo = new PageInfo<CstCustomer>(customerService.findAllCustomer());
		model.addAttribute("pageInfo",pageInfo);
		return "customer/customerPage";	
	}
	
	@RequestMapping("/likeCstCustom")
	public String findLikeCstCust(HttpSession session,Model model,Integer pageNow,String custName,String custNo,String custManagerName,String custRegion,Integer custLevel) {
		if(custName==null && custNo==null && custManagerName==null && custRegion=="" && custLevel==null) {
			session.removeAttribute("custName");
			session.removeAttribute("custNo");
			session.removeAttribute("custManagerName");
			session.removeAttribute("custRegion");
			session.removeAttribute("custLevel");
			session.removeAttribute("pageNowLike");
			return "redirect:/cust/customLike";
		}else{
			session.setAttribute("custName",custName);
			session.setAttribute("custNo",custNo);
			session.setAttribute("custManagerName",custManagerName);
			session.setAttribute("custRegion",custRegion);
			session.setAttribute("custLevel",custLevel);
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			PageHelper.startPage(pageNow,pageSize);
			model.addAttribute("pageInfo",new PageInfo<CstCustomer>(customerService.findLikeCustomer(custName, custNo, custManagerName,custRegion,custLevel)));
			return "customer/customerPage";
		}
	}
	@RequestMapping("/custom")
	public String findCstCustByPageLike(HttpSession session,Model model,Integer pageNow) {
		if(session.getAttribute("custName")==null 
			&& session.getAttribute("custNo")==null 
			&& session.getAttribute("custManagerName")==null 
			&& session.getAttribute("custRegion")==null 
			&& session.getAttribute("custLevel")==null) {
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			PageHelper.startPage(pageNow,pageSize);
			model.addAttribute("pageInfo",new PageInfo<CstCustomer>(customerService.findAllCustomer()));
			return "customer/customerPage";
		}else {
			session.setAttribute("pageNowLike", pageNow);
			return "redirect:/cust/customLike";
		}
	}
	@RequestMapping("/customLike")
	public String likePageCstCust(HttpSession session,Model model,Integer pageNowLike) {
		String custName = (String)session.getAttribute("custName");
		String custNo = (String)session.getAttribute("custNo");
		String custManagerName = (String)session.getAttribute("custManagerName");
		String custRegion = (String)session.getAttribute("custRegion");
		Integer custLevel = (Integer)session.getAttribute("custLevel");
		pageNowLike = (Integer) session.getAttribute("pageNowLike");
		int pageSize = 3;
		PageHelper.startPage(pageNowLike,pageSize);
		PageInfo<CstCustomer> pageInfo = new PageInfo<CstCustomer>(customerService.findLikeCustomer(custName, custNo, custManagerName,custRegion,custLevel));
		model.addAttribute("pageInfo",pageInfo);
		return "customer/customerPage";	
	}
	
	//根据客户编号查询客户信息
	@RequestMapping("/findCstById")
	public String findCstCustById(Model model,String custNo) {
		CstCustomer customer = customerService.findCstCustomerById(custNo);
		model.addAttribute("customer", customer);
		return "customer/customerEdit";
	}
	
	//编辑客户信息
	@RequestMapping("/updateCst")
	@ResponseBody
	public int updateCstCust(String custNo,String custName,String custRegion
			,Integer custLevel,Integer custSatisfy,Integer custCredit,String custAddr
			,String custZip,String custTel,String custFax,String custWebsite,String custLicenceNo
			,String custChieftain,Integer custBankroll,Integer custTurnover,String custBank
			,String custBankAccount,String custLocalTaxNo,String custNationalTaxNo) {
		CstCustomer cstCustomer = new CstCustomer();
		cstCustomer.setCustNo(custNo);
		cstCustomer.setCustName(custName);
		cstCustomer.setCustRegion(custRegion);
		cstCustomer.setCustLevel(custLevel);
		cstCustomer.setCustSatisfy(custSatisfy);
		cstCustomer.setCustCredit(custCredit);
		cstCustomer.setCustAddr(custAddr);
		cstCustomer.setCustZip(custZip);
		cstCustomer.setCustTel(custTel);
		cstCustomer.setCustFax(custFax);
		cstCustomer.setCustWebsite(custWebsite);
		cstCustomer.setCustLicenceNo(custLicenceNo);
		cstCustomer.setCustChieftain(custChieftain);
		cstCustomer.setCustBankroll(custBankroll);
		cstCustomer.setCustTurnover(custTurnover);
		cstCustomer.setCustBank(custBank);
		cstCustomer.setCustBankAccount(custBankAccount);
		cstCustomer.setCustLocalTaxNo(custLocalTaxNo);
		cstCustomer.setCustNationalTaxNo(custNationalTaxNo);
		return customerService.updateCstCustomer(cstCustomer);
	}
	
	//删除客户信息【同时删除客户联系人，客户交往记录，客户历史订单以及订单详情】
	@RequestMapping("deleteCst")
	@ResponseBody
	public int deleteCstCust(String custNo) {
		return customerService.deleteCstCustomerById(custNo);
	}
	
	
}
