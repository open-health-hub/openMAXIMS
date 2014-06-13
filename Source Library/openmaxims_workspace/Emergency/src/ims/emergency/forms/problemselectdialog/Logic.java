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
// This code was generated by Florin Blindu using IMS Development Environment (version 1.80 build 4342.23748)
// Copyright (C) 1995-2012 IMS MAXIMS. All rights reserved.

package ims.emergency.forms.problemselectdialog;

import ims.clinical.vo.ClinicalProblemShortVo;
import ims.clinical.vo.ClinicalProblemShortVoCollection;
import ims.emergency.forms.problemselectdialog.GenForm.grdProblemsRow;
import ims.emergency.forms.problemselectdialog.GenForm.grdSelectedProblemsRow;
import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.SortOrder;
import ims.framework.exceptions.PresentationLogicException;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	@Override
	protected void onGrdSelectedProblemsGridCheckBoxClicked(int column, GenForm.grdSelectedProblemsRow row, boolean isChecked) throws ims.framework.exceptions.PresentationLogicException
	{
		// TODO Add your code here.
	}

	@Override
	protected void onGrdProblemsGridCheckBoxClicked(int column, GenForm.grdProblemsRow row, boolean isChecked) throws ims.framework.exceptions.PresentationLogicException
	{
		// TODO Add your code here.
	}

	@Override
	protected void onBtnSearchClick() throws ims.framework.exceptions.PresentationLogicException
	{
		clearControls();
		doSearch();
	}

	private void doSearch()
	{
		if (form.txtProblemSearch().getValue() == null || form.txtProblemSearch().getValue() != null && form.txtProblemSearch().getValue().length() < 3) //WDEV-15859
		{
			engine.showMessage("Please enter a search string of at least 3 characters !");
			return;
		}
		
		ClinicalProblemShortVoCollection collProblems = domain.listProblems(form.txtProblemSearch().getValue());

		// form.getLocalContext().setselectedClinicianNote(null);// -----------
		if (collProblems == null || collProblems.size() == 0)
		{
			engine.showMessage("No Problems found !");
			return;
		}

		ClinicalProblemShortVoCollection filteredCollProblems = filterExistingProblems(collProblems);
		populateGridProblems(filteredCollProblems);

	}

	private ClinicalProblemShortVoCollection filterExistingProblems(ClinicalProblemShortVoCollection collProblems)
	{
		ClinicalProblemShortVoCollection existingColl = form.getGlobalContext().Emergency.getSelectedProblems();

		if (existingColl == null || (existingColl != null && existingColl.size() == 0))
			return collProblems;

		ClinicalProblemShortVoCollection newProblemColl = new ClinicalProblemShortVoCollection();
		existingColl.sort(SortOrder.ASCENDING);
		collProblems.sort(SortOrder.ASCENDING);

		for (int i = 0; i < collProblems.size(); i++)
		{
			if (existingColl.contains(collProblems.get(i)) == false)
			{
				newProblemColl.add(collProblems.get(i));
			}
		}

		return newProblemColl;
	}

	private void populateGridProblems(ClinicalProblemShortVoCollection collProblems)
	{
		if (collProblems == null)
			return;

		for (int i = 0; i < collProblems.size(); i++) 
		{
			ClinicalProblemShortVo problem = collProblems.get(i);

			grdProblemsRow row = form.grdProblems().getRows().newRow();
			row.setcolProblem(problem.getPCName());
			row.setcolSelect(false);
			row.setValue(problem);
		}

	}

	private void clearControls()
	{
		form.grdProblems().getRows().clear();
		form.grdProblems().setValue(null);

		form.grdSelectedProblems().getRows().clear();
		form.grdSelectedProblems().setValue(null);

	}

	@Override
	protected void onFormOpen(Object[] args) throws PresentationLogicException
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void onBtnCancelClick() throws PresentationLogicException
	{
		engine.close(DialogResult.CANCEL);

	}

	@Override
	protected void onBtnOkClick() throws PresentationLogicException
	{
		if (form.grdSelectedProblems().getRows().size() == 0) //WDEV-15859
		{
			engine.showMessage("No items were selected.");
			return;
		}

		ClinicalProblemShortVoCollection collSelectedProblems = form.getGlobalContext().Emergency.getSelectedProblems();

		for (int i = 0; i < form.grdSelectedProblems().getRows().size(); i++)
		{		
			grdSelectedProblemsRow selRow = form.grdSelectedProblems().getRows().get(i);
			if (selRow.getcolSelect())
			{
				collSelectedProblems.add(form.grdSelectedProblems().getRows().get(i).getValue());
			}
		}

		form.getGlobalContext().Emergency.setSelectedProblems(collSelectedProblems);

		engine.close(DialogResult.OK);
	}

	@Override
	protected void onBtnAddToListClick() throws PresentationLogicException
	{
		for (int i = 0; i < form.grdProblems().getRows().size(); i++)
		{
			grdProblemsRow row = form.grdProblems().getRows().get(i);

			if (row.getcolSelect() == false)
				continue;

			boolean problemFound = false;
			for (int j = 0; j < form.grdSelectedProblems().getRows().size(); j++)
			{
				grdSelectedProblemsRow selRow = form.grdSelectedProblems().getRows().get(j);

				if (row.getValue().equals(selRow.getValue()))
				{
					problemFound = true;
					break;
				}
			}

			if (problemFound == false)
			{
				grdSelectedProblemsRow selRow = form.grdSelectedProblems().getRows().newRow();

				selRow.setcolProblem((row.getValue()).getPCName());
				selRow.setcolSelect(true);
				selRow.setValue(row.getValue());
			}
		}

	}

	@Override
	protected void onBtnClearAllClick() throws PresentationLogicException
	{
		for (int i = 0; i < form.grdProblems().getRows().size(); i++)
		{
			grdProblemsRow row = form.grdProblems().getRows().get(i);

			row.setcolSelect(false);
		}

	}

	@Override
	protected void onBtnSelectAllClick() throws PresentationLogicException
	{
		for (int i = 0; i < form.grdProblems().getRows().size(); i++)
		{
			grdProblemsRow row = form.grdProblems().getRows().get(i);

			row.setcolSelect(true);
		}

	}
}