/*******************************************************************************
 * Copyright (c) 2005, 2008 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * QNX Software Systems - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jul 6, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.cdt.utils.coff.parser;

import org.eclipse.cdt.core.IAddress;
import org.eclipse.cdt.utils.Addr2line;
import org.eclipse.cdt.utils.Symbol;
import org.eclipse.core.runtime.IPath;

import java.io.IOException;

/**
 * @author DInglis
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
class CygwinSymbol
        extends Symbol
{

    /**
     * @param binary
     * @param name
     * @param type
     * @param addr
     * @param size
     * @param sourceFile
     * @param startLine
     * @param endLine
     */
    public CygwinSymbol(CygwinPEBinaryObject binary, String name, int type, IAddress addr, long size, IPath sourceFile, int startLine,
            int endLine)
    {
        super(binary, name, type, addr, size, sourceFile, startLine, endLine);
    }

    /**
     * @param binary
     * @param name
     * @param type
     * @param addr
     * @param size
     */
    public CygwinSymbol(CygwinPEBinaryObject binary, String name, int type, IAddress addr, long size)
    {
        super(binary, name, type, addr, size);
    }

    /* (non-Javadoc)
     * @see org.eclipse.cdt.utils.Symbol#getLineNumber(long)
     */
    @Override
    public int getLineNumber(long offset)
    {
        int line = -1;
        Addr2line addr2line = ((CygwinPEBinaryObject) binary).getAddr2line(true);
        if (addr2line != null) {
            try {
                return addr2line.getLineNumber(getAddress().add(offset));
            }
            catch (IOException e) {
                // ignore
            }
        }
        return line;
    }
}