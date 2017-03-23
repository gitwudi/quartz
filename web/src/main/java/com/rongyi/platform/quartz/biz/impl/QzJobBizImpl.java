package com.rongyi.platform.quartz.biz.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rongyi.platform.quartz.biz.JobBiz;
import com.rongyi.platform.quartz.biz.QzJobBiz;
import com.rongyi.platform.quartz.mapper.QzJobMapper;
import com.rongyi.platform.quartz.module.cont.QzJobConst;
import com.rongyi.platform.quartz.module.dto.QzJobDto;
import com.rongyi.platform.quartz.module.exception.BizException;
import com.rongyi.platform.quartz.module.model.QzJob;

@Service
public class QzJobBizImpl implements QzJobBiz {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QzJobMapper qzJobMapper;

	@Autowired
	private JobBiz jobBiz;

	@Override
	public List<QzJob> list(QzJobDto qzJobDto) {
		return qzJobMapper.list(qzJobDto);
	}

	@Override
	public QzJob find(Integer id) {
		return this.find(new QzJobDto(id));
	}

	public QzJob find(QzJobDto qzJobDto) {
		return qzJobMapper.find(qzJobDto);
	}

	@Override
	public void add(QzJob qzJob) {
		jobBiz.add(qzJob);
	}

	@Override
	public void pause(QzJobDto qzJobDto) {
		QzJob qzJob = qzJobMapper.find(qzJobDto);
		if (null == qzJob || !QzJobConst.STATUS_ENBALE.equals(qzJob.getStatus())) {
			throw new BizException("任务不存在");
		}
		this.updateStatus(qzJob.getId(), QzJobConst.STATUS_DISABALE);

		jobBiz.pause(qzJob);
		log.info("==========暂停任务成功========   qzJob - {}", qzJob);
	}

	@Override
	public void delete(QzJobDto qzJobDto) {
		QzJob qzJob = qzJobMapper.find(qzJobDto);
		if (null == qzJob) {
			throw new BizException("任务不存在");
		}
		jobBiz.delete(qzJob);
		log.info("==========删除任务成功========   qzJob - {}", qzJob);
	}

	@Override
	public void resume(QzJobDto qzJobDto) {
		QzJob qzJob = qzJobMapper.find(qzJobDto);
		if (null == qzJob || !QzJobConst.STATUS_DISABALE.equals(qzJob.getStatus())) {
			throw new BizException("任务不存在");
		}
		this.updateStatus(qzJob.getId(), QzJobConst.STATUS_ENBALE);

		jobBiz.resume(qzJob);
		log.info("==========恢复任务成功========   qzJob - {}", qzJob);
	}

	private void updateStatus(Integer id, Integer status) {
		QzJob qzJob = new QzJob();
		qzJob.setId(id);
		qzJob.setStatus(status);
		qzJobMapper.updateByPrimaryKeySelective(qzJob);
	}
}
