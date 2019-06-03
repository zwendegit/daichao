package com.daichao.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.daichao.admin.common.SimpleRedisCache;
import com.daichao.admin.input.advert.AdvertInput;
import com.daichao.admin.service.DcAdvertService;
import com.daichao.bean.advert.DcAdvert;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcThirdProduct;
import com.daichao.dao.advert.DcAdvertMapper;
import com.daichao.dao.product.DcThirdProductMapper;

@Service
public class DcAdvertServiceImpl implements DcAdvertService{

	@Autowired
	private DcAdvertMapper dcAdvertMapper;
	@Autowired
	private DcThirdProductMapper dcThirdProductMapper;
	
	@Autowired
	private SimpleRedisCache redis;
	@Override
	public List<DcAdvert> queryAdvertList(Integer location) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("location", location!=null?location:null);
		return dcAdvertMapper.queryAdvertList(map);
	}
	@Override
	public ResultOutput advertSave(AdvertInput input,Integer userId) {
		if(StringUtils.isNotEmpty(input.getProductIds())) {
			String ids[]=input.getProductIds().split(",");
			TreeSet<Integer> tree=new TreeSet<Integer>();
			if(ids!=null&&ids.length>0) {
				try {
					for (String string : ids) {
						tree.add(Integer.parseInt(string));
					}
				} catch (NumberFormatException e) {
					return new ResultOutput("1", "参数不合法");
				}
			}
			List<DcAdvert> advertList=dcAdvertMapper.queryAdvertByStatusAndLocation(input.getLocation());
			Iterator<Integer> it =tree.iterator();
			while(it.hasNext()){ {
				Integer id=it.next();
				boolean flag=true;
				if(!CollectionUtils.isEmpty(advertList)) {
					for (DcAdvert a : advertList) {
						if(id.equals(a.getProductId())) {
							flag=false;
							continue;
						}
					}
				}
				if(flag) {
					DcAdvert advert=new DcAdvert();
					advert.setProductId(id);
					advert.setLocation(input.getLocation());
					advert.setStatus(1);
					advert.setCreateId(userId);
					advert.setCreateTime(new Date());
					dcAdvertMapper.insertSelective(advert);
				}
			}
		}
	}
		return new ResultOutput();
	}
	@Override
	public ResultOutput advertUupate(Integer id,Integer userId) {
		DcAdvert advert=dcAdvertMapper.selectByPrimaryKey(id);
		if(advert==null) {
			return new ResultOutput("1", "此产品不存在");
		}
		if(advert.getStatus()!=0) {
			advert.setStatus(0);
			advert.setUpdateId(userId);
			advert.setUpdateTime(new Date());
			dcAdvertMapper.updateByPrimaryKeySelective(advert);
			if(!Objects.isNull(redis.get("indexProduct"))) {
				DcThirdProduct productRedis= (DcThirdProduct) redis.get("indexProduct");
				if(productRedis.getId().equals(advert.getProductId())) {
					redis.remove("indexProduct");
				}
			}
		}
		return new ResultOutput();
	}
	@Override
	public ResultOutput advertProductList(Integer location) {
		if(location==null) {
			return new ResultOutput("1", "参数不全");
		}
		return new ResultOutput(dcThirdProductMapper.getAdvertOtherProduct(location));
	}
}
