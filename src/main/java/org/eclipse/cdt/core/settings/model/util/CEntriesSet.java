/*******************************************************************************
 * Copyright (c) 2007, 2010 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Intel Corporation - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.settings.model.util;

import org.eclipse.cdt.core.settings.model.ICSettingEntry;

import java.util.LinkedHashMap;
import java.util.List;

public class CEntriesSet
{
    private LinkedHashMap<Object, ICSettingEntry> fEntriesMap = new LinkedHashMap<Object, ICSettingEntry>();

    public CEntriesSet()
    {
    }

    public CEntriesSet(List<ICSettingEntry> list)
    {
        setEntries(list);
    }

    public CEntriesSet(ICSettingEntry entries[])
    {
        setEntries(entries);
    }

    public int size()
    {
        return fEntriesMap.size();
    }

    /*	public ICLanguageSettingEntry removeEntry(String name) {
            ICLanguageSettingEntry entry = (ICLanguageSettingEntry)fMap.remove(name);
            if(entry != null)
                fList.remove(entry);
            return entry;
        }
    */
    public ICSettingEntry[] toArray()
    {
        return fEntriesMap.values().toArray(new ICSettingEntry[fEntriesMap.size()]);
    }

    protected Object getKey(ICSettingEntry entry)
    {
        return entry;
    }

    public ICSettingEntry addEntry(ICSettingEntry entry)
    {
        return fEntriesMap.put(getKey(entry), entry);
    }

    public void clear()
    {
        fEntriesMap.clear();
    }

    public void setEntries(List<ICSettingEntry> list)
    {
        clear();
        for (ICSettingEntry entry : list) {
            addEntry(entry);
        }
    }

    public void setEntries(ICSettingEntry[] entries)
    {
        clear();
        for (ICSettingEntry entry : entries) {
            if (entry != null) {
                addEntry(entry);
            }
        }
    }
}
