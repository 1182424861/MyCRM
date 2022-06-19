package com.crm.ssm.controller;

/**
 * ���ۻ���
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
	
	//����ҵ���
	@Autowired
	private SalChanceService salService;
	
	//��ҳ��ѯ���ۻ������(ҳ��:saleChance.html)
	@RequestMapping("/chancePage")
	public String findSalChanceByPageNow(HttpSession session,Model model,Integer pageNow) {
		//������ۻ�������ʱ��ģ����ѯ��sessionȫ�����
		session.removeAttribute("chcCustName");
		session.removeAttribute("chcLinkman");
		session.removeAttribute("chcTitle");
		session.removeAttribute("pageNowLike");
		pageNow = pageNow==null?1:pageNow;
		int pageSize = 3;
		//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
		PageHelper.startPage(pageNow,pageSize);
		//��ҳ
		PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findSalChance());
		model.addAttribute("pageInfo",pageInfo);
		return "sale/saleChance";	
	}
	
	//ģ����ѯ(ҳ��:saleChance.html)��ҳ������ı�ҳ���İ�ť(�����һҳ����һҳ�Ȱ�ť)��
	@RequestMapping("/chance")
	public String findSalChanceByPageLike(HttpSession session,Model model,Integer pageNow) {
		if(session.getAttribute("chcCustName")==null && session.getAttribute("chcLinkman")==null && session.getAttribute("chcTitle")==null) {
		//�ж��Ƿ���ģ����ѯ����
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
			PageHelper.startPage(pageNow,pageSize);
			//��ҳ
			PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findSalChance());
			model.addAttribute("pageInfo",pageInfo);
			return "sale/saleChance";
		}else {
		//�����ģ����ѯ����������pageNow(��ǰģ����ѯҳ��),��ת��/sale/chanceLike������ȥ
			session.setAttribute("pageNowLike", pageNow);
			return "redirect:/sale/chanceLike";
		}
	}
	//ģ����ѯ�������ѯ��ť��(ҳ��:saleChance.html)
	@RequestMapping("/likeSalChance")
	public String findLikeSalChance(HttpSession session,Model model,Integer pageNow,String chcCustName,String chcLinkman,String chcTitle) {
		if(chcCustName==null && chcLinkman==null && chcTitle==null ) {
		//�жϵ��ģ����ѯ��ʱ���ѯ�����Ƿ�Ϊ��
			session.removeAttribute("chcCustName");
			session.removeAttribute("chcLinkman");
			session.removeAttribute("chcTitle");
			session.removeAttribute("pageNowLike");
			return "redirect:chance";
		}else {
		//��ѯ������Ϊ�յ�ʱ��
			session.setAttribute("chcCustName",chcCustName);
			session.setAttribute("chcLinkman",chcLinkman);
			session.setAttribute("chcTitle",chcTitle);
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
			PageHelper.startPage(pageNow,pageSize);
			//��ҳ
			PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findLikeSalChance(chcCustName, chcLinkman, chcTitle,null));
			model.addAttribute("pageInfo",pageInfo);
			return "sale/saleChance";
		}
		
	}
	//������ı�ҳ���İ�ť��(ҳ��:saleChance.html)
	@RequestMapping("/chanceLike")
	public String likePageSalCHance(HttpSession session,Model model,Integer pageNowLike) {
		//�õ�session�е�ģ����ѯ�����Լ���ǰģ����ѯҳ��
		String chcCustName = (String)session.getAttribute("chcCustName");
		String chcLinkman = (String)session.getAttribute("chcLinkman");
		String chcTitle = (String)session.getAttribute("chcTitle");
		//ģ����ѯ�����ҳ�����������ʱ�����
		pageNowLike = (Integer) session.getAttribute("pageNowLike");
		int pageSize = 3;
		//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
		PageHelper.startPage(pageNowLike,pageSize);
		//��ҳ
		PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findLikeSalChance(chcCustName, chcLinkman, chcTitle,null));
		model.addAttribute("pageInfo",pageInfo);
		return "sale/saleChance";	
	}
	
	//��saleChance��ת��allotSaleҳ�桾Ҫ��ʾthymeleaf������controller��תҳ�桿
	@RequestMapping("/skipAllotSale")
	public String skipAllotSale(){
		return "sale/addSale";
	}
	
	//�½����ۻ��᡾���ۻ�����ӡ�
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
	
	//�����û���ҵ���
	@Autowired
	private UserService UserService;
	
	//����chcId��ѯ�������
	@RequestMapping("/allotSalChance")
	public String findByChcIdAllot(Model model,int chcId) {
		SalChance salChance = salService.findSalChanceById(chcId);
		model.addAttribute("salChance",salChance);
		List<SysUser> uNameList = UserService.findSalChanceUser(salChance.getChcCreateId());
		model.addAttribute("uNameList",uNameList);
		return "sale/allotSale";
	}
	
	//�������ۻ���
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
	
	//����chcId��ѯ���ۻ���
	@RequestMapping("findByChcId")
	public String findByChcId(Model model,int chcId) {
		SalChance salChance = salService.findSalChanceById(chcId);
		model.addAttribute("salChance",salChance);
		return "sale/editSale";
	}
	
	//�޸����ۻ���
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
	
	//ɾ�����ۻ��᡾����������⡿
	@RequestMapping("/delSalChance")
	public String delSalChance(int chcId) {
		salService.delSalChance(chcId);
		return "redirect:/sale/chancePage";
	}
	
	
	//��ҳ��ѯ�ͻ������ƻ�(ҳ��:saleManager.html)
	@RequestMapping("/chancePage01")
	public String findSalChanceByPageNow01(HttpSession session,Model model,Integer pageNow) {
		//����ͻ������ƻ���ʱ��ģ����ѯ��sessionȫ�����
		session.removeAttribute("chcCustName");
		session.removeAttribute("chcLinkman");
		session.removeAttribute("chcTitle");
		session.removeAttribute("chcStatus");
		session.removeAttribute("pageNowLike");
		pageNow = pageNow==null?1:pageNow;
		int pageSize = 3;
		//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
		PageHelper.startPage(pageNow,pageSize);
		//��ҳ
		PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findSalChance());
		model.addAttribute("pageInfo",pageInfo);
		return "sale/saleManager";	
	}
	
	//ģ����ѯ�������ѯ��ť��(ҳ��:saleManager.html)
	@RequestMapping("/likeSalChance01")
	public String findLikeSalChance01(HttpSession session,Model model,Integer pageNow,String chcCustName,String chcLinkman,String chcTitle,Integer chcStatus) {
		if(chcCustName==null && chcLinkman==null && chcTitle==null && chcStatus==null) {
		//�жϵ��ģ����ѯ��ʱ���ѯ�����Ƿ�Ϊ��
			session.removeAttribute("chcCustName");
			session.removeAttribute("chcLinkman");
			session.removeAttribute("chcTitle");
			session.removeAttribute("chcStatus");
			session.removeAttribute("pageNowLike");
			return "redirect:chance01";
		}else{
		//��ѯ������Ϊ�յ�ʱ��
			session.setAttribute("chcCustName",chcCustName);
			session.setAttribute("chcLinkman",chcLinkman);
			session.setAttribute("chcTitle",chcTitle);
			session.setAttribute("chcStatus",chcStatus);
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
			PageHelper.startPage(pageNow,pageSize);
			//��ҳ
			PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findLikeSalChance(chcCustName, chcLinkman, chcTitle, chcStatus));
			model.addAttribute("pageInfo",pageInfo);
			return "sale/saleManager";
		}
	}
	//ģ����ѯ(ҳ��:saleManger.html)��ҳ������ı�ҳ���İ�ť(�����һҳ����һҳ�Ȱ�ť)��
	@RequestMapping("/chance01")
	public String findSalChanceByPageLike01(HttpSession session,Model model,Integer pageNow) {
		if(session.getAttribute("chcCustName")==null 
			&& session.getAttribute("chcLinkman")==null 
			&& session.getAttribute("chcTitle")==null 
			&& session.getAttribute("chcStatus")==null) {
		//�ж��Ƿ���ģ����ѯ����
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
			PageHelper.startPage(pageNow,pageSize);
			//��ҳ
			PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findSalChance());
			model.addAttribute("pageInfo",pageInfo);
			return "sale/saleManager";
		}else {
		//�����ģ����ѯ����������pageNow(��ǰģ����ѯҳ��),��ת��/sale/chanceLike������ȥ
			session.setAttribute("pageNowLike", pageNow);
			return "redirect:/sale/chanceLike01";
		}
	}
	//������ı�ҳ���İ�ť��(ҳ��:saleManager.html)
	@RequestMapping("/chanceLike01")
	public String likePageSalChance01(HttpSession session,Model model,Integer pageNowLike) {
		//�õ�session�е�ģ����ѯ�����Լ���ǰģ����ѯҳ��
		String chcCustName = (String)session.getAttribute("chcCustName");
		String chcLinkman = (String)session.getAttribute("chcLinkman");
		String chcTitle = (String)session.getAttribute("chcTitle");
		
		//ģ����ѯ�����ҳ�����������ʱ�����
		pageNowLike = (Integer) session.getAttribute("pageNowLike");
		int pageSize = 3;
		//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
		PageHelper.startPage(pageNowLike,pageSize);
		//��ҳ
		PageInfo<SalChance> pageInfo = new PageInfo<SalChance>(salService.findLikeSalChance(chcCustName, chcLinkman, chcTitle, (Integer)session.getAttribute("chcStatus")));
		model.addAttribute("pageInfo",pageInfo);
		return "sale/saleManager";	
	}
	
	//��ѯ����״̬
	@RequestMapping("/findSalChanceStatus")
	@ResponseBody
	public int findSalChanceStatus(Integer chcId) {
		return salService.findSalStatus(chcId);
	}
	
	//�޸Ŀ���״̬
	@RequestMapping("/updateChcStatus")
	@ResponseBody
	public boolean updateChcStatus(Integer chcId,Integer chcStatus) {
		SalChance salChance = new SalChance();
		salChance.setChcStatus(chcStatus);
		salChance.setChcId(chcId);
		return salService.updateSalChance(salChance);
	}
	
	//���ؿ���״̬��chcId
	@RequestMapping("/findStatusChcId")
	@ResponseBody
	public String findStatusChcId(Integer chcId,Integer chcStatus) {
		String a = chcId.toString()+salService.findSalStatus(chcId);
		return a;
	}
	
	
}
