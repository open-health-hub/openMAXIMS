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

package ims.scheduling.vo.beans;

public class SessionServiceAndSlotActivityVoBean extends ims.vo.ValueObjectBean
{
	public SessionServiceAndSlotActivityVoBean()
	{
	}
	public SessionServiceAndSlotActivityVoBean(ims.scheduling.vo.SessionServiceAndSlotActivityVo vo)
	{
		this.slotactivity = vo.getSlotActivity() == null ? null : new ims.vo.RefVoBean(vo.getSlotActivity().getBoId(), vo.getSlotActivity().getBoVersion());
		this.sessionservice = vo.getSessionService() == null ? null : (ims.core.vo.beans.ServiceLiteVoBean)vo.getSessionService().getBean();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.scheduling.vo.SessionServiceAndSlotActivityVo vo)
	{
		this.slotactivity = vo.getSlotActivity() == null ? null : new ims.vo.RefVoBean(vo.getSlotActivity().getBoId(), vo.getSlotActivity().getBoVersion());
		this.sessionservice = vo.getSessionService() == null ? null : (ims.core.vo.beans.ServiceLiteVoBean)vo.getSessionService().getBean(map);
	}

	public ims.scheduling.vo.SessionServiceAndSlotActivityVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.scheduling.vo.SessionServiceAndSlotActivityVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.scheduling.vo.SessionServiceAndSlotActivityVo vo = null;
		if(map != null)
			vo = (ims.scheduling.vo.SessionServiceAndSlotActivityVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.scheduling.vo.SessionServiceAndSlotActivityVo();
			map.addValueObject(this, vo);
			vo.populate(map, this);
		}
		return vo;
	}

	public ims.vo.RefVoBean getSlotActivity()
	{
		return this.slotactivity;
	}
	public void setSlotActivity(ims.vo.RefVoBean value)
	{
		this.slotactivity = value;
	}
	public ims.core.vo.beans.ServiceLiteVoBean getSessionService()
	{
		return this.sessionservice;
	}
	public void setSessionService(ims.core.vo.beans.ServiceLiteVoBean value)
	{
		this.sessionservice = value;
	}

	private ims.vo.RefVoBean slotactivity;
	private ims.core.vo.beans.ServiceLiteVoBean sessionservice;
}
