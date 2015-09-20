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

package ims.pathways.vo.beans;

public class WorkListTargetCriteriaVoBean extends ims.vo.ValueObjectBean
{
	public WorkListTargetCriteriaVoBean()
	{
	}
	public WorkListTargetCriteriaVoBean(ims.pathways.vo.WorkListTargetCriteriaVo vo)
	{
		this.target = vo.getTarget() == null ? null : new ims.vo.RefVoBean(vo.getTarget().getBoId(), vo.getTarget().getBoVersion());
		this.status = vo.getStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getStatus().getBean();
		this.noofweeks = vo.getNoOfWeeks();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.pathways.vo.WorkListTargetCriteriaVo vo)
	{
		this.target = vo.getTarget() == null ? null : new ims.vo.RefVoBean(vo.getTarget().getBoId(), vo.getTarget().getBoVersion());
		this.status = vo.getStatus() == null ? null : (ims.vo.LookupInstanceBean)vo.getStatus().getBean();
		this.noofweeks = vo.getNoOfWeeks();
	}

	public ims.pathways.vo.WorkListTargetCriteriaVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.pathways.vo.WorkListTargetCriteriaVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.pathways.vo.WorkListTargetCriteriaVo vo = null;
		if(map != null)
			vo = (ims.pathways.vo.WorkListTargetCriteriaVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.pathways.vo.WorkListTargetCriteriaVo();
			map.addValueObject(this, vo);
			vo.populate(map, this);
		}
		return vo;
	}

	public ims.vo.RefVoBean getTarget()
	{
		return this.target;
	}
	public void setTarget(ims.vo.RefVoBean value)
	{
		this.target = value;
	}
	public ims.vo.LookupInstanceBean getStatus()
	{
		return this.status;
	}
	public void setStatus(ims.vo.LookupInstanceBean value)
	{
		this.status = value;
	}
	public Integer getNoOfWeeks()
	{
		return this.noofweeks;
	}
	public void setNoOfWeeks(Integer value)
	{
		this.noofweeks = value;
	}

	private ims.vo.RefVoBean target;
	private ims.vo.LookupInstanceBean status;
	private Integer noofweeks;
}