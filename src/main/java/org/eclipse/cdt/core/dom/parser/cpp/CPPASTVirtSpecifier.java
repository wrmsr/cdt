/*******************************************************************************
 * Copyright (c) 2014 Nathan Ridge.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Nathan Ridge - Initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.dom.parser.cpp;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPASTVirtSpecifier;
import org.eclipse.cdt.core.dom.parser.ASTNode;

public class CPPASTVirtSpecifier
        extends ASTNode
        implements ICPPASTVirtSpecifier
{
    private SpecifierKind fKind;

    public CPPASTVirtSpecifier(SpecifierKind kind)
    {
        fKind = kind;
    }

    @Override
    public SpecifierKind getKind()
    {
        return fKind;
    }

    @Override
    public ICPPASTVirtSpecifier copy()
    {
        return copy(CopyStyle.withoutLocations);
    }

    @Override
    public ICPPASTVirtSpecifier copy(CopyStyle style)
    {
        CPPASTVirtSpecifier copy = new CPPASTVirtSpecifier(fKind);
        return copy(copy, style);
    }

    @Override
    public boolean accept(ASTVisitor action)
    {
        if (action.shouldVisitVirtSpecifiers) {
            switch (action.visit(this)) {
                case ASTVisitor.PROCESS_ABORT:
                    return false;
                case ASTVisitor.PROCESS_SKIP:
                    return true;
                default:
                    break;
            }
        }

        if (action.shouldVisitVirtSpecifiers) {
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
}
