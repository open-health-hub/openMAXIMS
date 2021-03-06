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

package ims.core.vo.beans;

public class WardDataViewVoBean extends ims.vo.ValueObjectBean
{
	public WardDataViewVoBean()
	{
	}
	public WardDataViewVoBean(ims.core.vo.WardDataViewVo vo)
	{
		this.patient = vo.getPatient() == null ? null : (ims.core.vo.beans.PatientShortBean)vo.getPatient().getBean();
		this.bay = vo.getBay() == null ? null : (ims.core.vo.beans.LocationLiteVoBean)vo.getBay().getBean();
		this.bed = vo.getBed() == null ? null : (ims.core.vo.beans.BedSpaceVoBean)vo.getBed().getBean();
		this.pasevent = vo.getPasEvent() == null ? null : (ims.core.vo.beans.PasEventADTVoBean)vo.getPasEvent().getBean();
		this.inpatepisode = vo.getInpatEpisode() == null ? null : (ims.clinical.vo.beans.InpatientEpisodeForVTERiskAsessmentVoBean)vo.getInpatEpisode().getBean();
	}

	public void populate(ims.vo.ValueObjectBeanMap map, ims.core.vo.WardDataViewVo vo)
	{
		this.patient = vo.getPatient() == null ? null : (ims.core.vo.beans.PatientShortBean)vo.getPatient().getBean(map);
		this.bay = vo.getBay() == null ? null : (ims.core.vo.beans.LocationLiteVoBean)vo.getBay().getBean(map);
		this.bed = vo.getBed() == null ? null : (ims.core.vo.beans.BedSpaceVoBean)vo.getBed().getBean(map);
		this.pasevent = vo.getPasEvent() == null ? null : (ims.core.vo.beans.PasEventADTVoBean)vo.getPasEvent().getBean(map);
		this.inpatepisode = vo.getInpatEpisode() == null ? null : (ims.clinical.vo.beans.InpatientEpisodeForVTERiskAsessmentVoBean)vo.getInpatEpisode().getBean(map);
	}

	public ims.core.vo.WardDataViewVo buildVo()
	{
		return this.buildVo(new ims.vo.ValueObjectBeanMap());
	}

	public ims.core.vo.WardDataViewVo buildVo(ims.vo.ValueObjectBeanMap map)
	{
		ims.core.vo.WardDataViewVo vo = null;
		if(map != null)
			vo = (ims.core.vo.WardDataViewVo)map.getValueObject(this);
		if(vo == null)
		{
			vo = new ims.core.vo.WardDataViewVo();
			map.addValueObject(this, vo);
			vo.populate(map, this);
		}
		return vo;
	}

	public ims.core.vo.beans.PatientShortBean getPatient()
	{
		return this.patient;
	}
	public void setPatient(ims.core.vo.beans.PatientShortBean value)
	{
		this.patient = value;
	}
	public ims.core.vo.beans.LocationLiteVoBean getBay()
	{
		return this.bay;
	}
	public void setBay(ims.core.vo.beans.LocationLiteVoBean value)
	{
		this.bay = value;
	}
	public ims.core.vo.beans.BedSpaceVoBean getBed()
	{
		return this.bed;
	}
	public void setBed(ims.core.vo.beans.BedSpaceVoBean value)
	{
		this.bed = value;
	}
	public ims.core.vo.beans.PasEventADTVoBean getPasEvent()
	{
		return this.pasevent;
	}
	public void setPasEvent(ims.core.vo.beans.PasEventADTVoBean value)
	{
		this.pasevent = value;
	}
	public ims.clinical.vo.beans.InpatientEpisodeForVTERiskAsessmentVoBean getInpatEpisode()
	{
		return this.inpatepisode;
	}
	public void setInpatEpisode(ims.clinical.vo.beans.InpatientEpisodeForVTERiskAsessmentVoBean value)
	{
		this.inpatepisode = value;
	}

	private ims.core.vo.beans.PatientShortBean patient;
	private ims.core.vo.beans.LocationLiteVoBean bay;
	private ims.core.vo.beans.BedSpaceVoBean bed;
	private ims.core.vo.beans.PasEventADTVoBean pasevent;
	private ims.clinical.vo.beans.InpatientEpisodeForVTERiskAsessmentVoBean inpatepisode;
}
