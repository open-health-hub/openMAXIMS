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

package ims.core.vo;

/**
 * Linked to core.vitals.VitalSigns business object (ID: 1022100004).
 */
public class VitalSignsShortVo extends ims.core.vo.VitalSignsLiteVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public VitalSignsShortVo()
	{
	}
	public VitalSignsShortVo(Integer id, int version)
	{
		super(id, version);
	}
	public VitalSignsShortVo(ims.core.vo.beans.VitalSignsShortVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.carecontext = bean.getCareContext() == null ? null : new ims.core.admin.vo.CareContextRefVo(new Integer(bean.getCareContext().getId()), bean.getCareContext().getVersion());
		this.authoringinformation = bean.getAuthoringInformation() == null ? null : bean.getAuthoringInformation().buildVo();
		this.isventilationchartrecord = bean.getIsVentilationChartRecord();
		this.clinicalcontact = bean.getClinicalContact() == null ? null : bean.getClinicalContact().buildVo();
		this.vitalstakendatetime = bean.getVitalsTakenDateTime() == null ? null : bean.getVitalsTakenDateTime().buildDateTime();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.core.vo.beans.VitalSignsShortVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.carecontext = bean.getCareContext() == null ? null : new ims.core.admin.vo.CareContextRefVo(new Integer(bean.getCareContext().getId()), bean.getCareContext().getVersion());
		this.authoringinformation = bean.getAuthoringInformation() == null ? null : bean.getAuthoringInformation().buildVo(map);
		this.isventilationchartrecord = bean.getIsVentilationChartRecord();
		this.clinicalcontact = bean.getClinicalContact() == null ? null : bean.getClinicalContact().buildVo(map);
		this.vitalstakendatetime = bean.getVitalsTakenDateTime() == null ? null : bean.getVitalsTakenDateTime().buildDateTime();
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.core.vo.beans.VitalSignsShortVoBean bean = null;
		if(map != null)
			bean = (ims.core.vo.beans.VitalSignsShortVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.core.vo.beans.VitalSignsShortVoBean();
			map.addValueObjectBean(this, bean);
			bean.populate(map, this);
		}
		return bean;
	}
	public Object getFieldValueByFieldName(String fieldName)
	{
		if(fieldName == null)
			throw new ims.framework.exceptions.CodingRuntimeException("Invalid field name");
		fieldName = fieldName.toUpperCase();
		if(fieldName.equals("ISVENTILATIONCHARTRECORD"))
			return getIsVentilationChartRecord();
		if(fieldName.equals("CLINICALCONTACT"))
			return getClinicalContact();
		if(fieldName.equals("VITALSTAKENDATETIME"))
			return getVitalsTakenDateTime();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getIsVentilationChartRecordIsNotNull()
	{
		return this.isventilationchartrecord != null;
	}
	public Boolean getIsVentilationChartRecord()
	{
		return this.isventilationchartrecord;
	}
	public void setIsVentilationChartRecord(Boolean value)
	{
		this.isValidated = false;
		this.isventilationchartrecord = value;
	}
	public boolean getClinicalContactIsNotNull()
	{
		return this.clinicalcontact != null;
	}
	public ims.core.vo.ClinicalContactShortVo getClinicalContact()
	{
		return this.clinicalcontact;
	}
	public void setClinicalContact(ims.core.vo.ClinicalContactShortVo value)
	{
		this.isValidated = false;
		this.clinicalcontact = value;
	}
	public boolean getVitalsTakenDateTimeIsNotNull()
	{
		return this.vitalstakendatetime != null;
	}
	public ims.framework.utils.DateTime getVitalsTakenDateTime()
	{
		return this.vitalstakendatetime;
	}
	public void setVitalsTakenDateTime(ims.framework.utils.DateTime value)
	{
		this.isValidated = false;
		this.vitalstakendatetime = value;
	}
	public boolean isValidated()
	{
		if(this.isBusy)
			return true;
		this.isBusy = true;
	
		if(!this.isValidated)
		{
			this.isBusy = false;
			return false;
		}
		if(this.authoringinformation != null)
		{
			if(!this.authoringinformation.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		this.isBusy = false;
		return true;
	}
	public String[] validate()
	{
		return validate(null);
	}
	public String[] validate(String[] existingErrors)
	{
		if(this.isBusy)
			return null;
		this.isBusy = true;
	
		java.util.ArrayList<String> listOfErrors = new java.util.ArrayList<String>();
		if(existingErrors != null)
		{
			for(int x = 0; x < existingErrors.length; x++)
			{
				listOfErrors.add(existingErrors[x]);
			}
		}
		if(this.carecontext == null)
			listOfErrors.add("CareContext is mandatory");
		if(this.authoringinformation == null)
			listOfErrors.add("AuthoringInformation is mandatory");
		if(this.authoringinformation != null)
		{
			String[] listOfOtherErrors = this.authoringinformation.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.vitalstakendatetime == null)
			listOfErrors.add("VitalsTakenDateTime is mandatory");
		int errorCount = listOfErrors.size();
		if(errorCount == 0)
		{
			this.isBusy = false;
			this.isValidated = true;
			return null;
		}
		String[] result = new String[errorCount];
		for(int x = 0; x < errorCount; x++)
			result[x] = (String)listOfErrors.get(x);
		this.isBusy = false;
		this.isValidated = false;
		return result;
	}
	public void clearIDAndVersion()
	{
		this.id = null;
		this.version = 0;
	}
	public Object clone()
	{
		if(this.isBusy)
			return this;
		this.isBusy = true;
	
		VitalSignsShortVo clone = new VitalSignsShortVo(this.id, this.version);
		
		clone.carecontext = this.carecontext;
		if(this.authoringinformation == null)
			clone.authoringinformation = null;
		else
			clone.authoringinformation = (ims.core.vo.AuthoringInformationVo)this.authoringinformation.clone();
		clone.isventilationchartrecord = this.isventilationchartrecord;
		if(this.clinicalcontact == null)
			clone.clinicalcontact = null;
		else
			clone.clinicalcontact = (ims.core.vo.ClinicalContactShortVo)this.clinicalcontact.clone();
		if(this.vitalstakendatetime == null)
			clone.vitalstakendatetime = null;
		else
			clone.vitalstakendatetime = (ims.framework.utils.DateTime)this.vitalstakendatetime.clone();
		clone.isValidated = this.isValidated;
		
		this.isBusy = false;
		return clone;
	}
	public int compareTo(Object obj)
	{
		return compareTo(obj, true);
	}
	public int compareTo(Object obj, boolean caseInsensitive)
	{
		if (obj == null)
		{
			return -1;
		}
		if(caseInsensitive); // this is to avoid eclipse warning only.
		if (!(VitalSignsShortVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A VitalSignsShortVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		VitalSignsShortVo compareObj = (VitalSignsShortVo)obj;
		int retVal = 0;
		if (retVal == 0)
		{
			if(this.getID_VitalSigns() == null && compareObj.getID_VitalSigns() != null)
				return -1;
			if(this.getID_VitalSigns() != null && compareObj.getID_VitalSigns() == null)
				return 1;
			if(this.getID_VitalSigns() != null && compareObj.getID_VitalSigns() != null)
				retVal = this.getID_VitalSigns().compareTo(compareObj.getID_VitalSigns());
		}
		if (retVal == 0)
		{
			if(this.getAuthoringInformation() == null && compareObj.getAuthoringInformation() != null)
				return -1;
			if(this.getAuthoringInformation() != null && compareObj.getAuthoringInformation() == null)
				return 1;
			if(this.getAuthoringInformation() != null && compareObj.getAuthoringInformation() != null)
				retVal = this.getAuthoringInformation().compareTo(compareObj.getAuthoringInformation());
		}
		return retVal;
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = super.countFieldsWithValue();
		if(this.isventilationchartrecord != null)
			count++;
		if(this.clinicalcontact != null)
			count++;
		if(this.vitalstakendatetime != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return super.countValueObjectFields() + 3;
	}
	protected Boolean isventilationchartrecord;
	protected ims.core.vo.ClinicalContactShortVo clinicalcontact;
	protected ims.framework.utils.DateTime vitalstakendatetime;
	private boolean isValidated = false;
	private boolean isBusy = false;
}