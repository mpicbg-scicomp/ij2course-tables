package de.mpicbg.scf.rhaase.fiji.ij2course.resulttables;

import de.mpicbg.scf.rhaase.fiji.ij2course.resulttables.ResultsTablesMain;
import de.mpicbg.scf.rhaase.fiji.ij2course.resulttables.utilities.ResultsTableConverter;
import ij.measure.ResultsTable;
import net.imagej.ImageJ;
import net.imagej.table.GenericTable;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Author: Robert Haase, Scientific Computing Facility, MPI-CBG Dresden,
 * rhaase@mpi-cbg.de
 * Date: June 2017
 * <p>
 * Copyright 2017 Max Planck Institute of Molecular Cell Biology and Genetics,
 * Dresden, Germany
 * <p>
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holder nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
public class ResultsTableConverterTest {
    static double tolerance = 0.0001;

    @Test
    public void testIfConversionIsReversive() {
        ImageJ ij = new net.imagej.ImageJ();

        // create a test table
        ResultsTable table = ResultsTablesMain.createGoodOldTable();
        table.show("table");

        // convert from IJ1 to IJ2
        GenericTable convertedTable = ResultsTableConverter.convertIJ1toIJ2(table);
        ij.ui().show(convertedTable);

        // convert back from IJ2 to IJ1
        ResultsTable backConvertedTable = ResultsTableConverter.convertIJ2toIJ1(convertedTable);
        backConvertedTable.show("backConvertedTable");

        // check if conversion was reversible, no information should be lost
        for (int columnIndex = 0; table.columnExists(columnIndex); columnIndex++) {

            // check if double-converted table contains column as well
            assertTrue(backConvertedTable.columnExists(columnIndex));

            assertEquals(table.getColumnHeading(columnIndex), backConvertedTable.getColumnHeading(columnIndex));

            for (int rowIndex = 0; rowIndex < table.getCounter(); rowIndex++) {
                assertEquals(table.getValueAsDouble(columnIndex, rowIndex), backConvertedTable.getValueAsDouble(columnIndex, rowIndex), tolerance);
                assertEquals(table.getStringValue(columnIndex, rowIndex), backConvertedTable.getStringValue(columnIndex, rowIndex));
            }
        }
    }
}