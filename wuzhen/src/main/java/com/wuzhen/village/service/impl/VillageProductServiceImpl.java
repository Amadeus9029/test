package com.wuzhen.village.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wuzhen.village.dao.VillageProductDao;
import com.wuzhen.village.domain.VillageProductDO;
import com.wuzhen.village.service.VillageProductService;



@Service
public class VillageProductServiceImpl implements VillageProductService {
	@Autowired
	private VillageProductDao villageProductDao;
	
	@Override
	public VillageProductDO get(Integer id){
		return villageProductDao.get(id);
	}
	
	@Override
	public List<VillageProductDO> list(){
		return villageProductDao.list();
	}
	
	@Override
	public int count(Map<String, Object> map){
		return villageProductDao.count(map);
	}
	
	@Override
	public int save(VillageProductDO product){
		return villageProductDao.save(product);
	}
	
	@Override
	public int update(VillageProductDO product){
		return villageProductDao.update(product);
	}
	
	@Override
	public int remove(Integer id){
		return villageProductDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return villageProductDao.batchRemove(ids);
	}
	
}
