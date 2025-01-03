import org.apache.poi.ss.usermodel.WorkbookFactory
import com.google.gson.Gson
import java.io.File
import java.io.FileInputStream

fun convertExcelToJson(excelFilePath: String, jsonFilePath: String) {
    // Open the Excel file
    val fis = FileInputStream(excelFilePath)
    val workbook = WorkbookFactory.create(fis)

    val sheet = workbook.getSheetAt(0)  // Read the first sheet

    val data = mutableListOf<Map<String, String>>()
    val headerRow = sheet.getRow(0)
    val headers = (0 until headerRow.physicalNumberOfCells).map { headerRow.getCell(it).stringCellValue }

    // Loop through rows and collect key-value pairs
    for (rowIndex in 1..sheet.lastRowNum) {
        val row = sheet.getRow(rowIndex)
        val rowData = headers.associateWith { header ->
            val cell = row.getCell(headers.indexOf(header))
            cell?.toString() ?: ""
        }
        data.add(rowData)
    }

    // Convert to JSON
    val json = Gson().toJson(data)

    // Write to file
    File(jsonFilePath).writeText(json)
}

// Usage:
convertExcelToJson("localization.xlsx", "localization.json")
