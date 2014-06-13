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
// This code was generated by Callum Wells using IMS Development Environment (version 1.45 build 2417.24440)
// Copyright (C) 1995-2006 IMS MAXIMS plc. All rights reserved.

package ims.core.forms.vitalsignsgroupings;

import ims.core.forms.vitalsignsgroupings.GenForm.grdVSESelectRow;
import ims.core.forms.vitalsignsgroupings.GenForm.grdVSGroupsRow;
import ims.core.vo.VitalSignMonitoringGroupVo;
import ims.core.vo.VitalSignMonitoringGroupVoCollection;
import ims.core.vo.lookups.VSType;
import ims.core.vo.lookups.VSTypeCollection;
import ims.domain.exceptions.DomainInterfaceException;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.exceptions.UniqueKeyViolationException;
import ims.framework.FormName;
import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.PresentationLogicException;
import ims.core.vo.lookups.LookupHelper;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;

	protected void onBtnSaveClick() throws PresentationLogicException
	{
		save();
	}
	private boolean save()
	{
		VitalSignMonitoringGroupVo voVitalSignMonitoringGroup = form.getLocalContext().getVitalSignsGrouping();
		if (voVitalSignMonitoringGroup == null) voVitalSignMonitoringGroup = new VitalSignMonitoringGroupVo();

		voVitalSignMonitoringGroup.setGroupName(form.txtName().getValue());		
		voVitalSignMonitoringGroup.setIsActive(new Boolean(form.chkActive().getValue()));		
			
		VSTypeCollection vsCollVSTypeLkps = new VSTypeCollection();
		grdVSESelectRow row = null;
		for (int i=0; i < form.grdVSESelect().getRows().size(); i++)
		{
			row = form.grdVSESelect().getRows().get(i);	
			
			if (row.getColSelect())
				vsCollVSTypeLkps.add(form.grdVSESelect().getRows().get(i).getValue());
		}
		if (vsCollVSTypeLkps.size() > 0)
			voVitalSignMonitoringGroup.setVitalsSigns(vsCollVSTypeLkps);
		else
			voVitalSignMonitoringGroup.setVitalsSigns(null);	
		
		String[] arrErrors = voVitalSignMonitoringGroup.validate();
		if(arrErrors != null)
		{
			engine.showErrors("Validation Errors", arrErrors);
			return false;
		}
		
		try
		{
			voVitalSignMonitoringGroup = domain.saveVitalSignMonitoringGroupVo(voVitalSignMonitoringGroup);
		} 
		catch (StaleObjectException e)
		{
			engine.showMessage(ims.configuration.gen.ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			onBtnCancelClick();
			return false;
		} 
		catch(UniqueKeyViolationException e)
		{
			engine.showMessage(e.getMessage());
			return false;
		}

		if (voVitalSignMonitoringGroup != null)
			form.getLocalContext().setVitalSignsGrouping(voVitalSignMonitoringGroup);
		
		open();
		setViewInstanceDetails(voVitalSignMonitoringGroup);
		form.setMode(FormMode.VIEW);
		
		return true;
	}
	private void setViewInstanceDetails(VitalSignMonitoringGroupVo voVitalSignMonitoringGroup)
	{
		clearInstanceControls();		
		if (voVitalSignMonitoringGroup == null)
			return;		
			
		if (form.getLocalContext().getVitalSignsGrouping() != null)
			voVitalSignMonitoringGroup = form.getLocalContext().getVitalSignsGrouping();
		else
		{
			voVitalSignMonitoringGroup = domain.getVitalSignMonitoringGroupVo(voVitalSignMonitoringGroup);
			form.getLocalContext().setVitalSignsGrouping(voVitalSignMonitoringGroup);
		}
			
		form.txtName().setValue(voVitalSignMonitoringGroup.getGroupName());			
		if (voVitalSignMonitoringGroup.getIsActive() != null)
			form.chkActive().setValue(voVitalSignMonitoringGroup.getIsActive().booleanValue());
	
		if (voVitalSignMonitoringGroup.getVitalsSigns() != null)
		{
			grdVSESelectRow row;
			VSType item;

			for (int i = 0; i < voVitalSignMonitoringGroup.getVitalsSigns().size(); i++)
			{
				item = voVitalSignMonitoringGroup.getVitalsSigns().get(i);
				
				row = form.grdVSESelect().getRows().newRow();
				row.setColVSElement(item.getText());
				row.setValue(item);
				row.setColSelect(true);
			}
		}
		updateContextMenu();
	}
	protected void onBtnCancelClick()
	{
		clearInstanceControls();
		open();
	}
	protected void onBtnUpdateClick() throws PresentationLogicException
	{
		VitalSignMonitoringGroupVo vitalSignMonitoringGroupVo = null;
		if (form.getLocalContext().getVitalSignsGrouping() != null)
			vitalSignMonitoringGroupVo = form.getLocalContext().getVitalSignsGrouping();
		
		setInstanceDetails(vitalSignMonitoringGroupVo);
		updateInstance();
	}
	private void updateInstance()
	{
		form.txtName().setEnabled(false);
		form.setMode(FormMode.EDIT);
	}
	protected void onBtnNewClick() throws PresentationLogicException
	{
		newInstance();	
		populateGroupingGrid(loadLookups());
	}
	private void populateGroupingGrid(VSTypeCollection collection)
	{
		form.grdVSESelect().getRows().clear();
		
		for (int j = 0; j < collection.size(); j++)
		{
			GenForm.grdVSESelectRow row = form.grdVSESelect().getRows().newRow();
			row.setColVSElement(collection.get(j).getText());
			row.setValue(collection.get(j));
		}	
	}
	VSTypeCollection loadLookups()
	{
		VSTypeCollection coll = LookupHelper.getVSType(domain.getLookupService());
		return coll;		
	}
	protected void onFormOpen() throws PresentationLogicException
	{
		open();
	}
	public void open()
	{
		VitalSignMonitoringGroupVoCollection vsColl = new VitalSignMonitoringGroupVoCollection();
		
		try
		{
			vsColl = domain.listVitalSignMonitoringGroups();
		} 
		catch (DomainInterfaceException e)
		{
			e.printStackTrace();
		}
		
		setInstanceHeader(vsColl);
		form.txtName().setEnabled(false);
	}
	private void setInstanceHeader(VitalSignMonitoringGroupVoCollection vsColl)
	{
		clearInstanceControls();		
		if (vsColl == null)
			return;		
			
		form.grdVSGroups().getRows().clear();
			
		grdVSGroupsRow row = null;
		for (int i=0; i < vsColl.size(); i++)
		{
			row = form.grdVSGroups().getRows().newRow();					
			row.setColGroupName(vsColl.get(i).getGroupName());
			row.setValue(vsColl.get(i));
			
			if (vsColl.get(i).getIsActive() != null)
				row.setColStatus(vsColl.get(i).getIsActive().booleanValue());
		}
		
		//WDEV-14782
		if (form.getLocalContext().getVitalSignsGroupingIsNotNull())
		{
			form.grdVSGroups().setValue(form.getLocalContext().getVitalSignsGrouping());
			setViewInstanceDetails(form.grdVSGroups().getValue());
		}
		
		updateContextMenu();
	}
	protected void onFormDialogClosed(FormName formName, DialogResult result) throws PresentationLogicException
	{	
	}
	protected void onFormModeChanged()
	{	
	}
	private void newInstance()
	{
		form.getLocalContext().setVitalSignsGrouping(new VitalSignMonitoringGroupVo());
		form.setMode(FormMode.EDIT);
		
		if (form.grdVSGroups().getRows().size() > 0)
			form.txtName().setValue(null);
		
		form.txtName().setFocus();
		form.txtName().setEnabled(true);
		form.chkActive().setValue(true);
		form.grdVSESelect().getRows().clear();
	}
	private void clearInstanceControls()
	{
		form.txtName().setValue(null);
		form.chkActive().setValue(false);
		form.grdVSESelect().getRows().clear();
	}
	protected void onGrdVSGroupsSelectionChanged() throws PresentationLogicException 
	{
		form.getLocalContext().setVitalSignsGrouping(form.grdVSGroups().getValue());
		setViewInstanceDetails(form.grdVSGroups().getValue());
	}
	private void setInstanceDetails(VitalSignMonitoringGroupVo vitalSignMonitoringGroupVo)
	{
		clearInstanceControls();		
		if (vitalSignMonitoringGroupVo == null)
			return;		
			
		if (form.getLocalContext().getVitalSignsGrouping() != null)
			vitalSignMonitoringGroupVo = form.getLocalContext().getVitalSignsGrouping();
		else
		{
			vitalSignMonitoringGroupVo = domain.getVitalSignMonitoringGroupVo(vitalSignMonitoringGroupVo);
			form.getLocalContext().setVitalSignsGrouping(vitalSignMonitoringGroupVo);
		}
			
		form.txtName().setValue(vitalSignMonitoringGroupVo.getGroupName());			
		if (vitalSignMonitoringGroupVo.getIsActive() != null)
			form.chkActive().setValue(vitalSignMonitoringGroupVo.getIsActive().booleanValue());
	
		if (vitalSignMonitoringGroupVo.getVitalsSigns() != null)
		{
			grdVSESelectRow row;
			VSType item;
			VSTypeCollection coll = loadLookups();
			if (coll != null)
			{
				for (int i = 0; i < coll.size(); i++)
				{
					item = coll.get(i);
					
					row = form.grdVSESelect().getRows().newRow();
					row.setColVSElement(item.getText());
					row.setValue(item);
					
					for (int z = 0; z < vitalSignMonitoringGroupVo.getVitalsSigns().size(); z++)
					{
						if (item.getID() == vitalSignMonitoringGroupVo.getVitalsSigns().get(z).getID())
						{
							row.setColSelect(true);
							break;
						}
					}
				}
			}
		}
		updateContextMenu();
	}
	private void updateContextMenu()
	{
		updateControlsState();
	}
	private void updateControlsState()
	{
		form.setMode(FormMode.VIEW);
		// sets the update button to be enabled if there is a record selected
		form.btnUpdate().setVisible(true);			
		form.btnUpdate().setEnabled(form.grdVSGroups().getSelectedRow() != null);		
	}
}