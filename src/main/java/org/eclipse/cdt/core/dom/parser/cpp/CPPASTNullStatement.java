/*******************************************************************************
 * Copyright (c) 2004, 2014 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * IBM - Initial API and implementation
 * Sergey Prigogin (Google)
 *******************************************************************************/
package org.eclipse.cdt.core.dom.parser.cpp;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTNullStatement;
import org.eclipse.cdt.core.dom.parser.ASTAttributeOwner;

/**
 * @author jcamelon
 */
public class CPPASTNullStatement
        extends ASTAttributeOwner
        implements IASTNullStatement
{
    @Override
    public boolean accept(ASTVisitor action)
    {
        if (action.shouldVisitStatements) {
            switch (action.visit(this)) {
                case ASTVisitor.PROCESS_ABORT:
                    return false;
                case ASTVisitor.PROCESS_SKIP:
                    return true;
                default:
                    break;
            }
        }

        if (!acceptByAttributeSpecifiers(action)) {
            return false;
        }

        if (action.shouldVisitStatements) {
            switch (action.leave(this)) {
                case ASTVisitor.PROCESS_ABORT:
                    return false;
                case ASTVisitor.PROCESS_SKIP:
                    return true;
                default:
                    break;
            }
        }
        return true;
    }

    @Override
    public CPPASTNullStatement copy()
    {
        return copy(CopyStyle.withoutLocations);
    }

    @Override
    public CPPASTNullStatement copy(CopyStyle style)
    {
        CPPASTNullStatement copy = new CPPASTNullStatement();
        return copy(copy, style);
    }
}
