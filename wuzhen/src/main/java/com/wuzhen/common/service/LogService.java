package com.wuzhen.common.service;

import org.springframework.stereotype.Service;

import com.wuzhen.common.domain.LogDO;
import com.wuzhen.common.domain.PageDO;
import com.wuzhen.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
