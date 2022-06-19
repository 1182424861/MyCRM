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
 * 产品库存
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/sto")
public class StorageController {

	//调用业务层
	@Autowired
	private  StorageService storageService;
	
	//查询所有产品库存信息
	@RequestMapping("/findSto")
	public String findStorages(HttpSession session,Model model,Integer pageNow) {
		//点击查询库存的时候将模糊查询的session全部清空
		session.removeAttribute("prodName");
		session.removeAttribute("stkWarehouse");
		session.removeAttribute("pageNowLike");
		pageNow = pageNow==null?1:pageNow;
		int pageSize = 3;
		//开启分页设置【必须要写在查询结果集之前】
		PageHelper.startPage(pageNow,pageSize);
		//分页
		PageInfo<Storage> pageInfo = new PageInfo<Storage>(storageService.findStorageLike(null,null));
		model.addAttribute("pageInfo",pageInfo);
		return "manager/departSelect";
	}
	
	//模糊查询产品库存【点击查询按钮】
	@PostMapping("/findStoLike")
	public String findProductLike(HttpSession session,Model model,Integer pageNow,String prodName,String stkWarehouse) {
		if(prodName==null && stkWarehouse==null) {
		//判断点击模糊查询的时候查询条件是否为空
			return "redirect:findSto";
		}else {
		//查询条件不为空的时候
			session.setAttribute("prodName",prodName);
			session.setAttribute("prodType",stkWarehouse);
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//开启分页设置【必须要写在查询结果集之前】
			PageHelper.startPage(pageNow,pageSize);
			//分页
			PageInfo<Storage> pageInfo = new PageInfo<Storage>(storageService.findStorageLike(prodName, stkWarehouse));
			model.addAttribute("pageInfo",pageInfo);
			return "manager/departSelect";
		}
	}
	//模糊查询(页面:departSelect.html)分页【点击改变页数的按钮(点击上一页、下一页等按钮)】
	@RequestMapping("/storage")
	public String findStorageByPageLike(HttpSession session,Model model,Integer pageNow) {
		if(session.getAttribute("prodName")==null && session.getAttribute("stkWarehouse")==null) {
		//判断是否有模糊查询条件
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//开启分页设置【必须要写在查询结果集之前】
			PageHelper.startPage(pageNow,pageSize);
			//分页
			PageInfo<Storage> pageInfo = new PageInfo<Storage>(storageService.findStorageLike(null,null));
			model.addAttribute("pageInfo",pageInfo);
			return "manager/departSelect";
		}else {
		//如果有模糊查询条件，保存pageNow(当前模糊查询页数),跳转到/sale/chanceLike方法中去
			session.setAttribute("pageNowLike", pageNow);
			return "redirect:/sto/storageLike";
		}
	}
	//【点击改变页数的按钮】(页面:departSelect.html)
	@RequestMapping("/storageLike")
	public String likePageProductSelect(HttpSession session,Model model,Integer pageNowLike) {
		//得到session中的模糊查询条件以及当前模糊查询页数
		String prodName = (String)session.getAttribute("prodName");
		String stkWarehouse = (String)session.getAttribute("stkWarehouse");
		//模糊查询后进行页数操作点击的时候进入
		pageNowLike = (Integer) session.getAttribute("pageNowLike");
		int pageSize = 3;
		//开启分页设置【必须要写在查询结果集之前】
		PageHelper.startPage(pageNowLike,pageSize);
		//分页
		PageInfo<Storage> pageInfo = new PageInfo<Storage>(storageService.findStorageLike(prodName, stkWarehouse));
		model.addAttribute("pageInfo",pageInfo);
		return "manager/departSelect";	
	}
	
}
