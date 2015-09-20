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
// This code was generated by Marius Mihalec using IMS Development Environment (version 1.62 build 3112.33410)
// Copyright (C) 1995-2008 IMS MAXIMS plc. All rights reserved.

package ims.core.forms.warddataview;

import ims.clinical.vo.lookups.VTEAsessmentStatus;
import ims.configuration.gen.ConfigFlag;
import ims.core.admin.pas.vo.InpatientEpisodeRefVo;
import ims.core.admin.pas.vo.PASEventRefVo;
import ims.core.forms.warddataview.GenForm.grdInpatientsRow;
import ims.core.helper.ResetPIDBarHelper;
import ims.core.vo.InpatientEpisodeLiteVo;
import ims.core.vo.InpatientEpisodeLiteVoCollection;
import ims.core.vo.LocationLiteVo;
import ims.core.vo.LocationLiteVoCollection;
import ims.core.vo.PasEventADTVo;
import ims.core.vo.PatientId;
import ims.core.vo.PatientShort;
import ims.core.vo.PendingElectiveAdmissionAdmitVo;
import ims.core.vo.PendingElectiveAdmissionAdmitVoCollection;
import ims.core.vo.PendingEmergencyAdmissionAdmitVo;
import ims.core.vo.PendingEmergencyAdmissionAdmitVoCollection;
import ims.core.vo.PendingTransfersLiteVo;
import ims.core.vo.PendingTransfersLiteVoCollection;
import ims.core.vo.WardBayConfigVo;
import ims.core.vo.WardDataViewVo;
import ims.core.vo.WardDataViewVoCollection;
import ims.core.vo.WardViewSearchCriteriaVo;
import ims.core.vo.lookups.PatIdType;
import ims.framework.Control;
import ims.framework.FormName;
import ims.framework.controls.DynamicGrid;
import ims.framework.controls.DynamicGridCell;
import ims.framework.controls.DynamicGridColumn;
import ims.framework.controls.DynamicGridRow;
import ims.framework.enumerations.Alignment;
import ims.framework.enumerations.DialogResult;
import ims.framework.enumerations.DynamicCellType;
import ims.framework.exceptions.CodingRuntimeException;
import ims.framework.exceptions.PresentationLogicException;
import ims.framework.interfaces.ILocation;
import ims.framework.utils.Color;
import ims.framework.utils.Image;

import com.ims.query.builder.client.QueryBuilderClient;
import com.ims.query.builder.client.SeedValue;
import com.ims.query.builder.client.exceptions.QueryBuilderClientException;

public class Logic extends BaseLogic
{
	private static final long serialVersionUID = 1L;
	public static final String	COLNAME							= "-1";	
	public static final Integer	CURRENT							= -5;
	public static final Integer	INCOMING_ADMISION				= -6;
	public static final Integer	INCOMING_PENDING_TRANSFERS		= -7;
	public static final Integer	OUTGOING_DISCHARGE				= -8;
	public static final Integer	OUTGOING_PENDING_TRANSFERS		= -9;
	
	@Override
	protected void onFormOpen(Object[] args) throws ims.framework.exceptions.PresentationLogicException
	{		
		form.imbRefresh().setEnabled(false);
		loadHospitals();	
		
		if (form.getGlobalContext().Core.getWardDataViewSearchCriteriaIsNotNull())///WDEV-12925 -Start
		{
			form.cmbHospital().setValue(form.getGlobalContext().Core.getWardDataViewSearchCriteria().getHospital());
			loadWardsForSelectedHosp();
			form.cmbWard().setValue(form.getGlobalContext().Core.getWardDataViewSearchCriteria().getWard());
			wardValueChanged();
		}///WDEV-12925-End
		else
		{
			//WDEV-15908 - starts here
    		ILocation currentLocation = engine.getCurrentLocation();
    		LocationLiteVo currentHospiptal = domain.getCurrentHospital(currentLocation);
    		form.cmbHospital().setValue(currentHospiptal);
    		
    		loadWardsForSelectedHosp();
    		if(currentLocation instanceof LocationLiteVo)
    		{
    			form.cmbWard().setValue((LocationLiteVo) currentLocation);
    			wardValueChanged();
    		}
    		
    		if(form.cmbWard().getValue() == null && form.cmbWard().getValues() != null && form.cmbWard().getValues().size() == 1)
    		{
    			if(form.cmbWard().getValues().get(0) instanceof LocationLiteVo)
    			{
    				form.cmbWard().setValue((LocationLiteVo) form.cmbWard().getValues().get(0));
    				wardValueChanged();
    			}
    		}
    		//WDEV-15908 - ends here
		}
	}
	
	private void populateSearchCriteriaDataFromScreen()///WDEV-12925
	{
		if (form.cmbHospital().getValue() == null)
		{
			form.getGlobalContext().Core.setWardDataViewSearchCriteria(null);
			return;
		}
		WardViewSearchCriteriaVo crit = new WardViewSearchCriteriaVo();
		crit.setHospital(form.cmbHospital().getValue());
		crit.setWard(form.cmbWard().getValue());
		form.getGlobalContext().Core.setWardDataViewSearchCriteria(crit);
	}

	private void loadHospitals()
	{
		LocationLiteVoCollection hospColl = domain.listActiveHospitalsLite();
		if(hospColl != null)
		{
			for(int x = 0; x < hospColl.size(); x++)
			{
				if(hospColl.get(x) != null)
					form.cmbHospital().newRow(hospColl.get(x), hospColl.get(x).getName());
			}
		}
	}

	private void addWard(LocationLiteVo ward) 
	{
		if(ward == null)
			return;
		
		form.cmbWard().newRow(ward, ward.getName());
	}

	
	@Override
	protected void onCmbWardValueChanged() throws PresentationLogicException 
	{		
		wardValueChanged();
		populateSearchCriteriaDataFromScreen();///WDEV-12925
	}
	
	private void wardValueChanged()
	{
		form.getGlobalContext().Core.setSelectingPatientForm(null);
		form.getGlobalContext().Core.setPatientShort(null);
		form.getGlobalContext().Core.setCurrentCareContext(null);
		
		form.imbRefresh().setEnabled(false);
		
		if(form.cmbWard().getValue() != null)
		{
			WardBayConfigVo voWardBayConfig = domain.getWardBayConfigByWard(form.cmbWard().getValue());
			if(voWardBayConfig != null)
				form.getGlobalContext().Core.setHasWaitingArea(voWardBayConfig.getIsWaitingAreaIsNotNull() && voWardBayConfig != null && voWardBayConfig.getIsWaitingArea());
			
			form.imbRefresh().setEnabled(true);
		}
		
		loadData();
		populateDynamicGridFromData();
		populateInpatientsGrid();
	}

	private void populateInpatientsGrid()
	{
		form.grdInpatients().getRows().clear();
		
		PatIdType dispIdType = PatIdType.getNegativeInstance(ConfigFlag.UI.DISPLAY_PATID_TYPE.getValue()); 
		form.grdInpatients().setColDisplayIdCaption(dispIdType.getText());
		
		if(form.cmbWard().getValue() == null)
			return;
		
		if(ConfigFlag.UI.BED_INFO_UI_TYPE.getValue().equals("CCO"))
			fillGridFromHomeLeaveANDInpatRecords();
		else
			fillFromInpatRecords();
		
	}

	private void fillFromInpatRecords() 
	{
		WardDataViewVoCollection voCollWdv = domain.listInpatientsInBedsByWard(form.cmbWard().getValue());
		if(voCollWdv != null)
		{
			for (WardDataViewVo voWdv : voCollWdv)
			{
				grdInpatientsRow row = form.grdInpatients().getRows().newRow();
				PatientShort voPatient = voWdv.getPatient();
				if(voPatient != null)
				{
					if(voPatient.getNameIsNotNull())
					{
						row.setColForename(voPatient.getName().getForename());
						row.setColSurname(voPatient.getName().getSurname());
					}
					
					PatientId patId = voPatient.getDisplayId();
					row.setColDisplayId(patId != null ? patId.getValue() : null);				
					Integer age = voPatient.calculateAge();
					if(age != null)
						row.setColAge(String.valueOf(age));
	
					if(voPatient.getHasActiveAlertsIsNotNull() && voPatient.getHasActiveAlerts())   //wdev-11083
						row.setColAlert(form.getImages().Core.Alert16); //WDEV-18011 
					row.setColInfant(calculateInfants(voPatient));
				}
				
				if(voWdv.getBayIsNotNull())
					row.setColBay(voWdv.getBay().getName());
				if(voWdv.getBedIsNotNull())
					row.setColBedNumber(voWdv.getBed().getBedNumber());		
				
				row.setColPasEventHidden(voWdv.getPasEvent());
				
				//wdev-14784
				if(	voWdv.getInpatEpisodeIsNotNull() && voWdv.getInpatEpisode().getVTEAssessmentStatusIsNotNull()  && ConfigFlag.UI.VTE_RISK_ASSESSMENT_FUNCTIONALITY.getValue() == true) //wdev-15062
				{
					row.setColVTEStatus(VTEAsessmentStatus.REQUIRED.equals(voWdv.getInpatEpisode().getVTEAssessmentStatus()) ? form.getImages().OCRR.Requested : (VTEAsessmentStatus.INPROGRESS.equals(voWdv.getInpatEpisode().getVTEAssessmentStatus()) ? form.getImages().OCRR.InProgress : null));
											
				}
				else
				{
					row.setColVTEStatus(null);
				}
				//----------
				
				row.setTooltip(buildRowTooltip(voWdv));
				row.setValue(voPatient);
			}
		}
	}

	private void fillGridFromHomeLeaveANDInpatRecords()
	{
		InpatientEpisodeLiteVoCollection voCollHL = form.getLocalContext().getOnHomeLeave();
		for (int i=0; i < voCollHL.size() ; i++ )
		{
			InpatientEpisodeLiteVo voHLInpatEpis = voCollHL.get(i);
			grdInpatientsRow row = form.grdInpatients().getRows().newRow();
			
			PatientShort voPatient = voHLInpatEpis.getPasEvent().getPatient();
			if(voPatient != null)
			{
				if(voPatient.getNameIsNotNull())
				{
					row.setColForename(voPatient.getName().getForename());
					row.setColSurname(voPatient.getName().getSurname());
				}
				
				PatientId patId = voPatient.getDisplayId();
				row.setColDisplayId(patId != null ? patId.getValue() : null);				
				Integer age = voPatient.calculateAge();
				if(age != null)
					row.setColAge(String.valueOf(age));

				if(voPatient.getHasActiveAlertsIsNotNull() && voPatient.getHasActiveAlerts())   //wdev-11083
					row.setColAlert(form.getImages().Core.Alert16); //WDEV-18011 
				row.setColInfant(calculateInfants(voPatient));
			}
			//wdev-14784
			if(	voHLInpatEpis != null && voHLInpatEpis.getVTEAssessmentStatusIsNotNull() && ConfigFlag.UI.VTE_RISK_ASSESSMENT_FUNCTIONALITY.getValue() == true ) //wdev-15062
			{
				row.setColVTEStatus(VTEAsessmentStatus.REQUIRED.equals(voHLInpatEpis.getVTEAssessmentStatus()) ? form.getImages().OCRR.Requested : (VTEAsessmentStatus.INPROGRESS.equals(voHLInpatEpis.getVTEAssessmentStatus()) ? form.getImages().OCRR.InProgress : null));
										
			}
			else
			{
				row.setColVTEStatus(null);
			}
			//----------
			
			row.setColBay("");
			row.setColBedNumber("(H)");		
			
			row.setColPasEventHidden(voHLInpatEpis.getPasEvent());		
			
			row.setTooltip(buildRowTooltip(voHLInpatEpis.getPasEvent(),voHLInpatEpis));	//wdev-14784

			row.setValue(voHLInpatEpis.getPasEvent().getPatient());
		}

		fillFromInpatRecords();
		
		
		form.grdInpatients().sort(0);
	}

	private String buildRowTooltip(PasEventADTVo voPE, InpatientEpisodeLiteVo voHLInpatEpis)
	{
		if (voPE == null)
			throw new CodingRuntimeException("voPE cannot be null in method buildRowTooltip");
		
		String consultant = voPE.getConsultantIsNotNull() ? voPE.getConsultant().getIMosName() : "";
		String nhsNo =  voPE.getPatientIsNotNull() && voPE.getPatient().getNhsn() != null ?  voPE.getPatient().getNhsn().getValue() : "";
		//wdev-14784
		String vteStatus = "";
		//wdev-14784
		if(	voHLInpatEpis != null && voHLInpatEpis.getVTEAssessmentStatusIsNotNull() && ConfigFlag.UI.VTE_RISK_ASSESSMENT_FUNCTIONALITY.getValue() == true) //wdev-15062
		{
			vteStatus = VTEAsessmentStatus.REQUIRED.equals(voHLInpatEpis.getVTEAssessmentStatus()) ? voHLInpatEpis.getVTEAssessmentStatus().getIItemText()  : (VTEAsessmentStatus.INPROGRESS.equals(voHLInpatEpis.getVTEAssessmentStatus()) ? voHLInpatEpis.getVTEAssessmentStatus().getIItemText() : (VTEAsessmentStatus.COMPLETED.equals(voHLInpatEpis.getVTEAssessmentStatus()) ? voHLInpatEpis.getVTEAssessmentStatus().getIItemText() : ""));  //wdev-14858 
		}
		
		//---------
		
		StringBuffer strTooltip = new StringBuffer();
		
		if( ConfigFlag.UI.VTE_RISK_ASSESSMENT_FUNCTIONALITY.getValue() == true) //wdev-15062
		{
			strTooltip.append("<html><head></head><body><p><font color='#FF0000'><strong>Other Detail</strong></font>" +
				"<table width='210' border='0'><tr><td width='70'><font color='#0033FF'>NHS Number :</font></td><td>" +
				nhsNo +
				"</td></tr><tr><td><font color='#0033FF'>Consultant :</font></td><td>" +
				consultant +
				"</td></tr><tr><td><font color='#0033FF'>VTE Assessment Status :</font></td><td>" +
				vteStatus +
				"</td></tr></table></p>" +
				"</body></html>");
		}
		else
		{
			strTooltip.append("<html><head></head><body><p><font color='#FF0000'><strong>Other Detail</strong></font>" +
					"<table width='210' border='0'><tr><td width='70'><font color='#0033FF'>NHS Number :</font></td><td>" +
					nhsNo +
					"</td></tr><tr><td><font color='#0033FF'>Consultant :</font></td><td>" +
					consultant +
					"</td></tr></table></p>" +
					"</body></html>");
		}
		
		return strTooltip.toString();
	}

	private String buildRowTooltip(WardDataViewVo voWdv)
	{
		if (voWdv == null)
			throw new CodingRuntimeException("voWdv cannot be null in method buildRowTooltip");
		
		String consultant = voWdv.getPasEventIsNotNull() && voWdv.getPasEvent().getConsultantIsNotNull() ? voWdv.getPasEvent().getConsultant().getIMosName() : "";
		String nhsNo =  voWdv.getPatientIsNotNull() && voWdv.getPatient().getNhsn() != null ?  voWdv.getPatient().getNhsn().getValue() : "";
		
		//wdev-14784
		String vteStatus = "";
		//wdev-14784
		if(	voWdv.getInpatEpisodeIsNotNull() && voWdv.getInpatEpisode().getVTEAssessmentStatusIsNotNull()  && ConfigFlag.UI.VTE_RISK_ASSESSMENT_FUNCTIONALITY.getValue() == true) //wdev-15062
		{
			vteStatus = VTEAsessmentStatus.REQUIRED.equals(voWdv.getInpatEpisode().getVTEAssessmentStatus()) ? voWdv.getInpatEpisode().getVTEAssessmentStatus().getIItemText()  : (VTEAsessmentStatus.INPROGRESS.equals(voWdv.getInpatEpisode().getVTEAssessmentStatus()) ? voWdv.getInpatEpisode().getVTEAssessmentStatus().getIItemText() :(VTEAsessmentStatus.COMPLETED.equals(voWdv.getInpatEpisode().getVTEAssessmentStatus()) ? voWdv.getInpatEpisode().getVTEAssessmentStatus().getIItemText() :"")); //wdev-14858
		}
		
		//---------
		
		StringBuffer strTooltip = new StringBuffer();
		
		if( ConfigFlag.UI.VTE_RISK_ASSESSMENT_FUNCTIONALITY.getValue() == true)   //wdev-15062
		{
			strTooltip.append("<html><head></head><body><p><font color='#FF0000'><strong>Other Detail</strong></font>" +
				"<table width='210' border='0'><tr><td width='70'><font color='#0033FF'>NHS Number :</font></td><td>" +
				nhsNo +
				"</td></tr><tr><td><font color='#0033FF'>Consultant :</font></td><td>" +
				consultant +
				"</td></tr><tr><td><font color='#0033FF'>VTE Assessment Status :</font></td><td>" +
				vteStatus +
				"</td></tr></table></p>" +
				"</body></html>");
		}
		else
		{
			strTooltip.append("<html><head></head><body><p><font color='#FF0000'><strong>Other Detail</strong></font>" +
					"<table width='210' border='0'><tr><td width='70'><font color='#0033FF'>NHS Number :</font></td><td>" +
					nhsNo +
					"</td></tr><tr><td><font color='#0033FF'>Consultant :</font></td><td>" +
					consultant +
					"</td></tr></table></p>" +
					"</body></html>");
		}
		
		return strTooltip.toString();
	}

	private void clearDisplay()
	{
		clearData();
		form.dyngrdDisplay().clear();
		form.grdInpatients().getRows().clear();
	}

	private void clearData()
	{
		form.getLocalContext().setOnHomeLeave(null);
		form.getLocalContext().setOnTheWard(null);
		form.getLocalContext().setTransfersOut(null);
		form.getLocalContext().setPendingElective(null);
		form.getLocalContext().setPendingEmergency(null);
		form.getLocalContext().setTransfersIn(null);
		form.getLocalContext().setWaitingArea(null);
	}

	private DynamicGridColumn getColumn(String identifier)
	{
		return form.dyngrdDisplay().getColumns().getByIdentifier(identifier);
	}

	private void initializeDynamicGrid() 
	{	
		DynamicGrid dynGrid = form.dyngrdDisplay();
		dynGrid.clear();
		DynamicGridColumn colPatient = null;	
		colPatient = dynGrid.getColumns().newColumn("Patient", COLNAME);
		colPatient.setWidth(250);		
		colPatient.setAlignment(Alignment.CENTER);		
	}
	
	private void populateDynamicGridFromData()
	{		
		initializeDynamicGrid();
		
		DynamicGrid dynGrid = form.dyngrdDisplay();		
		DynamicGridRow dynRow = dynGrid.getRows().newRow();
		DynamicGridRow patientRow =null;
		dynRow.setIdentifier(CURRENT);
		dynRow.setReadOnly(false);
		dynRow.setSelectable(false);
		dynRow.setExpanded(true);

		DynamicGridCell cellPatient = dynRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);
		cellPatient.getItems().clear();
		cellPatient.setBackColor(Color.Bisque);
		cellPatient.setValue("<h3>ON THE WARD</h3>");

		if(ConfigFlag.UI.BED_INFO_UI_TYPE.getValue()!="WST")//WDEV-14410 
		{
		dynRow = cellPatient.getRow().getRows().newRow();
		dynRow.setExpanded(true);
		dynRow.setSelectable(false);
		
		DynamicGridCell cellHomeLeave = dynRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);				
		cellHomeLeave.setValue("<h3>Home Leave</h3>");
		cellHomeLeave.setBackColor(Color.Aqua);

		//wdev-13509

		InpatientEpisodeLiteVoCollection voCollHL = form.getLocalContext().getOnHomeLeave();
	    if(voCollHL != null)
	    {
			for (int i=0; i < voCollHL.size() ; i++ )
			{
			    InpatientEpisodeLiteVo voHLInpatEpis = voCollHL.get(i);
			    if(voHLInpatEpis.getIsOnHomeLeaveIsNotNull()
			    	&& voHLInpatEpis.getIsOnHomeLeave())
			    {
			    	dynRow = cellHomeLeave.getRow().getRows().newRow();
			    	dynRow.setSelectable(false);
					dynRow.setExpanded(true);
			    	
					DynamicGridCell cellPatientRow = dynRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);		
					cellPatientRow.setAutoPostBack(true);
					cellPatientRow.setBackColor(Color.White);
					cellPatientRow.setIdentifier(CURRENT);
					cellPatientRow.setValue("<img src = " + form.getImages().Core.Person.getImagePath() + "> &nbsp;&nbsp;" +  voHLInpatEpis.getPatientString());
					String tooltip = voHLInpatEpis.getPatientString();
					if (voHLInpatEpis.getBedIsNotNull() && voHLInpatEpis.getBed().getBayIsNotNull() && voHLInpatEpis.getBed().getBay().getNameIsNotNull())
						tooltip+=" Bay: "+voHLInpatEpis.getBed().getBay().getName();
					if (voHLInpatEpis.getVacatedBedNumberIsNotNull() 
						&& voHLInpatEpis.getVacatedBedNumber().toString() != "")
						tooltip+=" Vacated Bed: "+voHLInpatEpis.getVacatedBedNumber();
					if (voHLInpatEpis.getExpectedDateOfReturnIsNotNull())
						tooltip+=" Expected Return : "+voHLInpatEpis.getExpectedDateOfReturn().toString();
					if (voHLInpatEpis.getExpectedTimeOfReturnIsNotNull())
						tooltip+=" at "+ voHLInpatEpis.getExpectedTimeOfReturn().toString();
					cellPatientRow.setTooltip(tooltip);
					if (voHLInpatEpis.getPasEventIsNotNull())
					{
						dynRow.setValue(voHLInpatEpis.getPasEvent().getPatient());
						dynRow.setIdentifier(voHLInpatEpis);
					}
			    }
			}
	    }
		}
		dynRow = cellPatient.getRow().getRows().newRow();
		dynRow.setExpanded(true);
		dynRow.setSelectable(false);

		DynamicGridCell cellOutgoingDischarge = dynRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);				
		cellOutgoingDischarge.setValue("<b><i>For Discharge</i></b>");
		cellOutgoingDischarge.setBackColor(Color.Aqua);
		
		dynRow = cellOutgoingDischarge.getRow().getRows().newRow();
		dynRow.setExpanded(true);
		dynRow.setSelectable(false);
		DynamicGridCell cellOutgoingOccupiedDueDischarge24h = dynRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);				
		cellOutgoingOccupiedDueDischarge24h.setValue("<b><i>Due discharge within 24h</i></b>");
		
		InpatientEpisodeLiteVoCollection voCollInpatEpis = form.getLocalContext().getOnTheWard();
		if(voCollInpatEpis != null)
		{
			for (int i=0; i < voCollInpatEpis.size() ; i++ )
			{
				InpatientEpisodeLiteVo voInpatEpis = voCollInpatEpis.get(i);
				
				if(voInpatEpis.isDischargeDueWithin24Hrs())
			    {
					if(voInpatEpis.getIsConfirmedDischargeIsNotNull() && voInpatEpis.getIsConfirmedDischarge())
						continue;
					
					patientRow = cellOutgoingOccupiedDueDischarge24h.getRow().getRows().newRow();
					patientRow.setSelectable(true);
					DynamicGridCell cellPatientRow = patientRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);		
					cellPatientRow.setAutoPostBack(true);
					cellPatientRow.setBackColor( Color.fromRGB(255, 105, 0));
					cellPatientRow.setIdentifier(CURRENT);
					cellPatientRow.setValue("<img src = " + form.getImages().Core.Person.getImagePath() + "> &nbsp;&nbsp;" +  voInpatEpis.getPatientString());
					if (voInpatEpis.getPasEventIsNotNull())
					{
						patientRow.setValue(voInpatEpis.getPasEvent().getPatient());
						patientRow.setIdentifier(voInpatEpis.getPasEvent());
					}
					
			    }
			}
		}
		dynRow = cellOutgoingDischarge.getRow().getRows().newRow();
		dynRow.setExpanded(true);
		dynRow.setSelectable(false);
		DynamicGridCell cellOutgoingConfirmedDischarge24h = dynRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);				
		cellOutgoingConfirmedDischarge24h.setValue("<b><i>Confirmed discharge within 24h</i></b>");
		
		if(voCollInpatEpis != null)
		{
			for (int i=0; i < voCollInpatEpis.size() ; i++ )
			{
				InpatientEpisodeLiteVo voInpatEpis = voCollInpatEpis.get(i);
				
				if(voInpatEpis.isDischargeDueWithin24Hrs())
			    {
					if(voInpatEpis.getIsConfirmedDischargeIsNotNull() && voInpatEpis.getIsConfirmedDischarge())
					{
						patientRow = cellOutgoingConfirmedDischarge24h.getRow().getRows().newRow();			
						patientRow.setSelectable(true);
						DynamicGridCell cellPatientRow = patientRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);		
						cellPatientRow.setAutoPostBack(true);
						cellPatientRow.setBackColor(Color.Red);
						cellPatientRow.setIdentifier(CURRENT);
						cellPatientRow.setValue("<img src = " + form.getImages().Core.Person.getImagePath() + "> &nbsp;&nbsp;" +  voInpatEpis.getPatientString());
						if (voInpatEpis.getPasEventIsNotNull())
						{
							patientRow.setValue(voInpatEpis.getPasEvent().getPatient());
							patientRow.setIdentifier(voInpatEpis.getPasEvent());
						}
				    }
			    }
			}
		}

		
		dynRow = cellPatient.getRow().getRows().newRow();
		dynRow.setExpanded(true);
		dynRow.setSelectable(false);
		DynamicGridCell cellOutgoingTransfer = dynRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);				
		cellOutgoingTransfer.setValue("<b><i>For Transfer Out</i></b>");
		cellOutgoingTransfer.setBackColor(Color.Aqua);
		
		PendingTransfersLiteVoCollection voCollTransfersOut = form.getLocalContext().getTransfersOut();
		if(voCollTransfersOut != null)
		{
			for (int i=0; i < voCollTransfersOut.size(); i++)
			{
				PendingTransfersLiteVo voTransferOut = voCollTransfersOut.get(i);
				patientRow = cellOutgoingTransfer.getRow().getRows().newRow();	
				patientRow.setSelectable(true);
				DynamicGridCell cellPatientRow = patientRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);
				cellPatientRow.setBackColor(Color.LightBlue);
				cellPatientRow.setAutoPostBack(true);		
				cellPatientRow.setValue("<img src = " + form.getImages().Core.Person.getImagePath() + "> &nbsp;&nbsp;" + voTransferOut.getPatientString());
				if (voTransferOut.getInpatientEpisodeIsNotNull() && voTransferOut.getInpatientEpisode().getPasEventIsNotNull())
				{
					patientRow.setValue(voTransferOut.getInpatientEpisode().getPasEvent().getPatient());
					patientRow.setIdentifier(voTransferOut.getInpatientEpisode().getPasEvent());
				}
			}
		}
	
		dynRow = dynGrid.getRows().newRow();
		dynRow.setExpanded(true);
		dynRow.setSelectable(false);
		DynamicGridCell cellPatient1 = dynRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);
		cellPatient1.setBackColor(Color.Bisque);		
		cellPatient1.setValue("<h3>INCOMING</h3>");
				
		dynRow = cellPatient1.getRow().getRows().newRow();;
		dynRow.setExpanded(true);
		dynRow.setSelectable(false);
		DynamicGridCell cellIncomingdmissionEmergency = dynRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);				
		cellIncomingdmissionEmergency.setValue("<b><i>Emergency Admissions</i></b>");
		
		//from PendingEmergencyAdmission
		PendingEmergencyAdmissionAdmitVoCollection voCollEmergency = form.getLocalContext().getPendingEmergency();
		if(voCollEmergency != null)
		{
			if (voCollEmergency != null)
			{
				for (int i=0; i < voCollEmergency.size(); i++ )
				{
					PendingEmergencyAdmissionAdmitVo voEmergency = voCollEmergency.get(i);
					patientRow = cellIncomingdmissionEmergency.getRow().getRows().newRow();		
					patientRow.setSelectable(true);
					DynamicGridCell cellPatientRow = patientRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);		
					cellPatientRow.setAutoPostBack(true);		
					cellPatientRow.setValue("<img src = " + form.getImages().Core.Person.getImagePath() + "> &nbsp;&nbsp;" + voEmergency.getPatientString());	
					cellPatientRow.setTooltip(voEmergency.getPatientString());
					if (voEmergency.getPasEventIsNotNull())
					{
						patientRow.setValue(voEmergency.getPasEvent().getPatient());
						patientRow.setIdentifier(voEmergency.getPasEvent());
					}
				}
			}
		}
		
		dynRow = cellPatient1.getRow().getRows().newRow();;
		dynRow.setExpanded(true);
		dynRow.setSelectable(false);
		DynamicGridCell cellIncomingdmissionElective = dynRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);				
		cellIncomingdmissionElective.setValue("<b><i>Elective Admissions</i></b>");
		
		//from PendingElectiveAdmission
		PendingElectiveAdmissionAdmitVoCollection voCollPendingElective = form.getLocalContext().getPendingElective();
		if(voCollPendingElective != null)
		{
			for (int i=0; i < voCollPendingElective.size() ; i++ )
			{
				PendingElectiveAdmissionAdmitVo voPending = voCollPendingElective.get(i);
				patientRow = cellIncomingdmissionElective.getRow().getRows().newRow();	
				patientRow.setSelectable(true);
				DynamicGridCell cellPatientRow = patientRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);		
				cellPatientRow.setAutoPostBack(true);		
				cellPatientRow.setValue("<img src = " + form.getImages().Core.Person.getImagePath() + "> &nbsp;&nbsp;" + voPending.getPatientString());			
				cellPatientRow.setTooltip(voPending.getPatientString());
				if (voPending.getPasEventIsNotNull())
				{
					patientRow.setValue(voPending.getPasEvent().getPatient());
					patientRow.setIdentifier(voPending.getPasEvent());
				}
			}
		}

		
		dynRow = cellPatient1.getRow().getRows().newRow();
		dynRow.setExpanded(true);
		dynRow.setSelectable(false);
		DynamicGridCell cellPendingTransfers = dynRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);				
		cellPendingTransfers.setValue("<b><i>For Transfer In</b></i>");
		
		PendingTransfersLiteVoCollection voCollTransfersIn = form.getLocalContext().getTransfersIn();
		if(voCollTransfersIn != null)
		{
			for (int i=0; i < voCollTransfersIn.size(); i++)
			{
				PendingTransfersLiteVo voTransferIn = voCollTransfersIn.get(i);
				
				patientRow = cellPendingTransfers.getRow().getRows().newRow();
				patientRow.setSelectable(true);
				DynamicGridCell cellPatientRow = patientRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);		
				cellPatientRow.setAutoPostBack(true);			
				cellPatientRow.setIdentifier(INCOMING_PENDING_TRANSFERS);
				cellPatientRow.setValue("<img src = " + form.getImages().Core.Person.getImagePath() + "> &nbsp;&nbsp;" + voTransferIn.getPatientString());
				if (voTransferIn.getInpatientEpisodeIsNotNull() && voTransferIn.getInpatientEpisode().getPasEventIsNotNull())
				{
					patientRow.setValue(voTransferIn.getInpatientEpisode().getPasEvent().getPatient());
					patientRow.setIdentifier(voTransferIn.getInpatientEpisode().getPasEvent());
				}
			}
		}	
		
		//Waiting Area
		if(form.getGlobalContext().Core.getHasWaitingAreaIsNotNull() && form.getGlobalContext().Core.getHasWaitingArea())
		{
			DynamicGridRow dynWRow = dynGrid.getRows().newRow();
			dynWRow.setReadOnly(false);
			dynWRow.setSelectable(false);
			dynWRow.setExpanded(true);
			DynamicGridRow wRow = null;
			
			DynamicGridCell cellWPatient = dynWRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);
			cellWPatient.getItems().clear();
			cellWPatient.setBackColor(Color.Bisque);
			cellWPatient.setValue("<h3>WAITING AREA</h3>");
			
			if(form.getLocalContext().getWaitingAreaIsNotNull() && form.getLocalContext().getWaitingArea().size() > 0)
			{
				InpatientEpisodeLiteVoCollection voCollWaiting = form.getLocalContext().getWaitingArea();
			    if(voCollWaiting != null)
			    {
					for (int i=0; i < voCollWaiting.size() ; i++ )
					{
					    InpatientEpisodeLiteVo voInpatEpis = voCollWaiting.get(i);
					    wRow = cellWPatient.getRow().getRows().newRow();		
					    wRow.setSelectable(true);
						DynamicGridCell cellWRow = wRow.getCells().newCell(getColumn(COLNAME), DynamicCellType.HTMLVIEW);		
						cellWRow.setAutoPostBack(true);
						cellWRow.setBackColor(Color.White);
						cellWRow.setIdentifier(CURRENT);
						
						cellWRow.setValue("<img src = " + form.getImages().Core.Person.getImagePath() + "> &nbsp;&nbsp;" +  voInpatEpis.getPatientString());
						if (voInpatEpis.getPasEventIsNotNull())
						{
							wRow.setValue(voInpatEpis.getPasEvent().getPatient());
							wRow.setIdentifier(voInpatEpis.getPasEvent());
						}
					}
			    }
			}
		}
	
	}
		
	private Image calculateInfants(PatientShort patient)
	{
		Image imgNumInfants = null;
		//get number of infants for this patient
		int count = domain.countInfants(patient);
		
		if(count == 1)
			imgNumInfants = form.getImages().Core.Infant1;
		if(count == 2)
			imgNumInfants = form.getImages().Core.Infant2;
		if(count == 3)
			imgNumInfants = form.getImages().Core.Infant3;
		if(count == 4)
			imgNumInfants = form.getImages().Core.Infant4;
		if(count == 5)
			imgNumInfants = form.getImages().Core.Infant5;
		if(count == 6)
			imgNumInfants = form.getImages().Core.Infant6;
		if(count == 7)
			imgNumInfants = form.getImages().Core.Infant7;
		if(count == 8)
			imgNumInfants = form.getImages().Core.Infant8;
		
		return imgNumInfants;
	}

	@SuppressWarnings("unused")
	private boolean isInpatientEpisodeonTransefrOutList(InpatientEpisodeRefVo voInpatEpis)
	{
		if(voInpatEpis == null)
			throw new CodingRuntimeException("voInpatEpis is null in method isInpatientEpisodeonTransefrOutList");
		
		PendingTransfersLiteVoCollection voCollTransfersOut = form.getLocalContext().getTransfersOut();
		if(voCollTransfersOut == null)
			return false;
		
		for(PendingTransfersLiteVo voTransferOut : voCollTransfersOut)
		{
			if(voTransferOut.getInpatientEpisodeIsNotNull() && voTransferOut.getInpatientEpisode().equals(voInpatEpis))
				return true;
		}
		
		return false;
	}

	@Override
	protected void onFormDialogClosed(FormName formName, DialogResult result) throws PresentationLogicException 
	{
		if(result.equals(DialogResult.OK))
		{
			loadData();
			populateDynamicGridFromData();
		}
	}

	private void loadData()
	{
		form.getLocalContext().setOnHomeLeave(form.cmbWard().getValue() == null ? null : domain.listHomeLeaveByWard(form.cmbWard().getValue()));
		form.getLocalContext().setOnTheWard(form.cmbWard().getValue() == null ? null : domain.listInpatientEpisodeByWard(form.cmbWard().getValue()));
		form.getLocalContext().setTransfersOut(form.cmbWard().getValue() == null ? null : domain.listPendingTransfersOutByWard(form.cmbWard().getValue()));
		form.getLocalContext().setPendingElective(form.cmbWard().getValue() == null ? null : domain.listPendingElectiveAdmission(form.cmbWard().getValue()));
		form.getLocalContext().setPendingEmergency(form.cmbWard().getValue() == null ? null : domain.listEmergencyAdmission(form.cmbWard().getValue()));
		form.getLocalContext().setTransfersIn(form.cmbWard().getValue() == null ? null : domain.listPendingTransfersInByWard(form.cmbWard().getValue()));
		form.getLocalContext().setWaitingArea(form.cmbWard().getValue() == null ? null : domain.listWaitingAreaPatientsByWard(form.cmbWard().getValue()));	
	}

	@Override
	protected void onCmbHospitalValueChanged() throws PresentationLogicException
	{
		clearDisplay();
		form.imbRefresh().setEnabled(false);
		loadWardsForSelectedHosp();
		populateSearchCriteriaDataFromScreen();///WDEV-12925
	}

	private void loadWardsForSelectedHosp()
	{
		form.cmbWard().clear();
		
		if(form.cmbHospital().getValue() != null)
		{
			LocationLiteVoCollection wardsColl = domain.listWardsForCurrentLocation(form.cmbHospital().getValue());
			if(wardsColl != null)
			{
				for(int x = 0; x < wardsColl.size(); x++)
				{
					addWard(wardsColl.get(x));
				}
				
				//WDEV-15908 
				if(wardsColl.size() == 1)
				{
					form.cmbWard().setValue(wardsColl.get(0));
					wardValueChanged();
				}
			}
		}
	}

	@Override
	protected void onContextMenuItemClick(int menuItemID, Control sender) throws PresentationLogicException
	{
		switch (menuItemID)
		{
			default :
			break;
		}
	}

	@Override
	protected void onImbRefreshClick() throws PresentationLogicException
	{
		wardValueChanged();
	}

	@Override
	protected void onGrdInpatientsSelectionChanged() throws PresentationLogicException
	{
		form.getGlobalContext().Core.setSelectingPatientForm(engine.getFormName());
		form.getGlobalContext().Core.setPatientShort(form.grdInpatients().getValue());
		
		if(form.grdInpatients().getSelectedRow() != null && form.grdInpatients().getSelectedRow().getColPasEventHidden() != null)
			form.getGlobalContext().Core.setCurrentCareContext( domain.getCareContextForPasEvent(form.grdInpatients().getSelectedRow().getColPasEventHidden()));
		
		form.dyngrdDisplay().setValue(null);
		
		resetPIDBarText();//WDEV-14476
	}

	@Override
	protected void onDyngrdDisplayRowSelectionChanged(DynamicGridRow row) throws PresentationLogicException
	{
		if (row.getValue() instanceof PatientShort)
		{
			form.getGlobalContext().Core.setSelectingPatientForm(engine.getFormName());
			form.getGlobalContext().Core.setPatientShort((PatientShort) row.getValue());
			form.grdInpatients().setValue(null);
		}
		
		
		//WDEV-11449 - setting CareContext for EDischarge
		setCareContextIfApplicable(row);
		
		resetPIDBarText();//WDEV-14476
	}	
	
	private void setCareContextIfApplicable(DynamicGridRow row)
	{
		if(row.getIdentifier() instanceof PASEventRefVo)
			form.getGlobalContext().Core.setCurrentCareContext( domain.getCareContextForPasEvent((PASEventRefVo) row.getIdentifier()));	
		else
			form.getGlobalContext().Core.setCurrentCareContext(null);
	}

	@Override
	protected void onBtnPrintReportClick() throws PresentationLogicException
	{
		String urlQueryServer = ConfigFlag.GEN.QUERY_SERVER_URL.getValue();
		String urlReportServer = ConfigFlag.GEN.REPORT_SERVER_URL.getValue();

		
		if(form.cmbWard().getValue()==null)		
		{
			engine.showMessage("Please select a ward !");
			return;
		}
		
		//we need a better way to do this
		Object[] obj = domain.getSystemReportAndTemplate(new Integer(297));
		
		if(obj == null || obj.length < 2)
		{
			engine.showMessage("I could not get the report and template !");
			return;
		}
		
		if(obj[0] == null || obj[1] == null)
		{
			engine.showMessage("The report has not been deployed !");
			return;
		}
		
		QueryBuilderClient client = new QueryBuilderClient(urlQueryServer, engine.getSessionId());
				
		client.addSeed(new SeedValue("Ward",  form.cmbWard().getValue().getID_Location().intValue(), Integer.class));		
		client.addSeed(new SeedValue("Consultant",  null, Integer.class));
		client.addSeed(new SeedValue("Specialty",  null, Integer.class));
				
		String resultUrl = "";
		try
		{
			resultUrl = client.buildReportAsUrl((String)obj[0], (String)obj[1], urlReportServer, "PDF", "", 1);
		} 
		catch (QueryBuilderClientException e1)
		{
			engine.showMessage("Error creating report: " + e1.getMessage());
			return;
		}
		
		engine.openUrl(resultUrl);
		
	}
	
	//WDEV-14476
	private void resetPIDBarText()
	{
		if ( ! form.getGlobalContext().Core.getPatientShortIsNotNull())
			return;
		
		if ( ! form.getGlobalContext().Core.getCurrentCareContextIsNotNull())
			return;

		new ResetPIDBarHelper(engine, form.getGlobalContext().Core.getPatientShort() , domain.getPIDDiagnosisInfo(form.getGlobalContext().Core.getCurrentCareContext(), form.getGlobalContext().Core.getCurrentCareContext().getEpisodeOfCare()));
	}
}