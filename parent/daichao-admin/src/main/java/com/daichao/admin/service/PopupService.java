package com.daichao.admin.service;

import java.util.List;

import com.daichao.admin.input.popup.PopupInput;
import com.daichao.admin.input.popup.PopupSaveInput;
import com.daichao.bean.output.ResultOutput;
import com.daichao.bean.product.DcPopup;

public interface PopupService {

	List<DcPopup> queryPopupList(PopupInput input);
	
	ResultOutput popupSave(PopupSaveInput input);
}
