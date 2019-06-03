package com.daichao.dao.banner;

import com.daichao.bean.banner.DcBanner;

import java.util.List;
import java.util.Map;

public interface DcBannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DcBanner record);

    int insertSelective(DcBanner record);

    DcBanner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DcBanner record);

    int updateByPrimaryKey(DcBanner record);

    List<DcBanner> queryBannerList(Map<String, Object> map);

    List<DcBanner> selectIndexBanner();
}