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

package ims.ocrr.vo;

/**
 * Linked to OCRR.OrderingResults.SpecimenWorkListItem business object (ID: 1061100014).
 */
public class SpecimenWorkListItemVo extends ims.ocrr.orderingresults.vo.SpecimenWorkListItemRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public SpecimenWorkListItemVo()
	{
	}
	public SpecimenWorkListItemVo(Integer id, int version)
	{
		super(id, version);
	}
	public SpecimenWorkListItemVo(ims.ocrr.vo.beans.SpecimenWorkListItemVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.specimen = bean.getSpecimen() == null ? null : new ims.ocrr.orderingresults.vo.OrderSpecimenRefVo(new Integer(bean.getSpecimen().getId()), bean.getSpecimen().getVersion());
		this.listtype = bean.getListType() == null ? null : ims.ocrr.vo.lookups.SpecimenCollectionMethod.buildLookup(bean.getListType());
		this.datetocollect = bean.getDateToCollect() == null ? null : bean.getDateToCollect().buildDate();
		this.roundtocollect = bean.getRoundToCollect() == null ? null : ims.ocrr.vo.lookups.SpecimenCollectionTime.buildLookup(bean.getRoundToCollect());
		this.timetocollect = bean.getTimeToCollect() == null ? null : bean.getTimeToCollect().buildTime();
		this.collectionstatus = bean.getCollectionStatus() == null ? null : ims.ocrr.vo.lookups.SpecimenCollectionStatus.buildLookup(bean.getCollectionStatus());
		this.dftorderinvestigation = bean.getDFTOrderInvestigation() == null ? null : new ims.ocrr.orderingresults.vo.OrderInvestigationRefVo(new Integer(bean.getDFTOrderInvestigation().getId()), bean.getDFTOrderInvestigation().getVersion());
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.ocrr.vo.beans.SpecimenWorkListItemVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		this.specimen = bean.getSpecimen() == null ? null : new ims.ocrr.orderingresults.vo.OrderSpecimenRefVo(new Integer(bean.getSpecimen().getId()), bean.getSpecimen().getVersion());
		this.listtype = bean.getListType() == null ? null : ims.ocrr.vo.lookups.SpecimenCollectionMethod.buildLookup(bean.getListType());
		this.datetocollect = bean.getDateToCollect() == null ? null : bean.getDateToCollect().buildDate();
		this.roundtocollect = bean.getRoundToCollect() == null ? null : ims.ocrr.vo.lookups.SpecimenCollectionTime.buildLookup(bean.getRoundToCollect());
		this.timetocollect = bean.getTimeToCollect() == null ? null : bean.getTimeToCollect().buildTime();
		this.collectionstatus = bean.getCollectionStatus() == null ? null : ims.ocrr.vo.lookups.SpecimenCollectionStatus.buildLookup(bean.getCollectionStatus());
		this.dftorderinvestigation = bean.getDFTOrderInvestigation() == null ? null : new ims.ocrr.orderingresults.vo.OrderInvestigationRefVo(new Integer(bean.getDFTOrderInvestigation().getId()), bean.getDFTOrderInvestigation().getVersion());
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.ocrr.vo.beans.SpecimenWorkListItemVoBean bean = null;
		if(map != null)
			bean = (ims.ocrr.vo.beans.SpecimenWorkListItemVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.ocrr.vo.beans.SpecimenWorkListItemVoBean();
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
		if(fieldName.equals("SPECIMEN"))
			return getSpecimen();
		if(fieldName.equals("LISTTYPE"))
			return getListType();
		if(fieldName.equals("DATETOCOLLECT"))
			return getDateToCollect();
		if(fieldName.equals("ROUNDTOCOLLECT"))
			return getRoundToCollect();
		if(fieldName.equals("TIMETOCOLLECT"))
			return getTimeToCollect();
		if(fieldName.equals("COLLECTIONSTATUS"))
			return getCollectionStatus();
		if(fieldName.equals("DFTORDERINVESTIGATION"))
			return getDFTOrderInvestigation();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getSpecimenIsNotNull()
	{
		return this.specimen != null;
	}
	public ims.ocrr.orderingresults.vo.OrderSpecimenRefVo getSpecimen()
	{
		return this.specimen;
	}
	public void setSpecimen(ims.ocrr.orderingresults.vo.OrderSpecimenRefVo value)
	{
		this.isValidated = false;
		this.specimen = value;
	}
	public boolean getListTypeIsNotNull()
	{
		return this.listtype != null;
	}
	public ims.ocrr.vo.lookups.SpecimenCollectionMethod getListType()
	{
		return this.listtype;
	}
	public void setListType(ims.ocrr.vo.lookups.SpecimenCollectionMethod value)
	{
		this.isValidated = false;
		this.listtype = value;
	}
	public boolean getDateToCollectIsNotNull()
	{
		return this.datetocollect != null;
	}
	public ims.framework.utils.Date getDateToCollect()
	{
		return this.datetocollect;
	}
	public void setDateToCollect(ims.framework.utils.Date value)
	{
		this.isValidated = false;
		this.datetocollect = value;
	}
	public boolean getRoundToCollectIsNotNull()
	{
		return this.roundtocollect != null;
	}
	public ims.ocrr.vo.lookups.SpecimenCollectionTime getRoundToCollect()
	{
		return this.roundtocollect;
	}
	public void setRoundToCollect(ims.ocrr.vo.lookups.SpecimenCollectionTime value)
	{
		this.isValidated = false;
		this.roundtocollect = value;
	}
	public boolean getTimeToCollectIsNotNull()
	{
		return this.timetocollect != null;
	}
	public ims.framework.utils.Time getTimeToCollect()
	{
		return this.timetocollect;
	}
	public void setTimeToCollect(ims.framework.utils.Time value)
	{
		this.isValidated = false;
		this.timetocollect = value;
	}
	public boolean getCollectionStatusIsNotNull()
	{
		return this.collectionstatus != null;
	}
	public ims.ocrr.vo.lookups.SpecimenCollectionStatus getCollectionStatus()
	{
		return this.collectionstatus;
	}
	public void setCollectionStatus(ims.ocrr.vo.lookups.SpecimenCollectionStatus value)
	{
		this.isValidated = false;
		this.collectionstatus = value;
	}
	public boolean getDFTOrderInvestigationIsNotNull()
	{
		return this.dftorderinvestigation != null;
	}
	public ims.ocrr.orderingresults.vo.OrderInvestigationRefVo getDFTOrderInvestigation()
	{
		return this.dftorderinvestigation;
	}
	public void setDFTOrderInvestigation(ims.ocrr.orderingresults.vo.OrderInvestigationRefVo value)
	{
		this.isValidated = false;
		this.dftorderinvestigation = value;
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
		if(this.listtype == null)
			listOfErrors.add("ListType is mandatory");
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
	
		SpecimenWorkListItemVo clone = new SpecimenWorkListItemVo(this.id, this.version);
		
		clone.specimen = this.specimen;
		if(this.listtype == null)
			clone.listtype = null;
		else
			clone.listtype = (ims.ocrr.vo.lookups.SpecimenCollectionMethod)this.listtype.clone();
		if(this.datetocollect == null)
			clone.datetocollect = null;
		else
			clone.datetocollect = (ims.framework.utils.Date)this.datetocollect.clone();
		if(this.roundtocollect == null)
			clone.roundtocollect = null;
		else
			clone.roundtocollect = (ims.ocrr.vo.lookups.SpecimenCollectionTime)this.roundtocollect.clone();
		if(this.timetocollect == null)
			clone.timetocollect = null;
		else
			clone.timetocollect = (ims.framework.utils.Time)this.timetocollect.clone();
		if(this.collectionstatus == null)
			clone.collectionstatus = null;
		else
			clone.collectionstatus = (ims.ocrr.vo.lookups.SpecimenCollectionStatus)this.collectionstatus.clone();
		clone.dftorderinvestigation = this.dftorderinvestigation;
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
		if (!(SpecimenWorkListItemVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A SpecimenWorkListItemVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((SpecimenWorkListItemVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((SpecimenWorkListItemVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.specimen != null)
			count++;
		if(this.listtype != null)
			count++;
		if(this.datetocollect != null)
			count++;
		if(this.roundtocollect != null)
			count++;
		if(this.timetocollect != null)
			count++;
		if(this.collectionstatus != null)
			count++;
		if(this.dftorderinvestigation != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 7;
	}
	protected ims.ocrr.orderingresults.vo.OrderSpecimenRefVo specimen;
	protected ims.ocrr.vo.lookups.SpecimenCollectionMethod listtype;
	protected ims.framework.utils.Date datetocollect;
	protected ims.ocrr.vo.lookups.SpecimenCollectionTime roundtocollect;
	protected ims.framework.utils.Time timetocollect;
	protected ims.ocrr.vo.lookups.SpecimenCollectionStatus collectionstatus;
	protected ims.ocrr.orderingresults.vo.OrderInvestigationRefVo dftorderinvestigation;
	private boolean isValidated = false;
	private boolean isBusy = false;
}