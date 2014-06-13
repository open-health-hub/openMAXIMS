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

package ims.core.forms.carecontextselectdialog;

import java.io.Serializable;

public final class GlobalContext extends ims.framework.FormContext implements Serializable
{
	private static final long serialVersionUID = 1L;

	public GlobalContext(ims.framework.Context context)
	{
		super(context);

		Core = new CoreContext(context);
	}
	public final class CoreContext implements Serializable
	{
		private static final long serialVersionUID = 1L;

		private CoreContext(ims.framework.Context context)
		{
			this.context = context;

			CareContextSelectDialog = new CareContextSelectDialogContext(context);
		}
		public final class CareContextSelectDialogContext implements Serializable
		{
			private static final long serialVersionUID = 1L;

			private CareContextSelectDialogContext(ims.framework.Context context)
			{
				this.context = context;
			}
			public boolean getChosenCareContextIsNotNull()
			{
				return !cx_CoreCareContextSelectDialogChosenCareContext.getValueIsNull(context);
			}
			public ims.core.vo.CareContextPasVo getChosenCareContext()
			{
				return (ims.core.vo.CareContextPasVo)cx_CoreCareContextSelectDialogChosenCareContext.getValue(context);
			}
		public void setChosenCareContext(ims.core.vo.CareContextPasVo value)
		{
				cx_CoreCareContextSelectDialogChosenCareContext.setValue(context, value);
		}

			private ims.framework.ContextVariable cx_CoreCareContextSelectDialogChosenCareContext = new ims.framework.ContextVariable("Core.CareContextSelectDialog.ChosenCareContext", "_cv_Core.CareContextSelectDialog.ChosenCareContext");
			private ims.framework.Context context;
		}

		public boolean getPatientShortIsNotNull()
		{
			return !cx_CorePatientShort.getValueIsNull(context);
		}
		public ims.core.vo.PatientShort getPatientShort()
		{
			return (ims.core.vo.PatientShort)cx_CorePatientShort.getValue(context);
		}

		private ims.framework.ContextVariable cx_CorePatientShort = new ims.framework.ContextVariable("Core.PatientShort", "_cvp_Core.PatientShort");

		public CareContextSelectDialogContext CareContextSelectDialog;
		private ims.framework.Context context;
	}

	public CoreContext Core;
}