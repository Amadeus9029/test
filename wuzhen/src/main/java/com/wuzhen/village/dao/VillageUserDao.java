package com.wuzhen.village.dao;

import com.wuzhen.village.domain.VillageUserDO;

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
public interface VillageUserDao {

	VillageUserDO get(Integer id);
	
	List<VillageUserDO> list();
	
	int count(Map<String,Object> map);
	
	int save(VillageUserDO user);
	
	int update(VillageUserDO user);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
