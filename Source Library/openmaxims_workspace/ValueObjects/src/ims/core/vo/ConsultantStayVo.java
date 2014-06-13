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
 * Linked to core.admin.pas.Consultant Stay business object (ID: 1014100005).
 */
public class ConsultantStayVo extends ims.core.admin.pas.vo.ConsultantStayRefVo implements ims.vo.ImsCloneable, Comparable
{
	private static final long serialVersionUID = 1L;

	public ConsultantStayVo()
	{
	}
	public ConsultantStayVo(Integer id, int version)
	{
		super(id, version);
	}
	public ConsultantStayVo(ims.core.vo.beans.ConsultantStayVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		// Interface field type not supported.
		this.transferdatetime = bean.getTransferDateTime() == null ? null : bean.getTransferDateTime().buildDateTime();
	}
	public void populate(ims.vo.ValueObjectBeanMap map, ims.core.vo.beans.ConsultantStayVoBean bean)
	{
		this.id = bean.getId();
		this.version = bean.getVersion();
		// Interface field type not supported.
		this.transferdatetime = bean.getTransferDateTime() == null ? null : bean.getTransferDateTime().buildDateTime();
	}
	public ims.vo.ValueObjectBean getBean()
	{
		return this.getBean(new ims.vo.ValueObjectBeanMap());
	}
	public ims.vo.ValueObjectBean getBean(ims.vo.ValueObjectBeanMap map)
	{
		ims.core.vo.beans.ConsultantStayVoBean bean = null;
		if(map != null)
			bean = (ims.core.vo.beans.ConsultantStayVoBean)map.getValueObjectBean(this);
		if (bean == null)
		{
			bean = new ims.core.vo.beans.ConsultantStayVoBean();
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
		if(fieldName.equals("CONSULTANT"))
			return getConsultant();
		if(fieldName.equals("TRANSFERDATETIME"))
			return getTransferDateTime();
		return super.getFieldValueByFieldName(fieldName);
	}
	public boolean getConsultantIsNotNull()
	{
		return this.consultant != null;
	}
	public ims.vo.interfaces.IMos getConsultant()
	{
		return this.consultant;
	}
	public void setConsultant(ims.vo.interfaces.IMos value)
	{
		this.isValidated = false;
		this.consultant = (ims.core.vo.MedicVo)value;
	}
	public boolean getTransferDateTimeIsNotNull()
	{
		return this.transferdatetime != null;
	}
	public ims.framework.utils.DateTime getTransferDateTime()
	{
		return this.transferdatetime;
	}
	public void setTransferDateTime(ims.framework.utils.DateTime value)
	{
		this.isValidated = false;
		this.transferdatetime = value;
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
	
		ConsultantStayVo clone = new ConsultantStayVo(this.id, this.version);
		
		clone.consultant = this.consultant;
		if(this.transferdatetime == null)
			clone.transferdatetime = null;
		else
			clone.transferdatetime = (ims.framework.utils.DateTime)this.transferdatetime.clone();
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
		if (!(ConsultantStayVo.class.isAssignableFrom(obj.getClass())))
		{
			throw new ClassCastException("A ConsultantStayVo object cannot be compared an Object of type " + obj.getClass().getName());
		}
		if (this.id == null)
			return 1;
		if (((ConsultantStayVo)obj).getBoId() == null)
			return -1;
		return this.id.compareTo(((ConsultantStayVo)obj).getBoId());
	}
	public synchronized static int generateValueObjectUniqueID()
	{
		return ims.vo.ValueObject.generateUniqueID();
	}
	public int countFieldsWithValue()
	{
		int count = 0;
		if(this.consultant != null)
			count++;
		if(this.transferdatetime != null)
			count++;
		return count;
	}
	public int countValueObjectFields()
	{
		return 2;
	}
	protected ims.core.vo.MedicVo consultant;
	protected ims.framework.utils.DateTime transferdatetime;
	private boolean isValidated = false;
	private boolean isBusy = false;
}