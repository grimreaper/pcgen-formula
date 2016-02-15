/*
 * Copyright 2014 (C) Tom Parker <thpr@users.sourceforge.net>
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package pcgen.base.formula.library;

import org.junit.Test;

import pcgen.base.formula.library.ValueFunction;
import pcgen.base.formula.parse.SimpleNode;
import pcgen.base.formula.visitor.ReconstructionVisitor;
import pcgen.base.testsupport.AbstractFormulaTestCase;
import pcgen.base.testsupport.TestUtilities;

public class ValueFunctionTest extends AbstractFormulaTestCase
{

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		getFunctionLibrary().addFunction(new ValueFunction(4));
	}

	@Test
	public void testInvalidTooManyArg()
	{
		String formula = "value(2)";
		SimpleNode node = TestUtilities.doParse(formula);
		isNotValid(formula, node, numberManager);
	}

	@Test
	public void testValue()
	{
		String formula = "value()";
		SimpleNode node = TestUtilities.doParse(formula);
		isValid(formula, node, numberManager);
		isStatic(formula, node, true);
		evaluatesTo(formula, node, Integer.valueOf(4));
		Object rv =
				new ReconstructionVisitor().visit(node, new StringBuilder());
		assertTrue(rv.toString().equals(formula));
	}
}
