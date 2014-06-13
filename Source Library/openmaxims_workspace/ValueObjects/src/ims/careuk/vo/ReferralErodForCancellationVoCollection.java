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

package ims.careuk.vo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import ims.framework.enumerations.SortOrder;

/**
 * Linked to CAREUK.ReferralEROD business object (ID: 1096100062).
 */
public class ReferralErodForCancellationVoCollection extends ims.vo.ValueObjectCollection implements ims.vo.ImsCloneable, Iterable<ReferralErodForCancellationVo>
{
	private static final long serialVersionUID = 1L;

	private ArrayList<ReferralErodForCancellationVo> col = new ArrayList<ReferralErodForCancellationVo>();
	public String getBoClassName()
	{
		return "ims.careuk.domain.objects.ReferralEROD";
	}
	public boolean add(ReferralErodForCancellationVo value)
	{
		if(value == null)
			return false;
		if(this.col.indexOf(value) < 0)
		{
			return this.col.add(value);
		}
		return false;
	}
	public boolean add(int index, ReferralErodForCancellationVo value)
	{
		if(value == null)
			return false;
		if(this.col.indexOf(value) < 0)
		{
			this.col.add(index, value);
			return true;
		}
		return false;
	}
	public void clear()
	{
		this.col.clear();
	}
	public void remove(int index)
	{
		this.col.remove(index);
	}
	public int size()
	{
		return this.col.size();
	}
	public int indexOf(ReferralErodForCancellationVo instance)
	{
		return col.indexOf(instance);
	}
	public ReferralErodForCancellationVo get(int index)
	{
		return this.col.get(index);
	}
	public boolean set(int index, ReferralErodForCancellationVo value)
	{
		if(value == null)
			return false;
		this.col.set(index, value);
		return true;
	}
	public void remove(ReferralErodForCancellationVo instance)
	{
		if(instance != null)
		{
			int index = indexOf(instance);
			if(index >= 0)
				remove(index);
		}
	}
	public boolean contains(ReferralErodForCancellationVo instance)
	{
		return indexOf(instance) >= 0;
	}
	public Object clone()
	{
		ReferralErodForCancellationVoCollection clone = new ReferralErodForCancellationVoCollection();
		
		for(int x = 0; x < this.col.size(); x++)
		{
			if(this.col.get(x) != null)
				clone.col.add((ReferralErodForCancellationVo)this.col.get(x).clone());
			else
				clone.col.add(null);
		}
		
		return clone;
	}
	public boolean isValidated()
	{
		for(int x = 0; x < col.size(); x++)
			if(!this.col.get(x).isValidated())
				return false;
		return true;
	}
	public String[] validate()
	{
		return validate(null);
	}
	public String[] validate(String[] existingErrors)
	{
		if(col.size() == 0)
			return null;
		java.util.ArrayList<String> listOfErrors = new java.util.ArrayList<String>();
		if(existingErrors != null)
		{
			for(int x = 0; x < existingErrors.length; x++)
			{
				listOfErrors.add(existingErrors[x]);
			}
		}
		for(int x = 0; x < col.size(); x++)
		{
			String[] listOfOtherErrors = this.col.get(x).validate();
			if(listOfOtherErrors != null)
			{
				for(int y = 0; y < listOfOtherErrors.length; y++)
				{
					listOfErrors.add(listOfOtherErrors[y]);
				}
			}
		}
		
		int errorCount = listOfErrors.size();
		if(errorCount == 0)
			return null;
		String[] result = new String[errorCount];
		for(int x = 0; x < errorCount; x++)
			result[x] = (String)listOfErrors.get(x);
		return result;
	}
	public ReferralErodForCancellationVoCollection sort()
	{
		return sort(SortOrder.ASCENDING);
	}
	public ReferralErodForCancellationVoCollection sort(boolean caseInsensitive)
	{
		return sort(SortOrder.ASCENDING, caseInsensitive);
	}
	public ReferralErodForCancellationVoCollection sort(SortOrder order)
	{
		return sort(new ReferralErodForCancellationVoComparator(order));
	}
	public ReferralErodForCancellationVoCollection sort(SortOrder order, boolean caseInsensitive)
	{
		return sort(new ReferralErodForCancellationVoComparator(order, caseInsensitive));
	}
	@SuppressWarnings("unchecked")
	public ReferralErodForCancellationVoCollection sort(Comparator comparator)
	{
		Collections.sort(col, comparator);
		return this;
	}
	public ims.careuk.vo.ReferralERODRefVoCollection toRefVoCollection()
	{
		ims.careuk.vo.ReferralERODRefVoCollection result = new ims.careuk.vo.ReferralERODRefVoCollection();
		for(int x = 0; x < this.col.size(); x++)
		{
			result.add(this.col.get(x));
		}
		return result;
	}
	public ReferralErodForCancellationVo[] toArray()
	{
		ReferralErodForCancellationVo[] arr = new ReferralErodForCancellationVo[col.size()];
		col.toArray(arr);
		return arr;
	}
	public Iterator<ReferralErodForCancellationVo> iterator()
	{
		return col.iterator();
	}
	@Override
	protected ArrayList getTypedCollection()
	{
		return col;
	}
	private class ReferralErodForCancellationVoComparator implements Comparator
	{
		private int direction = 1;
		private boolean caseInsensitive = true;
		public ReferralErodForCancellationVoComparator()
		{
			this(SortOrder.ASCENDING);
		}
		public ReferralErodForCancellationVoComparator(SortOrder order)
		{
			if (order == SortOrder.DESCENDING)
			{
				direction = -1;
			}
		}
		public ReferralErodForCancellationVoComparator(SortOrder order, boolean caseInsensitive)
		{
			if (order == SortOrder.DESCENDING)
			{
				direction = -1;
			}
			this.caseInsensitive = caseInsensitive;
		}
		public int compare(Object obj1, Object obj2)
		{
			ReferralErodForCancellationVo voObj1 = (ReferralErodForCancellationVo)obj1;
			ReferralErodForCancellationVo voObj2 = (ReferralErodForCancellationVo)obj2;
			return direction*(voObj1.compareTo(voObj2, this.caseInsensitive));
		}
		public boolean equals(Object obj)
		{
			return false;
		}
	}
	public ims.careuk.vo.beans.ReferralErodForCancellationVoBean[] getBeanCollection()
	{
		return getBeanCollectionArray();
	}
	public ims.careuk.vo.beans.ReferralErodForCancellationVoBean[] getBeanCollectionArray()
	{
		ims.careuk.vo.beans.ReferralErodForCancellationVoBean[] result = new ims.careuk.vo.beans.ReferralErodForCancellationVoBean[col.size()];
		for(int i = 0; i < col.size(); i++)
		{
			ReferralErodForCancellationVo vo = ((ReferralErodForCancellationVo)col.get(i));
			result[i] = (ims.careuk.vo.beans.ReferralErodForCancellationVoBean)vo.getBean();
		}
		return result;
	}
	public static ReferralErodForCancellationVoCollection buildFromBeanCollection(java.util.Collection beans)
	{
		ReferralErodForCancellationVoCollection coll = new ReferralErodForCancellationVoCollection();
		if(beans == null)
			return coll;
		java.util.Iterator iter = beans.iterator();
		while (iter.hasNext())
		{
			coll.add(((ims.careuk.vo.beans.ReferralErodForCancellationVoBean)iter.next()).buildVo());
		}
		return coll;
	}
	public static ReferralErodForCancellationVoCollection buildFromBeanCollection(ims.careuk.vo.beans.ReferralErodForCancellationVoBean[] beans)
	{
		ReferralErodForCancellationVoCollection coll = new ReferralErodForCancellationVoCollection();
		if(beans == null)
			return coll;
		for(int x = 0; x < beans.length; x++)
		{
			coll.add(beans[x].buildVo());
		}
		return coll;
	}
}