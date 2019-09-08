package com.wuzhen.village.service;

import com.wuzhen.village.domain.VillageProductDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xxx
 * @email xxx@163.com
 * @date 2019-09-08 16:53:57
 */
public interface VillageProductService {
	
	VillageProductDO get(Integer id);
	
	List<VillageProductDO> list();
	
	int count(Map<String, Object> map);
	
	int save(VillageProductDO product);
	
	int update(VillageProductDO product);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
