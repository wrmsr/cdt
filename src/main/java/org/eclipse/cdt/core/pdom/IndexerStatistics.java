/*******************************************************************************
 * Copyright (c) 2007, 2008 Wind River Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Markus Schorn - initial API and implementation
 *******************************************************************************/

package org.eclipse.cdt.core.pdom;

public class IndexerStatistics
{
    public int fResolutionTime;
    public int fParsingTime;
    public int fAddToIndexTime;
    public int fErrorCount;
    public int fReferenceCount = 0;
    public int fDeclarationCount = 0;
    public int fProblemBindingCount = 0;
    public int fUnresolvedIncludesCount = 0;
    public int fPreprocessorProblemCount = 0;
    public int fSyntaxProblemsCount = 0;
    public int fTooManyTokensCount = 0;
}
