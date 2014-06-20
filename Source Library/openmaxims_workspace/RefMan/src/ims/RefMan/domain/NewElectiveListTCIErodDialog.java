// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.domain;

// Generated from form domain impl
public interface NewElectiveListTCIErodDialog extends ims.domain.DomainInterface
{
	// Generated from form domain interface definition
	public ims.RefMan.vo.CatsReferralforElectiveListDetailsVo getReferralDetails(ims.RefMan.vo.CatsReferralRefVo referralRef);

	// Generated from form domain interface definition
	public ims.admin.vo.ElectiveListConfigurationVoCollection getElectiveListConfigForHCP(Integer hcpId, Integer serviceId);

	// Generated from form domain interface definition
	public ims.admin.vo.ElectiveListConfigurationVoCollection getElectiveListConfigForService(Integer serviceId);

	// Generated from form domain interface definition
	public ims.admin.vo.ElectiveListConfigurationVoCollection getElectiveListsBySpecialty(Integer specialtyId);

	// Generated from form domain interface definition
	public ims.core.vo.ProcedureLiteVo getProcedureFromSuitableForSurgery(ims.core.admin.vo.CareContextRefVo careContextRef);

	// Generated from form domain interface definition
	public ims.core.vo.ProcedureLiteVoCollection listProcedures(String name) throws ims.domain.exceptions.DomainInterfaceException;

	// Generated from form domain interface definition
	public ims.RefMan.vo.PatientElectiveListDetailsToSaveVo getPatientElectiveList(ims.RefMan.vo.PatientElectiveListRefVo electiveListRef);

	// Generated from form domain interface definition
	public ims.core.vo.LocationLiteVoCollection listWards(String name, ims.core.resource.place.vo.LocationRefVo locationRef);

	// Generated from form domain interface definition
	public ims.RefMan.vo.PatientElectiveListDetailsToSaveVo save(ims.RefMan.vo.PatientElectiveListDetailsToSaveVo patientElectiveList) throws ims.domain.exceptions.StaleObjectException;

	// Generated from form domain interface definition
	public Boolean isFitForSurgery(ims.core.admin.vo.CareContextRefVo carecontextRef);

	// Generated from form domain interface definition
	public Boolean isStaleNote(ims.RefMan.vo.PatientElectiveListNotesVo patientElectiveListNote);

	// Generated from form domain interface definition
	public ims.core.vo.LocationLiteVoCollection listHospitals(String name);

	// Generated from form domain interface definition
	public ims.admin.vo.ElectiveListConfigurationVoCollection getElectiveList();

	// Generated from form domain interface definition
	public ims.scheduling.vo.Booking_AppointmentVo getBookingAppointment(ims.scheduling.vo.Booking_AppointmentRefVo appt);

	// Generated from form domain interface definition
	public ims.scheduling.vo.Booking_AppointmentVo cancelAppt(ims.scheduling.vo.Booking_AppointmentVo appt, ims.chooseandbook.vo.lookups.ActionRequestType requestType, String requestSource) throws ims.domain.exceptions.StaleObjectException;

	// Generated from form domain interface definition
	public ims.RefMan.vo.SuspensionDetailsForPatientElectiveListVoCollection getSuspensionsForPatientElectiveList(ims.RefMan.vo.PatientElectiveListRefVo patientElectiveListRef);
}