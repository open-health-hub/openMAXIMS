// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.forms.referralcoding;

public abstract class BaseLogic extends Handlers
{
	public final Class getDomainInterface() throws ClassNotFoundException
	{
		return ims.RefMan.domain.ReferralCoding.class;
	}
	public final void setContext(ims.framework.UIEngine engine, GenForm form, ims.RefMan.domain.ReferralCoding domain)
	{
		setContext(engine, form);
		this.domain = domain;
	}
	protected final void bindgrdCodingColLateralityLookup()
	{
		this.form.grdCoding().ColLateralityComboBox().clear();
		ims.core.vo.lookups.LateralityLRBCollection lookupCollection = ims.core.vo.lookups.LookupHelper.getLateralityLRB(this.domain.getLookupService());
		for(int x = 0; x < lookupCollection.size(); x++)
		{
			this.form.grdCoding().ColLateralityComboBox().newRow(lookupCollection.get(x), lookupCollection.get(x).getText(), lookupCollection.get(x).getImage(), lookupCollection.get(x).getTextColor());
		}
	}
	public final void free()
	{
		super.free();
		domain = null;
	}
	
	protected ims.RefMan.domain.ReferralCoding domain;
}
