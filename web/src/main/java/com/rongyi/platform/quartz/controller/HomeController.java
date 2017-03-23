package com.rongyi.platform.quartz.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rongyi.platform.quartz.biz.QzJobBiz;
import com.rongyi.platform.quartz.module.dto.QzJobDto;
import com.rongyi.platform.quartz.module.vo.ResultVo;

@Controller
@RequestMapping("/home")
public class HomeController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Resource
	private QzJobBiz qzJobBiz;

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void list(QzJobDto qzJobDto, ModelMap model) {
		model.addAttribute(qzJobBiz.list(qzJobDto));
	}

	@ResponseBody
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public ResultVo delete(QzJobDto qzJobDto) {
		try {
			qzJobBiz.delete(qzJobDto);
		} catch (Exception e) {
			log.error("删除失败, qzJob = " + qzJobDto.toString(), e);
		}
		return ResultVo.success();
	}

	@ResponseBody
	@RequestMapping(value = "pause", method = RequestMethod.POST)
	public ResultVo pause(QzJobDto qzJobDto) {
		try {
			qzJobBiz.pause(qzJobDto);
		} catch (Exception e) {
			log.error("暂停失败, qzJob = " + qzJobDto.toString(), e);
		}
		return ResultVo.success();
	}

	@ResponseBody
	@RequestMapping(value = "resume", method = RequestMethod.POST)
	public ResultVo resume(QzJobDto qzJobDto) {
		try {
			qzJobBiz.resume(qzJobDto);
		} catch (Exception e) {
			log.error("恢复失败, qzJob = " + qzJobDto.toString(), e);
		}
		return ResultVo.success();
	}
}
