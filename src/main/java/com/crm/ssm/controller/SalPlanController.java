package com.crm.ssm.controller;

/**
 * 客户开发计划
 * 
 */

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.ssm.entity.SalChance;
import com.crm.ssm.entity.SalPlan;
import com.crm.ssm.service.SalChanceService;
import com.crm.ssm.service.SalPlanService;

@Controller
@RequestMapping("/plan")
public class SalPlanController {

	//调用业务层
	@Autowired
	private SalChanceService salChanceService;
	
	@Autowired
	private SalPlanService salPlanService;
	
	//查询销售机会和销售计划【】
	@RequestMapping("/findSal")
	public String findSalById(Model model,int chcId) {
		SalChance salChance = salChanceService.findSalChanceById(chcId);
		model.addAttribute("salChance",salChance);
		List<SalPlan> salPlan = salPlanService.findSalPlanById(chcId);
		model.addAttribute("salPlan",salPlan);
		return "sale/setPlay";
	}
	
	//编辑计划【修改计划】
	@RequestMapping("/updateSalPlan")
	@ResponseBody
	public boolean updateSalPlan(int plaId,String plaTodo) {
		SalPlan salPlan = new SalPlan();
		salPlan.setPlaTodo(plaTodo);
		salPlan.setPlaId(plaId);
		return salPlanService.updateSalPlanTodo(salPlan);
	}
	
	//删除计划【删除计划】
	@RequestMapping("/deleteSalPlan")
	@ResponseBody
	public boolean deleteSalPlan(int plaId) {
		return salPlanService.deleteSalPlan(plaId);
	}
	
	//添加计划【添加计划】
	@RequestMapping("/addSalPlan")
	public String addSalPlan(Integer plaChcId,Date plaDate,String plaTodo) {
		SalPlan salPlan = new SalPlan();
		salPlan.setPlaChcId(plaChcId);
		salPlan.setPlaDate(plaDate);
		salPlan.setPlaTodo(plaTodo);
		salPlanService.addSalPlan(salPlan);
		return "redirect:/sale/chancePage01";
	}
	
	//查询销售机会和销售计划【执行计划】
	@RequestMapping("/findSalPlan")
	public String findSalPlan(Model model,int chcId) {
		SalChance salChance = salChanceService.findSalChanceById(chcId);
		model.addAttribute("salChance",salChance);
		List<SalPlan> salPlan = salPlanService.findSalPlanById(chcId);
		model.addAttribute("salPlan",salPlan);
		return "sale/execPlay";
	}
	
	//编辑执行效果【修改执行结果】
	@RequestMapping("/updateSalPlanResult")
	@ResponseBody
	public boolean updateSalPlanResult(int plaId,String plaResult) {
		SalPlan salPlan = new SalPlan();
		salPlan.setPlaResult(plaResult);
		salPlan.setPlaId(plaId);
		return salPlanService.updateSalPlanTodo(salPlan);
	}
	
	
	
}
