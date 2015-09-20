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
// This code was generated by Billy Mahon using IMS Development Environment (version 1.42 build 2196.26417)
// Copyright (C) 1995-2006 IMS MAXIMS plc. All rights reserved.

package ims.therapies.forms.tens;

import ims.core.vo.Hcp;
import ims.core.vo.HcpCollection;
import ims.core.vo.HcpFilter;
import ims.core.vo.PersonName;
import ims.domain.exceptions.StaleObjectException;
import ims.domain.exceptions.UniqueKeyViolationException;
import ims.framework.Control;
import ims.framework.enumerations.FormMode;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.utils.Color;
import ims.framework.utils.DateTime;
import ims.therapies.forms.tens.GenForm;
import ims.therapies.forms.tens.GenForm.grdTENSRow;
import ims.therapies.vo.ElectrotherapyTENSShortVo;
import ims.therapies.vo.ElectrotherapyTENSShortVoCollection;
import ims.therapies.vo.ElectrotherapyTENSVo;
import ims.therapies.vo.ElectrotherapyTensTreatmentVo;
import ims.therapies.vo.ElectrotherapyTensTreatmentVoCollection;

public class Logic extends BaseLogic
{
	protected void onFormOpen() throws ims.framework.exceptions.FormOpenException
	{
		initialize();
		open();
	}

	private void open() 
	{
		clear();
		resetContextVariables();		
							
		if(form.getGlobalContext().Core.getCurrentCareContextIsNotNull())
		{
			populateParentNodes(domain.listElectrotherapyTensByCareContext(form.getGlobalContext().Core.getCurrentCareContext()));			
		}
									
		form.setMode(FormMode.VIEW);
		reselectUpdatedNode();
	}

	private void reselectUpdatedNode()
	{
		// Pattern demands that the last updated node is displayed
		if (form.getLocalContext().getUpdatedChildIsNotNull())
		{
			form.grdTENS().setValue(form.getLocalContext().getUpdatedChild());
			getSelectedInstance();
			form.getLocalContext().setUpdatedChild(null);
		}
	}
	
	private void getSelectedInstance()
	{
		// In update mode we can select nodes without displaying anything but the context
		// menus can change based on what's selected so we need to update them
		if (form.getMode().equals(FormMode.EDIT)) 
		{
			updateContextMenusState();
			return;
		}		
		
		//expand the selected parent
		if(form.grdTENS().getValue() instanceof ElectrotherapyTENSShortVo)
		{		
			form.getLocalContext().setSelectedParentInstance(getParentFromDomain((ElectrotherapyTENSShortVo) form.grdTENS().getValue()));
			populateParentInstanceControls(form.getLocalContext().getSelectedParentInstance());
			//add activities if not added already
			populateChildNodes(form.grdTENS().getSelectedRow(),form.getLocalContext().getSelectedParentInstance().getTensTreatments());			
			form.getLocalContext().setSelectedChildInstance(null);
			clearChildInstanceControls();
		}		
		else if(form.grdTENS().getValue() instanceof ElectrotherapyTensTreatmentVo)
		{
			form.getLocalContext().setSelectedChildInstance((ElectrotherapyTensTreatmentVo) form.grdTENS().getValue());
			populateChildInstanceControls(form.getLocalContext().getSelectedChildInstance());			
		}
 
		if (form.getGlobalContext().Core.getCurrentClinicalContact() == null)
			form.getLocalContext().setGoldenInstanceSelected(new Boolean(false));
		else
			form.getLocalContext().setGoldenInstanceSelected(new Boolean(form.getLocalContext().getSelectedParentInstance().getClinicalContact().getID_ClinicalContact().equals(form.getGlobalContext().Core.getCurrentClinicalContact().getID_ClinicalContact())));
		
		updateControlsState();
		
		if (form.getGlobalContext().Core.getCurrentClinicalContactIsNotNull())
		{
			form.btnNew().setVisible(false);
			form.btnUpdate().setVisible(false);
		}
	}
	
	private void updateControlsState()
	{
		// The new button
		form.btnNew().setVisible(form.getMode().equals(FormMode.VIEW) 
				&& form.getLocalContext().getGoldenInstanceFound().booleanValue() == false
				&& form.getGlobalContext().Core.getCurrentClinicalContactIsNotNull());

 		form.btnUpdate().setVisible(form.getMode().equals(FormMode.VIEW) 
 				&& form.getGlobalContext().Core.getCurrentClinicalContactIsNotNull()
 				&& form.grdTENS().getValue() instanceof ElectrotherapyTensTreatmentVo
 				&& form.getLocalContext().getGoldenInstanceFound().booleanValue() == true);
		
		// The collapsible container
		form.ctnDetails().setCollapsed(form.getLocalContext().getSelectedParentInstance() == null);						

		// The empty tab
		if (form.grdTENS().getValue() == null)
			form.ctnDetails().lyrTENS().showtabEmpty1();

		// The Parent tab
		setParentTabVisability();						

		// The details tab
		setDetailsTabVisibility();		

		// Pick a tab to show
		showHeaderOrDetailsTab();																				

		// The context menus
		updateContextMenusState();	
	}
	
	private void showHeaderOrDetailsTab()
	{
		if (form.getMode().equals(FormMode.VIEW))
		{
			// In view mode just show the selected node 
			if (form.grdTENS().getValue() instanceof ElectrotherapyTENSShortVo)		
				form.ctnDetails().lyrTENS().showtabHeader();
			else if (form.grdTENS().getValue() instanceof ElectrotherapyTensTreatmentVo)
				form.ctnDetails().lyrTENS().showtabDetails();
			else
				form.ctnDetails().lyrTENS().showtabEmpty1();
		}
		else
		{
			if (form.getLocalContext().getUpdatingParent().equals(Boolean.TRUE))
				form.ctnDetails().lyrTENS().showtabHeader();
			else
				form.ctnDetails().lyrTENS().showtabDetails();
		}
	}

	private void setDetailsTabVisibility()
	{
		if (form.getMode().equals(FormMode.VIEW))
		{	
			// In view mode, make the details tab visible if a detail instance is selected in the hierarchy grid.  
			form.ctnDetails().lyrTENS().tabDetails().setHeaderVisible(form.grdTENS().getValue() instanceof ElectrotherapyTensTreatmentVo);									
		}
		else
		{			
			// In edit mode, make the details tab visible if a hierarchy item has been selected and we're not
			// editing a parent.
			form.ctnDetails().lyrTENS().tabDetails().setHeaderVisible(form.getLocalContext().getSelectedParentInstanceIsNotNull() && 
					form.getLocalContext().getUpdatingParent().equals(Boolean.FALSE));
			
			setAddApplyCaption();			
		}			
		enableDetailsButtons();	
	}
	
	private void enableDetailsButtons()
	{
		boolean boolVisible = (form.getMode().equals(FormMode.EDIT) &&
				  form.ctnDetails().lyrTENS().tabDetails().isHeaderVisible() &&
				  form.getLocalContext().getSelectedChildInstance() == null);

		form.ctnDetails().lyrTENS().tabDetails().btnOk().setVisible(boolVisible);
		form.ctnDetails().lyrTENS().tabDetails().btnCancelDetails().setVisible(boolVisible);
	}
	
	private void setAddApplyCaption()
	{
		form.ctnDetails().lyrTENS().tabDetails().btnOk().setText("Add");
		
		if (form.getLocalContext().getSelectedChildInstance() == null &&
			 (form.grdTENS().getValue() instanceof ElectrotherapyTensTreatmentVo))
				form.ctnDetails().lyrTENS().tabDetails().btnOk().setText("Apply");
	}

	private void setParentTabVisability()
	{
		form.ctnDetails().lyrTENS().tabHeader().setHeaderVisible(form.getLocalContext().getSelectedParentInstanceIsNotNull());					
		enableParentControls();
	}

	private void enableParentControls()
	{
		boolean boolEnabled = (form.getMode().equals(FormMode.EDIT));
		form.ctnDetails().lyrTENS().tabHeader().dtimAuthoring().setEnabled(boolEnabled);
		form.ctnDetails().lyrTENS().tabHeader().qmbAuthoringCP().setEnabled(boolEnabled);
	}

	private void populateChildInstanceControls(ElectrotherapyTensTreatmentVo voTensTreatments)
	{
		if (voTensTreatments == null ) 
			return;
			
		//Display the details data
		form.ctnDetails().lyrTENS().tabDetails().cmbArea().setValue(voTensTreatments.getAreaIsNotNull() ? voTensTreatments.getArea(): null);
		form.ctnDetails().lyrTENS().tabDetails().cmbLaterality().setValue(voTensTreatments.getLateralityIsNotNull() ? voTensTreatments.getLaterality(): null);
		form.ctnDetails().lyrTENS().tabDetails().cmbModel().setValue(voTensTreatments.getModelIsNotNull() ? voTensTreatments.getModel(): null);
		form.ctnDetails().lyrTENS().tabDetails().txtSerial().setValue(voTensTreatments.getSerialNrIsNotNull() ? voTensTreatments.getSerialNr(): null);
		form.ctnDetails().lyrTENS().tabDetails().cmbMode().setValue(voTensTreatments.getModeIsNotNull() ? voTensTreatments.getMode(): null);
		form.ctnDetails().lyrTENS().tabDetails().ansProtocol().setValue(voTensTreatments.getProtocolFollowedIsNotNull() ? voTensTreatments.getProtocolFollowed(): null);
		form.ctnDetails().lyrTENS().tabDetails().intPulseRate().setValue(voTensTreatments.getPulseRateIsNotNull() ? voTensTreatments.getPulseRate(): null);
		form.ctnDetails().lyrTENS().tabDetails().intPulseWidth().setValue(voTensTreatments.getPulseWidthIsNotNull() ? voTensTreatments.getPulseWidth(): null);
		form.ctnDetails().lyrTENS().tabDetails().intDuration().setValue(voTensTreatments.getDurationIsNotNull() ? voTensTreatments.getDuration(): null);
		form.ctnDetails().lyrTENS().tabDetails().cmbUsage().setValue(voTensTreatments.getDailyUsageIsNotNull() ? voTensTreatments.getDailyUsage(): null);
		form.ctnDetails().lyrTENS().tabDetails().cmbElectrodes().setValue(voTensTreatments.getElectrodesIsNotNull() ? voTensTreatments.getElectrodes(): null);
		form.ctnDetails().lyrTENS().tabDetails().cmbSize().setValue(voTensTreatments.getSizeIsNotNull() ? voTensTreatments.getSize(): null);
	}

	private void populateParentInstanceControls(ElectrotherapyTENSVo voTENSParent)
	{
		if(voTENSParent != null)
		{
			form.ctnDetails().lyrTENS().tabHeader().dtimAuthoring().setValue(voTENSParent.getAuthoringDateTime());
			if(voTENSParent.getAuthoringCP() != null)
				form.ctnDetails().lyrTENS().tabHeader().qmbAuthoringCP().newRow(voTENSParent.getAuthoringCP(), voTENSParent.getAuthoringCP().toString());
			form.ctnDetails().lyrTENS().tabHeader().qmbAuthoringCP().setValue(voTENSParent.getAuthoringCP());
			form.ansWarning().setValue(voTENSParent.getWarningGiven());
		}
	}

	private ElectrotherapyTENSVo getParentFromDomain(ElectrotherapyTENSShortVo voTENSShort)
	{
		//return domain.getElectrotherapyTens(voTENSShort.getClinicalContact());
		return domain.getElectrotherapyTensByRef(voTENSShort);//	WDEV-13592
	}

	private void updateContextMenusState()
	{
		if (form.getGlobalContext().Core.getCurrentClinicalContactIsNotNull())
		{
			if(form.getLocalContext().getGoldenInstanceSelected().booleanValue())
			{
				// If the golden instance is currently selected we change the text of the update context menu
				// based on whether a parent or child node is selected in the grid.
				if (form.grdTENS().getValue() instanceof ElectrotherapyTENSShortVo)
					form.getContextMenus().getGenericGridUpdateItem().setVisible(false);						
				else if (form.grdTENS().getValue() instanceof ElectrotherapyTensTreatmentVo)
					form.getContextMenus().getGenericGridUpdateItem().setText("Edit TENS Treatment Details");
				
				// If the golden node is selected we can now only add child nodes so set the menu text accordingly.
				form.getContextMenus().getGenericGridAddItem().setText("Add TENS Treatment Details");			
	
				// Only show the update menu if the user has selected an item to update.
				form.getContextMenus().getGenericGridUpdateItem().setVisible((form.getMode().equals(FormMode.VIEW)&& 
																			 form.grdTENS().getSelectedRowIndex()>=0) &&
																			 form.grdTENS().getValue() instanceof ElectrotherapyTensTreatmentVo ||
																			 (form.getMode().equals(FormMode.EDIT) &&
																			 form.grdTENS().getValue() instanceof ElectrotherapyTensTreatmentVo &&
																			 ((ElectrotherapyTensTreatmentVo)form.grdTENS().getValue()).getID_ElectrotherapyTensTreatment() == null));																								
			}
			else
			{
				// If the golden instance has been found but NOT selected hide the context menu's 
				if (form.getLocalContext().getGoldenInstanceFound().booleanValue() == true)
				{
					form.getContextMenus().hideAllGenericGridMenuItems();
					return;
				}
				else
				{
					form.getContextMenus().getGenericGridAddItem().setText("New TENS Record");
					form.getContextMenus().getGenericGridUpdateItem().setVisible(false);				
				}
			}
			form.getContextMenus().getGenericGridAddItem().setVisible(form.getMode().equals(FormMode.VIEW));
			form.getContextMenus().getGenericGridRemoveItem().setVisible(form.getMode().equals(FormMode.EDIT) &&
					 form.grdTENS().getValue() instanceof ElectrotherapyTensTreatmentVo &&
					 ((ElectrotherapyTensTreatmentVo)form.grdTENS().getValue()).getID_ElectrotherapyTensTreatment() == null);
		}
		else
		{
			form.getContextMenus().hideAllGenericGridMenuItems();
		}
	}

	private void populateParentNodes(ElectrotherapyTENSShortVoCollection voTENSColl)
	{
		if (voTENSColl == null || voTENSColl.size() <= 0 ) return;
		
		for(int i=0; i<voTENSColl.size(); i++)
		{
			populateParentNode(voTENSColl.get(i));			
		}
	}

	private void populateParentNode(ElectrotherapyTENSShortVo voTENS)
	{
		GenForm.grdTENSRow parRow = form.grdTENS().getRows().newRow();
		parRow.setcolAreaParent(voTENS.getAuthoringDateTime().toString());
		
		parRow.setValue(voTENS);
		form.grdTENS().setValue(voTENS);
		expandParentNode(parRow);
	}

	private void expandParentNode(grdTENSRow selectedRow)
	{
		if (selectedRow.getValue() instanceof ElectrotherapyTENSShortVo == false ) return;
		
		form.getLocalContext().setSelectedParentInstance(getParentFromDomain((ElectrotherapyTENSShortVo) selectedRow.getValue()));		
		if (form.getLocalContext().getSelectedParentInstance() == null ) return; 				

		ElectrotherapyTENSVo voParentInstance = form.getLocalContext().getSelectedParentInstance();				
		if(form.getGlobalContext().Core.getCurrentClinicalContactIsNotNull()
				&& (voParentInstance.getClinicalContact().getID_ClinicalContact().equals
									(form.getGlobalContext().Core.getCurrentClinicalContact().getID_ClinicalContact())))
		{
			// If this is the Golden Node then we'll colour it golden (ok then beige).
			form.getLocalContext().setGoldenInstanceFound(new Boolean(true));
			form.getLocalContext().setGoldenInstanceSelected(new Boolean(true));
			selectedRow.setBackColor(Color.Beige);			
		}
		if (voParentInstance.getTensTreatmentsIsNotNull())  
		{
			selectedRow.setExpanded(true);
			populateChildNodes(selectedRow, voParentInstance.getTensTreatments());
		}
		populateParentInstanceControls(voParentInstance);		
	}

	private void populateChildNodes(grdTENSRow parRow, ElectrotherapyTensTreatmentVoCollection voTensTreatmentColl)
	{
		parRow.getRows().clear();
		
		if (voTensTreatmentColl == null || voTensTreatmentColl.size()<=0) return;		
		
		//RehabilitationVo voParentInstance = form.getLocalContext().getSelectedParentInstance();				
		//if(voParentInstance.getClinicalContact().getID_ClinicalContact().equals
		//						(form.getGlobalContext().Core.getCurrentClinicalContact().getID_ClinicalContact()))
		
		voTensTreatmentColl.sort();
		for(int i=0; i<voTensTreatmentColl.size(); i++)
		{
			if (parRow != null)
			{
				GenForm.grdTENSRow childRow = parRow.getRows().newRow();
				populateChildNode(childRow, voTensTreatmentColl.get(i));				
			}
		}
		//reselect parent
		form.grdTENS().setValue(parRow.getValue());
		parRow.setExpanded(true);
	}

	private void populateChildNode(grdTENSRow childRow, ElectrotherapyTensTreatmentVo voTensTreatments)
	{
		if (voTensTreatments == null) return;		
		
		childRow.setValue(voTensTreatments);
		if(voTensTreatments.getArea() != null)
			childRow.setcolAreaParent(voTensTreatments.getArea().toString());
		if(voTensTreatments.getLaterality() != null)
			childRow.setcolLateralityChild(voTensTreatments.getLaterality().toString());
		if(voTensTreatments.getModel() != null)
			childRow.setcolModel(voTensTreatments.getModel().toString());
		if(voTensTreatments.getMode() != null)
			childRow.setcolMode(voTensTreatments.getMode().toString());			
		if(voTensTreatments.getDuration() != null)
			childRow.setcolDuration(voTensTreatments.getDuration().toString());			
		
		//if not saved then set the colour
		if(voTensTreatments.getID_ElectrotherapyTensTreatment() == null)
			childRow.setTextColor(Color.Red);
	}

	private void resetContextVariables()
	{
		form.getLocalContext().setGoldenInstanceSelected(new Boolean(false));
		form.getLocalContext().setGoldenInstanceFound(new Boolean(false));
		form.getLocalContext().setUpdatingParent(new Boolean(false));
		form.getLocalContext().setSelectedParentInstance(null);
		form.getLocalContext().setSelectedChildInstance(null);
	}

	private void clear()
	{
		form.grdTENS().getRows().clear();		
		clearChildInstanceControls();
	}

	private void clearChildInstanceControls()
	{
		form.ctnDetails().lyrTENS().tabDetails().cmbArea().setValue(null);
		form.ctnDetails().lyrTENS().tabDetails().cmbLaterality().setValue(null);
		form.ctnDetails().lyrTENS().tabDetails().cmbModel().setValue(null);
		form.ctnDetails().lyrTENS().tabDetails().txtSerial().setValue(null);
		form.ctnDetails().lyrTENS().tabDetails().cmbMode().setValue(null);
		form.ctnDetails().lyrTENS().tabDetails().ansProtocol().setValue(null);
		form.ctnDetails().lyrTENS().tabDetails().intPulseRate().setValue(null);
		form.ctnDetails().lyrTENS().tabDetails().intPulseWidth().setValue(null);
		form.ctnDetails().lyrTENS().tabDetails().intDuration().setValue(null);
		form.ctnDetails().lyrTENS().tabDetails().cmbUsage().setValue(null);
		form.ctnDetails().lyrTENS().tabDetails().cmbElectrodes().setValue(null);
		form.ctnDetails().lyrTENS().tabDetails().cmbSize().setValue(null);
	}

	private void initialize()
	{
		form.ctnDetails().setCollapsed(true);
		form.ctnDetails().lyrTENS().tabEmpty1().setHeaderVisible(false);
	}

	protected void onFormModeChanged() 
	{
		updateControlsState();
	}

	protected void onQmbAuthoringCPTextSubmited(String value) throws ims.framework.exceptions.PresentationLogicException
	{
		form.ctnDetails().lyrTENS().tabHeader().qmbAuthoringCP().clear();
		HcpFilter filter = new HcpFilter();
		PersonName name = new PersonName();
		name.setSurname(value);
		filter.setQueryName(name);
		
		HcpCollection coll = domain.listHcps(filter);
		for (int i = 0; i < coll.size(); i++)
		{
			Hcp med = coll.get(i);
			form.ctnDetails().lyrTENS().tabHeader().qmbAuthoringCP().newRow(med, med.toString());			
		}
		if (coll.size() == 1)
		{
			form.ctnDetails().lyrTENS().tabHeader().qmbAuthoringCP().setValue(coll.get(0));
		}
		else if (coll.size() > 1)
		{
			form.ctnDetails().lyrTENS().tabHeader().qmbAuthoringCP().showOpened();		
		}		
	}

	private boolean addOrUpdateChild()
	{
		if (form.getLocalContext().getGoldenInstanceSelected().equals(Boolean.FALSE))			
			newParentNode(form.getLocalContext().getSelectedParentInstance());			
				
		ElectrotherapyTensTreatmentVo voElectrotherapyTensTreatment = form.getLocalContext().getSelectedChildInstance();
		
		if (voElectrotherapyTensTreatment == null)
			voElectrotherapyTensTreatment = new ElectrotherapyTensTreatmentVo(); 

		populateChildInstanceData(voElectrotherapyTensTreatment);

		if (form.grdTENS().getValue() instanceof ElectrotherapyTensTreatmentVo && form.getLocalContext().getSelectedChildInstanceIsNotNull())
		{
			ElectrotherapyTensTreatmentVo voElectrotherapyTensTreatmentSelected = (ElectrotherapyTensTreatmentVo)form.grdTENS().getValue();
			
			if (voElectrotherapyTensTreatmentSelected.getID_ElectrotherapyTensTreatment() == null) 
			{
				//allow update of selected child that is not saved yet.
				promoteUpdatedChild(voElectrotherapyTensTreatment);
				newChildInstance();
				updateControlsState();
			}
		}
		else
		{
			if(newChildNode(voElectrotherapyTensTreatment))
				return true;			
		}
		return false;		
	}

	private boolean newChildNode(ElectrotherapyTensTreatmentVo voTensTreatment)
	{
		if (form.getLocalContext().getSelectedChildInstance() != null) return false;
		
		if (voTensTreatment.countFieldsWithValue() >= 1)
		{
			String strErrors[] = voTensTreatment.validate();
		
			if(strErrors != null && strErrors.length > 0)
			{
				engine.showErrors(strErrors);
				return true;
			}
			promoteChild(voTensTreatment);			
			clearChildInstanceControls();
		}
		return false;
	}

	private void promoteChild(ElectrotherapyTensTreatmentVo voTensTreatment)
	{
		if (voTensTreatment == null) return;
		GenForm.grdTENSRow childRow = null;
		form.grdTENS().setValue(form.getLocalContext().getSelectedParentInstance());
		if(form.grdTENS().getSelectedRow().getValue() instanceof ElectrotherapyTensTreatmentVo)
			childRow = form.grdTENS().getSelectedRow().getParentRow().getRows().newRow();
		else
			childRow = form.grdTENS().getSelectedRow().getRows().newRow();		
		populateChildNode(childRow, voTensTreatment);	
	}

	private void newChildInstance()
	{
		clearChildInstanceControls();
		form.getLocalContext().setSelectedChildInstance(null);
		if (form.grdTENS().getValue() instanceof ElectrotherapyTENSShortVo)
			form.grdTENS().setValue(form.grdTENS().getSelectedRow().getValue());
		else if (form.grdTENS().getValue() instanceof ElectrotherapyTensTreatmentVo)
			form.grdTENS().setValue(form.grdTENS().getSelectedRow().getParentRow().getValue());
	}

	private void promoteUpdatedChild(ElectrotherapyTensTreatmentVo voTensTreatments)
	{
		if (voTensTreatments == null) return;
		populateChildNode(form.grdTENS().getSelectedRow(),voTensTreatments);	
	}

	private void populateChildInstanceData(ElectrotherapyTensTreatmentVo voTensTreatments)
	{
		voTensTreatments.setArea(form.ctnDetails().lyrTENS().tabDetails().cmbArea().getValue());
		voTensTreatments.setLaterality(form.ctnDetails().lyrTENS().tabDetails().cmbLaterality().getValue());
		voTensTreatments.setModel(form.ctnDetails().lyrTENS().tabDetails().cmbModel().getValue());
		voTensTreatments.setSerialNr(form.ctnDetails().lyrTENS().tabDetails().txtSerial().getValue());
		voTensTreatments.setMode(form.ctnDetails().lyrTENS().tabDetails().cmbMode().getValue());
		voTensTreatments.setProtocolFollowed(form.ctnDetails().lyrTENS().tabDetails().ansProtocol().getValue());
		voTensTreatments.setPulseRate(form.ctnDetails().lyrTENS().tabDetails().intPulseRate().getValue());
		voTensTreatments.setPulseWidth(form.ctnDetails().lyrTENS().tabDetails().intPulseWidth().getValue());
		voTensTreatments.setDuration(form.ctnDetails().lyrTENS().tabDetails().intDuration().getValue());
		voTensTreatments.setDailyUsage(form.ctnDetails().lyrTENS().tabDetails().cmbUsage().getValue());
		voTensTreatments.setElectrodes(form.ctnDetails().lyrTENS().tabDetails().cmbElectrodes().getValue());
		voTensTreatments.setSize(form.ctnDetails().lyrTENS().tabDetails().cmbSize().getValue());
	}

	private void newParentNode(ElectrotherapyTENSVo voNewParent)
	{
		if (voNewParent == null) return;
		
		ElectrotherapyTENSShortVo voParentShort = new ElectrotherapyTENSShortVo();
		voParentShort.setAuthoringCP(voNewParent.getAuthoringCP());
		voParentShort.setAuthoringDateTime(voNewParent.getAuthoringDateTime());
		voParentShort.setClinicalContact(voNewParent.getClinicalContact());

		GenForm.grdTENSRow parentRow = form.grdTENS().getRows().newRow();
		if(voParentShort.getAuthoringCP() != null)
			parentRow.setcolAreaParent(voParentShort.getAuthoringDateTime().toString()+ " - " + 
							   	voParentShort.getAuthoringCP().toString());			
		parentRow.setValue(voParentShort);
		parentRow.setBackColor(Color.Beige);
		form.getLocalContext().setGoldenInstanceSelected(new Boolean(true));
		parentRow.setExpanded(true);
		form.grdTENS().setValue(voParentShort);
	}

	private boolean save()
	{
		if(form.ctnDetails().lyrTENS().tabHeader().qmbAuthoringCP().getValue() == null)
		{
			engine.showMessage("No Authoring HCP has been provided! Please select an Authoring HCP from the TENS tab before proceeding.");
			return false;
		}
		
		if(form.ctnDetails().lyrTENS().tabHeader().dtimAuthoring().getValue() == null) //WDEV-15384
		{
			engine.showMessage("Authoring Date/Time from the TENS tab is mandatory!");
			return false;
		}
		
		ElectrotherapyTENSVo voTENS  = populateInstanceData(form.getLocalContext().getSelectedParentInstance());
		
		if (voTENS.getClinicalContact() == null)
			voTENS.setClinicalContact(form.getGlobalContext().Core.getCurrentClinicalContact());

		String[] arrErrors =  voTENS.validate(validateUIRules());	
		
		if(arrErrors != null)
		{
			engine.showErrors(arrErrors);
			return false;
		}
		
		if(checkForUnsavedChild()) //WDEV-15384
			return false;
		
		try
		{
			form.getLocalContext().setSelectedParentInstance(domain.saveElectrotherapyTens(voTENS));
		}
		catch(StaleObjectException e)
		{
			engine.showMessage(ims.configuration.gen.ConfigFlag.UI.STALE_OBJECT_MESSAGE.getValue());
			open();
			return false;
		} 
		catch (UniqueKeyViolationException e) 
		{
			engine.showMessage("A TENS record already exists for this SOAP clinical contact. " + e.getMessage());
			open();
			return false;
		}
		setUpdatedChild();
		return true;		
	}

	private void setUpdatedChild()
	{
		// Straight child update - Select the currently being updated child as the one so show - post save
		if (form.grdTENS().getValue() instanceof ElectrotherapyTensTreatmentVo)		
		{
			form.getLocalContext().setUpdatedChild((ElectrotherapyTensTreatmentVo) form.grdTENS().getValue());
		}
		else
		{
			// Find the most recently added child
			if (form.getLocalContext().getUpdatingParent().equals(Boolean.FALSE))
			{
				form.getLocalContext().getSelectedParentInstance().getTensTreatments().sort();
				form.getLocalContext().setUpdatedChild(
						form.getLocalContext().getSelectedParentInstance().getTensTreatments().get(
								form.getLocalContext().getSelectedParentInstance().getTensTreatments().size() - 1));
			}
		}
	}

	private String[] validateUIRules()
	{
		return null;
	}

	private ElectrotherapyTENSVo populateInstanceData(ElectrotherapyTENSVo voTENS)
	{
		if (voTENS == null)
			voTENS = new ElectrotherapyTENSVo();
				
		populateParentInstanceData(voTENS);						

		if (form.getLocalContext().getSelectedChildInstance() == null)
		{
			populateChildCollectionFromGrid(voTENS);			
		}
		else 		// Update child instance.
		{
			ElectrotherapyTensTreatmentVo voTensTreatments = form.getLocalContext().getSelectedChildInstance();
			populateChildInstanceData(voTensTreatments);
			form.getLocalContext().setSelectedChildInstance(voTensTreatments);
			
			for (int i=0; i < voTENS.getTensTreatments().size(); i++)
			{
				if (voTENS.getTensTreatments().get(i).getID_ElectrotherapyTensTreatment() == 
					form.getLocalContext().getSelectedChildInstance().getID_ElectrotherapyTensTreatment())
				{						
					voTENS.getTensTreatments().set(i, form.getLocalContext().getSelectedChildInstance());
				}
			}								
		}
		return voTENS;	
	}

	private void populateChildCollectionFromGrid(ElectrotherapyTENSVo voTENS)
	{
		if (form.grdTENS().getValue() == null) return;

		if (voTENS.getTensTreatments() == null)
			voTENS.setTensTreatments(new ElectrotherapyTensTreatmentVoCollection());
		
		GenForm.grdTENSRow parentRow = form.grdTENS().getValue() instanceof ElectrotherapyTENSShortVo ? 
										    form.grdTENS().getSelectedRow() : form.grdTENS().getSelectedRow().getParentRow();		
		
        if (parentRow.getRows().size() > 0 )
        	voTENS.getTensTreatments().clear();
										    
		for (int i=0; i < parentRow.getRows().size(); i++)
        {
			voTENS.getTensTreatments().add((ElectrotherapyTensTreatmentVo) parentRow.getRows().get(i).getValue());
        }
	}

	private void populateParentInstanceData(ElectrotherapyTENSVo voTENS)
	{
		voTENS.setWarningGiven(form.ansWarning().getValue());
		voTENS.setAuthoringCP(form.ctnDetails().lyrTENS().tabHeader().qmbAuthoringCP().getValue());
		voTENS.setAuthoringDateTime(form.ctnDetails().lyrTENS().tabHeader().dtimAuthoring().getValue());
	}

	private boolean checkForUnsavedChild()
	{
		if (form.getLocalContext().getSelectedChildInstance() == null)
		{				
			if(addOrUpdateChild())
				return true;
		}
		return false;
	}

	private void newInstance()
	{
		// New instance has been invoked for a parent instance with no current selection made OR
		// with a parent that is not the Golden node..
		if (form.grdTENS().getValue() == null || ((form.getLocalContext().getSelectedParentInstanceIsNotNull() 
				&& form.getLocalContext().getGoldenInstanceSelected().equals(Boolean.FALSE))))
		{
			newParentInstance();			
			form.setMode(FormMode.EDIT);		
		}
		else
		{
			newChildInstance();
			form.setMode(FormMode.EDIT);		
			disableHeaderInfo();
		}
	}

	private void newParentInstance()
	{
		ElectrotherapyTENSVo voNewParent = new ElectrotherapyTENSVo(); 		
		voNewParent.setAuthoringCP((Hcp) domain.getHcpUser());
		voNewParent.setAuthoringDateTime(new DateTime());
		voNewParent.setClinicalContact(form.getGlobalContext().Core.getCurrentClinicalContact());
		voNewParent.setTensTreatments(new ElectrotherapyTensTreatmentVoCollection());
		populateParentInstanceControls(voNewParent);
		form.getLocalContext().setSelectedParentInstance(voNewParent);					
	}
	
	protected void onContextMenuItemClick(int menuItemID, Control sender) throws PresentationLogicException
	{
		switch(menuItemID)
		{
			case GenForm.ContextMenus.GenericGrid.Add:
				newInstance();
				break;
			case GenForm.ContextMenus.GenericGrid.Update:
				updateInstance();
				break;
			case GenForm.ContextMenus.GenericGrid.Remove:
				removeChild();
		}
	}

	private void removeChild()
	{
		ElectrotherapyTENSShortVo voParent = (ElectrotherapyTENSShortVo) form.grdTENS().getSelectedRow().getParentRow().getValue();
		form.grdTENS().removeSelectedRow();
		form.grdTENS().setValue(voParent);
		updateContextMenusState();
	}

	private void updateInstance()
	{
		form.getLocalContext().setUpdatingParent(new Boolean(form.grdTENS().getValue() instanceof ElectrotherapyTENSShortVo));
		form.setMode(FormMode.EDIT);
		if (form.grdTENS().getValue() instanceof ElectrotherapyTensTreatmentVo)			
		{
			ElectrotherapyTensTreatmentVo voChild = (ElectrotherapyTensTreatmentVo) form.grdTENS().getValue(); 
			if ( voChild.getID_ElectrotherapyTensTreatment() == null)
			{
				populateChildInstanceControls(voChild);
			}			
		}
		disableHeaderInfo();
	}

	private void disableHeaderInfo()
	{
		form.ctnDetails().lyrTENS().tabHeader().dtimAuthoring().setEnabled(false);
		form.ctnDetails().lyrTENS().tabHeader().qmbAuthoringCP().setEnabled(false);
	}

	protected void onBtnOkClick() throws PresentationLogicException
	{
		addOrUpdateChild();
	}

	protected void onBtnCancelDetailsClick() throws PresentationLogicException
	{
		clearChildInstanceControls();
		newChildInstance();
	}

	protected void onBtnNewClick() throws PresentationLogicException
	{
		newInstance();
	}

	protected void onBtnCancelClick() throws PresentationLogicException
	{
		open();
	}

	protected void onBtnSaveClick() throws PresentationLogicException
	{
		if (save())
			open();	
	}

	protected void onGrdTENSSelectionChanged() throws PresentationLogicException
	{
		getSelectedInstance();
	}

	protected void onBtnUpdateClick() throws PresentationLogicException
	{
		updateInstance();
	}
	
	protected void updateContextMenus()
	{
		if (form.grdTENS().getSelectedRowIndex()>=0)
		{
			form.getContextMenus().getGenericGridAddItem().setVisible(true);
			form.getContextMenus().getGenericGridUpdateItem().setVisible(true);
		}
		else
		{
			form.getContextMenus().getGenericGridAddItem().setVisible(true);
			form.getContextMenus().getGenericGridUpdateItem().setVisible(false);
		}
		form.getContextMenus().getGenericGridMoveDownItem().setVisible(false);
		form.getContextMenus().getGenericGridMoveUpItem().setVisible(false);
		form.getContextMenus().getGenericGridRemoveItem().setVisible(false);
		form.getContextMenus().getGenericGridViewItem().setVisible(false);
	}	
}