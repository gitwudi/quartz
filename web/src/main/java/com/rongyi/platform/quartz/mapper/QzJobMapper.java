package com.rongyi.platform.quartz.mapper;

import java.util.List;

import com.rongyi.platform.quartz.module.dto.QzJobDto;
import com.rongyi.platform.quartz.module.model.QzJob;

public interface QzJobMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(QzJob record);

	int insertSelective(QzJob record);

	QzJob selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(QzJob record);

	int updateByPrimaryKey(QzJob record);

	List<QzJob> list(QzJobDto qzJobDto);

	QzJob find(QzJobDto qzJobDto);
}