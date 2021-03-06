/*******************************************************************************
 * Copyright (c) 2008, 2015 Wind River Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Markus Schorn - Initial API and implementation
 * Sergey Prigogin (Google)
 *******************************************************************************/
package org.eclipse.cdt.core.dom.parser.cpp;

import org.eclipse.cdt.core.dom.ast.IASTBinaryExpression;
import org.eclipse.cdt.core.dom.ast.IASTCastExpression;
import org.eclipse.cdt.core.dom.ast.IASTImplicitDestructorName;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTExpression;
import org.eclipse.cdt.core.dom.parser.ASTAmbiguousBinaryVsCastExpression;

public class CPPASTAmbiguousBinaryVsCastExpression
        extends ASTAmbiguousBinaryVsCastExpression
        implements ICPPASTExpression
{

    public CPPASTAmbiguousBinaryVsCastExpression(IASTBinaryExpression bexp, IASTCastExpression castExpr)
    {
        super(bexp, castExpr);
    }

    @Override
    public IASTImplicitDestructorName[] getImplicitDestructorNames()
    {
        throw new UnsupportedOperationException();
    }
}
