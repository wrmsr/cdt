/*******************************************************************************
 * Copyright (c) 2007 Intel Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Intel Corporation - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.settings.model;

public class CProjectDescriptionWorkspacePreferences
        extends
        CProjectDescriptionPreferences
        implements ICProjectDescriptionWorkspacePreferences
{

    public CProjectDescriptionWorkspacePreferences(
            CProjectDescriptionPreferences base, boolean isReadOnly)
    {
        super(base, isReadOnly);
    }

    public CProjectDescriptionWorkspacePreferences(
            CProjectDescriptionPreferences base,
            CProjectDescriptionPreferences superPreference, boolean isReadOnly)
    {
        super(base, superPreference, isReadOnly);
    }

    public CProjectDescriptionWorkspacePreferences(ICStorageElement el,
            CProjectDescriptionPreferences superPreference, boolean isReadOnly)
    {
        super(el, superPreference, isReadOnly);
    }
}
