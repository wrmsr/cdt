/*******************************************************************************
 * Copyright (c) 2015 QNX Software Systems and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.cdt.core.build;

import org.eclipse.cdt.core.model.ILanguage;
import org.eclipse.cdt.core.parser.IScannerInfo;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.PlatformObject;

import java.util.List;
import java.util.Map;

/**
 * Root class for CDT toolchains.
 *
 * @since 5.12
 */
public abstract class CToolChain
        extends PlatformObject
{

    private final CBuildConfiguration config;

    private ScannerInfoData scannerInfo;

    protected CToolChain(CBuildConfiguration config)
    {
        this.config = config;
    }

    public static String[] splitCommand(String command)
    {
        // TODO deal with quotes properly, for now just strip
        return command.replace("\"", "").split("\\s+"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    public CBuildConfiguration getBuildConfiguration()
    {
        return config;
    }

    /**
     * Update the given environment to run the toolchain.
     *
     * @param env
     */
    public void setEnvironment(Map<String, String> env)
    {
        // default, nothing
    }

    /**
     * Scan the commandLine and save the scanner info for the resource being
     * built, or if perProject is true, for all resources in the project. The
     * buildFolder to help find the resource is where the command ran.
     *
     * @param buildFolder
     * @param commandLine
     * @throws CoreException
     */
    public void scanBuildOutput(IFolder buildFolder, String commandLine, boolean perProject)
            throws CoreException
    {
        // default, nothing
    }

    protected void putScannerInfo(IResource resource, Map<String, String> definedSymbols,
            List<String> includePaths, List<String> macroFiles, List<String> includeFiles,
            List<String> localIncludePath)
            throws CoreException
    {
        if (scannerInfo == null) {
            loadScannerInfo();
        }
        scannerInfo.putScannerInfo(resource, new ToolChainScannerInfo(definedSymbols, includePaths,
                macroFiles, includeFiles, localIncludePath));
    }

    protected void putScannerInfo(ILanguage language, Map<String, String> definedSymbols,
            List<String> includePaths, List<String> macroFiles, List<String> includeFiles,
            List<String> localIncludePath)
            throws CoreException
    {
        if (scannerInfo == null) {
            loadScannerInfo();
        }
        scannerInfo.putScannerInfo(language, new ToolChainScannerInfo(definedSymbols, includePaths,
                macroFiles, includeFiles, localIncludePath));
    }

    private void loadScannerInfo()
    {
        if (scannerInfo == null) {
            scannerInfo = ScannerInfoData.load(this);
        }
    }

    /**
     * Return the scanner info for the given resource.
     *
     * @param resource
     * @return scanner info for the resource
     * @throws CoreException
     */
    public IScannerInfo getScannerInfo(IResource resource)
            throws CoreException
    {
        loadScannerInfo();
        return scannerInfo.getScannerInfo(resource);
    }

    public void clearScannerInfo()
            throws CoreException
    {
        if (scannerInfo == null) {
            scannerInfo = new ScannerInfoData();
            scannerInfo.queueSave();
        }
        else {
            scannerInfo.clear();
        }
    }

    /**
     * Return the console parsers to be used when this toolchain is being used
     * for a build.
     *
     * @return console parsers
     */
    public CConsoleParser[] getConsoleParsers()
    {
        return null;
    }
}
