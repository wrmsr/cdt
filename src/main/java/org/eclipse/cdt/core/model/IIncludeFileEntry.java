/*******************************************************************************
 * Copyright (c) 2000, 2009 QNX Software Systems and others.
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

/**
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IIncludeFileEntry
        extends IPathEntry
{

    /**
     * Returns the include file
     * @return IPath
     */
    IPath getIncludeFilePath();

    /**
     * Return the includeFilePath with the base path.
     */
    IPath getFullIncludeFilePath();

    /**
     * Return the base path of the includePath
     * @return IPath
     */
    IPath getBasePath();

    /**
     * Return the reference path
     */
    IPath getBaseReference();

    /**
     * If isRecursive() is true, specify an exclude file patterns.
     * @return IPath
     */
    IPath[] getExclusionPatterns();

    /**
     * Returns a char based representation of the exclusions patterns full path.
     */
    public char[][] fullExclusionPatternChars();
}
