package com.daichao.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.daichao.admin.service.AdminRoleMenuService;
import com.daichao.bean.admin.DcAdminMenu;
import com.daichao.bean.admin.DcAdminRoleMenu;
import com.daichao.bean.output.ResultOutput;
import com.daichao.dao.admin.DcAdminMenuMapper;
import com.daichao.dao.admin.DcAdminRoleMenuMapper;
@Service
public class AdminRoleMenuServiceImpl implements AdminRoleMenuService{

	@Autowired
	private DcAdminRoleMenuMapper dcAdminRoleMenuMapper;
	@Autowired
	private DcAdminMenuMapper dcAdminMenuMapper;
	@Override
	public List<DcAdminMenu> queryAdminRoleMenuListByRoleId(Integer roleId) {
		List<DcAdminMenu> listMenu=new ArrayList<>();
		List<DcAdminMenu> menuList=dcAdminMenuMapper.queryAdminMenuListByRoleId(roleId);
		if(!CollectionUtils.isEmpty(menuList)) {
			for (DcAdminMenu menu : menuList) {
				if(menu.getParentId()==0) {
					listMenu.add(menu);
				}
			}
			for (DcAdminMenu childMenu : listMenu) {
				List<DcAdminMenu> list=getChild(childMenu.getId(), menuList);
				childMenu.setList(list);
			}
			if(listMenu.size() == 0){
			      return new ArrayList<DcAdminMenu>();
			    }
		}
		return listMenu;
	}
	@Override
	public List<DcAdminMenu> adminMenuList() {
		List<DcAdminMenu> listMenu=new ArrayList<>();
		List<DcAdminMenu> menuList=dcAdminMenuMapper.queryAdminMenuList();
		if(!CollectionUtils.isEmpty(menuList)) {
			for (DcAdminMenu menu : menuList) {
				if(menu.getParentId()==0) {
					listMenu.add(menu);
				}
			}
			for (DcAdminMenu childMenu : listMenu) {
				List<DcAdminMenu> list=getChild(childMenu.getId(), menuList);
				childMenu.setList(list);
			}
			if(listMenu.size() == 0){
			      return new ArrayList<DcAdminMenu>();
			    }
		}
		return listMenu;
	}

	public static List<DcAdminMenu> getChild(Integer id,List<DcAdminMenu> allMenu){
	    //子菜单
	    List<DcAdminMenu> childList = new ArrayList<DcAdminMenu>();
	    for (DcAdminMenu nav : allMenu) {
	      // 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
	      //相等说明：为该根节点的子节点。
	      if(nav.getParentId().equals(id)){
	        childList.add(nav);
	      }
	    }
	    //递归
	    for (DcAdminMenu nav : childList) {
	      nav.setList(getChild(nav.getId(), allMenu));
	    }
	    //如果节点下没有子节点，返回一个空List（递归退出）
	    if(childList.size() == 0){
	      return new ArrayList<DcAdminMenu>();
	    }
	    return childList;
	  }
	@Override
	public List<DcAdminMenu> queryMenuListByUserId(Integer userId) {
		List<DcAdminMenu> listMenu=new ArrayList<>();
		List<DcAdminMenu> menuList=dcAdminMenuMapper.queryMenuListByUserId(userId);
		if(!CollectionUtils.isEmpty(menuList)) {
			for (DcAdminMenu menu : menuList) {
				if(menu.getParentId()==0) {
					listMenu.add(menu);
				}
			}
			for (DcAdminMenu childMenu : listMenu) {
				List<DcAdminMenu> list=getChild(childMenu.getId(), menuList);
				childMenu.setList(list);
			}
			if(listMenu.size() == 0){
			      return new ArrayList<DcAdminMenu>();
			    }
		}
		return listMenu;
	}
	@Override
	public ResultOutput menuSave(DcAdminMenu menu) {
		if(menu.getId()!=null&&menu.getId()>0) dcAdminMenuMapper.updateByPrimaryKeySelective(menu);
		else dcAdminMenuMapper.insertSelective(menu);
		return new ResultOutput();
	}
	@Override
	public ResultOutput adminRoleMenuUpdate(Integer roleId, String ids) {
		dcAdminRoleMenuMapper.deleteByRoleId(roleId);
		if(StringUtils.isNotEmpty(ids)) {
			String s[]=ids.split(",");
			if(s!=null&&s.length>0) {
				for (String string : s) {
					DcAdminRoleMenu menu=new DcAdminRoleMenu();
					menu.setRoleId(roleId);
					menu.setMenuId(Integer.parseInt(string));
					menu.setStatus(1);
					dcAdminRoleMenuMapper.insertSelective(menu);
				}
			}
		}
		return new ResultOutput();
	}
}
