/*******************************************************************************
 * Copyright (c) 2007, 2008 Symbian Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Andrew Ferguson (Symbian) - Initial implementation
 *******************************************************************************/
package org.eclipse.cdt.core.index.provider;

import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.settings.model.ICConfigurationDescription;
import org.eclipse.cdt.core.index.IIndexFragment;
import org.eclipse.cdt.core.pdom.PDOM;
import org.eclipse.core.runtime.CoreException;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapts a IReadOnlyPDOMProvider to an IIndexFragmentProvider.
 */
public class ReadOnlyPDOMProviderBridge
        implements IIndexFragmentProvider
{
    private final IReadOnlyPDOMProvider provider;

    public ReadOnlyPDOMProviderBridge(IReadOnlyPDOMProvider provider)
    {
        this.provider = provider;
    }

    @Override
    public IIndexFragment[] getIndexFragments(ICConfigurationDescription config)
            throws CoreException
    {
        List<PDOM> result = new ArrayList<PDOM>();
        IPDOMDescriptor[] descriptions = provider.getDescriptors(config);

        if (descriptions != null) {
            for (IPDOMDescriptor desc : descriptions) {
                PDOM pdom = PDOMCache.getInstance().getPDOM(desc.getLocation(),
                        desc.getIndexLocationConverter());
                if (pdom != null) {
                    result.add(pdom);
                }
            }
        }

        return result.toArray(new IIndexFragment[result.size()]);
    }

    @Override
    public boolean providesFor(ICProject cproject)
            throws CoreException
    {
        return provider.providesFor(cproject);
    }
}
