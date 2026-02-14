/**
 * MCP Server for EDT - Tests
 * Copyright (C) 2025 DitriX (https://github.com/DitriXNew)
 * Licensed under AGPL-3.0-or-later
 */

package com.ditrix.edt.mcp.server.tools.impl;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ditrix.edt.mcp.server.tools.IMcpTool.ResponseType;

/**
 * Tests for {@link ValidateQueryTool}.
 */
public class ValidateQueryToolTest
{
    @Test
    public void testName()
    {
        ValidateQueryTool tool = new ValidateQueryTool();
        assertEquals("validate_query", tool.getName()); //$NON-NLS-1$
    }

    @Test
    public void testResponseType()
    {
        ValidateQueryTool tool = new ValidateQueryTool();
        assertEquals(ResponseType.JSON, tool.getResponseType());
    }

    @Test
    public void testInputSchemaContainsRequiredParameters()
    {
        ValidateQueryTool tool = new ValidateQueryTool();
        String schema = tool.getInputSchema();

        assertNotNull(schema);
        assertTrue(schema.contains("\"projectName\"")); //$NON-NLS-1$
        assertTrue(schema.contains("\"queryText\"")); //$NON-NLS-1$
        assertTrue(schema.contains("\"dcsMode\"")); //$NON-NLS-1$
        assertTrue(schema.contains("\"required\":[\"projectName\",\"queryText\"]")); //$NON-NLS-1$
    }

    @Test
    public void testExecuteMissingProjectName()
    {
        ValidateQueryTool tool = new ValidateQueryTool();

        Map<String, String> params = new HashMap<>();
        params.put("queryText", "SELECT 1"); //$NON-NLS-1$ //$NON-NLS-2$

        String result = tool.execute(params);

        assertNotNull(result);
        assertTrue(result.contains("\"success\":false")); //$NON-NLS-1$
        assertTrue(result.contains("projectName is required")); //$NON-NLS-1$
    }

    @Test
    public void testExecuteMissingQueryText()
    {
        ValidateQueryTool tool = new ValidateQueryTool();

        Map<String, String> params = new HashMap<>();
        params.put("projectName", "AnyProject"); //$NON-NLS-1$ //$NON-NLS-2$

        String result = tool.execute(params);

        assertNotNull(result);
        assertTrue(result.contains("\"success\":false")); //$NON-NLS-1$
        assertTrue(result.contains("queryText is required")); //$NON-NLS-1$
    }
}
