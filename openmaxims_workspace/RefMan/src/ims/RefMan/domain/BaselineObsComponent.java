// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.domain;

// Generated from form domain impl
public interface BaselineObsComponent extends ims.domain.DomainInterface
{
	// Generated from form domain interface definition
	/**
	* saveBaselineObs
	*/
	public ims.RefMan.vo.BaselineObservationsVo saveBaselineObs(ims.RefMan.vo.BaselineObservationsVo voBaselineObs) throws ims.domain.exceptions.StaleObjectException;

	// Generated from form domain interface definition
	/**
	* get BaselineObs
	*/
	public ims.RefMan.vo.BaselineObservationsVo getBaselineObs(ims.RefMan.vo.CatsReferralRefVo voCATSReferralRef);

	// Generated from form domain interface definition
	/**
	* lists
	*/
	public ims.core.vo.MemberOfStaffShortVoCollection listMOS(ims.core.vo.MemberOfStaffShortVo voMOSFilter);

	// Generated from form domain interface definition
	public ims.core.vo.MemberOfStaffVo getMemberOfStaff(ims.core.vo.MemberOfStaffShortVo mos);

	// Generated from form domain interface definition
	public ims.core.vo.HcpLiteVo getHCP(ims.core.vo.MemberOfStaffShortVo voMOS);

	// Generated from form domain interface definition
	public ims.core.vo.MemberOfStaffVo getMos(ims.core.vo.HcpLiteVo voHCP);

	// Generated from form domain interface definition
	public ims.RefMan.vo.PresentationReferralSummaryVo getPresentationReferralSummary(ims.RefMan.vo.CatsReferralRefVo catsRefVo);

	// Generated from form domain interface definition
	/**
	* List Allergens
	*/
	public ims.core.vo.AllergenVoCollection listActiveAllergies(String filter) throws ims.domain.exceptions.DomainInterfaceException;
}