package com.crm.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.ssm.entity.Storage;
import com.crm.ssm.service.StorageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * ��Ʒ���
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/sto")
public class StorageController {

	//����ҵ���
	@Autowired
	private  StorageService storageService;
	
	//��ѯ���в�Ʒ�����Ϣ
	@RequestMapping("/findSto")
	public String findStorages(HttpSession session,Model model,Integer pageNow) {
		//�����ѯ����ʱ��ģ����ѯ��sessionȫ�����
		session.removeAttribute("prodName");
		session.removeAttribute("stkWarehouse");
		session.removeAttribute("pageNowLike");
		pageNow = pageNow==null?1:pageNow;
		int pageSize = 3;
		//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
		PageHelper.startPage(pageNow,pageSize);
		//��ҳ
		PageInfo<Storage> pageInfo = new PageInfo<Storage>(storageService.findStorageLike(null,null));
		model.addAttribute("pageInfo",pageInfo);
		return "manager/departSelect";
	}
	
	//ģ����ѯ��Ʒ��桾�����ѯ��ť��
	@PostMapping("/findStoLike")
	public String findProductLike(HttpSession session,Model model,Integer pageNow,String prodName,String stkWarehouse) {
		if(prodName==null && stkWarehouse==null) {
		//�жϵ��ģ����ѯ��ʱ���ѯ�����Ƿ�Ϊ��
			return "redirect:findSto";
		}else {
		//��ѯ������Ϊ�յ�ʱ��
			session.setAttribute("prodName",prodName);
			session.setAttribute("prodType",stkWarehouse);
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
			PageHelper.startPage(pageNow,pageSize);
			//��ҳ
			PageInfo<Storage> pageInfo = new PageInfo<Storage>(storageService.findStorageLike(prodName, stkWarehouse));
			model.addAttribute("pageInfo",pageInfo);
			return "manager/departSelect";
		}
	}
	//ģ����ѯ(ҳ��:departSelect.html)��ҳ������ı�ҳ���İ�ť(�����һҳ����һҳ�Ȱ�ť)��
	@RequestMapping("/storage")
	public String findStorageByPageLike(HttpSession session,Model model,Integer pageNow) {
		if(session.getAttribute("prodName")==null && session.getAttribute("stkWarehouse")==null) {
		//�ж��Ƿ���ģ����ѯ����
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
			PageHelper.startPage(pageNow,pageSize);
			//��ҳ
			PageInfo<Storage> pageInfo = new PageInfo<Storage>(storageService.findStorageLike(null,null));
			model.addAttribute("pageInfo",pageInfo);
			return "manager/departSelect";
		}else {
		//�����ģ����ѯ����������pageNow(��ǰģ����ѯҳ��),��ת��/sale/chanceLike������ȥ
			session.setAttribute("pageNowLike", pageNow);
			return "redirect:/sto/storageLike";
		}
	}
	//������ı�ҳ���İ�ť��(ҳ��:departSelect.html)
	@RequestMapping("/storageLike")
	public String likePageProductSelect(HttpSession session,Model model,Integer pageNowLike) {
		//�õ�session�е�ģ����ѯ�����Լ���ǰģ����ѯҳ��
		String prodName = (String)session.getAttribute("prodName");
		String stkWarehouse = (String)session.getAttribute("stkWarehouse");
		//ģ����ѯ�����ҳ�����������ʱ�����
		pageNowLike = (Integer) session.getAttribute("pageNowLike");
		int pageSize = 3;
		//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
		PageHelper.startPage(pageNowLike,pageSize);
		//��ҳ
		PageInfo<Storage> pageInfo = new PageInfo<Storage>(storageService.findStorageLike(prodName, stkWarehouse));
		model.addAttribute("pageInfo",pageInfo);
		return "manager/departSelect";	
	}
	
}
