package com.crm.ssm.controller;

import java.math.BigDecimal;

/**
 * ������Ϣ
 * 
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crm.ssm.entity.Orders;
import com.crm.ssm.entity.OrdersLine;
import com.crm.ssm.service.OrdersLineService;
import com.crm.ssm.service.OrdersService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/ord")
public class OrdersController {

	//����ҵ���
	@Autowired
	private OrdersService ordersService;
	
	//����ҵ���
	@Autowired
	private OrdersLineService ordersLineService;
	
	//���ݿͻ���Ų�ѯ������Ϣ
	@RequestMapping("/findOrdByName")
	public String findOrdersByName(String custNo,String custName,Model model,Integer pageNow) {
		pageNow = pageNow==null? 1 :pageNow;
		int pageSize = 3;
		//������ҳ���á�����Ҫд�ڲ�ѯ�����֮ǰ��
		PageHelper.startPage(pageNow,pageSize);
		//��ҳ
		PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersService.findAllOrdByName(custName));
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("custNo", custNo);
		return "customer/orderPage";	
	}
	
	//��ֶ���odrId��ѯ���������Լ�����ܽ��
	@RequestMapping("/findOrdLineByOdrId")
	public String findOrdersLineByOdrId(Model model,int odrId) {
		Orders ord = ordersService.findOrdersById(odrId);
		model.addAttribute("ord", ord);
		List<OrdersLine> ordLine = ordersLineService.findOrdersLineByOdrId(odrId);
		model.addAttribute("ordLine", ordLine);
		BigDecimal total = new BigDecimal(0);//�ܽ��
		for (int i = 0; i < ordLine.size(); i++) {//ѭ������õ��ܼ�
			BigDecimal number = new BigDecimal(ordLine.get(i).getOddCount());
			BigDecimal num = number.multiply(ordLine.get(i).getOddPrice());
			total = total.add(num);
		}
		model.addAttribute("total", total);
		return "customer/orderDetail";
	}
	
}
