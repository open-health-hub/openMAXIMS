//#############################################################################
//#                                                                           #
//#  Copyright (C) <2014>  <IMS MAXIMS>                                       #
//#                                                                           #
//#  This program is free software: you can redistribute it and/or modify     #
//#  it under the terms of the GNU Affero General Public License as           #
//#  published by the Free Software Foundation, either version 3 of the       #
//#  License, or (at your option) any later version.                          # 
//#                                                                           #
//#  This program is distributed in the hope that it will be useful,          #
//#  but WITHOUT ANY WARRANTY; without even the implied warranty of           #
//#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            #
//#  GNU Affero General Public License for more details.                      #
//#                                                                           #
//#  You should have received a copy of the GNU Affero General Public License #
//#  along with this program.  If not, see <http://www.gnu.org/licenses/>.    #
//#                                                                           #
//#############################################################################
//#EOH
// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.therapies.forms.treatmentequipmentconfig;

public abstract class BaseLogic extends Handlers
{
	public final Class getDomainInterface() throws ClassNotFoundException
	{
		return ims.therapies.domain.TreatmentEquipmentConfig.class;
	}
	public final void setContext(ims.framework.UIEngine engine, GenForm form, ims.therapies.domain.TreatmentEquipmentConfig domain)
	{
		setContext(engine, form);
		this.domain = domain;
	}
	public void clearContextInformation()
	{
		engine.clearPatientContextInformation();
	}
	protected final void oncmbMachineTypeValueSet(Object value)
	{
		java.util.ArrayList listOfValues = this.form.ctnConfig().cmbMachineType().getValues();

		if(value == null)
		{
			if(listOfValues != null && listOfValues.size() > 0)
			{
				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.therapies.vo.lookups.TreatmentEquipmentTypeConfig existingInstance = (ims.therapies.vo.lookups.TreatmentEquipmentTypeConfig)listOfValues.get(x);
					if(!existingInstance.isActive())
					{
						bindcmbMachineTypeLookup();
						return;
					}
				}
			}
		}
		else if(value instanceof ims.therapies.vo.lookups.TreatmentEquipmentTypeConfig)
		{
			ims.therapies.vo.lookups.TreatmentEquipmentTypeConfig instance = (ims.therapies.vo.lookups.TreatmentEquipmentTypeConfig)value;

			if(listOfValues != null)
			{
				if(listOfValues.size() == 0)
					bindcmbMachineTypeLookup();

				for(int x = 0; x < listOfValues.size(); x++)
				{
					ims.therapies.vo.lookups.TreatmentEquipmentTypeConfig existingInstance = (ims.therapies.vo.lookups.TreatmentEquipmentTypeConfig)listOfValues.get(x);
					if(existingInstance.equals(instance))
						return;
				}
			}

			this.form.ctnConfig().cmbMachineType().newRow(instance, instance.getText(), instance.getImage(), instance.getTextColor());
		}
	}
	protected final void bindcmbMachineTypeLookup()
	{
		this.form.ctnConfig().cmbMachineType().clear();
		ims.therapies.vo.lookups.TreatmentEquipmentTypeConfigCollection lookupCollection = ims.therapies.vo.lookups.LookupHelper.getTreatmentEquipmentTypeConfig(this.domain.getLookupService());
		for(int x = 0; x < lookupCollection.size(); x++)
		{
			this.form.ctnConfig().cmbMachineType().newRow(lookupCollection.get(x), lookupCollection.get(x).getText(), lookupCollection.get(x).getImage(), lookupCollection.get(x).getTextColor());
		}
	}
	protected final void setcmbMachineTypeLookupValue(int id)
	{
		ims.therapies.vo.lookups.TreatmentEquipmentTypeConfig instance = ims.therapies.vo.lookups.LookupHelper.getTreatmentEquipmentTypeConfigInstance(this.domain.getLookupService(), id);
		if(instance != null)
			this.form.ctnConfig().cmbMachineType().setValue(instance);
	}
	protected final void defaultcmbMachineTypeLookupValue()
	{
		this.form.ctnConfig().cmbMachineType().setValue((ims.therapies.vo.lookups.TreatmentEquipmentTypeConfig)domain.getLookupService().getDefaultInstance(ims.therapies.vo.lookups.TreatmentEquipmentTypeConfig.class, engine.getFormName().getID(), ims.therapies.vo.lookups.TreatmentEquipmentTypeConfig.TYPE_ID));
	}
	protected void clearScreen()
	{
		this.form.ctnConfig().txtSerialNr().setValue("");
		this.form.ctnConfig().txtModel().setValue("");
		this.form.ctnConfig().cmbMachineType().setValue(null);
		this.form.ctnConfig().txtDescription().setValue("");
	}
	protected void populateScreenFromData(ims.therapies.vo.TreatmentEquipmentConfigVo value)
	{
		clearScreen();
		if(value == null)
			return;

		this.form.ctnConfig().txtSerialNr().setValue(value.getSerialNumberIsNotNull() ? value.getSerialNumber(): null);
		this.form.ctnConfig().txtModel().setValue(value.getModelIsNotNull() ? value.getModel(): null);
		this.form.ctnConfig().cmbMachineType().setValue(value.getTypeIsNotNull() ? value.getType() : null);
		this.form.ctnConfig().txtDescription().setValue(value.getDescriptionIsNotNull() ? value.getDescription(): null);
	}
	protected ims.therapies.vo.TreatmentEquipmentConfigVo populateDataFromScreen(ims.therapies.vo.TreatmentEquipmentConfigVo value)
	{
		if(value == null)
			value = new ims.therapies.vo.TreatmentEquipmentConfigVo();

		value.setSerialNumber(this.form.ctnConfig().txtSerialNr().getValue());
		value.setModel(this.form.ctnConfig().txtModel().getValue());
		value.setType(this.form.ctnConfig().cmbMachineType().getValue());
		value.setDescription(this.form.ctnConfig().txtDescription().getValue());

		return value;
	}
	protected ims.therapies.vo.TreatmentEquipmentConfigVo populateDataFromScreen()
	{
		return populateDataFromScreen(new ims.therapies.vo.TreatmentEquipmentConfigVo());
	}
	public final void free()
	{
		super.free();
		domain = null;
	}
	
	protected ims.therapies.domain.TreatmentEquipmentConfig domain;
}