/*******************************************************************************
 * Copyright (c) 2004, 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.dom;

import org.eclipse.cdt.core.model.IWorkingCopyProvider;
import org.eclipse.cdt.core.parser.CodeReader;
import org.eclipse.cdt.core.parser.IScanner;

/**
 * @author jcamelon
 */
@Deprecated
public class WorkingCopyCodeReaderFactory
        extends PartialWorkingCopyCodeReaderFactory
{

    /**
     * @param provider
     */
    public WorkingCopyCodeReaderFactory(IWorkingCopyProvider provider, IIncludeFileResolutionHeuristics heuristics)
    {
        super(provider, heuristics);
    }

    /* (non-Javadoc)
     * @see org.eclipse.cdt.core.dom.ICodeReaderFactory#getUniqueIdentifier()
     */
    @Override
    public int getUniqueIdentifier()
    {
        return CDOM.PARSE_WORKING_COPY_WHENEVER_POSSIBLE;
    }

    /* (non-Javadoc)
     * @see org.eclipse.cdt.core.dom.ICodeReaderFactory#createCodeReaderForInclusion(java.lang.String)
     */
    public CodeReader createCodeReaderForInclusion(IScanner scanner, String path)
    {
        return checkWorkingCopyThenCache(path);
    }
}
