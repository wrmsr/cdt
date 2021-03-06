/*******************************************************************************
 * Copyright (c) 2008 Wind River Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Markus Schorn - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.dom.parser.c;

import org.eclipse.cdt.core.dom.ast.IASTCastExpression;
import org.eclipse.cdt.core.dom.ast.IASTFunctionCallExpression;
import org.eclipse.cdt.core.dom.parser.ASTAmbiguousCastVsFunctionCallExpression;

public class CASTAmbiguousCastVsFunctionCallExpression
        extends ASTAmbiguousCastVsFunctionCallExpression
{

    public CASTAmbiguousCastVsFunctionCallExpression(IASTCastExpression castExpr, IASTFunctionCallExpression funcCall)
    {
        super(castExpr, funcCall);
    }
}
