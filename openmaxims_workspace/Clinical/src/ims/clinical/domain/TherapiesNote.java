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

package ims.clinical.domain;

// Generated from form domain impl
public interface TherapiesNote extends ims.domain.DomainInterface
{
	// Generated from form domain interface definition
	/**
	* saveClinicalNotes
	*/
	public ims.core.vo.ClinicalNotesVo saveClinicalNotes(ims.core.vo.ClinicalNotesVo clinicalNotesVo) throws ims.domain.exceptions.DomainInterfaceException, ims.domain.exceptions.StaleObjectException;

	// Generated from form domain interface definition
	/**
	* Save the SOAPVo
	*/
	public void saveSOAPsVo(ims.core.vo.SOAPVo voSOAP) throws ims.domain.exceptions.DomainInterfaceException, ims.domain.exceptions.StaleObjectException, ims.domain.exceptions.UniqueKeyViolationException;

	// Generated from form domain interface definition
	/**
	* Get SOAPVo
	*/
	public ims.core.vo.SOAPVo getSOAPsVo(ims.core.clinical.vo.ClinicalNotesRefVo clinnoteRefVo);

	// Generated from form domain interface definition
	/**
	* getMemberOfStaff
	*/
	public ims.core.vo.MemberOfStaffVo getMemberOfStaff(ims.core.vo.MemberOfStaffShortVo mos);

	// Generated from form domain interface definition
	/**
	* getClinicalNotesForCareContext
	*/
	public ims.core.vo.ClinicalNotesVo getClinicalNotesForCareContext(ims.core.admin.vo.CareContextRefVo careContxetRefVo);

	// Generated from form domain interface definition
	/**
	* Get Cre Context for ref
	*/
	public ims.core.vo.CareContextVo getCurrentCareContext(ims.core.admin.vo.CareContextRefVo careContextRefVo);

	// Generated from form domain interface definition
	public ims.core.vo.ClinicalNotesVo getClinicalNote(ims.core.clinical.vo.ClinicalNotesRefVo clinicalNoteRefVo);

	// Generated from form domain interface definition
	public ims.core.vo.ClinicalNotesVoCollection getAllClinicalNotesForCareContext(ims.core.admin.vo.CareContextRefVo careContextRefVo);
}