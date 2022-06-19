package com.crm.ssm.controller;
/**
 * 产品
 * 
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.ssm.entity.Product;
import com.crm.ssm.service.ProductService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/prod")
public class ProductController {

	//调用业务层
	@Autowired
	private  ProductService productService;
	
	//查询所有产品信息
	@RequestMapping("/findProd")
	public String findProducts(HttpSession session,Model model,Integer pageNow) {
		//点击查询产品信息的时候将模糊查询的session全部清空
		session.removeAttribute("prodName");
		session.removeAttribute("prodType");
		session.removeAttribute("prodBatch");
		session.removeAttribute("pageNowLike");
		pageNow = pageNow==null?1:pageNow;
		int pageSize = 3;
		//开启分页设置【必须要写在查询结果集之前】
		PageHelper.startPage(pageNow,pageSize);
		//分页
		PageInfo<Product> pageInfo = new PageInfo<Product>(productService.findProduct());
		model.addAttribute("pageInfo",pageInfo);
		return "manager/productSelect";
	}
	
	//模糊查询产品
	@PostMapping("/findProdLike")
	public String findProductLike(HttpSession session,Model model,Integer pageNow,String prodName,String prodType,String prodBatch) {
		if(prodName==null && prodType==null && prodBatch==null ) {
		//判断点击模糊查询的时候查询条件是否为空
			return "redirect:findProd";
		}else {
		//查询条件不为空的时候
			session.setAttribute("prodName",prodName);
			session.setAttribute("prodType",prodType);
			session.setAttribute("prodBatch",prodBatch);
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//开启分页设置【必须要写在查询结果集之前】
			PageHelper.startPage(pageNow,pageSize);
			//分页
			PageInfo<Product> pageInfo = new PageInfo<Product>(productService.findProductLikes(prodName, prodType, prodBatch));
			model.addAttribute("pageInfo",pageInfo);
			return "manager/productSelect";
		}
	}
	//模糊查询(页面:productSelect.html)分页【点击改变页数的按钮(点击上一页、下一页等按钮)】
	@RequestMapping("/product")
	public String findProductByPageLike(HttpSession session,Model model,Integer pageNow) {
		if(session.getAttribute("prodName")==null && session.getAttribute("prodType")==null && session.getAttribute("prodBatch")==null) {
		//判断是否有模糊查询条件
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//开启分页设置【必须要写在查询结果集之前】
			PageHelper.startPage(pageNow,pageSize);
			//分页
			PageInfo<Product> pageInfo = new PageInfo<Product>(productService.findProduct());
			model.addAttribute("pageInfo",pageInfo);
			return "manager/productSelect";
		}else {
		//如果有模糊查询条件，保存pageNow(当前模糊查询页数),跳转到/sale/chanceLike方法中去
			session.setAttribute("pageNowLike", pageNow);
			return "redirect:/prod/productLike";
		}
	}
	//【点击改变页数的按钮】(页面:productSelect.html)
	@RequestMapping("/productLike")
	public String likePageProductSelect(HttpSession session,Model model,Integer pageNowLike) {
		//得到session中的模糊查询条件以及当前模糊查询页数
		String prodName = (String)session.getAttribute("prodName");
		String prodType = (String)session.getAttribute("prodType");
		String prodBatch = (String)session.getAttribute("prodBatch");
		//模糊查询后进行页数操作点击的时候进入
		pageNowLike = (Integer) session.getAttribute("pageNowLike");
		int pageSize = 3;
		//开启分页设置【必须要写在查询结果集之前】
		PageHelper.startPage(pageNowLike,pageSize);
		//分页
		PageInfo<Product> pageInfo = new PageInfo<Product>(productService.findProductLikes(prodName, prodType, prodBatch));
		model.addAttribute("pageInfo",pageInfo);
		return "manager/productSelect";	
	}
}
