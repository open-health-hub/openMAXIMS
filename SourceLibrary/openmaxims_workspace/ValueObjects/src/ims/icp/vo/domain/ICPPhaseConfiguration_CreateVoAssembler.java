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
/*
 * This code was generated
 * Copyright (C) 1995-2004 IMS MAXIMS plc. All rights reserved.
 * IMS Development Environment (version 1.80 build 5007.25751)
 * WARNING: DO NOT MODIFY the content of this file
 * Generated on 16/04/2014, 12:32
 *
 */
package ims.icp.vo.domain;

import ims.vo.domain.DomainObjectMap;
import java.util.HashMap;

import org.hibernate.proxy.HibernateProxy;

/**
 * @author George Cristian Josan
 */
public class ICPPhaseConfiguration_CreateVoAssembler
{
  	/**
	 * Copy one ValueObject to another
	 * @param valueObjectDest to be updated
	 * @param valueObjectSrc to copy values from
	 */
	 public static ims.icp.vo.ICPPhaseConfiguration_CreateVo copy(ims.icp.vo.ICPPhaseConfiguration_CreateVo valueObjectDest, ims.icp.vo.ICPPhaseConfiguration_CreateVo valueObjectSrc) 
	 {
	 	if (null == valueObjectSrc) 
		{
			return valueObjectSrc;
		}
		valueObjectDest.setID_ICPPhase(valueObjectSrc.getID_ICPPhase());
	    valueObjectDest.setIsRIE(valueObjectSrc.getIsRIE());
		// Name
		valueObjectDest.setName(valueObjectSrc.getName());
		// Actions
		valueObjectDest.setActions(valueObjectSrc.getActions());
		// Status
		valueObjectDest.setStatus(valueObjectSrc.getStatus());
		// isActivatedOnInitiation
		valueObjectDest.setIsActivatedOnInitiation(valueObjectSrc.getIsActivatedOnInitiation());
	 	return valueObjectDest;
	 }

 
	/**
	 * Create the ValueObject collection to hold the set of DomainObjects.
	 * This is a convenience method only.
	 * It is intended to be used when one called to an Assembler is made.
 	 * If more than one call to an Assembler is made then #createICPPhaseConfiguration_CreateVoCollectionFromICPPhase(DomainObjectMap, Set) should be used.
	 * @param domainObjectSet - Set of ims.icps.configuration.domain.objects.ICPPhase objects.
	 */
	public static ims.icp.vo.ICPPhaseConfiguration_CreateVoCollection createICPPhaseConfiguration_CreateVoCollectionFromICPPhase(java.util.Set domainObjectSet)	
	{
		return createICPPhaseConfiguration_CreateVoCollectionFromICPPhase(new DomainObjectMap(), domainObjectSet);
	}
	
	/**
	 * Create the ValueObject collection to hold the set of DomainObjects.
	 * @param map - maps DomainObjects to created ValueObjects
	 * @param domainObjectSet - Set of ims.icps.configuration.domain.objects.ICPPhase objects.
	 */
	public static ims.icp.vo.ICPPhaseConfiguration_CreateVoCollection createICPPhaseConfiguration_CreateVoCollectionFromICPPhase(DomainObjectMap map, java.util.Set domainObjectSet)	
	{
		ims.icp.vo.ICPPhaseConfiguration_CreateVoCollection voList = new ims.icp.vo.ICPPhaseConfiguration_CreateVoCollection();
		if ( null == domainObjectSet ) 
		{
			return voList;
		}
		int rieCount=0;
		int activeCount=0;
		java.util.Iterator iterator = domainObjectSet.iterator();
		while( iterator.hasNext() ) 
		{
			ims.icps.configuration.domain.objects.ICPPhase domainObject = (ims.icps.configuration.domain.objects.ICPPhase) iterator.next();
			ims.icp.vo.ICPPhaseConfiguration_CreateVo vo = create(map, domainObject);
			
			if (vo != null)
				voList.add(vo);
				
			if (domainObject != null)
			{				
				if (domainObject.getIsRIE() != null && domainObject.getIsRIE().booleanValue() == true)
					rieCount++;
				else
					activeCount++;
			}
		}
		voList.setRieCount(rieCount);
		voList.setActiveCount(activeCount);
		return voList;
	}

	/**
	 * Create the ValueObject collection to hold the list of DomainObjects.
	 * @param domainObjectList - List of ims.icps.configuration.domain.objects.ICPPhase objects.
	 */
	public static ims.icp.vo.ICPPhaseConfiguration_CreateVoCollection createICPPhaseConfiguration_CreateVoCollectionFromICPPhase(java.util.List domainObjectList) 
	{
		return createICPPhaseConfiguration_CreateVoCollectionFromICPPhase(new DomainObjectMap(), domainObjectList);
	}
	
	/**
	 * Create the ValueObject collection to hold the list of DomainObjects.
	 * @param map - maps DomainObjects to created ValueObjects
	 * @param domainObjectList - List of ims.icps.configuration.domain.objects.ICPPhase objects.
	 */
	public static ims.icp.vo.ICPPhaseConfiguration_CreateVoCollection createICPPhaseConfiguration_CreateVoCollectionFromICPPhase(DomainObjectMap map, java.util.List domainObjectList) 
	{
		ims.icp.vo.ICPPhaseConfiguration_CreateVoCollection voList = new ims.icp.vo.ICPPhaseConfiguration_CreateVoCollection();
		if ( null == domainObjectList ) 
		{
			return voList;
		}		
		int rieCount=0;
		int activeCount=0;
		for (int i = 0; i < domainObjectList.size(); i++)
		{
			ims.icps.configuration.domain.objects.ICPPhase domainObject = (ims.icps.configuration.domain.objects.ICPPhase) domainObjectList.get(i);
			ims.icp.vo.ICPPhaseConfiguration_CreateVo vo = create(map, domainObject);

			if (vo != null)
				voList.add(vo);
			
			if (domainObject != null)
			{
				if (domainObject.getIsRIE() != null && domainObject.getIsRIE().booleanValue() == true)
					rieCount++;
				else
					activeCount++;
			}
		}
		
		voList.setRieCount(rieCount);
		voList.setActiveCount(activeCount);
		return voList;
	}

	/**
	 * Create the ims.icps.configuration.domain.objects.ICPPhase set from the value object collection.
	 * @param domainFactory - used to create existing (persistent) domain objects.
	 * @param voCollection - the collection of value objects	 
	 */
	 public static java.util.Set extractICPPhaseSet(ims.domain.ILightweightDomainFactory domainFactory, ims.icp.vo.ICPPhaseConfiguration_CreateVoCollection voCollection) 
	 {
	 	return extractICPPhaseSet(domainFactory, voCollection, null, new HashMap());
	 }
	 
	 public static java.util.Set extractICPPhaseSet(ims.domain.ILightweightDomainFactory domainFactory, ims.icp.vo.ICPPhaseConfiguration_CreateVoCollection voCollection, java.util.Set domainObjectSet, HashMap domMap) 
	 {
	 	int size = (null == voCollection) ? 0 : voCollection.size();
		if (domainObjectSet == null)
		{
			domainObjectSet = new java.util.HashSet();			
		}
		java.util.Set newSet = new java.util.HashSet();
		for(int i=0; i<size; i++) 
		{
			ims.icp.vo.ICPPhaseConfiguration_CreateVo vo = voCollection.get(i);
			ims.icps.configuration.domain.objects.ICPPhase domainObject = ICPPhaseConfiguration_CreateVoAssembler.extractICPPhase(domainFactory, vo, domMap);

			//TODO: This can only occur in the situation of a stale object exception. For now leave it to the Interceptor to handle it.
			if (domainObject == null)
			{
				continue;
			}
			
			//Trying to avoid the hibernate collection being marked as dirty via its public interface methods. (like add)
			if (!domainObjectSet.contains(domainObject)) domainObjectSet.add(domainObject);
			newSet.add(domainObject);			
		}
		java.util.Set removedSet = new java.util.HashSet();
		java.util.Iterator iter = domainObjectSet.iterator();
		//Find out which objects need to be removed
		while (iter.hasNext())
		{
			ims.domain.DomainObject o = (ims.domain.DomainObject)iter.next();			
			if ((o == null || o.getIsRIE() == null || !o.getIsRIE().booleanValue()) && !newSet.contains(o))
			{
				removedSet.add(o);
			}
		}
		iter = removedSet.iterator();
		//Remove the unwanted objects
		while (iter.hasNext())
		{
			domainObjectSet.remove(iter.next());
		}
		return domainObjectSet;	 
	 }


	/**
	 * Create the ims.icps.configuration.domain.objects.ICPPhase list from the value object collection.
	 * @param domainFactory - used to create existing (persistent) domain objects.
	 * @param voCollection - the collection of value objects	 
	 */
	 public static java.util.List extractICPPhaseList(ims.domain.ILightweightDomainFactory domainFactory, ims.icp.vo.ICPPhaseConfiguration_CreateVoCollection voCollection) 
	 {
	 	return extractICPPhaseList(domainFactory, voCollection, null, new HashMap());
	 }
	 
	 public static java.util.List extractICPPhaseList(ims.domain.ILightweightDomainFactory domainFactory, ims.icp.vo.ICPPhaseConfiguration_CreateVoCollection voCollection, java.util.List domainObjectList, HashMap domMap) 
	 {
	 	int size = (null == voCollection) ? 0 : voCollection.size();
		if (domainObjectList == null)
		{
			domainObjectList = new java.util.ArrayList();			
		}
		for(int i=0; i<size; i++) 
		{
			ims.icp.vo.ICPPhaseConfiguration_CreateVo vo = voCollection.get(i);
			ims.icps.configuration.domain.objects.ICPPhase domainObject = ICPPhaseConfiguration_CreateVoAssembler.extractICPPhase(domainFactory, vo, domMap);

			//TODO: This can only occur in the situation of a stale object exception. For now leave it to the Interceptor to handle it.
			if (domainObject == null)
			{
				continue;
			}

			int domIdx = domainObjectList.indexOf(domainObject);
			if (domIdx == -1)
			{
				domainObjectList.add(i, domainObject);
			}
			else if (i != domIdx && i < domainObjectList.size())
			{
				Object tmp = domainObjectList.get(i);
				domainObjectList.set(i, domainObjectList.get(domIdx));
				domainObjectList.set(domIdx, tmp);
			}
		}
		
		//Remove all ones in domList where index > voCollection.size() as these should
		//now represent the ones removed from the VO collection. No longer referenced.
		int i1=domainObjectList.size();
		while (i1 > size)
		{
			domainObjectList.remove(i1-1);
			i1=domainObjectList.size();
		}
		return domainObjectList;	 
	 }

 

	/**
	 * Create the ValueObject from the ims.icps.configuration.domain.objects.ICPPhase object.
	 * @param domainObject ims.icps.configuration.domain.objects.ICPPhase
	 */
	 public static ims.icp.vo.ICPPhaseConfiguration_CreateVo create(ims.icps.configuration.domain.objects.ICPPhase domainObject) 
	 {
	 	if (null == domainObject) 
	 	{
			return null;
		}
		DomainObjectMap map = new DomainObjectMap();
		return create(map, domainObject);
	 }
	 
	 /**
	  * Create the ValueObject from the ims.icps.configuration.domain.objects.ICPPhase object.
	  * @param map DomainObjectMap of DomainObjects to already created ValueObjects.
	  * @param domainObject
	  */
	  public static ims.icp.vo.ICPPhaseConfiguration_CreateVo create(DomainObjectMap map, ims.icps.configuration.domain.objects.ICPPhase domainObject) 
	  {
	  		if (null == domainObject) 
	  		{
				return null;
			}
			// check if the domainObject already has a valueObject created for it
			ims.icp.vo.ICPPhaseConfiguration_CreateVo valueObject = (ims.icp.vo.ICPPhaseConfiguration_CreateVo) map.getValueObject(domainObject, ims.icp.vo.ICPPhaseConfiguration_CreateVo.class);
			if ( null == valueObject ) 
			{
				valueObject = new ims.icp.vo.ICPPhaseConfiguration_CreateVo(domainObject.getId(), domainObject.getVersion());
				map.addValueObject(domainObject, valueObject);

				valueObject = insert(map, valueObject, domainObject);
				
			}
	 		return valueObject;
	  }

	/**
	 * Update the ValueObject with the Domain Object.
	 * @param valueObject to be updated
	 * @param domainObject ims.icps.configuration.domain.objects.ICPPhase
	 */
	 public static ims.icp.vo.ICPPhaseConfiguration_CreateVo insert(ims.icp.vo.ICPPhaseConfiguration_CreateVo valueObject, ims.icps.configuration.domain.objects.ICPPhase domainObject) 
	 {
	 	if (null == domainObject) 
	 	{
			return valueObject;
		}
		DomainObjectMap map = new DomainObjectMap();
		return insert(map, valueObject, domainObject);
	 }
	 
	/**
	 * Update the ValueObject with the Domain Object.
	 * @param map DomainObjectMap of DomainObjects to already created ValueObjects.
	 * @param valueObject to be updated
	 * @param domainObject ims.icps.configuration.domain.objects.ICPPhase
	 */
	 public static ims.icp.vo.ICPPhaseConfiguration_CreateVo insert(DomainObjectMap map, ims.icp.vo.ICPPhaseConfiguration_CreateVo valueObject, ims.icps.configuration.domain.objects.ICPPhase domainObject) 
	 {
	 	if (null == domainObject) 
	 	{
			return valueObject;
		}
	 	if (null == map) 
	 	{
			map = new DomainObjectMap();
		}

		valueObject.setID_ICPPhase(domainObject.getId());
		valueObject.setIsRIE(domainObject.getIsRIE());
		
		// If this is a recordedInError record, and the domainObject
		// value isIncludeRecord has not been set, then we return null and
		// not the value object
		if (valueObject.getIsRIE() != null && valueObject.getIsRIE().booleanValue() == true && !domainObject.isIncludeRecord())
			return null;
			
		// If this is not a recordedInError record, and the domainObject
		// value isIncludeRecord has been set, then we return null and
		// not the value object
		if ((valueObject.getIsRIE() == null || valueObject.getIsRIE().booleanValue() == false) && domainObject.isIncludeRecord())
			return null;
			
		// Name
		valueObject.setName(domainObject.getName());
		// Actions
		valueObject.setActions(ims.icp.vo.domain.ICPActionConfiguration_CreateVoAssembler.createICPActionConfiguration_CreateVoCollectionFromICPAction(map, domainObject.getActions()) );
		// Status
		ims.domain.lookups.LookupInstance instance3 = domainObject.getStatus();
		if ( null != instance3 ) {
			ims.framework.utils.ImagePath img = null;
			ims.framework.utils.Color color = null;		
			img = null;
			if (instance3.getImage() != null) 
			{
				img = new ims.framework.utils.ImagePath(instance3.getImage().getImageId(), instance3.getImage().getImagePath());
			}
			color = instance3.getColor();
			if (color != null) 
				color.getValue();

			ims.core.vo.lookups.PreActiveActiveInactiveStatus voLookup3 = new ims.core.vo.lookups.PreActiveActiveInactiveStatus(instance3.getId(),instance3.getText(), instance3.isActive(), null, img, color);
			ims.core.vo.lookups.PreActiveActiveInactiveStatus parentVoLookup3 = voLookup3;
			ims.domain.lookups.LookupInstance parent3 = instance3.getParent();
			while (parent3 != null)
			{
				if (parent3.getImage() != null) 
				{
					img = new ims.framework.utils.ImagePath(parent3.getImage().getImageId(), parent3.getImage().getImagePath() );
				}
				else 
				{
					img = null;
				}
				color = parent3.getColor();
    			if (color != null) 
    				color.getValue();
								parentVoLookup3.setParent(new ims.core.vo.lookups.PreActiveActiveInactiveStatus(parent3.getId(),parent3.getText(), parent3.isActive(), null, img, color));
				parentVoLookup3 = parentVoLookup3.getParent();
								parent3 = parent3.getParent();
			}			
			valueObject.setStatus(voLookup3);
		}
				// isActivatedOnInitiation
		valueObject.setIsActivatedOnInitiation( domainObject.isIsActivatedOnInitiation() );
 		return valueObject;
	 }


	/**
	 * Create the domain object from the value object.
	 * @param domainFactory - used to create existing (persistent) domain objects.
	 * @param valueObject - extract the domain object fields from this.
	 */
	public static ims.icps.configuration.domain.objects.ICPPhase extractICPPhase(ims.domain.ILightweightDomainFactory domainFactory, ims.icp.vo.ICPPhaseConfiguration_CreateVo valueObject) 
	{
		return 	extractICPPhase(domainFactory, valueObject, new HashMap());
	}

	public static ims.icps.configuration.domain.objects.ICPPhase extractICPPhase(ims.domain.ILightweightDomainFactory domainFactory, ims.icp.vo.ICPPhaseConfiguration_CreateVo valueObject, HashMap domMap) 
	{
		if (null == valueObject) 
		{
			return null;
		}
		Integer id = valueObject.getID_ICPPhase();
		ims.icps.configuration.domain.objects.ICPPhase domainObject = null;
		if ( null == id) 
		{
			if (domMap.get(valueObject) != null)
			{
				return (ims.icps.configuration.domain.objects.ICPPhase)domMap.get(valueObject);
			}
			// ims.icp.vo.ICPPhaseConfiguration_CreateVo ID_ICPPhase field is unknown
			domainObject = new ims.icps.configuration.domain.objects.ICPPhase();
			domMap.put(valueObject, domainObject);
		}
		else 
		{
			String key = (valueObject.getClass().getName() + "__" + valueObject.getID_ICPPhase());
			if (domMap.get(key) != null)
			{
				return (ims.icps.configuration.domain.objects.ICPPhase)domMap.get(key);
			}
			domainObject = (ims.icps.configuration.domain.objects.ICPPhase) domainFactory.getDomainObject(ims.icps.configuration.domain.objects.ICPPhase.class, id );
			
			//TODO: Not sure how this should be handled. Effectively it must be a staleobject exception, but maybe should be handled as that further up.
			if (domainObject == null) 
				return null;

			domMap.put(key, domainObject);
		}
		domainObject.setVersion(valueObject.getVersion_ICPPhase());

		//This is to overcome a bug in both Sybase and Oracle which prevents them from storing an empty string correctly
		//Sybase stores it as a single space, Oracle stores it as NULL. This fix will make them consistent at least.
		if (valueObject.getName() != null && valueObject.getName().equals(""))
		{
			valueObject.setName(null);
		}
		domainObject.setName(valueObject.getName());
		domainObject.setActions(ims.icp.vo.domain.ICPActionConfiguration_CreateVoAssembler.extractICPActionSet(domainFactory, valueObject.getActions(), domainObject.getActions(), domMap));		
		// create LookupInstance from vo LookupType
		ims.domain.lookups.LookupInstance value3 = null;
		if ( null != valueObject.getStatus() ) 
		{
			value3 =
				domainFactory.getLookupInstance(valueObject.getStatus().getID());
		}
		domainObject.setStatus(value3);
		domainObject.setIsActivatedOnInitiation(valueObject.getIsActivatedOnInitiation());

		return domainObject;
	}

}
