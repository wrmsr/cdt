/*******************************************************************************
 * Copyright (c) 2000, 2008 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * QNX Software Systems - Initial API and implementation
 * Anton Leherbauer (Wind River Systems)
 *******************************************************************************/

package org.eclipse.cdt.core.model;

import org.eclipse.core.resources.IFile;

import java.util.Map;

/**
 * CreateWorkingCopyOperation
 */
public class CreateWorkingCopyOperation
        extends CModelOperation
{

    Map<ITranslationUnit, WorkingCopy> perFactoryWorkingCopies;
    IBufferFactory factory;
    IProblemRequestor problemRequestor;

    /*
     * Creates a working copy from the given original tu and the given buffer factory.
     * perFactoryWorkingCopies map is not null if the working copy is a shared working copy.
     */
    public CreateWorkingCopyOperation(ITranslationUnit originalElement, Map<ITranslationUnit, WorkingCopy> perFactoryWorkingCopies, IBufferFactory factory, IProblemRequestor problemRequestor)
    {
        super(new ICElement[] {originalElement});
        this.perFactoryWorkingCopies = perFactoryWorkingCopies;
        this.factory = factory;
        this.problemRequestor = problemRequestor;
    }

    @Override
    protected void executeOperation()
            throws CModelException
    {
        ITranslationUnit tu = getTranslationUnit();

        WorkingCopy workingCopy;

        if (tu.getResource() != null) {
            workingCopy = new WorkingCopy(tu.getParent(), (IFile) tu.getResource(), tu.getContentTypeId(), this.factory, this.problemRequestor);
        }
        else {
            workingCopy = new WorkingCopy(tu.getParent(), tu.getLocationURI(), tu.getContentTypeId(), this.factory);
        }
        // Open the working copy now to ensure contents are that of the current state of this element.
        // Alain: Actually no, delay the parsing 'till it is really needed.  Doing the parsing here
        // really slows down the opening of the CEditor.
        //workingCopy.open(this.fMonitor);

        if (this.perFactoryWorkingCopies != null) {
            this.perFactoryWorkingCopies.put(tu, workingCopy);
            //if (TranslationUnit.SHARED_WC_VERBOSE) {
            //	System.out.println("Creating shared working copy " + workingCopy.toStringWithAncestors()); //$NON-NLS-1$
            //}
        }

        // Report added C delta.
        CElementDelta delta = new CElementDelta(this.getCModel());
        delta.added(workingCopy);
        addDelta(delta);

        fResultElements = new ICElement[] {workingCopy};
    }

    /**
     * Returns the translation unit this operation is working on.
     */
    protected ITranslationUnit getTranslationUnit()
    {
        return (ITranslationUnit) getElementToProcess();
    }

    /**
     * @see CModelOperation#isReadOnly
     */
    @Override
    public boolean isReadOnly()
    {
        return true;
    }
}
