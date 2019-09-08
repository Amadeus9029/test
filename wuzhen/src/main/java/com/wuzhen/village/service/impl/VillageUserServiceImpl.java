package com.wuzhen.village.service.impl;

import com.wuzhen.village.dao.VillageUserDao;
import com.wuzhen.village.domain.VillageUserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wuzhen.village.service.VillageUserService;



@Service
public class VillageUserServiceImpl implements VillageUserService {
	@Autowired
	private VillageUserDao villageUserDao;
	
	@Override
	public VillageUserDO get(Integer id){
		return villageUserDao.get(id);
	}
	
	@Override
	public List<VillageUserDO> list(){
		return villageUserDao.list();
	}
	
	@Override
	public int count(Map<String, Object> map){
		return villageUserDao.count(map);
	}
	
	@Override
	public int save(VillageUserDO user){
		return villageUserDao.save(user);
	}
	
	@Override
	public int update(VillageUserDO user){
		return villageUserDao.update(user);
	}
	
	@Override
	public int remove(Integer id){
		return villageUserDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return villageUserDao.batchRemove(ids);
	}
	
}
