package com.wuzhen.village.controller;

import java.util.List;
import java.util.Map;

import com.wuzhen.village.domain.VillageUserDO;
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

import com.wuzhen.village.service.VillageUserService;
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
@RequestMapping("/village/user")
public class VillageUserController {
	@Autowired
	private VillageUserService userService;
	
	@GetMapping()
	@RequiresPermissions("village:user:user")
	String User(){
	    return "village/user/user";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("village:user:user")
	public List<VillageUserDO> list(){
		List<VillageUserDO> userList=userService.list();
		return userList;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("village:user:add")
	String add(){
	    return "village/user/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("village:user:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		VillageUserDO user = userService.get(id);
		model.addAttribute("user", user);
	    return "village/user/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("village:user:add")
	public R save( VillageUserDO user){
		if(userService.save(user)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("village:user:edit")
	public R update( VillageUserDO user){
		userService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("village:user:remove")
	public R remove( Integer id){
		if(userService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("village:user:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		userService.batchRemove(ids);
		return R.ok();
	}
	
}
