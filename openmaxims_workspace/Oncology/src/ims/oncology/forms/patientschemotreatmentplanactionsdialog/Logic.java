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
// This code was generated by Rory Fitzpatrick using IMS Development Environment (version 1.80 build 4261.20360)
// Copyright (C) 1995-2011 IMS MAXIMS. All rights reserved.

package ims.oncology.forms.patientschemotreatmentplanactionsdialog;

import ims.ccosched.vo.PatTreatPlanActionLiteVo;
import ims.ccosched.vo.PatTreatPlanActionLiteVoCollection;
import ims.framework.MessageButtons;
import ims.framework.MessageIcon;
import ims.framework.enumerations.DialogResult;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.Date;
import ims.oncology.forms.patientschemotreatmentplanactionsdialog.GenForm.grdDetailsRow;
import ims.oncology.vo.ChemotherapyDetailsLiteDialogVoCollection;
import ims.oncology.vo.PatTreatmentPlanRadiotherapyDialogVoCollection;

import java.text.ParseException;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{
		ChemotherapyDetailsLiteDialogVoCollection voExistingActionColl;
		
		PatTreatmentPlanRadiotherapyDialogVoCollection voColl;
		try 
		{
			voColl = domain.listActivePatTreatMentPlans(form.getGlobalContext().Core.getPatientShort(), form.getGlobalContext().Core.getEpisodeofCareShort());
			voExistingActionColl = domain.listActionsAlreadyLinkedToChemotherapy(form.getGlobalContext().Core.getPatientShort());
		} 
		catch (NumberFormatException e) 
		{
			engine.showMessage(e.getMessage());
			return;
		} 
		if (voColl == null || voColl.size() == 0 )
			engine.showMessage("No Records found.");
		
		form.grdDetails().getRows().clear();
		for (int i = 0 ; voColl != null && i < voColl.size() ; i++)
		{
			PatTreatPlanActionLiteVoCollection voActionsColl = voColl.get(i).getActions();
			
			grdDetailsRow row = null;
			for (int j = 0 ; voActionsColl != null && j < voActionsColl.size() ; j++)
			{
				if ( ! checkIfNoLinkedRecordYet(voActionsColl.get(j), voExistingActionColl) 
					&& voActionsColl.get(j).getActiveIsNotNull()
					&& voActionsColl.get(j).getActive()
					&& voActionsColl.get(j).getActivityIsNotNull()
					&& voActionsColl.get(j).getActivity().getActivityId().equals(-201)) //WDEV-15378
				{
					row = form.grdDetails().getRows().newRow();
					row.setcolRegime(voActionsColl.get(j).getPlannedRegimeIsNotNull() ? voActionsColl.get(j).getPlannedRegime().getText() : "");
					
					row.setcolTreatDate(voColl.get(i).getDecisionToTreatIsNotNull() ? voColl.get(i).getDecisionToTreat().toString() : "");
					row.setcolCons(voActionsColl.get(j).getTreatingConsultantIsNotNull() ? voActionsColl.get(j).getTreatingConsultant().getIMosName().toString() : "");
					row.setValue(voActionsColl.get(j));
				}
			}
		}
		
		if (form.grdDetails().getRows().size() == 0)
		{
			engine.showMessage("No active and unused actions for the treatment plan(s) found.","No actions found", MessageButtons.OK, MessageIcon.INFORMATION);
			engine.close(DialogResult.CANCEL);
			return;
		}
		form.btnSave().setEnabled(false);
		//form.getGlobalContext().Oncology.setDecisionTreatFromDialog(value) DecisionTreatFromDialog(value) RadiotherapyPatTreatmentPlanAction(null);
		form.getGlobalContext().Oncology.setDecisionTreatFromDialog(null);
	}
	
	private boolean checkIfNoLinkedRecordYet(PatTreatPlanActionLiteVo voAction, ChemotherapyDetailsLiteDialogVoCollection voExistingActionColl) 
	{
		for (int i = 0 ; voExistingActionColl != null && i < voExistingActionColl.size() ; i++)
		{
			if (voExistingActionColl.get(i).getAssociatedTreatmentPlanActionIsNotNull()
				&& voAction.getID_PatAction().equals(voExistingActionColl.get(i).getAssociatedTreatmentPlanAction().getID_PatAction()))
				return true;
		}
		return false;
	}

	@Override
	protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException
	{
		form.getGlobalContext().Oncology.setRadiotherapyPatTreatmentPlanAction(form.grdDetails().getSelectedRow().getValue());
		try 
		{
			form.getGlobalContext().Oncology.setDecisionTreatFromDialog(form.grdDetails().getSelectedRow().getcolTreatDate() != null ? new Date(form.grdDetails().getSelectedRow().getcolTreatDate()) : null);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		engine.close(DialogResult.OK);
	}
	@Override
	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		form.getGlobalContext().Oncology.setRadiotherapyPatTreatmentPlanAction(null);
		form.getGlobalContext().Oncology.setDecisionTreatFromDialog(null);
		engine.close(DialogResult.CANCEL);
	}
	@Override
	protected void onGrdDetailsSelectionChanged() throws PresentationLogicException 
	{
		form.btnSave().setEnabled(true);
	}
}