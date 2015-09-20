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
// This code was generated by Vasile Purdila using IMS Development Environment (version 1.20 build 40813.1700)
// Copyright (C) 1995-2004 IMS MAXIMS plc. All rights reserved.

package ims.nursing.domain.impl;

import ims.coe.assessment.domain.objects.MobilityComponent;
import ims.coe.assessmenttools.domain.objects.FallAssessment;
import ims.coe.vo.AssessmentMobility;
import ims.coe.vo.domain.AssessmentMobilityAssembler;
import ims.coe.vo.domain.FallAssessmentAssembler;
import ims.domain.exceptions.DomainException;
import ims.domain.DomainFactory;
import ims.domain.exceptions.DomainInterfaceException;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.impl.DomainImpl;


public class FallAssessmentDialogImpl extends DomainImpl implements ims.nursing.domain.FallAssessmentDialog, ims.domain.impl.Transactional
{
	private static final long	serialVersionUID	= 1L;

	/**
	* Retrieves header record
	*/
	public ims.coe.vo.FallAssessment getRecord(Integer recordID) 
	{
		DomainFactory factory = getDomainFactory();
		FallAssessment domAssess = (FallAssessment)factory.getDomainObject(FallAssessment.class, recordID);
		return(FallAssessmentAssembler.create(domAssess));
	}
	
	public Boolean isPartOfMobilityAssessment(
			ims.coe.vo.FallAssessment fallRecord)
			throws DomainInterfaceException
	{
		DomainFactory factory = getDomainFactory();
		
		FallAssessment domFall = FallAssessmentAssembler.extractFallAssessment(factory, fallRecord);
		java.util.List fall = factory.find("from MobilityComponent M where M.optionalFallAssessment = :ofall", "ofall", domFall);
		
		if(fall == null || fall.size() == 0)
			return Boolean.FALSE;
		
		return Boolean.TRUE;
	}

	/**
	* Save the record
	*/
	public AssessmentMobility saveRecord(ims.coe.vo.FallAssessment voFallAssessment, ims.coe.vo.AssessmentMobility component) throws DomainInterfaceException, StaleObjectException
	{
		// Ensure the Value Object was validated
		if (!voFallAssessment.isValidated())
			throw new DomainRuntimeException("This FallAssessment has not been validated");
			
		DomainFactory factory = getDomainFactory();
		FallAssessment domAssessment = null;
		if(component==null)
			domAssessment = FallAssessmentAssembler.extractFallAssessment(factory, voFallAssessment);

		try
		{
			if(component==null){
				factory.save(domAssessment);
				return null;
			}
			else
			{
				//ims.coe.vo.FallAssessment fall = FallAssessmentAssembler.create(domAssessment);
				component.validate();

				component.setOptionalFallAssessment(voFallAssessment);
				
				MobilityComponent compDO = AssessmentMobilityAssembler.extractMobilityComponent(factory, component);
				//compDO.setOptionalFallAssessment(domAssessment);
				
				factory.save(compDO);
				return AssessmentMobilityAssembler.create(compDO);
			}
		}
		catch (DomainException e)
		{
			throw new DomainRuntimeException("DomainException occurred.\r\n" + e.getMessage(), e);
		}		
	}
	
	/**
	* Save the record
	*/
/*	public ims.coe.vo.FallAssessment saveRecord(ims.coe.vo.FallAssessment voFallAssessment, ims.core.vo.ClinicalEpisode clinicalEpisode, AssessmentComponent comp) throws StaleObjectException
	{
		// Ensure the Value Object was validated
		if (!voFallAssessment.isValidated())
			throw new DomainRuntimeException("This FallAssessment has not been validated");
			
		DomainFactory factory = getDomainFactory();			
		FallAssessment domAssessment = FallAssessmentAssembler.extractFallAssessment(factory, voFallAssessment);

		// Get the Episode and attached for the forms commenced
		ClinicalEpisode domEpis = (ClinicalEpisode)factory.getDomainObject(ClinicalEpisode.class, clinicalEpisode.getID_ClinicalEpisode());
		if(!NursingDetailsAccessor.class.isAssignableFrom(domEpis.getClass()))
		{
			throw new DomainRuntimeException("The episode does not contain Nursing Details");
		}
		NursingDetailsAccessor nsAccess = (NursingDetailsAccessor)domEpis;
		NursingEpisDetails doNursingEpisDetails = nsAccess.getNursingEpisDetails();
		
		try
		{
			factory.save(domAssessment);
			
			if (doNursingEpisDetails == null)
			{
				doNursingEpisDetails = new NursingEpisDetails();
				factory.save(doNursingEpisDetails);
				nsAccess.setNursingEpisDetails(doNursingEpisDetails);
			}

			doNursingEpisDetails.getAssessments().add(domAssessment);
			
			// If the component is not null
			if (comp != null)
			{
				ims.coe.vo.FallAssessment fall = FallAssessmentAssembler.create(domAssessment);
				fall.validate();

				((AssessmentMobility)comp).setOptionalFallAssessment(fall);
				
				MobilityComponent compDO = AssessmentMobilityAssembler.extractMobilityComponent(factory, (AssessmentMobility)comp);
				//compDO.setOptionalFallAssessment(domAssessment);
				factory.save(compDO);
				factory.save(domEpis);
				return fall;
			}
			else
			{
				factory.save(domEpis);
				return FallAssessmentAssembler.create(domAssessment);
			}			
		}
		catch (DomainException e)
		{
			throw new DomainRuntimeException("DomainException occurred.\r\n" + e.getMessage(), e);
		}		
	}
*/
}