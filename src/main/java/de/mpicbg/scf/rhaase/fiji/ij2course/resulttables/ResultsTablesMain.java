package de.mpicbg.scf.rhaase.fiji.ij2course.resulttables;

import de.mpicbg.scf.rhaase.fiji.ij2course.resulttables.utilities.ResultsTableConverter;
import ij.measure.ResultsTable;
import net.imagej.ImageJ;
import net.imagej.table.DefaultGenericTable;
import net.imagej.table.DoubleColumn;
import net.imagej.table.GenericColumn;
import net.imagej.table.GenericTable;

/**
 * Author: Robert Haase, Scientific Computing Facility, MPI-CBG Dresden,
 * rhaase@mpi-cbg.de
 * Date: May 2017
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
public class ResultsTablesMain {

    public static void main(String... args) {
        GenericTable table = createTable();

        ImageJ ij2 = new net.imagej.ImageJ();
        ij2.ui().show(table);

        new ij.ImageJ();

        // create and show a test table
        ResultsTable goodOldTable = ResultsTableConverter.convertIJ2toIJ1(table);
        goodOldTable.show("Results");

        // read out something from that table
        String firstTownInTable = goodOldTable.getStringValue("Town", 0);

        System.out.print("First town in table is: " + firstTownInTable);

        // save the table to disc
        goodOldTable.save("output.csv");
    }

    static ResultsTable createGoodOldTable() {
        ResultsTable table = new ResultsTable();

        // we fill the table row by row with information about the largest towns in the world.
        table.incrementCounter();
        table.addValue("Town", "Shanghai");
        table.addValue("Population", 24256800.0);

        table.incrementCounter();
        table.addValue("Town","Karachi");
        table.addValue("Population", 23500000.0);

        table.incrementCounter();
        table.addValue("Town","Bejing");
        table.addValue("Population",21516000.0);

        table.incrementCounter();
        table.addValue("Town","Sao Paolo");
        table.addValue("Population",21292893.0);

        return table;
    }

    static GenericTable createTable() {
        // we create two columns
        GenericColumn nameColumn = new GenericColumn("Town");
        DoubleColumn populationColumn = new DoubleColumn("Population");

        // we fill the columns with information about the largest towns in the world.
        nameColumn.add("Karachi");
        populationColumn.add(23500000.0);

        nameColumn.add("Bejing");
        populationColumn.add(21516000.0);

        nameColumn.add("Sao Paolo");
        populationColumn.add(21292893.0);

        // but actually, the largest town is Shanghai,
        // so let's add it at the beginning of the table.
        nameColumn.add(0, "Shanghai");
        populationColumn.add(0, 24256800.0);

        // After filling the columns, you can create a table
        GenericTable table = new DefaultGenericTable();

        // and add the columns to that table
        table.add(nameColumn);
        table.add(populationColumn);

        return table;
    }
}
