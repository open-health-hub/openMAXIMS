// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.forms.appointmentoutcomeproceduredialog;

public abstract class BaseLogic extends Handlers
{
	public final Class getDomainInterface() throws ClassNotFoundException
	{
		return ims.RefMan.domain.AppointmentOutcomeProcedureDialog.class;
	}
	public final void setContext(ims.framework.UIEngine engine, GenForm form, ims.RefMan.domain.AppointmentOutcomeProcedureDialog domain)
	{
		setContext(engine, form);
		this.domain = domain;
	}
	protected final void oncmbLateralityValueSet(Object value)
	{
		java.util.ArrayList listOfValues = this.form.cmbLaterality().getValues();

		if(value == null)
		{
			if(listOfValues != null && listOfValues.size() > 0)
			{
				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.core.vo.lookups.LateralityLRB existingInstance = (ims.core.vo.lookups.LateralityLRB)listOfValues.get(x);
					if(!existingInstance.isActive())
					{
						bindcmbLateralityLookup();
						return;
					}
				}
			}
		}
		else if(value instanceof ims.core.vo.lookups.LateralityLRB)
		{
			ims.core.vo.lookups.LateralityLRB instance = (ims.core.vo.lookups.LateralityLRB)value;

			if(listOfValues != null)
			{
				if(listOfValues.size() == 0)
					bindcmbLateralityLookup();

				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.core.vo.lookups.LateralityLRB existingInstance = (ims.core.vo.lookups.LateralityLRB)listOfValues.get(x);
					if(existingInstance.equals(instance))
						return;
				}
			}

			this.form.cmbLaterality().newRow(instance, instance.getText(), instance.getImage(), instance.getTextColor());
		}
	}
	protected final void bindcmbLateralityLookup()
	{
		this.form.cmbLaterality().clear();
		ims.core.vo.lookups.LateralityLRBCollection lookupCollection = ims.core.vo.lookups.LookupHelper.getLateralityLRB(this.domain.getLookupService());
		for(int x = 0; x < lookupCollection.size(); x++)
		{
			this.form.cmbLaterality().newRow(lookupCollection.get(x), lookupCollection.get(x).getText(), lookupCollection.get(x).getImage(), lookupCollection.get(x).getTextColor());
		}
	}
	protected final void setcmbLateralityLookupValue(int id)
	{
		ims.core.vo.lookups.LateralityLRB instance = ims.core.vo.lookups.LookupHelper.getLateralityLRBInstance(this.domain.getLookupService(), id);
		if(instance != null)
			this.form.cmbLaterality().setValue(instance);
	}
	protected final void defaultcmbLateralityLookupValue()
	{
		this.form.cmbLaterality().setValue((ims.core.vo.lookups.LateralityLRB)domain.getLookupService().getDefaultInstance(ims.core.vo.lookups.LateralityLRB.class, engine.getFormName().getID(), ims.core.vo.lookups.LateralityLRB.TYPE_ID));
	}
	public final void free()
	{
		super.free();
		domain = null;
	}
	
	protected ims.RefMan.domain.AppointmentOutcomeProcedureDialog domain;
}