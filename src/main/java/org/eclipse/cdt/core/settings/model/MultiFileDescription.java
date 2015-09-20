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

/**
 *
 *
 */
public class MultiFileDescription
        extends MultiResourceDescription
        implements
        ICFileDescription
{

    public MultiFileDescription(ICFileDescription[] res)
    {
        super(res);
    }

    /* (non-Javadoc)
     * @see org.eclipse.cdt.core.settings.model.ICFileDescription#getLanguageSetting()
     */
    @Override
    public ICLanguageSetting getLanguageSetting()
    {
        if (DEBUG) {
            System.out.println("Limited multi access: MultiFileDescription.getLanguageSetting()"); //$NON-NLS-1$
        }
        return ((ICFileDescription) fRess[0]).getLanguageSetting();
    }
}
