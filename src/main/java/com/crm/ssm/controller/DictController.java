package com.crm.ssm.controller;

/**
 * 数据字典管理
 * 
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crm.ssm.entity.BaseDict;
import com.crm.ssm.service.BaseDictService;

@Controller
@RequestMapping("/dict")
public class DictController {

	//调用业务层
	@Autowired
	private BaseDictService baseDictService;
	
	//查询所有数据字典管理
	@RequestMapping("/findDict")
	public String findBaseDicts(Model model) {
		List<BaseDict> dicts = baseDictService.findBaseDict();
		model.addAttribute("dicts", dicts);
		return "manager/directionData";
	}
	
	//点击查询按钮【directionData.html】[当查询条件都为空时查询所有]
	@PostMapping("/findDictLike")
	public String findBaseDictLike(Model model,String dictType,String dictItem,String dictValue) {
		List<BaseDict> dicts = baseDictService.findBaseDictLike(dictType,dictItem,dictValue);
		model.addAttribute("dicts", dicts);
		return "manager/directionData";
	}
	
	
	//新建数据字典管理信息
	@RequestMapping("/insertDict")
	@ResponseBody
	public int insertBaseDict(String dictType,String dictItem,String dictValue,Integer dictIsEditable) {	
		BaseDict baseDict = new BaseDict();
		baseDict.setDictType(dictType);
		baseDict.setDictItem(dictItem);
		baseDict.setDictValue(dictValue);
		baseDict.setDictIsEditable(dictIsEditable);
		int insertNum = baseDictService.insertBaseDict(baseDict);
		return insertNum;
	}
	
	//根据dictId查询数据字典管理
	@RequestMapping("/findDictById")
	public String  findBDictById(Model model,Integer dictId) {
		BaseDict dict = baseDictService.findBaseDictById(dictId);
		model.addAttribute("dict", dict);
		return "manager/dataEdit";
	}
	
	//修改数据字典管理信息
	@RequestMapping("/updateDict")
	@ResponseBody
	public int updateBaseDict(Integer dictId,String dictType,String dictItem,String dictValue,Integer dictIsEditable) {	
		BaseDict baseDict = new BaseDict();
		baseDict.setDictType(dictType);
		baseDict.setDictItem(dictItem);
		baseDict.setDictValue(dictValue);
		baseDict.setDictIsEditable(dictIsEditable);
		baseDict.setDictId(dictId);
		int updateNum = baseDictService.updateBaseDictById(baseDict);
		return updateNum;
	}
	
	//根据dictId删除数据字典管理信息
	@RequestMapping("/deleteDict")
	@ResponseBody
	public int deleteBaseDict(Integer dictId) {
		return baseDictService.deleteBaseDictById(dictId);
	}
	
	
}
