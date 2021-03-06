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

package ims.clinical.vo;

/**
 * Linked to clinical.SurgicalAuditRecovery business object (ID: 1072100106).
 */
public class SurgicalAuditRecoveryVo extends ims.clinical.vo.SurgicalAuditRecoveryRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public SurgicalAuditRecoveryVo()
	{
	}
	public SurgicalAuditRecoveryVo(Integer id, int version)
	{
		super(id, version);
	}
	public SurgicalAuditRecoveryVo(ims.clinical.vo.beans.SurgicalAuditRecoveryVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.patient = bean.getPatient() == null ? null : new ims.core.patient.vo.PatientRefVo(new Integer(bean.getPatient().getId()), bean.getPatient().getVersion());
		this.carecontext = bean.getCareContext() == null ? null : new ims.core.admin.vo.CareContextRefVo(new Integer(bean.getCareContext().getId()), bean.getCareContext().getVersion());
		this.authoringinformation = bean.getAuthoringInformation() == null ? null : bean.getAuthoringInformation().buildVo();
		this.recoveryroomnurse = bean.getRecoveryRoomNurse() == null ? null : bean.getRecoveryRoomNurse().buildVo();
		this.confirmpatientarrival = bean.getConfirmPatientArrival() == null ? null : ims.core.vo.lookups.YesNo.buildLookup(bean.getConfirmPatientArrival());
		this.timearrivesinrecovery = bean.getTimeArrivesInRecovery() == null ? null : bean.getTimeArrivesInRecovery().buildDateTime();
		this.timewardnotified = bean.getTimeWardNotified() == null ? null : bean.getTimeWardNotified().buildDateTime();
		this.timeleavesrecovery = bean.getTimeLeavesRecovery() == null ? null : bean.getTimeLeavesRecovery().buildDateTime();
		this.sentto = bean.getSentTo() == null ? null : bean.getSentTo().buildVo();
		this.handoverfromrecoverynurse = bean.getHandoverfromRecoveryNurse() == null ? null : bean.getHandoverfromRecoveryNurse().buildVo();
		this.wardunitnurse = bean.getWardUnitNurse() == null ? null : bean.getWardUnitNurse().buildVo();
		this.recoverylocumbool = bean.getRecoveryLocumBool();
		this.recoverylocumnurse = bean.getRecoveryLocumNurse();
		this.recoveryhandoverlocumbool = bean.getRecoveryHandoverLocumBool();
		this.recoveryhandoverlocumnurse = bean.getRecoveryHandoverLocumNurse();
		this.wardlocumbool = bean.getWardLocumBool();
		this.wardlocumnurse = bean.getWardLocumNurse();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.clinical.vo.beans.SurgicalAuditRecoveryVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.patient = bean.getPatient() == null ? null : new ims.core.patient.vo.PatientRefVo(new Integer(bean.getPatient().getId()), bean.getPatient().getVersion());
		this.carecontext = bean.getCareContext() == null ? null : new ims.core.admin.vo.CareContextRefVo(new Integer(bean.getCareContext().getId()), bean.getCareContext().getVersion());
		this.authoringinformation = bean.getAuthoringInformation() == null ? null : bean.getAuthoringInformation().buildVo(map);
		this.recoveryroomnurse = bean.getRecoveryRoomNurse() == null ? null : bean.getRecoveryRoomNurse().buildVo(map);
		this.confirmpatientarrival = bean.getConfirmPatientArrival() == null ? null : ims.core.vo.lookups.YesNo.buildLookup(bean.getConfirmPatientArrival());
		this.timearrivesinrecovery = bean.getTimeArrivesInRecovery() == null ? null : bean.getTimeArrivesInRecovery().buildDateTime();
		this.timewardnotified = bean.getTimeWardNotified() == null ? null : bean.getTimeWardNotified().buildDateTime();
		this.timeleavesrecovery = bean.getTimeLeavesRecovery() == null ? null : bean.getTimeLeavesRecovery().buildDateTime();
		this.sentto = bean.getSentTo() == null ? null : bean.getSentTo().buildVo(map);
		this.handoverfromrecoverynurse = bean.getHandoverfromRecoveryNurse() == null ? null : bean.getHandoverfromRecoveryNurse().buildVo(map);
		this.wardunitnurse = bean.getWardUnitNurse() == null ? null : bean.getWardUnitNurse().buildVo(map);
		this.recoverylocumbool = bean.getRecoveryLocumBool();
		this.recoverylocumnurse = bean.getRecoveryLocumNurse();
		this.recoveryhandoverlocumbool = bean.getRecoveryHandoverLocumBool();
		this.recoveryhandoverlocumnurse = bean.getRecoveryHandoverLocumNurse();
		this.wardlocumbool = bean.getWardLocumBool();
		this.wardlocumnurse = bean.getWardLocumNurse();
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.clinical.vo.beans.SurgicalAuditRecoveryVoBean bean = null;
		if(map != null)
			bean = (ims.clinical.vo.beans.SurgicalAuditRecoveryVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.clinical.vo.beans.SurgicalAuditRecoveryVoBean();
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
		if(fieldName.equals("PATIENT"))
			return getPatient();
		if(fieldName.equals("CARECONTEXT"))
			return getCareContext();
		if(fieldName.equals("AUTHORINGINFORMATION"))
			return getAuthoringInformation();
		if(fieldName.equals("RECOVERYROOMNURSE"))
			return getRecoveryRoomNurse();
		if(fieldName.equals("CONFIRMPATIENTARRIVAL"))
			return getConfirmPatientArrival();
		if(fieldName.equals("TIMEARRIVESINRECOVERY"))
			return getTimeArrivesInRecovery();
		if(fieldName.equals("TIMEWARDNOTIFIED"))
			return getTimeWardNotified();
		if(fieldName.equals("TIMELEAVESRECOVERY"))
			return getTimeLeavesRecovery();
		if(fieldName.equals("SENTTO"))
			return getSentTo();
		if(fieldName.equals("HANDOVERFROMRECOVERYNURSE"))
			return getHandoverfromRecoveryNurse();
		if(fieldName.equals("WARDUNITNURSE"))
			return getWardUnitNurse();
		if(fieldName.equals("RECOVERYLOCUMBOOL"))
			return getRecoveryLocumBool();
		if(fieldName.equals("RECOVERYLOCUMNURSE"))
			return getRecoveryLocumNurse();
		if(fieldName.equals("RECOVERYHANDOVERLOCUMBOOL"))
			return getRecoveryHandoverLocumBool();
		if(fieldName.equals("RECOVERYHANDOVERLOCUMNURSE"))
			return getRecoveryHandoverLocumNurse();
		if(fieldName.equals("WARDLOCUMBOOL"))
			return getWardLocumBool();
		if(fieldName.equals("WARDLOCUMNURSE"))
			return getWardLocumNurse();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getPatientIsNotNull()
	{
		return this.patient != null;
	}
	public ims.core.patient.vo.PatientRefVo getPatient()
	{
		return this.patient;
	}
	public void setPatient(ims.core.patient.vo.PatientRefVo value)
	{
		this.isValidated = false;
		this.patient = value;
	}
	public boolean getCareContextIsNotNull()
	{
		return this.carecontext != null;
	}
	public ims.core.admin.vo.CareContextRefVo getCareContext()
	{
		return this.carecontext;
	}
	public void setCareContext(ims.core.admin.vo.CareContextRefVo value)
	{
		this.isValidated = false;
		this.carecontext = value;
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
	public boolean getRecoveryRoomNurseIsNotNull()
	{
		return this.recoveryroomnurse != null;
	}
	public ims.core.vo.NurseVo getRecoveryRoomNurse()
	{
		return this.recoveryroomnurse;
	}
	public void setRecoveryRoomNurse(ims.core.vo.NurseVo value)
	{
		this.isValidated = false;
		this.recoveryroomnurse = value;
	}
	public boolean getConfirmPatientArrivalIsNotNull()
	{
		return this.confirmpatientarrival != null;
	}
	public ims.core.vo.lookups.YesNo getConfirmPatientArrival()
	{
		return this.confirmpatientarrival;
	}
	public void setConfirmPatientArrival(ims.core.vo.lookups.YesNo value)
	{
		this.isValidated = false;
		this.confirmpatientarrival = value;
	}
	public boolean getTimeArrivesInRecoveryIsNotNull()
	{
		return this.timearrivesinrecovery != null;
	}
	public ims.framework.utils.DateTime getTimeArrivesInRecovery()
	{
		return this.timearrivesinrecovery;
	}
	public void setTimeArrivesInRecovery(ims.framework.utils.DateTime value)
	{
		this.isValidated = false;
		this.timearrivesinrecovery = value;
	}
	public boolean getTimeWardNotifiedIsNotNull()
	{
		return this.timewardnotified != null;
	}
	public ims.framework.utils.DateTime getTimeWardNotified()
	{
		return this.timewardnotified;
	}
	public void setTimeWardNotified(ims.framework.utils.DateTime value)
	{
		this.isValidated = false;
		this.timewardnotified = value;
	}
	public boolean getTimeLeavesRecoveryIsNotNull()
	{
		return this.timeleavesrecovery != null;
	}
	public ims.framework.utils.DateTime getTimeLeavesRecovery()
	{
		return this.timeleavesrecovery;
	}
	public void setTimeLeavesRecovery(ims.framework.utils.DateTime value)
	{
		this.isValidated = false;
		this.timeleavesrecovery = value;
	}
	public boolean getSentToIsNotNull()
	{
		return this.sentto != null;
	}
	public ims.core.vo.LocationLiteVo getSentTo()
	{
		return this.sentto;
	}
	public void setSentTo(ims.core.vo.LocationLiteVo value)
	{
		this.isValidated = false;
		this.sentto = value;
	}
	public boolean getHandoverfromRecoveryNurseIsNotNull()
	{
		return this.handoverfromrecoverynurse != null;
	}
	public ims.core.vo.NurseVo getHandoverfromRecoveryNurse()
	{
		return this.handoverfromrecoverynurse;
	}
	public void setHandoverfromRecoveryNurse(ims.core.vo.NurseVo value)
	{
		this.isValidated = false;
		this.handoverfromrecoverynurse = value;
	}
	public boolean getWardUnitNurseIsNotNull()
	{
		return this.wardunitnurse != null;
	}
	public ims.core.vo.NurseVo getWardUnitNurse()
	{
		return this.wardunitnurse;
	}
	public void setWardUnitNurse(ims.core.vo.NurseVo value)
	{
		this.isValidated = false;
		this.wardunitnurse = value;
	}
	public boolean getRecoveryLocumBoolIsNotNull()
	{
		return this.recoverylocumbool != null;
	}
	public Boolean getRecoveryLocumBool()
	{
		return this.recoverylocumbool;
	}
	public void setRecoveryLocumBool(Boolean value)
	{
		this.isValidated = false;
		this.recoverylocumbool = value;
	}
	public boolean getRecoveryLocumNurseIsNotNull()
	{
		return this.recoverylocumnurse != null;
	}
	public String getRecoveryLocumNurse()
	{
		return this.recoverylocumnurse;
	}
	public static int getRecoveryLocumNurseMaxLength()
	{
		return 100;
	}
	public void setRecoveryLocumNurse(String value)
	{
		this.isValidated = false;
		this.recoverylocumnurse = value;
	}
	public boolean getRecoveryHandoverLocumBoolIsNotNull()
	{
		return this.recoveryhandoverlocumbool != null;
	}
	public Boolean getRecoveryHandoverLocumBool()
	{
		return this.recoveryhandoverlocumbool;
	}
	public void setRecoveryHandoverLocumBool(Boolean value)
	{
		this.isValidated = false;
		this.recoveryhandoverlocumbool = value;
	}
	public boolean getRecoveryHandoverLocumNurseIsNotNull()
	{
		return this.recoveryhandoverlocumnurse != null;
	}
	public String getRecoveryHandoverLocumNurse()
	{
		return this.recoveryhandoverlocumnurse;
	}
	public static int getRecoveryHandoverLocumNurseMaxLength()
	{
		return 100;
	}
	public void setRecoveryHandoverLocumNurse(String value)
	{
		this.isValidated = false;
		this.recoveryhandoverlocumnurse = value;
	}
	public boolean getWardLocumBoolIsNotNull()
	{
		return this.wardlocumbool != null;
	}
	public Boolean getWardLocumBool()
	{
		return this.wardlocumbool;
	}
	public void setWardLocumBool(Boolean value)
	{
		this.isValidated = false;
		this.wardlocumbool = value;
	}
	public boolean getWardLocumNurseIsNotNull()
	{
		return this.wardlocumnurse != null;
	}
	public String getWardLocumNurse()
	{
		return this.wardlocumnurse;
	}
	public static int getWardLocumNurseMaxLength()
	{
		return 100;
	}
	public void setWardLocumNurse(String value)
	{
		this.isValidated = false;
		this.wardlocumnurse = value;
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
		if(this.patient == null)
			listOfErrors.add("Patient is mandatory");
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
		if(this.confirmpatientarrival == null)
			listOfErrors.add("ConfirmPatientArrival is mandatory");
		if(this.timearrivesinrecovery == null)
			listOfErrors.add("TimeArrivesInRecovery is mandatory");
		if(this.timewardnotified == null)
			listOfErrors.add("TimeWardNotified is mandatory");
		if(this.timeleavesrecovery == null)
			listOfErrors.add("TimeLeavesRecovery is mandatory");
		if(this.sentto == null)
			listOfErrors.add("SentTo is mandatory");
		if(this.recoverylocumnurse != null)
			if(this.recoverylocumnurse.length() > 100)
				listOfErrors.add("The length of the field [recoverylocumnurse] in the value object [ims.clinical.vo.SurgicalAuditRecoveryVo] is too big. It should be less or equal to 100");
		if(this.recoveryhandoverlocumnurse != null)
			if(this.recoveryhandoverlocumnurse.length() > 100)
				listOfErrors.add("The length of the field [recoveryhandoverlocumnurse] in the value object [ims.clinical.vo.SurgicalAuditRecoveryVo] is too big. It should be less or equal to 100");
		if(this.wardlocumnurse != null)
			if(this.wardlocumnurse.length() > 100)
				listOfErrors.add("The length of the field [wardlocumnurse] in the value object [ims.clinical.vo.SurgicalAuditRecoveryVo] is too big. It should be less or equal to 100");
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
	
		SurgicalAuditRecoveryVo clone = new SurgicalAuditRecoveryVo(this.id, this.version);
		
		clone.patient = this.patient;
		clone.carecontext = this.carecontext;
		if(this.authoringinformation == null)
			clone.authoringinformation = null;
		else
			clone.authoringinformation = (ims.core.vo.AuthoringInformationVo)this.authoringinformation.clone();
		if(this.recoveryroomnurse == null)
			clone.recoveryroomnurse = null;
		else
			clone.recoveryroomnurse = (ims.core.vo.NurseVo)this.recoveryroomnurse.clone();
		if(this.confirmpatientarrival == null)
			clone.confirmpatientarrival = null;
		else
			clone.confirmpatientarrival = (ims.core.vo.lookups.YesNo)this.confirmpatientarrival.clone();
		if(this.timearrivesinrecovery == null)
			clone.timearrivesinrecovery = null;
		else
			clone.timearrivesinrecovery = (ims.framework.utils.DateTime)this.timearrivesinrecovery.clone();
		if(this.timewardnotified == null)
			clone.timewardnotified = null;
		else
			clone.timewardnotified = (ims.framework.utils.DateTime)this.timewardnotified.clone();
		if(this.timeleavesrecovery == null)
			clone.timeleavesrecovery = null;
		else
			clone.timeleavesrecovery = (ims.framework.utils.DateTime)this.timeleavesrecovery.clone();
		if(this.sentto == null)
			clone.sentto = null;
		else
			clone.sentto = (ims.core.vo.LocationLiteVo)this.sentto.clone();
		if(this.handoverfromrecoverynurse == null)
			clone.handoverfromrecoverynurse = null;
		else
			clone.handoverfromrecoverynurse = (ims.core.vo.NurseVo)this.handoverfromrecoverynurse.clone();
		if(this.wardunitnurse == null)
			clone.wardunitnurse = null;
		else
			clone.wardunitnurse = (ims.core.vo.NurseVo)this.wardunitnurse.clone();
		clone.recoverylocumbool = this.recoverylocumbool;
		clone.recoverylocumnurse = this.recoverylocumnurse;
		clone.recoveryhandoverlocumbool = this.recoveryhandoverlocumbool;
		clone.recoveryhandoverlocumnurse = this.recoveryhandoverlocumnurse;
		clone.wardlocumbool = this.wardlocumbool;
		clone.wardlocumnurse = this.wardlocumnurse;
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
		if (!(SurgicalAuditRecoveryVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A SurgicalAuditRecoveryVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((SurgicalAuditRecoveryVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((SurgicalAuditRecoveryVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.patient != null)
			count++;
		if(this.carecontext != null)
			count++;
		if(this.authoringinformation != null)
			count++;
		if(this.recoveryroomnurse != null)
			count++;
		if(this.confirmpatientarrival != null)
			count++;
		if(this.timearrivesinrecovery != null)
			count++;
		if(this.timewardnotified != null)
			count++;
		if(this.timeleavesrecovery != null)
			count++;
		if(this.sentto != null)
			count++;
		if(this.handoverfromrecoverynurse != null)
			count++;
		if(this.wardunitnurse != null)
			count++;
		if(this.recoverylocumbool != null)
			count++;
		if(this.recoverylocumnurse != null)
			count++;
		if(this.recoveryhandoverlocumbool != null)
			count++;
		if(this.recoveryhandoverlocumnurse != null)
			count++;
		if(this.wardlocumbool != null)
			count++;
		if(this.wardlocumnurse != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 17;
	}
	protected ims.core.patient.vo.PatientRefVo patient;
	protected ims.core.admin.vo.CareContextRefVo carecontext;
	protected ims.core.vo.AuthoringInformationVo authoringinformation;
	protected ims.core.vo.NurseVo recoveryroomnurse;
	protected ims.core.vo.lookups.YesNo confirmpatientarrival;
	protected ims.framework.utils.DateTime timearrivesinrecovery;
	protected ims.framework.utils.DateTime timewardnotified;
	protected ims.framework.utils.DateTime timeleavesrecovery;
	protected ims.core.vo.LocationLiteVo sentto;
	protected ims.core.vo.NurseVo handoverfromrecoverynurse;
	protected ims.core.vo.NurseVo wardunitnurse;
	protected Boolean recoverylocumbool;
	protected String recoverylocumnurse;
	protected Boolean recoveryhandoverlocumbool;
	protected String recoveryhandoverlocumnurse;
	protected Boolean wardlocumbool;
	protected String wardlocumnurse;
	private boolean isValidated = false;
	private boolean isBusy = false;
}
