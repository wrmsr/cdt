/*******************************************************************************
 * Copyright (c) 2011, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.resources;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;

import java.util.LinkedList;
import java.util.List;

/**
 * @author vkong
 */
public class ResourceExclusion
        extends RefreshExclusion
{
    @Override
    public synchronized String getName()
    {
        return "ErrorParserExtensionManager";
    }

    @Override
    public boolean supportsExclusionInstances()
    {
        return true;
    }

    @Override
    public synchronized boolean testExclusion(IResource resource)
    {
        // Populate the resources to be excluded by this exclusion.
        List<IResource> excludedResources = new LinkedList<IResource>();
        List<ExclusionInstance> exclusionInstances = getExclusionInstances();

        for (ExclusionInstance instance : exclusionInstances) {
            excludedResources.add(instance.getResource());
        }

        if (excludedResources.contains(resource)) {
            return true;
        }

        // Check to see if the given resource is part of this exclusion.
        for (IResource excludedResource : excludedResources) {
            // TODO: need to update this for Phase 2 implementation
            if (excludedResource instanceof IContainer) {
                IContainer container = (IContainer) excludedResource;
                if (container.getFullPath().isPrefixOf(resource.getFullPath())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object clone()
    {
        ResourceExclusion clone = new ResourceExclusion();
        copyTo(clone);
        return clone;
    }
}
