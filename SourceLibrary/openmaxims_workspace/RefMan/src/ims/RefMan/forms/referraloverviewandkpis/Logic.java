// This code was generated by Cristian Belciug using IMS Development Environment (version 1.65 build 3198.21681)
// Copyright (C) 1995-2008 IMS MAXIMS plc. All rights reserved.

package ims.RefMan.forms.referraloverviewandkpis;

import ims.RefMan.vo.CatsReferralListVo;
import ims.RefMan.vo.CatsReferralRefVo;
import ims.RefMan.vo.CatsReferralStatusVoCollection;
import ims.RefMan.vo.lookups.ReferralApptStatus;
import ims.configuration.gen.ConfigFlag;
import ims.icp.vo.PatientICPFullVo;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		initialize();
	}
	
	private void open()
	{
		if(form.getLocalContext().getCurrentCatsReferralIsNotNull() && form.getLocalContext().getCurrentCatsReferral().getID_CatsReferralIsNotNull())
		{
			CatsReferralListVo catsReferralFull;
			catsReferralFull = domain.getCatsReferral(form.getLocalContext().getCurrentCatsReferral());
			form.getLocalContext().setCatsReferralFull(catsReferralFull);
			populateScreenFromData(catsReferralFull);
			
			form.lblICP().setVisible(false);
			form.txtICP().setVisible(false);
			if (ConfigFlag.GEN.CREATE_ICP_ON_ACCEPTANCE_OF_REFERRAL.getValue())
			{
				form.lblICP().setVisible(true);
				form.txtICP().setVisible(true);
				form.txtICP().setEnabled(false);

				PatientICPFullVo voICP = domain.getAssociatedICP(form.getLocalContext().getCatsReferralFull().getCareContext());
				populateICP(voICP);
			}
		}
		
		updateControlState();
	}
	
	private void populateICP(PatientICPFullVo voICP) 
	{
		if (voICP == null)
			return;
		
		StringBuffer sb = new StringBuffer();
		sb.append("Started: ");
		sb.append(voICP.getStartedDateTimeIsNotNull() ? voICP.getStartedDateTime().toString() : "");
		sb.append(" By: ");
		sb.append(voICP.getAuthoringInformationIsNotNull() && voICP.getAuthoringInformation().getAuthoringHcpIsNotNull() && voICP.getAuthoringInformation().getAuthoringHcp().getName() != null? voICP.getAuthoringInformation().getAuthoringHcp().getName().toString() : "");
		sb.append(" ICP: ");
		sb.append(voICP.getICPIsNotNull() ? voICP.getICP().getName() : "");
		
		if (voICP.getCompletedDateTimeIsNotNull())
		{
			sb.append(" Completed: ");
			sb.append(voICP.getCompletedDateTimeIsNotNull() ? voICP.getCompletedDateTime().toString() : "");
		}

		form.txtICP().setValue(sb.toString());
	}

	private void updateControlState() 
	{
		form.btnHistory().setEnabled(form.getLocalContext().getCatsReferralFullIsNotNull() && form.getLocalContext().getCatsReferralFull().getStatusHistoryIsNotNull());
	}

	private void populateScreenFromData(CatsReferralListVo catsReferralFull) 
	{
		clearScreen();
		
		if(catsReferralFull == null)
			return;
		
		form.txtPathwayID().setValue(catsReferralFull.getPathwayID());//wdev-18432

		if(catsReferralFull.getCurrentStatusIsNotNull())
		{
			form.txtCurrentReferralStatus().setValue(catsReferralFull.getCurrentStatus().getReferralStatusIsNotNull() ? catsReferralFull.getCurrentStatus().getReferralStatus().getIItemText() : null);
		}
		
		if(catsReferralFull.getReferralDetailsIsNotNull())
		{
			form.txtDateOfReferral().setValue(catsReferralFull.getReferralDetails().getDateOfReferralIsNotNull() ? catsReferralFull.getReferralDetails().getDateOfReferral().toString() : null);
			form.txtDateReferralLetter().setValue(catsReferralFull.getReferralDetails().getDateReferralReceivedIsNotNull() ? catsReferralFull.getReferralDetails().getDateReferralReceived().toString() : null);
			//WDEV-11657
			form.txtEnd20().setValue(catsReferralFull.getReferralDetails().getEndDateKPIIsNotNull() ? catsReferralFull.getReferralDetails().getEndDateKPI().toString() : null);
			form.txtEnd20Mail().setValue(catsReferralFull.getReferralDetails().getEndDateEmailKPIIsNotNull() ? catsReferralFull.getReferralDetails().getEndDateEmailKPI().toString() : null);
			form.txtEnd20Paper().setValue(catsReferralFull.getReferralDetails().getEndDatePaperKPIIsNotNull() ? catsReferralFull.getReferralDetails().getEndDatePaperKPI().toString() : null);
			//WDEV-11657-end
		}
		
		if (catsReferralFull.getAwaitingClinicalInfoIsNotNull())
		{
			form.dtimAwaitingClinicalInfo().setValue(catsReferralFull.getAwaitingClinicalInfo().getDateAwaitingClinicalInfo());
			form.dtimAllClinicalInfoReceived().setValue(catsReferralFull.getAwaitingClinicalInfo().getDateClinicalInfoReceived());
		}
		
		if(catsReferralFull.getConsultationApptIsNotNull())
		{
			form.txtConsultationAppointment().setValue((catsReferralFull.getConsultationAppt().getAppointmentDateIsNotNull() ? catsReferralFull.getConsultationAppt().getAppointmentDate().toString(): "") + " " + (catsReferralFull.getConsultationAppt().getApptStartTimeIsNotNull() ? catsReferralFull.getConsultationAppt().getApptStartTime().toString() : "" ));
		}
		
		if(catsReferralFull.getStatusHistoryIsNotNull())
		{
			form.txtTriage().setValue(searchForStatusTime(catsReferralFull.getStatusHistory()));
		}
	}

	private String searchForStatusTime(CatsReferralStatusVoCollection statusHistoryColl) 
	{
		if(statusHistoryColl == null)
			return null;
		
		for(int i=0; i<statusHistoryColl.size(); i++)
		{
			if(statusHistoryColl.get(i) != null && statusHistoryColl.get(i).getReferralStatusIsNotNull() && (statusHistoryColl.get(i).getReferralStatus().equals(ReferralApptStatus.REFERRAL_ACCEPTED) || statusHistoryColl.get(i).getReferralStatus().equals(ReferralApptStatus.REFERRAL_REJECTED)))
			{
				return statusHistoryColl.get(i).getStatusDateTimeIsNotNull() ? statusHistoryColl.get(i).getStatusDateTime().toString() : null;
			}
		}
		return null;
	}

	private void initialize() 
	{
		clearScreen();
	}
	
	private void clearScreen() 
	{
		form.txtCurrentReferralStatus().setValue(null);
		
		form.txtDateOfReferral().setValue(null);
		form.txtDateReferralLetter().setValue(null);
		
		form.txtEnd20().setValue(null);
		form.txtEnd20Mail().setValue(null);
		form.txtEnd20Paper().setValue(null);
		
		form.txtTriage().setValue(null);
		form.txtConsultationAppointment().setValue(null);
		
		form.txtStartContact().setValue(null);
		form.txtEndContact().setValue(null);
		
		form.txtStartConsultation().setValue(null);
		form.txtEndConsultation().setValue(null);
	}

	@Override
	protected void onBtnHistoryClick() throws ims.framework.exceptions.PresentationLogicException
	{
		if(form.getLocalContext().getCatsReferralFullIsNotNull())
		{
			form.getGlobalContext().RefMan.setCatsReferralStatusHistory(form.getLocalContext().getCatsReferralFull().getStatusHistory());
			engine.open(form.getForms().RefMan.ReferralStatusHistory);
		}
	}
	
	public void initialize(CatsReferralRefVo catsReferral) 
	{
		form.getLocalContext().setCurrentCatsReferral(catsReferral);
		form.ccERODHistory().initialise(catsReferral);
		
		open();
	}
}