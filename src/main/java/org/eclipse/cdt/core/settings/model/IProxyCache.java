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
package org.eclipse.cdt.core.settings.model;

import org.eclipse.cdt.core.settings.model.extension.CDataObject;

import java.util.Map;

public interface IProxyCache
{
    CDataProxy[] getCachedProxies();

    Map<String, CDataProxy> getCachedProxiesMap();

    CDataProxy getCachedProxy(String id);

    CDataProxy getCachedProxy(CDataObject data);

//	Object addCachedProxy(CDataProxy proxy);

    void addCachedProxy(CDataProxy proxy);

    void removeCachedProxy(CDataProxy proxy);

    void removeCachedProxy(String id);

//	void clearInvalidCachedProxies();

    void clear();

//	Map reuseProxies(List dataList, Map freeProxyMap);

//	void cacheScope(IDataScope scope);
//	boolean presentsScope(IDataScope scope);
}
