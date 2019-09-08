package com.wuzhen.village.service;

import com.wuzhen.village.domain.VillageUserDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xxx
 * @email xxx@163.com
 * @date 2019-09-08 16:53:57
 */
public interface VillageUserService {
	
	VillageUserDO get(Integer id);
	
	List<VillageUserDO> list();
	
	int count(Map<String, Object> map);
	
	int save(VillageUserDO user);
	
	int update(VillageUserDO user);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
