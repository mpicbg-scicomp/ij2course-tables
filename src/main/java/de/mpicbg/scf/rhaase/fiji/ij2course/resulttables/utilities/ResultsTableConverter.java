package de.mpicbg.scf.rhaase.fiji.ij2course.resulttables.utilities;

import ij.measure.ResultsTable;
import net.imagej.table.*;

/**
 * Author: Robert Haase, Scientific Computing Facility, MPI-CBG Dresden, rhaase@mpi-cbg.de
 * Date: July 2016
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
public class ResultsTableConverter {
    public static ResultsTable convertIJ2toIJ1(GenericTable tableIn) {
        ResultsTable tableOut = new ResultsTable();

        return tableOut;
    }

    public static DefaultGenericTable convertIJ1toIJ2(ResultsTable tableIn) {
        DefaultGenericTable table = new DefaultGenericTable();

        for (int columnIndex = 0; tableIn.columnExists(columnIndex); columnIndex++) {
            // read header of a column
            String header = tableIn.getColumnHeading(columnIndex);

            // determine types of column
            Column column;
            if (!Double.isNaN(tableIn.getValueAsDouble(columnIndex, 0))){
                column = new DoubleColumn(header);
            } else {
                column = new GenericColumn(header);
            }

            // copy column to the new table
            for (int rowIndex = 0; rowIndex < tableIn.getCounter(); rowIndex++) {
                if (column instanceof GenericColumn) {
                    String value = tableIn.getStringValue(columnIndex, rowIndex);
                    column.add(value);
                } else {
                    double value = tableIn.getValueAsDouble(columnIndex, rowIndex);
                    column.add(value);
                }
            }
            table.add(column);
        }
        return table;
    }
}
