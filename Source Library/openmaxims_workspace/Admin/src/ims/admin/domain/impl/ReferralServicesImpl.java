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
// This code was generated by Aidan Mc Donnell using IMS Development Environment (version 1.65 build 3195.17642)
// Copyright (C) 1995-2008 IMS MAXIMS plc. All rights reserved.

package ims.admin.domain.impl;

import ims.admin.domain.base.impl.BaseReferralServicesImpl;
import ims.careuk.domain.objects.ReferralService;
import ims.careuk.vo.ReferralServiceVo;
import ims.careuk.vo.domain.ReferralServiceVoAssembler;
import ims.core.vo.ServiceLiteVoCollection;
import ims.core.vo.domain.ServiceLiteVoAssembler;
import ims.domain.DomainFactory;
import ims.domain.exceptions.DomainInterfaceException;
import ims.domain.exceptions.DomainRuntimeException;
import ims.domain.exceptions.ForeignKeyViolationException;
import ims.domain.exceptions.StaleObjectException;

import java.util.List;

public class ReferralServicesImpl extends BaseReferralServicesImpl
{

	private static final long	serialVersionUID	= 1L;

	public ims.core.vo.ServiceLiteVoCollection getServices()
	{
		DomainFactory factory = getDomainFactory();
		StringBuffer hql = new StringBuffer("from Service as service where service.isRIE is null and service.isActive = :isActive");
		List list = factory.find(hql.toString(), new String[]{"isActive"}, new Object[]{true});
		ServiceLiteVoCollection serviceLiteVoColl = ServiceLiteVoAssembler.createServiceLiteVoCollectionFromService(list);
		
		if (serviceLiteVoColl.size() > 0)
		{
			return serviceLiteVoColl;
		}
		else
		{
			return null;
		}
	}

	public ReferralServiceVo getReferralService()
	{
		DomainFactory factory = getDomainFactory();
		StringBuffer hql = new StringBuffer("from ReferralService");

		List list = factory.find(hql.toString());
		
		if (list.size() > 0 && list != null)
			return ReferralServiceVoAssembler.create((ReferralService) list.get(0));
		
		return null;

	}

	public ReferralServiceVo save(ReferralServiceVo value) throws DomainInterfaceException, StaleObjectException, ForeignKeyViolationException
	{
		if (value != null)
		{
			if (!value.isValidated())
			{
				throw new DomainRuntimeException("ReferralServiceVo not validated");
			}

			DomainFactory factory = getDomainFactory();
			ReferralService doReferralService = ReferralServiceVoAssembler.extractReferralService(factory, value);
			factory.save(doReferralService);
			ReferralServiceVo savedValue = ReferralServiceVoAssembler.create(doReferralService);
		return savedValue ;
			
		}
		return null;
	}

}