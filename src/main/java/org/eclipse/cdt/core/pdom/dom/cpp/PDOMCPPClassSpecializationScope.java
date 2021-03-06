/*******************************************************************************
 * Copyright (c) 2008 Wind River Systems, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Markus Schorn - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core.pdom.dom.cpp;

import org.eclipse.cdt.core.dom.ast.DOMException;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.ast.IBinding;
import org.eclipse.cdt.core.dom.ast.cpp.ICPPClassSpecialization;
import org.eclipse.cdt.core.index.IIndexBinding;
import org.eclipse.cdt.core.index.IIndexName;
import org.eclipse.cdt.core.dom.parser.cpp.AbstractCPPClassSpecializationScope;
import org.eclipse.cdt.core.dom.parser.cpp.semantics.CPPSemantics;
import org.eclipse.cdt.core.index.IIndexScope;

/**
 * Reuses the specialization scope of the ast and marks it as an index scope.
 */
public class PDOMCPPClassSpecializationScope
        extends AbstractCPPClassSpecializationScope
        implements IIndexScope
{
    public PDOMCPPClassSpecializationScope(ICPPClassSpecialization specialization)
    {
        super(specialization);
    }

    @Override
    public IIndexBinding getScopeBinding()
    {
        return (IIndexBinding) getClassType();
    }

    @Override
    public IIndexScope getParent()
    {
        try {
            return (IIndexScope) getScopeBinding().getScope();
        }
        catch (DOMException e) {
        }
        return null;
    }

    @Override
    public IIndexName getScopeName()
    {
        return null;
    }

    @Override
    public IBinding[] find(String name, IASTTranslationUnit tu)
    {
        return CPPSemantics.findBindingsInScope(this, name, tu);
    }
}
