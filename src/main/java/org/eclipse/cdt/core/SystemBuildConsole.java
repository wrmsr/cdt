/*******************************************************************************
 * Copyright (c) 2004, 2008 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * <p/>
 * Contributors:
 * QNX Software Systems - initial API and implementation
 *******************************************************************************/
package org.eclipse.cdt.core;

import org.eclipse.cdt.core.resources.IConsole;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

/**
 * CDT console which redirects output to system console ({@code System.out}, {@code System.err}).
 * Used by headless builder.
 *
 */
public class SystemBuildConsole
        implements IConsole
{
    final ConsoleOutputStream out;
    final ConsoleOutputStream err;

    public SystemBuildConsole()
    {
        out = new ConsoleOutputStream()
        {
            @Override
            public synchronized void write(byte[] b, int off, int len)
                    throws java.io.IOException
            {
                System.out.write(b, off, len);
            }

            @Override
            public synchronized void write(int c)
                    throws java.io.IOException
            {
                System.out.write(c);
            }
        };
        err = new ConsoleOutputStream()
        {
            @Override
            public synchronized void write(byte[] b, int off, int len)
                    throws java.io.IOException
            {
                System.err.write(b, off, len);
            }

            @Override
            public synchronized void write(int c)
                    throws java.io.IOException
            {
                System.err.write(c);
            }
        };
    }

    @Override
    public void start(IProject project)
    {
    }

    @Override
    public ConsoleOutputStream getOutputStream()
            throws CoreException
    {
        return out;
    }

    @Override
    public ConsoleOutputStream getInfoStream()
            throws CoreException
    {
        return out;
    }

    @Override
    public ConsoleOutputStream getErrorStream()
            throws CoreException
    {
        return err;
    }
}
