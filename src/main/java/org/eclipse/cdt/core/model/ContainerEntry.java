/*******************************************************************************
 * Copyright (c) 2000, 2008 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * QNX Software Systems - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.model;

import org.eclipse.core.runtime.IPath;

public class ContainerEntry
        extends PathEntry
        implements IContainerEntry
{

    public ContainerEntry(IPath path, boolean isExported)
    {
        super(IPathEntry.CDT_CONTAINER, path, isExported);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof IContainerEntry) {
            IContainerEntry container = (IContainerEntry) obj;
            if (!super.equals(container)) {
                return false;
            }
            if (path == null) {
                if (container.getPath() != null) {
                    return false;
                }
            }
            else {
                if (!path.equals(container.getPath())) {
                    return false;
                }
            }
            return true;
        }
        return super.equals(obj);
    }
}
