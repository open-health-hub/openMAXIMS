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
 * Linked to core.clinical.PatientAlert business object (ID: 1003100012).
 */
public class PatientAlert extends ims.core.vo.PatientAlertLiteVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public PatientAlert()
	{
	}
	public PatientAlert(Integer id, int version)
	{
		super(id, version);
	}
	public PatientAlert(ims.core.vo.beans.PatientAlertBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.alerttype = bean.getAlertType() == null ? null : ims.core.vo.lookups.AlertType.buildLookup(bean.getAlertType());
		this.iscurrentlyactivealert = bean.getIsCurrentlyActiveAlert();
		this.sourceofinformation = bean.getSourceofInformation() == null ? null : ims.core.vo.lookups.SourceofInformation.buildLookup(bean.getSourceofInformation());
		this.patient = bean.getPatient() == null ? null : new ims.core.patient.vo.PatientRefVo(new Integer(bean.getPatient().getId()), bean.getPatient().getVersion());
		this.comments = bean.getComments();
		this.dateidentified = bean.getDateIdentified() == null ? null : bean.getDateIdentified().buildPartialDate();
		this.sysinfo = bean.getSysInfo() == null ? null : bean.getSysInfo().buildSystemInformation();
		this.inactivationcomments = bean.getInactivationComments();
		this.authoringinformation = bean.getAuthoringInformation() == null ? null : bean.getAuthoringInformation().buildVo();
		this.inactivationauthoringinfo = bean.getInactivationAuthoringInfo() == null ? null : bean.getInactivationAuthoringInfo().buildVo();
		this.recordinginformation = bean.getRecordingInformation() == null ? null : bean.getRecordingInformation().buildVo();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.core.vo.beans.PatientAlertBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.alerttype = bean.getAlertType() == null ? null : ims.core.vo.lookups.AlertType.buildLookup(bean.getAlertType());
		this.iscurrentlyactivealert = bean.getIsCurrentlyActiveAlert();
		this.sourceofinformation = bean.getSourceofInformation() == null ? null : ims.core.vo.lookups.SourceofInformation.buildLookup(bean.getSourceofInformation());
		this.patient = bean.getPatient() == null ? null : new ims.core.patient.vo.PatientRefVo(new Integer(bean.getPatient().getId()), bean.getPatient().getVersion());
		this.comments = bean.getComments();
		this.dateidentified = bean.getDateIdentified() == null ? null : bean.getDateIdentified().buildPartialDate();
		this.sysinfo = bean.getSysInfo() == null ? null : bean.getSysInfo().buildSystemInformation();
		this.inactivationcomments = bean.getInactivationComments();
		this.authoringinformation = bean.getAuthoringInformation() == null ? null : bean.getAuthoringInformation().buildVo(map);
		this.inactivationauthoringinfo = bean.getInactivationAuthoringInfo() == null ? null : bean.getInactivationAuthoringInfo().buildVo(map);
		this.recordinginformation = bean.getRecordingInformation() == null ? null : bean.getRecordingInformation().buildVo(map);
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.core.vo.beans.PatientAlertBean bean = null;
		if(map != null)
			bean = (ims.core.vo.beans.PatientAlertBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.core.vo.beans.PatientAlertBean();
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
		if(fieldName.equals("SYSINFO"))
			return getSysInfo();
		if(fieldName.equals("INACTIVATIONCOMMENTS"))
			return getInactivationComments();
		if(fieldName.equals("AUTHORINGINFORMATION"))
			return getAuthoringInformation();
		if(fieldName.equals("INACTIVATIONAUTHORINGINFO"))
			return getInactivationAuthoringInfo();
		if(fieldName.equals("RECORDINGINFORMATION"))
			return getRecordingInformation();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getSysInfoIsNotNull()
	{
		return this.sysinfo != null;
	}
	public ims.vo.SystemInformation getSysInfo()
	{
		return this.sysinfo;
	}
	public void setSysInfo(ims.vo.SystemInformation value)
	{
		this.isValidated = false;
		this.sysinfo = value;
	}
	public boolean getInactivationCommentsIsNotNull()
	{
		return this.inactivationcomments != null;
	}
	public String getInactivationComments()
	{
		return this.inactivationcomments;
	}
	public static int getInactivationCommentsMaxLength()
	{
		return 500;
	}
	public void setInactivationComments(String value)
	{
		this.isValidated = false;
		this.inactivationcomments = value;
	}
	public boolean getAuthoringInformationIsNotNull()
	{
		return this.authoringinformation != null;
	}
	public ims.core.vo.AuthoringInformationVo getAuthoringInformation()
	{
		return this.authoringinformation;
	}
	public void setAuthoringInformation(ims.core.vo.AuthoringInformationVo value)
	{
		this.isValidated = false;
		this.authoringinformation = value;
	}
	public boolean getInactivationAuthoringInfoIsNotNull()
	{
		return this.inactivationauthoringinfo != null;
	}
	public ims.core.vo.AuthoringInformationVo getInactivationAuthoringInfo()
	{
		return this.inactivationauthoringinfo;
	}
	public void setInactivationAuthoringInfo(ims.core.vo.AuthoringInformationVo value)
	{
		this.isValidated = false;
		this.inactivationauthoringinfo = value;
	}
	public boolean getRecordingInformationIsNotNull()
	{
		return this.recordinginformation != null;
	}
	public ims.core.vo.RecordingUserInformationVo getRecordingInformation()
	{
		return this.recordinginformation;
	}
	public void setRecordingInformation(ims.core.vo.RecordingUserInformationVo value)
	{
		this.isValidated = false;
		this.recordinginformation = value;
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
		if(this.inactivationauthoringinfo != null)
		{
			if(!this.inactivationauthoringinfo.isValidated())
			{
				this.isBusy = false;
				return false;
			}
		}
		if(this.recordinginformation != null)
		{
			if(!this.recordinginformation.isValidated())
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
		if(this.alerttype == null)
			listOfErrors.add("AlertType is mandatory");
		if(this.sourceofinformation == null)
			listOfErrors.add("SourceofInformation is mandatory");
		if(this.patient == null)
			listOfErrors.add("Patient is mandatory");
		if(this.comments != null)
			if(this.comments.length() > 255)
				listOfErrors.add("The length of the field [comments] in the value object [ims.core.vo.PatientAlert] is too big. It should be less or equal to 255");
		if(this.inactivationcomments != null)
			if(this.inactivationcomments.length() > 500)
				listOfErrors.add("The length of the field [inactivationcomments] in the value object [ims.core.vo.PatientAlert] is too big. It should be less or equal to 500");
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
		if(this.inactivationauthoringinfo != null)
		{
			String[] listOfOtherErrors = this.inactivationauthoringinfo.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
		if(this.recordinginformation != null)
		{
			String[] listOfOtherErrors = this.recordinginformation.validate();
			if(listOfOtherErrors != null)
			{
				for(int x = 0; x < listOfOtherErrors.length; x++)
				{
					listOfErrors.add(listOfOtherErrors[x]);
				}
			}
		}
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
	
		PatientAlert clone = new PatientAlert(this.id, this.version);
		
		if(this.alerttype == null)
			clone.alerttype = null;
		else
			clone.alerttype = (ims.core.vo.lookups.AlertType)this.alerttype.clone();
		clone.iscurrentlyactivealert = this.iscurrentlyactivealert;
		if(this.sourceofinformation == null)
			clone.sourceofinformation = null;
		else
			clone.sourceofinformation = (ims.core.vo.lookups.SourceofInformation)this.sourceofinformation.clone();
		clone.patient = this.patient;
		clone.comments = this.comments;
		if(this.dateidentified == null)
			clone.dateidentified = null;
		else
			clone.dateidentified = (ims.framework.utils.PartialDate)this.dateidentified.clone();
		if(this.sysinfo == null)
			clone.sysinfo = null;
		else
			clone.sysinfo = (ims.vo.SystemInformation)this.sysinfo.clone();
		clone.inactivationcomments = this.inactivationcomments;
		if(this.authoringinformation == null)
			clone.authoringinformation = null;
		else
			clone.authoringinformation = (ims.core.vo.AuthoringInformationVo)this.authoringinformation.clone();
		if(this.inactivationauthoringinfo == null)
			clone.inactivationauthoringinfo = null;
		else
			clone.inactivationauthoringinfo = (ims.core.vo.AuthoringInformationVo)this.inactivationauthoringinfo.clone();
		if(this.recordinginformation == null)
			clone.recordinginformation = null;
		else
			clone.recordinginformation = (ims.core.vo.RecordingUserInformationVo)this.recordinginformation.clone();
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
		if (!(PatientAlert.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A PatientAlert object cannot be compared an Object of type " + obj.getClass().getName());
		}
		PatientAlert compareObj = (PatientAlert)obj;
		int retVal = 0;
		if (retVal == 0)
		{
			if(this.getSysInfo() == null && compareObj.getSysInfo() != null)
				return -1;
			if(this.getSysInfo() != null && compareObj.getSysInfo() == null)
				return 1;
			if(this.getSysInfo() != null && compareObj.getSysInfo() != null)
				retVal = this.getSysInfo().compareTo(compareObj.getSysInfo());
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
		if(this.sysinfo != null)
			count++;
		if(this.inactivationcomments != null)
			count++;
		if(this.authoringinformation != null)
			count++;
		if(this.inactivationauthoringinfo != null)
			count++;
		if(this.recordinginformation != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return super.countValueObjectFields() + 5;
	}
	protected ims.vo.SystemInformation sysinfo;
	protected String inactivationcomments;
	protected ims.core.vo.AuthoringInformationVo authoringinformation;
	protected ims.core.vo.AuthoringInformationVo inactivationauthoringinfo;
	protected ims.core.vo.RecordingUserInformationVo recordinginformation;
	private boolean isValidated = false;
	private boolean isBusy = false;
}