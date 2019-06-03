package com.daichao.service.impl;

import com.daichao.bean.product.DcThirdProductTag;
import com.daichao.bean.product.DcThirdTag;
import com.daichao.dao.product.DcThirdProductTagMapper;
import com.daichao.dao.product.DcThirdTagMapper;
import com.daichao.service.DcThirdTagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DcThirdTagServiceImpl implements DcThirdTagService {

    @Resource
    private DcThirdTagMapper dcThirdTagMapper;
    @Autowired
    private DcThirdProductTagMapper dcThirdProductTagMapper;

    @Override
    public List<DcThirdTag> selectIndexThirdTag() {
        return dcThirdTagMapper.selectIndexThirdTag();
    }

	@Override
	public List<DcThirdTag> indexTagList() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("status", 1);
		List<DcThirdTag> taglist=dcThirdTagMapper.queryDcThirdTagList(map);
		if(!CollectionUtils.isEmpty(taglist)) {
			for (int i = 0; i < taglist.size(); i++) {
				DcThirdTag dcThirdTag=taglist.get(i);
				map.put("tagId", dcThirdTag.getId());
				String productIds="";
				List<DcThirdProductTag> productTagList= dcThirdProductTagMapper.queryProductTagList(map);
				if(!CollectionUtils.isEmpty(productTagList)) {
					for (DcThirdProductTag tags : productTagList) {
						if(tags.getStatus()==1) {
							productIds=productIds+tags.getProductId()+",";
						}
					}
				}else {
					productIds="0";
				}
				dcThirdTag.setProductIds(productIds);
			}
		}
		return taglist;
	}
}
