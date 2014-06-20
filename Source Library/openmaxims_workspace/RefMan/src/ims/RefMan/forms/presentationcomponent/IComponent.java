// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.forms.presentationcomponent;

import ims.framework.enumerations.FormMode;

public interface IComponent
{
	public void setMode(FormMode mode);
	public FormMode getMode();
	public void initialize();

	public String[] validate(String[] value);

	public void setValue(ims.RefMan.vo.PresentationReferralSummaryVo value);

	public ims.RefMan.vo.PresentationReferralSummaryVo getValue();

	public void setReadOnly(Boolean value);
}