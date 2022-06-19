package com.crm.ssm.controller;

import java.math.BigDecimal;

/**
 * 订单信息
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

	//调用业务层
	@Autowired
	private OrdersService ordersService;
	
	//调用业务层
	@Autowired
	private OrdersLineService ordersLineService;
	
	//根据客户编号查询订单信息
	@RequestMapping("/findOrdByName")
	public String findOrdersByName(String custNo,String custName,Model model,Integer pageNow) {
		pageNow = pageNow==null? 1 :pageNow;
		int pageSize = 3;
		//开启分页设置【必须要写在查询结果集之前】
		PageHelper.startPage(pageNow,pageSize);
		//分页
		PageInfo<Orders> pageInfo = new PageInfo<Orders>(ordersService.findAllOrdByName(custName));
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("custNo", custNo);
		return "customer/orderPage";	
	}
	
	//格局订单odrId查询订单详情以及算出总金额
	@RequestMapping("/findOrdLineByOdrId")
	public String findOrdersLineByOdrId(Model model,int odrId) {
		Orders ord = ordersService.findOrdersById(odrId);
		model.addAttribute("ord", ord);
		List<OrdersLine> ordLine = ordersLineService.findOrdersLineByOdrId(odrId);
		model.addAttribute("ordLine", ordLine);
		BigDecimal total = new BigDecimal(0);//总金额
		for (int i = 0; i < ordLine.size(); i++) {//循环计算得到总价
			BigDecimal number = new BigDecimal(ordLine.get(i).getOddCount());
			BigDecimal num = number.multiply(ordLine.get(i).getOddPrice());
			total = total.add(num);
		}
		model.addAttribute("total", total);
		return "customer/orderDetail";
	}
	
}
