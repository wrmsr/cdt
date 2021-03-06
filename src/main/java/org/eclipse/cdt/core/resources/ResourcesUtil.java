/*******************************************************************************
 * Copyright (c) 2010, 2010 Andrew Gvozdev and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Andrew Gvozdev - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.resources;

import org.eclipse.cdt.core.CCorePlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import java.net.URI;

/**
 * A collection of utility methods related to resources.
 *
 * @since 5.3
 */
public class ResourcesUtil
{
    /**
     * Refresh file when it happens to belong to Workspace. There could
     * be multiple workspace {@link IFile} associated with one URI.
     * Hint: use {@link org.eclipse.core.filesystem.URIUtil#toURI(String)}
     * to convert filesystem path to URI.
     *
     * @param uri - URI of the file.
     */
    public static void refreshWorkspaceFiles(URI uri)
    {
        if (uri != null) {
            IFile[] files = ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(uri);
            for (IFile file : files) {
                try {
                    file.refreshLocal(IResource.DEPTH_ZERO, null);
                }
                catch (CoreException e) {
                    CCorePlugin.log(e);
                }
            }
        }
    }
}
