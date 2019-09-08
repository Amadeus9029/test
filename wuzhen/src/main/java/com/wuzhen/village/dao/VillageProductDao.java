package com.wuzhen.village.dao;

import com.wuzhen.village.domain.VillageProductDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author xxx
 * @email xxx@163.com
 * @date 2019-09-08 16:53:57
 */
@Mapper
public interface VillageProductDao {

	VillageProductDO get(Integer id);
	
	List<VillageProductDO> list();
	
	int count(Map<String,Object> map);
	
	int save(VillageProductDO product);
	
	int update(VillageProductDO product);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
