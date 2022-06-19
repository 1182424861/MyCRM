package com.crm.ssm.controller;

/**
 * �����ֵ����
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

	//����ҵ���
	@Autowired
	private BaseDictService baseDictService;
	
	//��ѯ���������ֵ����
	@RequestMapping("/findDict")
	public String findBaseDicts(Model model) {
		List<BaseDict> dicts = baseDictService.findBaseDict();
		model.addAttribute("dicts", dicts);
		return "manager/directionData";
	}
	
	//�����ѯ��ť��directionData.html��[����ѯ������Ϊ��ʱ��ѯ����]
	@PostMapping("/findDictLike")
	public String findBaseDictLike(Model model,String dictType,String dictItem,String dictValue) {
		List<BaseDict> dicts = baseDictService.findBaseDictLike(dictType,dictItem,dictValue);
		model.addAttribute("dicts", dicts);
		return "manager/directionData";
	}
	
	
	//�½������ֵ������Ϣ
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
	
	//����dictId��ѯ�����ֵ����
	@RequestMapping("/findDictById")
	public String  findBDictById(Model model,Integer dictId) {
		BaseDict dict = baseDictService.findBaseDictById(dictId);
		model.addAttribute("dict", dict);
		return "manager/dataEdit";
	}
	
	//�޸������ֵ������Ϣ
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
	
	//����dictIdɾ�������ֵ������Ϣ
	@RequestMapping("/deleteDict")
	@ResponseBody
	public int deleteBaseDict(Integer dictId) {
		return baseDictService.deleteBaseDictById(dictId);
	}
	
	
}
