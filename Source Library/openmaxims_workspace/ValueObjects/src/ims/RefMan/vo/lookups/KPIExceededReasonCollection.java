// This code was generated by Barbara Worwood using IMS Development Environment (version 1.80 build 5007.25751)
// Copyright (C) 1995-2014 IMS MAXIMS. All rights reserved.
// WARNING: DO NOT MODIFY the content of this file

package ims.RefMan.vo.lookups;

import ims.framework.cn.data.TreeModel;
import ims.framework.cn.data.TreeNode;
import ims.vo.LookupInstanceCollection;
import ims.vo.LookupInstVo;

public class KPIExceededReasonCollection extends LookupInstanceCollection implements ims.vo.ImsCloneable, TreeModel
{
	private static final long serialVersionUID = 1L;
	public void add(KPIExceededReason value)
	{
		super.add(value);
	}
	public int indexOf(KPIExceededReason instance)
	{
		return super.indexOf(instance);
	}
	public boolean contains(KPIExceededReason instance)
	{
		return indexOf(instance) >= 0;
	}
	public KPIExceededReason get(int index)
	{
		return (KPIExceededReason)super.getIndex(index);
	}
	public void remove(KPIExceededReason instance)
	{
		if(instance != null)
		{
			int index = indexOf(instance);
			if(index >= 0)
				remove(index);
		}
	}
	public Object clone()
	{
		KPIExceededReasonCollection newCol = new KPIExceededReasonCollection();
		KPIExceededReason item;
		for (int i = 0; i < super.size(); i++)
		{
			item = this.get(i);
			newCol.add(new KPIExceededReason(item.getID(), item.getText(), item.isActive(), item.getParent(), item.getImage(), item.getColor(), item.getOrder()));
		}
		for (int i = 0; i < newCol.size(); i++)
		{
			item = newCol.get(i);
			if (item.getParent() != null)
			{
				int parentIndex = this.indexOf(item.getParent());
				if(parentIndex >= 0)
					item.setParent(newCol.get(parentIndex));
				else
					item.setParent((KPIExceededReason)item.getParent().clone());
			}
		}
		return newCol;
	}
	public KPIExceededReason getInstance(int instanceId)
	{
		return (KPIExceededReason)super.getInstanceById(instanceId);
	}
	public TreeNode[] getRootNodes()
	{
		LookupInstVo[] roots = super.getRoots();
		TreeNode[] nodes = new TreeNode[roots.length];
		System.arraycopy(roots, 0, nodes, 0, roots.length);
		return nodes;
	}
	public KPIExceededReason[] toArray()
	{
		KPIExceededReason[] arr = new KPIExceededReason[this.size()];
		super.toArray(arr);
		return arr;
	}
	public static KPIExceededReasonCollection buildFromBeanCollection(java.util.Collection beans)
	{
		KPIExceededReasonCollection coll = new KPIExceededReasonCollection();
		if(beans == null)
			return coll;
		java.util.Iterator iter = beans.iterator();
		while(iter.hasNext())
		{
			coll.add(KPIExceededReason.buildLookup((ims.vo.LookupInstanceBean)iter.next()));
		}
		return coll;
	}
	public static KPIExceededReasonCollection buildFromBeanCollection(ims.vo.LookupInstanceBean[] beans)
	{
		KPIExceededReasonCollection coll = new KPIExceededReasonCollection();
		if(beans == null)
			return coll;
		for(int x = 0; x < beans.length; x++)
		{
			coll.add(KPIExceededReason.buildLookup(beans[x]));
		}
		return coll;
	}
}