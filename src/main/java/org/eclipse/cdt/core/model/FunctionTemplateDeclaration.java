/*******************************************************************************
 * Copyright (c) 2005, 2009 QnX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * Qnx Software Systems - initial API and implementation
 *******************************************************************************/

package org.eclipse.cdt.core.model;

public class FunctionTemplateDeclaration
        extends FunctionDeclaration
        implements IFunctionTemplateDeclaration
{

    protected Template fTemplate;

    public FunctionTemplateDeclaration(ICElement parent, String name)
    {
        super(parent, name, ICElement.C_TEMPLATE_FUNCTION_DECLARATION);
        fTemplate = new Template(name);
    }

    @Override
    public String[] getTemplateParameterTypes()
    {
        return fTemplate.getTemplateParameterTypes();
    }

    @Override
    public String[] getTemplateArguments()
    {
        return fTemplate.getTemplateArguments();
    }

    @Override
    public String getTemplateSignature()
            throws CModelException
    {
        StringBuffer sig = new StringBuffer(fTemplate.getTemplateSignature());
        sig.append(this.getParameterClause());
        if (isConst()) {
            sig.append(" const"); //$NON-NLS-1$
        }
        if (isVolatile()) {
            sig.append(" volatile"); //$NON-NLS-1$
        }

        if ((this.getReturnType() != null) && (this.getReturnType().length() > 0)) {
            sig.append(" : "); //$NON-NLS-1$
            sig.append(this.getReturnType());
        }

        return sig.toString();
    }

    @Override
    public int getNumberOfTemplateParameters()
    {
        return fTemplate.getNumberOfTemplateParameters();
    }

    /**
     * Sets the template parameter types.
     */
    public void setTemplateParameterTypes(String[] templateParameterTypes)
    {
        fTemplate.setTemplateInfo(templateParameterTypes, null);
    }
}
