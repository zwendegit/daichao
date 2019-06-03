package com.daichao.admin.service;

import java.util.List;

import com.daichao.admin.input.channel.ChannelPageInput;
import com.daichao.admin.input.channel.ProductConfigInput;
import com.daichao.bean.channel.DcChannel;
import com.daichao.bean.output.ResultOutput;

public interface ChannelServie {

	List<DcChannel> queryChannelList(ChannelPageInput input);
	
	ResultOutput channelEdit(DcChannel channel);
	
	ResultOutput channelConfigDetail(Integer channelId);
	
	ResultOutput channelConfigSave(ProductConfigInput config,Integer userId);
	
	List<String> queryChannelCodeList(Integer status);
	
	ResultOutput channelUrlStatusUpdate(Integer status,Integer id);
}
