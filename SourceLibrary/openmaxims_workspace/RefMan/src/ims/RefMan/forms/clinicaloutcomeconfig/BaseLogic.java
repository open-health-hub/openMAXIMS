// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.forms.clinicaloutcomeconfig;

public abstract class BaseLogic extends Handlers
{
	public final Class getDomainInterface() throws ClassNotFoundException
	{
		return ims.RefMan.domain.ClinicalOutcomeConfig.class;
	}
	public final void setContext(ims.framework.UIEngine engine, GenForm form, ims.RefMan.domain.ClinicalOutcomeConfig domain)
	{
		setContext(engine, form);
		this.domain = domain;
	}
	public void clearContextInformation()
	{
		engine.clearPatientContextInformation();
	}
	protected final void oncmbSpecialtyValueSet(Object value)
	{
		java.util.ArrayList listOfValues = this.form.cmbSpecialty().getValues();

		if(value == null)
		{
			if(listOfValues != null && listOfValues.size() > 0)
			{
				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.core.vo.lookups.Specialty existingInstance = (ims.core.vo.lookups.Specialty)listOfValues.get(x);
					if(!existingInstance.isActive())
					{
						bindcmbSpecialtyLookup();
						return;
					}
				}
			}
		}
		else if(value instanceof ims.core.vo.lookups.Specialty)
		{
			ims.core.vo.lookups.Specialty instance = (ims.core.vo.lookups.Specialty)value;

			if(listOfValues != null)
			{
				if(listOfValues.size() == 0)
					bindcmbSpecialtyLookup();

				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.core.vo.lookups.Specialty existingInstance = (ims.core.vo.lookups.Specialty)listOfValues.get(x);
					if(existingInstance.equals(instance))
						return;
				}
			}

			this.form.cmbSpecialty().newRow(instance, instance.getText(), instance.getImage(), instance.getTextColor());
		}
	}
	protected final void bindcmbSpecialtyLookup()
	{
		this.form.cmbSpecialty().clear();
		ims.core.vo.lookups.SpecialtyCollection lookupCollection = ims.core.vo.lookups.LookupHelper.getSpecialty(this.domain.getLookupService());
		for(int x = 0; x < lookupCollection.size(); x++)
		{
			this.form.cmbSpecialty().newRow(lookupCollection.get(x), lookupCollection.get(x).getText(), lookupCollection.get(x).getImage(), lookupCollection.get(x).getTextColor());
		}
	}
	protected final void setcmbSpecialtyLookupValue(int id)
	{
		ims.core.vo.lookups.Specialty instance = ims.core.vo.lookups.LookupHelper.getSpecialtyInstance(this.domain.getLookupService(), id);
		if(instance != null)
			this.form.cmbSpecialty().setValue(instance);
	}
	protected final void defaultcmbSpecialtyLookupValue()
	{
		this.form.cmbSpecialty().setValue((ims.core.vo.lookups.Specialty)domain.getLookupService().getDefaultInstance(ims.core.vo.lookups.Specialty.class, engine.getFormName().getID(), ims.core.vo.lookups.Specialty.TYPE_ID));
	}
	protected final void oncmbOutcomeSpecialtyValueSet(Object value)
	{
		java.util.ArrayList listOfValues = this.form.ctnConfigDetails().cmbOutcomeSpecialty().getValues();

		if(value == null)
		{
			if(listOfValues != null && listOfValues.size() > 0)
			{
				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.core.vo.lookups.Specialty existingInstance = (ims.core.vo.lookups.Specialty)listOfValues.get(x);
					if(!existingInstance.isActive())
					{
						bindcmbOutcomeSpecialtyLookup();
						return;
					}
				}
			}
		}
		else if(value instanceof ims.core.vo.lookups.Specialty)
		{
			ims.core.vo.lookups.Specialty instance = (ims.core.vo.lookups.Specialty)value;

			if(listOfValues != null)
			{
				if(listOfValues.size() == 0)
					bindcmbOutcomeSpecialtyLookup();

				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.core.vo.lookups.Specialty existingInstance = (ims.core.vo.lookups.Specialty)listOfValues.get(x);
					if(existingInstance.equals(instance))
						return;
				}
			}

			this.form.ctnConfigDetails().cmbOutcomeSpecialty().newRow(instance, instance.getText(), instance.getImage(), instance.getTextColor());
		}
	}
	protected final void bindcmbOutcomeSpecialtyLookup()
	{
		this.form.ctnConfigDetails().cmbOutcomeSpecialty().clear();
		ims.core.vo.lookups.SpecialtyCollection lookupCollection = ims.core.vo.lookups.LookupHelper.getSpecialty(this.domain.getLookupService());
		for(int x = 0; x < lookupCollection.size(); x++)
		{
			this.form.ctnConfigDetails().cmbOutcomeSpecialty().newRow(lookupCollection.get(x), lookupCollection.get(x).getText(), lookupCollection.get(x).getImage(), lookupCollection.get(x).getTextColor());
		}
	}
	protected final void setcmbOutcomeSpecialtyLookupValue(int id)
	{
		ims.core.vo.lookups.Specialty instance = ims.core.vo.lookups.LookupHelper.getSpecialtyInstance(this.domain.getLookupService(), id);
		if(instance != null)
			this.form.ctnConfigDetails().cmbOutcomeSpecialty().setValue(instance);
	}
	protected final void defaultcmbOutcomeSpecialtyLookupValue()
	{
		this.form.ctnConfigDetails().cmbOutcomeSpecialty().setValue((ims.core.vo.lookups.Specialty)domain.getLookupService().getDefaultInstance(ims.core.vo.lookups.Specialty.class, engine.getFormName().getID(), ims.core.vo.lookups.Specialty.TYPE_ID));
	}
	protected final void oncmbOutcomeCategoryValueSet(Object value)
	{
		java.util.ArrayList listOfValues = this.form.ctnConfigDetails().cmbOutcomeCategory().getValues();

		if(value == null)
		{
			if(listOfValues != null && listOfValues.size() > 0)
			{
				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.clinical.vo.lookups.ClinicalOutcomeCategory existingInstance = (ims.clinical.vo.lookups.ClinicalOutcomeCategory)listOfValues.get(x);
					if(!existingInstance.isActive())
					{
						bindcmbOutcomeCategoryLookup();
						return;
					}
				}
			}
		}
		else if(value instanceof ims.clinical.vo.lookups.ClinicalOutcomeCategory)
		{
			ims.clinical.vo.lookups.ClinicalOutcomeCategory instance = (ims.clinical.vo.lookups.ClinicalOutcomeCategory)value;

			if(listOfValues != null)
			{
				if(listOfValues.size() == 0)
					bindcmbOutcomeCategoryLookup();

				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.clinical.vo.lookups.ClinicalOutcomeCategory existingInstance = (ims.clinical.vo.lookups.ClinicalOutcomeCategory)listOfValues.get(x);
					if(existingInstance.equals(instance))
						return;
				}
			}

			this.form.ctnConfigDetails().cmbOutcomeCategory().newRow(instance, instance.getText(), instance.getImage(), instance.getTextColor());
		}
	}
	protected final void bindcmbOutcomeCategoryLookup()
	{
		this.form.ctnConfigDetails().cmbOutcomeCategory().clear();
		ims.clinical.vo.lookups.ClinicalOutcomeCategoryCollection lookupCollection = ims.clinical.vo.lookups.LookupHelper.getClinicalOutcomeCategory(this.domain.getLookupService());
		for(int x = 0; x < lookupCollection.size(); x++)
		{
			this.form.ctnConfigDetails().cmbOutcomeCategory().newRow(lookupCollection.get(x), lookupCollection.get(x).getText(), lookupCollection.get(x).getImage(), lookupCollection.get(x).getTextColor());
		}
	}
	protected final void setcmbOutcomeCategoryLookupValue(int id)
	{
		ims.clinical.vo.lookups.ClinicalOutcomeCategory instance = ims.clinical.vo.lookups.LookupHelper.getClinicalOutcomeCategoryInstance(this.domain.getLookupService(), id);
		if(instance != null)
			this.form.ctnConfigDetails().cmbOutcomeCategory().setValue(instance);
	}
	protected final void defaultcmbOutcomeCategoryLookupValue()
	{
		this.form.ctnConfigDetails().cmbOutcomeCategory().setValue((ims.clinical.vo.lookups.ClinicalOutcomeCategory)domain.getLookupService().getDefaultInstance(ims.clinical.vo.lookups.ClinicalOutcomeCategory.class, engine.getFormName().getID(), ims.clinical.vo.lookups.ClinicalOutcomeCategory.TYPE_ID));
	}
	protected final void oncmbCategoryValueSet(Object value)
	{
		java.util.ArrayList listOfValues = this.form.cmbCategory().getValues();

		if(value == null)
		{
			if(listOfValues != null && listOfValues.size() > 0)
			{
				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.clinical.vo.lookups.ClinicalOutcomeCategory existingInstance = (ims.clinical.vo.lookups.ClinicalOutcomeCategory)listOfValues.get(x);
					if(!existingInstance.isActive())
					{
						bindcmbCategoryLookup();
						return;
					}
				}
			}
		}
		else if(value instanceof ims.clinical.vo.lookups.ClinicalOutcomeCategory)
		{
			ims.clinical.vo.lookups.ClinicalOutcomeCategory instance = (ims.clinical.vo.lookups.ClinicalOutcomeCategory)value;

			if(listOfValues != null)
			{
				if(listOfValues.size() == 0)
					bindcmbCategoryLookup();

				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.clinical.vo.lookups.ClinicalOutcomeCategory existingInstance = (ims.clinical.vo.lookups.ClinicalOutcomeCategory)listOfValues.get(x);
					if(existingInstance.equals(instance))
						return;
				}
			}

			this.form.cmbCategory().newRow(instance, instance.getText(), instance.getImage(), instance.getTextColor());
		}
	}
	protected final void bindcmbCategoryLookup()
	{
		this.form.cmbCategory().clear();
		ims.clinical.vo.lookups.ClinicalOutcomeCategoryCollection lookupCollection = ims.clinical.vo.lookups.LookupHelper.getClinicalOutcomeCategory(this.domain.getLookupService());
		for(int x = 0; x < lookupCollection.size(); x++)
		{
			this.form.cmbCategory().newRow(lookupCollection.get(x), lookupCollection.get(x).getText(), lookupCollection.get(x).getImage(), lookupCollection.get(x).getTextColor());
		}
	}
	protected final void setcmbCategoryLookupValue(int id)
	{
		ims.clinical.vo.lookups.ClinicalOutcomeCategory instance = ims.clinical.vo.lookups.LookupHelper.getClinicalOutcomeCategoryInstance(this.domain.getLookupService(), id);
		if(instance != null)
			this.form.cmbCategory().setValue(instance);
	}
	protected final void defaultcmbCategoryLookupValue()
	{
		this.form.cmbCategory().setValue((ims.clinical.vo.lookups.ClinicalOutcomeCategory)domain.getLookupService().getDefaultInstance(ims.clinical.vo.lookups.ClinicalOutcomeCategory.class, engine.getFormName().getID(), ims.clinical.vo.lookups.ClinicalOutcomeCategory.TYPE_ID));
	}
	public void initialize() throws ims.framework.exceptions.FormOpenException
	{
	}
	public boolean allowNew()
	{
		return form.getMode() == ims.framework.enumerations.FormMode.VIEW && !form.isReadOnly();
	}
	public boolean allowUpdate()
	{
		return form.getMode() == ims.framework.enumerations.FormMode.VIEW && !form.isReadOnly();
	}
	public String[] validateUIRules()
	{
		return null;
	}
	public void clear()
	{
	}
	public void search()
	{
	}
	public final void free()
	{
		super.free();
		domain = null;
	}
	
	protected ims.RefMan.domain.ClinicalOutcomeConfig domain;
}