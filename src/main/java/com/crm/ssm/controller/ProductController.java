package com.crm.ssm.controller;
/**
 * ��Ʒ
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

	//����ҵ���
	@Autowired
	private  ProductService productService;
	
	//��ѯ���в�Ʒ��Ϣ
	@RequestMapping("/findProd")
	public String findProducts(HttpSession session,Model model,Integer pageNow) {
		//�����ѯ��Ʒ��Ϣ��ʱ��ģ����ѯ��sessionȫ�����
		session.removeAttribute("prodName");
		session.removeAttribute("prodType");
		session.removeAttribute("prodBatch");
		session.removeAttribute("pageNowLike");
		pageNow = pageNow==null?1:pageNow;
		int pageSize = 3;
		//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
		PageHelper.startPage(pageNow,pageSize);
		//��ҳ
		PageInfo<Product> pageInfo = new PageInfo<Product>(productService.findProduct());
		model.addAttribute("pageInfo",pageInfo);
		return "manager/productSelect";
	}
	
	//ģ����ѯ��Ʒ
	@PostMapping("/findProdLike")
	public String findProductLike(HttpSession session,Model model,Integer pageNow,String prodName,String prodType,String prodBatch) {
		if(prodName==null && prodType==null && prodBatch==null ) {
		//�жϵ��ģ����ѯ��ʱ���ѯ�����Ƿ�Ϊ��
			return "redirect:findProd";
		}else {
		//��ѯ������Ϊ�յ�ʱ��
			session.setAttribute("prodName",prodName);
			session.setAttribute("prodType",prodType);
			session.setAttribute("prodBatch",prodBatch);
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
			PageHelper.startPage(pageNow,pageSize);
			//��ҳ
			PageInfo<Product> pageInfo = new PageInfo<Product>(productService.findProductLikes(prodName, prodType, prodBatch));
			model.addAttribute("pageInfo",pageInfo);
			return "manager/productSelect";
		}
	}
	//ģ����ѯ(ҳ��:productSelect.html)��ҳ������ı�ҳ���İ�ť(�����һҳ����һҳ�Ȱ�ť)��
	@RequestMapping("/product")
	public String findProductByPageLike(HttpSession session,Model model,Integer pageNow) {
		if(session.getAttribute("prodName")==null && session.getAttribute("prodType")==null && session.getAttribute("prodBatch")==null) {
		//�ж��Ƿ���ģ����ѯ����
			pageNow = pageNow==null?1:pageNow;
			int pageSize = 3;
			//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
			PageHelper.startPage(pageNow,pageSize);
			//��ҳ
			PageInfo<Product> pageInfo = new PageInfo<Product>(productService.findProduct());
			model.addAttribute("pageInfo",pageInfo);
			return "manager/productSelect";
		}else {
		//�����ģ����ѯ����������pageNow(��ǰģ����ѯҳ��),��ת��/sale/chanceLike������ȥ
			session.setAttribute("pageNowLike", pageNow);
			return "redirect:/prod/productLike";
		}
	}
	//������ı�ҳ���İ�ť��(ҳ��:productSelect.html)
	@RequestMapping("/productLike")
	public String likePageProductSelect(HttpSession session,Model model,Integer pageNowLike) {
		//�õ�session�е�ģ����ѯ�����Լ���ǰģ����ѯҳ��
		String prodName = (String)session.getAttribute("prodName");
		String prodType = (String)session.getAttribute("prodType");
		String prodBatch = (String)session.getAttribute("prodBatch");
		//ģ����ѯ�����ҳ�����������ʱ�����
		pageNowLike = (Integer) session.getAttribute("pageNowLike");
		int pageSize = 3;
		//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
		PageHelper.startPage(pageNowLike,pageSize);
		//��ҳ
		PageInfo<Product> pageInfo = new PageInfo<Product>(productService.findProductLikes(prodName, prodType, prodBatch));
		model.addAttribute("pageInfo",pageInfo);
		return "manager/productSelect";	
	}
}
