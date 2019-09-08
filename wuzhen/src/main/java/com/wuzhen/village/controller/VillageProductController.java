package com.wuzhen.village.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wuzhen.village.domain.VillageProductDO;
import com.wuzhen.village.service.VillageProductService;
import com.wuzhen.common.utils.PageUtils;
import com.wuzhen.common.utils.Query;
import com.wuzhen.common.utils.R;

/**
 * 
 * 
 * @author xxx
 * @email xxx@163.com
 * @date 2019-09-08 16:53:57
 */
 
@Controller
@RequestMapping("/village/product")
public class VillageProductController {
	@Autowired
	private VillageProductService villageProductService;
	
	@GetMapping()
	@RequiresPermissions("village:product:product")
	String Product(){
	    return "village/product/product";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("village:product:product")
	public List<VillageProductDO> list(){
		List<VillageProductDO> productList = villageProductService.list();
		return productList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("village:product:add")
	String add(){
	    return "village/product/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("village:product:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		VillageProductDO product = villageProductService.get(id);
		model.addAttribute("product", product);
	    return "village/product/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("village:product:add")
	public R save( VillageProductDO product){
		if(villageProductService.save(product)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("village:product:edit")
	public R update( VillageProductDO product){
		villageProductService.update(product);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("village:product:remove")
	public R remove( Integer id){
		if(villageProductService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("village:product:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		villageProductService.batchRemove(ids);
		return R.ok();
	}
	
}
