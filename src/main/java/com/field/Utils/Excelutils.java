package com.field.Utils;

import com.field.Constants.Constants;
import com.field.Exceptions.FrameWorkExceptions;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The {@code Excelutils} class provides utility methods for reading data from Excel files.
 * <p>This class includes methods to read test details from a specified sheet in an Excel workbook.
 * It uses Apache POI library to handle Excel file operations.</p>
 * 
 * @author Ezhilraj
 * @version 1.0
 * @since 1.0
 */
public final class Excelutils {

    private static final Logger logger = Logger.getLogger(Excelutils.class);
    private static String currentSheetName = "";

    // Private constructor to prevent instantiation
    private Excelutils() {
        logger.debug("Excelutils instance created.");
    }

    /**
     * Retrieves test details from a specified sheet in the Excel file.
     * <p>Reads the data from the specified sheet and returns it as a list of maps,
     * where each map represents a row with column headers as keys.</p>
     *
     * @param sheetname the name of the sheet from which data is to be read.
     * @return a list of maps where each map contains key-value pairs of column headers and cell values.
     * @throws FileNotFoundException if the Excel file is not found.
     * @throws FrameWorkExceptions if there is an I/O exception while reading the file.
     */
    public static List<Map<String, String>> getTestDetails(String sheetname) throws FileNotFoundException {
        logger.info("Reading test details from sheet: " + sheetname);

        List<Map<String, String>> list = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(Constants.getExceldatafilepath())) {
            logger.debug("Excel file found at path: " + Constants.getExceldatafilepath());
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(sheetname);
            if (sheet == null) {
                logger.error("Sheet " + sheetname + " not found in the Excel file.");
                throw new IllegalArgumentException("Sheet " + sheetname + " not found in the Excel file.");
            }

            currentSheetName = sheetname;
            logger.info("Current sheet set to: " + currentSheetName);

            int lastrownum = sheet.getLastRowNum();
            int lastcolnum = sheet.getRow(0).getLastCellNum();
            logger.debug("Number of rows: " + lastrownum + ", Number of columns: " + lastcolnum);

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                logger.error("The header row is missing in the sheet: " + sheetname);
                throw new IllegalArgumentException("The header row is missing in the sheet: " + sheetname);
            }

            for (int i = 1; i <= lastrownum; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    logger.debug("Skipping empty row at index: " + i);
                    continue; // Skip empty rows
                }

                Map<String, String> map = new HashMap<>();
                for (int j = 0; j < lastcolnum; j++) {
                    Cell keyCell = headerRow.getCell(j);
                    Cell valueCell = row.getCell(j);

                    if (keyCell == null || valueCell == null) {
                        logger.debug("Skipping empty cell at row: " + i + ", column: " + j);
                        continue;
                    }

                    String key = keyCell.getStringCellValue().trim();
                    String value = valueCell.getCellType() == Cell.CELL_TYPE_STRING ?
                            valueCell.getStringCellValue().trim() :
                            String.valueOf(valueCell.getNumericCellValue());
                    map.put(key, value);
                }
                logger.debug("Row " + i + " processed: " + map);
                list.add(map);
            }

        } catch (IOException e) {
            logger.error("An I/O exception occurred while reading the Excel file.", e);
            throw new FrameWorkExceptions("An I/O exception occurred while reading the Excel file -->", e);
        } catch (NullPointerException e) {
            logger.error("A null pointer exception occurred, likely due to missing or unexpected data in the Excel sheet.", e);
            throw new FrameWorkExceptions("A null pointer exception occurred, likely due to missing or unexpected data in the Excel sheet -->", e);
        }

        logger.info("Completed reading test details from sheet: " + sheetname);
        return list;
    }

    public static String getCurrentSheetName() {
        logger.info("Getting current sheet name: " + currentSheetName);
        return currentSheetName;
    }
}

