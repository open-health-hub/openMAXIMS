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
// This code was generated by Charlotte Murphy using IMS Development Environment (version 1.45 build 2250.23637)
// Copyright (C) 1995-2006 IMS MAXIMS plc. All rights reserved.

package ims.therapies.forms.leisure;

import ims.core.vo.Hcp;
import ims.core.vo.HcpCollection;
import ims.core.vo.HcpFilter;
import ims.core.vo.PersonName;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.exceptions.UniqueKeyViolationException;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.Color;
import ims.framework.utils.DateTime;
import ims.therapies.forms.leisure.GenForm.grdDetailsRow;
import ims.therapies.vo.LeisureActivitiesVo;
import ims.therapies.vo.LeisureActivitiesVoCollection;
import ims.therapies.vo.LeisureActivityOnWardVo;
import ims.therapies.vo.LeisureActivityOnWardVoCollection;

public class Logic extends BaseLogic
{
	protected void onFormOpen() throws ims.framework.exceptions.PresentationLogicException
	{
		open();
	}
	private void open()
	{		
		clear();
		resetContextVariables();
		
		if(form.getGlobalContext().Core.getCurrentCareContextIsNotNull()){
			form.getLocalContext().setLeisureActivities(null);
			LeisureActivitiesVoCollection coll = domain.getLeisureActivitiesVoByCareContext(form.getGlobalContext().Core.getCurrentCareContext());
			if(coll!=null && coll.size()>0)
				form.getLocalContext().setLeisureActivities(coll.get(0));
		}
		
		populateControls(form.getLocalContext().getLeisureActivities());
		
		form.setMode(FormMode.VIEW);
	}
	protected void onFormModeChanged() 
	{
		updateControlsState();		
	}
	private void getSelectedInstance()
	{
		if (form.getMode().equals(FormMode.EDIT) && !form.getLocalContext().getUpdateChildInstance().booleanValue())	//wdev-12479 
		{
			updateContextMenusState();
			return;
		}
		
		form.getLocalContext().setActivityOnWard(form.grdDetails().getValue());
		populateChildInstanceControls(form.getLocalContext().getActivityOnWard());			
		
		updateControlsState();
	}
	private void populateChildInstanceControls(LeisureActivityOnWardVo voActivityOnWard)
	{
		form.qmbAuthoringCPWard().newRow(voActivityOnWard.getAuthoringCP(), voActivityOnWard.getAuthoringCP().toString());
		form.qmbAuthoringCPWard().setValue(voActivityOnWard.getAuthoringCP());
		form.dtimAuthoringWard().setValue(voActivityOnWard.getAuthoringDateTime());
		form.dteActivity().setValue(voActivityOnWard.getActivityDate());
		form.txtActivities().setValue(voActivityOnWard.getActivity());
		form.txtComment().setValue(voActivityOnWard.getComment());
	}
	private void resetContextVariables()
	{
		form.getLocalContext().setActivityOnWard(null);
		form.getLocalContext().setLeisureActivities(null);	
		form.getLocalContext().setUpdateChildInstance(new Boolean(false));
	}
	private void populateControls(LeisureActivitiesVo voLeisureActivities)
	{
		populateInstanceControl(voLeisureActivities);
	}
	private void populateInstanceControl(LeisureActivitiesVo voLeisureActivities)
	{
		if(voLeisureActivities == null) return;
		
		if(voLeisureActivities.getOnWardLeisureIsNotNull())
			populateListControl(voLeisureActivities.getOnWardLeisure());
		
		form.qmbAuthoringCP().newRow(voLeisureActivities.getAuthoringCP(), voLeisureActivities.getAuthoringCP().toString());
		form.qmbAuthoringCP().setValue(voLeisureActivities.getAuthoringCP());
		form.dtimAuthoring().setValue(voLeisureActivities.getAuthoringDateTime());
		form.txtActive().setValue(voLeisureActivities.getActiveInterest());
		form.txtPassive().setValue(voLeisureActivities.getPassiveInterest());
		form.txtSocial().setValue(voLeisureActivities.getSocialInterest());		
	}
	private void populateListControl(LeisureActivityOnWardVoCollection onWardLeisureColl)
	{
		if(onWardLeisureColl == null) return;
		
		clear();
		
		GenForm.grdDetailsRow row;
		for (int i =0; i < onWardLeisureColl.size();i++)
		{
			row = form.grdDetails().getRows().newRow();
			
			row.setValue(onWardLeisureColl.get(i));
			row.setColDate(onWardLeisureColl.get(i).getAuthoringDateTime().getDate());
			row.setColActivity(onWardLeisureColl.get(i).getActivity());
			row.setColComment(onWardLeisureColl.get(i).getComment());
			//WDEV-15299
			row.setTooltipForColDate(onWardLeisureColl.get(i).getAuthoringDateTime().getDate().toString());
			row.setTooltipForColActivity(onWardLeisureColl.get(i).getActivity());
			row.setTooltipForColComment(onWardLeisureColl.get(i).getComment());
		}
	}
	private void clear()
	{
		form.grdDetails().getRows().clear();
		clearParentInstanceControls();
		clearChildInstanceControls();
	}
	private void clearParentInstanceControls()
	{
		form.qmbAuthoringCP().clear();
		form.dtimAuthoring().setValue(null);
		form.txtActive().setValue("");
		form.txtPassive().setValue("");
		form.txtSocial().setValue("");
	}
	private void clearChildInstanceControls()
	{
		form.qmbAuthoringCPWard().clear();	
		form.dtimAuthoringWard().setValue(null);
		form.dteActivity().setValue(null);
		form.txtActivities().setValue(null);
		form.txtComment().setValue(null);
	}	
	protected void onBtnOKClick() throws ims.framework.exceptions.PresentationLogicException
	{
		if(!validateUI())
			addOrUpdateChild();
		
		updateControlsState();//wdev-15308
	}
	private boolean validateUI()
	{
		if(form.txtActivities().getValue() == null)
		{
			engine.showMessage("'Activity' is a mandatory field");
			return true;
		}
		return false;
	}
	protected void onBtnCancelDetailsClick() throws ims.framework.exceptions.PresentationLogicException
	{
		clearChildInstanceControls();
	}
	protected void onBtnNewClick() throws ims.framework.exceptions.PresentationLogicException
	{
		newInstance();
	}
	private void newInstance()
	{
		if(form.getLocalContext().getLeisureActivities() == null){ //new Record
			newLeisureInstance();	
			form.setMode(FormMode.EDIT);
			form.qmbAuthoringCP().setEnabled(true);
			form.dtimAuthoring().setEnabled(true);
		}
		else													  //new Activity
			newChildInstance();
		form.setMode(FormMode.EDIT);
	}
	private void newLeisureInstance()
	{
		LeisureActivitiesVo voNewParent = new LeisureActivitiesVo(); 	
		form.getLocalContext().setUpdateChildInstance(new Boolean(true));
		voNewParent.setCareContext(form.getGlobalContext().Core.getCurrentCareContext());
		populateParentInstanceControls();		
		form.getLocalContext().setLeisureActivities(voNewParent);
	}
	private void populateParentInstanceControls()
	{
		setDefaultValues();
	}
	private void setDefaultValues()
	{
		Hcp hcpUser = (Hcp)domain.getHcpUser();
		if(hcpUser != null)
		{
			form.qmbAuthoringCP().newRow(hcpUser, hcpUser.getName().toString());
			form.qmbAuthoringCP().setValue(hcpUser);
		}
		form.dtimAuthoring().setValue(new DateTime());	
	}
	protected void onBtnSaveClick() throws ims.framework.exceptions.PresentationLogicException
	{
		if(save())
			open();
	}
	private boolean save()
	{
		if(checkForUnsavedChild())
			return false;
		
		if(form.txtActivities().isEnabled() && !form.btnOK().isVisible() && validateUI()) //WDEV-15299
			return false;
		
		LeisureActivitiesVo voLeisureActivities  = populateInstanceData(form.getLocalContext().getLeisureActivities());
				
		String[] arrErrors =  voLeisureActivities.validate();	
		
		if(arrErrors != null)
		{
			engine.showErrors(arrErrors);
			return false;
		}
		
		try
		{
			domain.save(voLeisureActivities);
		}
		catch(StaleObjectException e)
		{
			engine.showMessage(ims.configuration.gen.ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			open();
			return false;
		} 
		catch (UniqueKeyViolationException e) 
		{
			engine.showMessage("A Leisure record already exists for this clinical contact. " + e.getMessage());
			open();
			return false;
		}
		catch(DomainRuntimeException e)
		{
			engine.showMessage(e.getMessage());
			open();
			return false;
		}
		
		return true;		
	}
	private LeisureActivitiesVo populateInstanceData(LeisureActivitiesVo voLeisureActivities)
	{
		if (voLeisureActivities == null)
			voLeisureActivities = new LeisureActivitiesVo();
				
		populateParentInstanceData(voLeisureActivities);						

		if (form.getLocalContext().getActivityOnWard() == null)
		{
			populateChildCollectionFromGrid(voLeisureActivities);			
		}
		else 															// Update child instance.
		{
			LeisureActivityOnWardVo voActivityOnWard = form.getLocalContext().getActivityOnWard();
			populateChildInstanceData(voActivityOnWard);
			form.getLocalContext().setActivityOnWard(voActivityOnWard);
			
			if(voLeisureActivities.getOnWardLeisure() != null)
			{
    			for (int i=0; i < voLeisureActivities.getOnWardLeisure().size(); i++)
    			{
    				if (voLeisureActivities.getOnWardLeisure().get(i).getID_LeisureActivityOnWard() == 
    					form.getLocalContext().getActivityOnWard().getID_LeisureActivityOnWard())
    				{						
    					voLeisureActivities.getOnWardLeisure().set(i, form.getLocalContext().getActivityOnWard());
    				}
    			}	
			}
		}
		return voLeisureActivities;	
	}
	private void populateChildCollectionFromGrid(LeisureActivitiesVo voLeisureActivities)
	{
		//if (form.grdDetails().getValue() == null) return;
								    
		for (int i=0; i < form.grdDetails().getRows().size(); i++)
        {
			if (voLeisureActivities.getOnWardLeisure() == null)
				voLeisureActivities.setOnWardLeisure(new LeisureActivityOnWardVoCollection());
			
			voLeisureActivities.getOnWardLeisure().add(form.grdDetails().getRows().get(i).getValue());
        }
	}
	private void populateParentInstanceData(LeisureActivitiesVo voLeisureActivities)
	{
		voLeisureActivities.setAuthoringCP(form.qmbAuthoringCP().getValue());
		voLeisureActivities.setAuthoringDateTime(form.dtimAuthoring().getValue());
		voLeisureActivities.setActiveInterest(form.txtActive().getValue());
		voLeisureActivities.setSocialInterest(form.txtSocial().getValue());
		voLeisureActivities.setPassiveInterest(form.txtPassive().getValue());
	}
	private boolean checkForUnsavedChild()
	{
		if (form.getLocalContext().getActivityOnWard() == null)
		{				
			if(addOrUpdateChild())
				return true;
		}
		return false;
	}
	private boolean addOrUpdateChild()
	{		
		LeisureActivityOnWardVo voLeisureActivityOnWard = form.getLocalContext().getActivityOnWard();
		
		if (voLeisureActivityOnWard == null)
			voLeisureActivityOnWard = new LeisureActivityOnWardVo(); 

		populateChildInstanceData(voLeisureActivityOnWard);

		if (form.getLocalContext().getActivityOnWard() != null)
		{
			promoteUpdatedChild(voLeisureActivityOnWard);
			newChildInstance();
			updateControlsState();
		}
		else
		{
			if(newChild(voLeisureActivityOnWard))
				return true;
		}
		form.grdDetails().setValue(form.getLocalContext().getActivityOnWard());
		form.grdDetails().setEnabled(true);
		return false;
	}
	private void updateControlsState()
	{
		// The new button
		if(form.getMode().equals(FormMode.VIEW))
		{
			form.btnNew().setVisible(form.getLocalContext().getLeisureActivities() == null);
			form.btnUpdate().setVisible(form.getLocalContext().getLeisureActivitiesIsNotNull());
			enableChildControls(false);
			enableChildButtons(false);
		}	
		else
		{
			form.btnNew().setVisible(false);
			form.btnUpdate().setVisible(false);
		}

		setDetailsButton();
		
		// The context menus
		updateContextMenusState();	
	}
	private void setDetailsButton()
	{
		// In edit mode, make the details tab visible if a hierarchy item has been selected and we're not
		// editing a parent.
		if(form.getMode().equals(FormMode.EDIT)&& !form.getLocalContext().getUpdateChildInstance().booleanValue())
		{
			setAddApplyCaption();
		}
	}
	private void setAddApplyCaption()
	{
		form.btnOK().setText("Add");
		
		if (!form.getLocalContext().getUpdateChildInstance().booleanValue()
				&& form.grdDetails().getValue() != null && form.getLocalContext().getActivityOnWardIsNotNull())
				form.btnOK().setText("Apply");
	}
	private void enableChildControls(boolean enable)
	{
		form.dtimAuthoringWard().setEnabled(enable);
		form.qmbAuthoringCPWard().setEnabled(enable);
		form.dteActivity().setEnabled(enable);
		form.txtActivities().setEnabled(enable);
		form.txtComment().setEnabled(enable);
	}
	private void enableChildButtons(boolean visible)
	{
		form.btnOK().setVisible(visible);
		form.btnCancelDetails().setVisible(visible);
	}
	private void updateContextMenusState()
	{
		form.getContextMenus().getGenericGridAddItem().setVisible(form.getLocalContext().getUpdateChildInstance().booleanValue());
		form.getContextMenus().getGenericGridUpdateItem().setVisible(form.getLocalContext().getUpdateChildInstance().booleanValue() &&form.grdDetails().getValue() != null ||
						form.grdDetails().getValue() != null && form.grdDetails().getValue().getID_LeisureActivityOnWard() == null);		
		
	}
	private void promoteUpdatedChild(LeisureActivityOnWardVo voLeisureActivityOnWard)
	{
		if (voLeisureActivityOnWard == null) return;
			populateChild(form.grdDetails().getSelectedRow(),voLeisureActivityOnWard);		
	}
	private void newChildInstance()
	{
		clearChildInstanceControls();
		setDefaultChildValues();
		enableChildControls(true);
		enableChildButtons(true);
		form.getLocalContext().setUpdateChildInstance(new Boolean(false));
		form.getLocalContext().setActivityOnWard(null);
	}
	private void setDefaultChildValues()
	{
		Hcp hcpUser = (Hcp)domain.getHcpUser();
		if(hcpUser != null)
		{
			form.qmbAuthoringCPWard().newRow(hcpUser, hcpUser.getName().toString());
			form.qmbAuthoringCPWard().setValue(hcpUser);
		}
		form.dtimAuthoringWard().setValue(new DateTime());			
	}
	private boolean newChild(LeisureActivityOnWardVo voLeisureActivityOnWard)
	{
		if (form.getLocalContext().getActivityOnWard() != null) return false;
		
		if (voLeisureActivityOnWard.countFieldsWithValue() >= 1)
		{
			String strErrors[] = voLeisureActivityOnWard.validate();
		
			if(strErrors != null && strErrors.length > 0)
			{
				engine.showErrors(strErrors);
				return true;
			}
			
	 		if(validateUI())	
	 			return true;
	
			promoteChild(voLeisureActivityOnWard);			
			clearChildInstanceControls();
			setDefaultChildValues();
		}
		return false;
	}
	private void promoteChild(LeisureActivityOnWardVo voLeisureActivityOnWard)
	{
		if (voLeisureActivityOnWard == null) return;
		GenForm.grdDetailsRow childRow = form.grdDetails().getRows().newRow();		
		populateChild(childRow, voLeisureActivityOnWard);
	}
	private void populateChild(grdDetailsRow childRow, LeisureActivityOnWardVo voLeisureActivityOnWard)
	{
		if (voLeisureActivityOnWard == null) return;		
		
		childRow.setValue(voLeisureActivityOnWard);
		childRow.setColDate(voLeisureActivityOnWard.getAuthoringDateTime().getDate());
		childRow.setColActivity(voLeisureActivityOnWard.getActivity());
		childRow.setColComment(voLeisureActivityOnWard.getComment());
	
		//if not saved then set the colour
		if(voLeisureActivityOnWard.getID_LeisureActivityOnWard() == null)
			childRow.setTextColor(Color.Red);
	}
	private void populateChildInstanceData(LeisureActivityOnWardVo voLeisureActivityOnWard)
	{
		voLeisureActivityOnWard.setAuthoringCP(form.qmbAuthoringCPWard().getValue());
		voLeisureActivityOnWard.setAuthoringDateTime(form.dtimAuthoringWard().getValue());
		voLeisureActivityOnWard.setActivityDate(form.dteActivity().getValue());
		voLeisureActivityOnWard.setActivity(form.txtActivities().getValue());
		voLeisureActivityOnWard.setComment(form.txtComment().getValue());
	}
	protected void onBtnCancelClick() throws ims.framework.exceptions.PresentationLogicException
	{
		open();
	}
	protected void onBtnUpdateClick() throws ims.framework.exceptions.PresentationLogicException
	{
		form.getLocalContext().setUpdateChildInstance(new Boolean(true));
		form.setMode(FormMode.EDIT);
	}
	protected void onContextMenuItemClick(int menuItemID, ims.framework.Control sender) throws ims.framework.exceptions.PresentationLogicException
	{
		switch(menuItemID)
		{
			case GenForm.ContextMenus.GenericGrid.Add:
				newInstance();
				break;
			case GenForm.ContextMenus.GenericGrid.Update:
				updateInstance();
				break;
		}
		
		updateControlsState();//wdev-15308
	}
	private void updateInstance()
	{		
		LeisureActivityOnWardVo voCachedChild = form.grdDetails().getValue(); 
		if (voCachedChild.getID_LeisureActivityOnWard() == null)
		{
			populateChildInstanceControls(voCachedChild);
			form.getLocalContext().setActivityOnWard(voCachedChild);
		}			
		form.dteActivity().setEnabled(true);
		form.txtActivities().setEnabled(true);
		form.txtComment().setEnabled(true);
		form.setMode(FormMode.EDIT);
		form.grdDetails().setEnabled(false);
	}
	protected void onQmbAuthoringCPTextSubmited(String value) throws PresentationLogicException
	{
		form.qmbAuthoringCP().clear();
		HcpCollection voHcpColl = setAuthoringCP(value);
		
		for (int i = 0; i < voHcpColl.size(); i++)
		{
			Hcp med = voHcpColl.get(i);
			form.qmbAuthoringCP().newRow(med, med.toString());			
		}
		
		if (voHcpColl.size() == 1)
			form.qmbAuthoringCP().setValue(voHcpColl.get(0));
		else if (voHcpColl.size() > 1)
			form.qmbAuthoringCP().showOpened();
	}
	protected void onQmbAuthoringCPWardTextSubmited(String value) 
	{
		form.qmbAuthoringCPWard().clear();
		HcpCollection voHcpColl = setAuthoringCP(value);	
		
		for (int i = 0; i < voHcpColl.size(); i++)
		{
			Hcp med = voHcpColl.get(i);
			form.qmbAuthoringCPWard().newRow(med, med.toString());			
		}
		
		if (voHcpColl.size() == 1)
			form.qmbAuthoringCPWard().setValue(voHcpColl.get(0));
		else if (voHcpColl.size() > 1)
			form.qmbAuthoringCPWard().showOpened();
	}
	private HcpCollection setAuthoringCP(String value)
	{
		HcpFilter filter = new HcpFilter();
		PersonName name = new PersonName();
		name.setSurname(value);
		filter.setQueryName(name);
		
		return domain.listMedics(filter);
	}
	protected void onGrdDetailsSelectionChanged() throws PresentationLogicException
	{
		getSelectedInstance();
	}

}