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

/**
 * This operation moves resources (package fragments and compilation units) from their current
 * container to a specified destination container, optionally renaming the
 * elements.
 * A move resource operation is equivalent to a copy resource operation, where
 * the source resources are deleted after the copy.
 * <p>This operation can be used for reorganizing resources within the same container.
 *
 * @see CopyResourceElementsOperation
 */
public class MoveResourceElementsOperation
        extends CopyResourceElementsOperation
{
    /**
     * When executed, this operation will move the given elements to the given containers.
     */
    public MoveResourceElementsOperation(ICElement[] elementsToMove, ICElement[] destContainers, boolean force)
    {
        super(elementsToMove, destContainers, force);
    }

    /**
     * @see MultiOperation
     */
    @Override
    protected String getMainTaskName()
    {
        return CoreModelMessages.getString("operation.moveResourceProgress"); //$NON-NLS-1$
    }

    /**
     * @see CopyResourceElementsOperation#isMove()
     */
    @Override
    protected boolean isMove()
    {
        return true;
    }
}
