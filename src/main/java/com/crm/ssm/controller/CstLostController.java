package com.crm.ssm.controller;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.ssm.entity.CstLost;
import com.crm.ssm.service.CstLostService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;



@Controller
@RequestMapping("/lost")
public class CstLostController {

	@Autowired
	private CstLostService cstLostService;
	
	@RequestMapping("/cstLostPage")
	public String findCstLostByPageNow(HttpSession session,Model model,Integer pageNow) {
		session.removeAttribute("lstCustName");
		session.removeAttribute("lstCustManagerName");
		session.removeAttribute("lstStatus");
		session.removeAttribute("pageNowLike");
		pageNow = pageNow==null?1:pageNow;
		int pageSize = 3;
		PageHelper.startPage(pageNow,pageSize);
		PageInfo<CstLost> pageInfo = new PageInfo<CstLost>(cstLostService.findAllLost());
		model.addAttribute("pageInfo",pageInfo);
		return "customer/lostsPage";	
	}
	
	@RequestMapping("/likeCstLost")
	public String findLikeCstLost(HttpSession session,Model model,Integer pageNow,String lstCustName,String lstCustManagerName,String lstStatus) {
		if(lstCustName=="" && lstCustManagerName=="" && lstStatus=="" ) {
			session.removeAttribute("lstCustName");
			session.removeAttribute("lstCustManagerName");
			session.removeAttribute("lstStatus");
			session.removeAttribute("pageNowLike");
			return "redirect:losts";
		}else{
			session.setAttribute("lstCustName",lstCustName);
			session.setAttribute("lstCustManagerName",lstCustManagerName);
			session.setAttribute("lstStatus",lstStatus);
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			PageHelper.startPage(pageNow,pageSize);
			model.addAttribute("pageInfo",new PageInfo<CstLost>(cstLostService.findLikeCstLost(lstCustName, lstCustManagerName, lstStatus)));
			return "customer/lostsPage";
		}
	}
	@RequestMapping("/losts")
	public String findCstLostByPageLike(HttpSession session,Model model,Integer pageNow) {
		if(session.getAttribute("lstCustName")==null 
			&& session.getAttribute("lstCustManagerName")==null 
			&& session.getAttribute("lstStatus")==null) {
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			PageHelper.startPage(pageNow,pageSize);
			//·ÖÒ³
			model.addAttribute("pageInfo",new PageInfo<CstLost>(cstLostService.findAllLost()));
			return "customer/lostsPage";
		}else {
			session.setAttribute("pageNowLike", pageNow);
			return "redirect:/lost/lostLike";
		}
	}
	@RequestMapping("/lostLike")
	public String likePageCstLost(HttpSession session,Model model,Integer pageNowLike) {
		String lstCustName = (String)session.getAttribute("lstCustName");
		String lstCustManagerName = (String)session.getAttribute("lstCustManagerName");
		pageNowLike = (Integer) session.getAttribute("pageNowLike");
		int pageSize = 3;
		PageHelper.startPage(pageNowLike,pageSize);
		//·ÖÒ³
		PageInfo<CstLost> pageInfo = new PageInfo<CstLost>(cstLostService.findLikeCstLost(lstCustName, lstCustManagerName, (String)session.getAttribute("lstStatus")));
		model.addAttribute("pageInfo",pageInfo);
		return "customer/lostsPage";	
	}
	
	@RequestMapping("/findLostById")
	public String findLostByLstId(Integer lstId,Model model,Integer num) {
		CstLost lost = cstLostService.findCstLostByLstId(lstId);
		model.addAttribute("lost", lost);
		if(num.equals(1)) {
			return "customer/lostPause";
		}else if(num.equals(2)) {
			return "customer/lostEnter";
		}else {
			return "customer/lostLook";
		}
		
	}	
	
	@RequestMapping("/updateDelay")
	@ResponseBody
	public int insertLstDelay(Integer lstId,String lstDelay) {
		CstLost lost = new CstLost();
		CstLost lst = cstLostService.findCstLostByLstId(lstId);
		String a = lst.getLstDelay()+"\n"+lstDelay;
		lost.setLstDelay(a);
		lost.setLstStatus("2");
		lost.setLstId(lstId);
		return cstLostService.insertLstDelay(lost);
	}
	
	@RequestMapping("/updateLstReason")
	@ResponseBody
	public int updateLstReason(int lstId,Date lstLostDate,String lstReason) {
		CstLost lost = new CstLost();
		lost.setLstId(lstId);
		lost.setLstLostDate(lstLostDate);
		lost.setLstReason(lstReason);
		lost.setLstStatus("3");
		return cstLostService.updateLstReason(lost);
	}
	
}
