package com.crm.ssm.controller;

/**
 * ��ϵ��
 * 
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.ssm.entity.CstCustomer;
import com.crm.ssm.entity.CstLinkman;
import com.crm.ssm.service.CstLinkmanService;

@Controller
@RequestMapping("/lkm")
public class LkmController {

	//����ҵ���
	@Autowired
	private CstLinkmanService cstLinkmanService;
	
	//���ݿͻ���Ų�ѯ�ͻ���ϵ��
	@RequestMapping("/findLkmByNo")
	public String findCstLinkmanByNo(Model model,String custNo) {
		List<CstLinkman> list = cstLinkmanService.findCstLinkmanByNo(custNo);
		model.addAttribute("list",list);
		return "customer/linkManPage";
	}
	
	//����lkmId��ѯ��ϵ��
	@RequestMapping("/findLkmById")
	public String findCstLinkmanByNo(Model model,int lkmId) {
		CstLinkman lkm = cstLinkmanService.findCstLkmById(lkmId);
		model.addAttribute("linkman", lkm);
		return "customer/linkManEdit.html";
	}
	
	//�༭��ϵ��
	@RequestMapping("/updateLkm")
	@ResponseBody
	public int updateLinkman(Integer lkmId,String lkmName,String lkmSex,String lkmPostion
			,String lkmTel,String lkmMobile,String lkmMemo) {	
		CstLinkman lkm = new CstLinkman(); 
		lkm.setLkmName(lkmName);
		lkm.setLkmSex(lkmSex);
		lkm.setLkmPostion(lkmPostion);
		lkm.setLkmTel(lkmTel);
		lkm.setLkmMobile(lkmMobile);
		lkm.setLkmMemo(lkmMemo);
		lkm.setLkmId(lkmId);
		int updateNum = cstLinkmanService.updateLinkman(lkm);
		return updateNum;
	}
	
	//ɾ����ϵ��
	@RequestMapping("/deleteLkm")
	@ResponseBody
	public int deleteLinkman(Integer lkmId) {	
		return cstLinkmanService.deleteLinkman(lkmId);
	}
	
	//��custNo��custName���ݵ������ϵ��ҳ��
	@RequestMapping("/findCustNoName")
	public String findCustNoAndCustName(Model model,String lkmCustNo,String lkmCustName) {
		CstCustomer customer = new CstCustomer();
		customer.setCustNo(lkmCustNo);
		customer.setCustName(lkmCustName);
		model.addAttribute("cst",customer);
		return "customer/linkManAdd";
	}
	
	//�����ϵ��
	@RequestMapping("/addLinkman")
	@ResponseBody
	public int insertLinkMan(String lkmCustNo,String lkmCustName,String lkmName,String lkmSex
							,String lkmPostion,String lkmTel,String lkmMobile,String lkmMemo) {
		CstLinkman lkm = new CstLinkman();
		lkm.setLkmCustNo(lkmCustNo);
		lkm.setLkmCustName(lkmCustName);
		lkm.setLkmName(lkmName);
		lkm.setLkmSex(lkmSex);
		lkm.setLkmPostion(lkmPostion);
		lkm.setLkmTel(lkmTel);
		lkm.setLkmMobile(lkmMobile);
		lkm.setLkmMemo(lkmMemo);
		return cstLinkmanService.insertLinkman(lkm);
	}
	
}
