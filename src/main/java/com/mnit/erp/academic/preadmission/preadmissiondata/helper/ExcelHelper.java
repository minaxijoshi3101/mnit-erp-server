package com.mnit.erp.academic.preadmission.preadmissiondata.helper;

import static org.apache.poi.ss.usermodel.CellType.BLANK;
import static org.apache.poi.ss.usermodel.CellType.NUMERIC;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mnit.erp.academic.admissionType.model.AdmissionType;
import com.mnit.erp.academic.admissionType.repository.AdmissionTypeRepository;
import com.mnit.erp.academic.branch.model.Branch;
import com.mnit.erp.academic.branch.repository.BranchRepository;
import com.mnit.erp.academic.degree.model.Degree;
import com.mnit.erp.academic.degree.repository.DegreeRepository;
import com.mnit.erp.academic.preadmission.preadmissiondata.model.PreAdmissionData;
import com.mnit.erp.academic.preadmission.preadmissiondata.repository.PreAdmissionDataRepository;
import com.mnit.erp.academic.selectionboard.model.SelectionBoard;
import com.mnit.erp.academic.specialization.model.Specialization;
import com.mnit.erp.academic.specialization.repository.SpecializationRepository;
import com.mnit.erp.category.model.Category;
import com.mnit.erp.category.repository.CategoryRepository;
import com.mnit.erp.common.Gender;
import com.mnit.erp.common.Pwd;
import com.mnit.erp.exceptions.ServiceException;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author praha
 */
@Slf4j
@Component
public class ExcelHelper {  
    
    private static CategoryRepository categoryRepository;
    private static BranchRepository branchRepository;
    private static DegreeRepository degreeRepository;
    private static SpecializationRepository specializationRepository;
    private static AdmissionTypeRepository admissionTypeRepository;
    private static PreAdmissionDataRepository preAdmissionDataRepository;
    
    @Autowired
    private ExcelHelper(CategoryRepository categoryRepo, BranchRepository branchRepo, 
            DegreeRepository degreeRepo, SpecializationRepository specializationRepo, AdmissionTypeRepository admissionTypeRepo, 
            PreAdmissionDataRepository preAdmissionDataRepo
    ) {
        ExcelHelper.categoryRepository = categoryRepo;
        ExcelHelper.branchRepository = branchRepo;
        ExcelHelper.degreeRepository = degreeRepo;
        ExcelHelper.specializationRepository = specializationRepo;
        ExcelHelper.admissionTypeRepository = admissionTypeRepo;
        ExcelHelper.preAdmissionDataRepository = preAdmissionDataRepo;
    }  
    
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    //static String[] HEADERs = { "Id", "Title", "Description", "Published" };
    static String SHEET = "PreAdmissionData";
    
    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
          return false;
        }
        return true;
    }
    
    public static boolean isValidRow(SelectionBoard sb, int year, long rollno, String dob) {
        PreAdmissionData preAdmissionDataObj = new PreAdmissionData();
        preAdmissionDataObj = preAdmissionDataRepository.findBySelectionBoardAndYearAndRollNoAndDob(sb, year, rollno,dob);
        
        return Objects.isNull(preAdmissionDataObj);
    }
    
    public static List<PreAdmissionData> excelToPreAdmissionData(InputStream is, SelectionBoard sb) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            
            List<PreAdmissionData> preAdmissionDataList = new ArrayList<>();
            
            int cellCount = 0;

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    cellCount = currentRow.getLastCellNum();
                    rowNumber++;
                    continue;
                }
                
                //Iterator<Cell> cellsInRow = currentRow.iterator();

                PreAdmissionData preAdmissionData = new PreAdmissionData();

                int cellIdx = 0;
                //while (cellsInRow.hasNext()) {
                while (cellIdx < cellCount) {
                    //Cell currentCell = cellsInRow.next();
                    Cell currentCell = currentRow.getCell(cellIdx);                    
                    switch (cellIdx) {
                        case 0:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank Exam Year at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank Exam Year at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() != NUMERIC ){
                                throw new ServiceException("Invalid format of Exam Year at row number <"+rowNumber+">.");
                            }
                            preAdmissionData.setExamYear((int)currentCell.getNumericCellValue());
                            break;
                        case 1:
                            if( Objects.isNull(currentCell) ){
                                preAdmissionData.setExamType(null);
                            }
                            else{
                                if( currentCell.getCellType() == BLANK ){
                                    preAdmissionData.setExamType(null);
                                }
                                else{
                                    preAdmissionData.setExamType(currentCell.getStringCellValue().trim());
                                }
                            }
                            break;
                        case 2:
                            if( Objects.isNull(currentCell) ){
                                preAdmissionData.setProgram(null);
                            }
                            else{
                                if( currentCell.getCellType() == BLANK ){
                                    preAdmissionData.setProgram(null);
                                }
                                else{
                                    preAdmissionData.setProgram(currentCell.getStringCellValue().trim());
                                }
                            }
                            break;
                        case 3:
                            preAdmissionData.setSelectionBoard(sb);
                            break;
                        case 4:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank RollNo at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank RollNo at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() != NUMERIC ){
                                throw new ServiceException("Invalid format of Rollno at row number <"+rowNumber+">.");
                            }   
                            
//                            preAdmissionData.setRollno((long) currentCell.getNumericCellValue());
                            preAdmissionData.setRollNo((long) currentCell.getNumericCellValue());
                        break;
                        case 5:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank Name at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank Name at row number <"+rowNumber+"> is not allowed.");
                            }
                            preAdmissionData.setName(currentCell.getStringCellValue().trim());
                        break;
                        case 6:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank Father Name at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank Father Name at row number <"+rowNumber+"> is not allowed.");
                            }
                            preAdmissionData.setFatherName(currentCell.getStringCellValue().trim());
                        break;
                        case 7:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank Mother Name at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank Mother Name at row number <"+rowNumber+"> is not allowed.");
                            }
                            preAdmissionData.setMotherName(currentCell.getStringCellValue().trim());
                        break;
                        case 8:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank DoB at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank DoB at row number <"+rowNumber+"> is not allowed.");
                            }
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                            Date dob = currentCell.getDateCellValue();
                            String dobFormatted = df.format(dob);
                            preAdmissionData.setDob(dobFormatted);
                        break;
                        case 9:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank Gender at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank Gender at row number <"+rowNumber+"> is not allowed.");
                            }
                            
                            if( currentCell.getStringCellValue().trim().equalsIgnoreCase(Gender.FEMALE.toString()) ){
                                preAdmissionData.setGender(Gender.FEMALE); 
                            }
                            else if( currentCell.getStringCellValue().trim().equalsIgnoreCase(Gender.MALE.toString()) ){
                                preAdmissionData.setGender(Gender.MALE); 
                            }
                            else if( currentCell.getStringCellValue().trim().equalsIgnoreCase(Gender.OTHER.toString()) ){
                                preAdmissionData.setGender(Gender.OTHER); 
                            }
                            else{
                                throw new ServiceException("Gender at row number <"+rowNumber+ "> not found.");
                            }
                        break;
                        case 10:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank Category at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank Category at row number <"+rowNumber+"> is not allowed.");
                            }
                            
                            preAdmissionData.setCategoryValue(currentCell.getStringCellValue().trim());                            
                            Category category = ExcelHelper.categoryRepository.findByAbbreviation(currentCell.getStringCellValue().trim());
                            if(Objects.isNull(category)){
                                throw new ServiceException("Category at row number <" +rowNumber+ "> not found.");
                            }
                            else{
                                preAdmissionData.setCategory(category);
                            } 
                        break;
                        case 11:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank PwD at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank PwD at row number <"+rowNumber+"> is not allowed.");
                            }
                            
                            if( currentCell.getStringCellValue().trim().equalsIgnoreCase(Pwd.YES.toString()) ){
                                preAdmissionData.setPwd(Pwd.YES); 
                            }
                            else if( currentCell.getStringCellValue().trim().equalsIgnoreCase(Pwd.NO.toString()) ){
                                preAdmissionData.setPwd(Pwd.NO); 
                            }
                            else{
                                throw new ServiceException("PwD at row number <"+rowNumber+ "> not found.");
                            }
                        break;
                        case 12:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank AdmitCategory at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank AdmitCategory at row number <"+rowNumber+"> is not allowed.");
                            }
                            
                            preAdmissionData.setAdmitCategoryValue(currentCell.getStringCellValue().trim());                            
                            Category admitcategory = ExcelHelper.categoryRepository.findByAbbreviation(currentCell.getStringCellValue().trim());                            
                            if(Objects.isNull(admitcategory)){
                                throw new ServiceException("Admit Category at row number <"+rowNumber+"> not found.");
                            }
                            else{
                                preAdmissionData.setAdmitCategory(admitcategory);
                            } 
                        break;
                        case 13:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank AdmitPwD at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank AdmitPwD at row number <"+rowNumber+"> is not allowed.");
                            }
                            
                            if( currentCell.getStringCellValue().trim().equalsIgnoreCase(Pwd.YES.toString()) ){
                                preAdmissionData.setAdmitPwd(Pwd.YES); 
                            }
                            else if( currentCell.getStringCellValue().trim().equalsIgnoreCase(Pwd.NO.toString()) ){
                                preAdmissionData.setAdmitPwd(Pwd.NO); 
                            }
                            else{
                                throw new ServiceException("Admit PwD at row number <"+rowNumber+ "> not found.");
                            }
                        break;
                        case 14:
                            if( Objects.isNull(currentCell) ){
                                preAdmissionData.setAdmitQuota(null);
                            }
                            else{
                                if( currentCell.getCellType() == BLANK ){
                                    preAdmissionData.setAdmitQuota(null);
                                }
                                else{
                                    preAdmissionData.setAdmitQuota(currentCell.getStringCellValue().trim());
                                }
                            }
                        break;
                        case 15:
                            if( Objects.isNull(currentCell) ){
                                preAdmissionData.setBranch(null);
                                preAdmissionData.setBranchValue(null);
                            }
                            else{
                                if( currentCell.getCellType() == BLANK ){
                                    preAdmissionData.setBranch(null);
                                    preAdmissionData.setBranchValue(null);
                                }
                                else{                            
                                    preAdmissionData.setBranchValue(currentCell.getStringCellValue().trim());                            
                                    Branch branch = ExcelHelper.branchRepository.findByName(currentCell.getStringCellValue().trim());
                                    if(Objects.isNull(branch)){
                                        throw new ServiceException("Branch at row number <"+rowNumber+"> not found.");
                                    }
                                    else{
                                        preAdmissionData.setBranch(branch);
                                    }  
                                } 
                            }                       
                        break;
                        case 16:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank Degree at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank Degree at row number <"+rowNumber+"> is not allowed.");
                            }
                            
                            preAdmissionData.setDegreeValue(currentCell.getStringCellValue().trim());                            
                            Degree degree = ExcelHelper.degreeRepository.findByAbbreviation(currentCell.getStringCellValue().trim());
                            if(Objects.isNull(degree)){
                                throw new ServiceException("Degree at row number <"+rowNumber+"> not found.");
                            }
                            else{
                               preAdmissionData.setDegree(degree);
                            }
                        break;
                        case 17:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank Department at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank Department at row number <"+rowNumber+"> is not allowed.");
                            }

                            preAdmissionData.setDepartment(currentCell.getStringCellValue().trim());

                            break;
                        case 18:
                            if( Objects.isNull(currentCell) ){
                                preAdmissionData.setSpecialization(null);
                                preAdmissionData.setSpecializationValue(null);
                            }
                            else{
                                if( currentCell.getCellType() == BLANK ){
                                    preAdmissionData.setSpecialization(null);
                                    preAdmissionData.setSpecializationValue(null); 
                                }
                                else{                                                       
                                    preAdmissionData.setSpecializationValue(currentCell.getStringCellValue().trim());                            
                                    Specialization specialization = ExcelHelper.specializationRepository.findByName(currentCell.getStringCellValue().trim());
                                    if(Objects.isNull(specialization)){
                                        throw new ServiceException("Specialization at row number <"+rowNumber+"> not found.");
                                    }
                                    else{
                                        preAdmissionData.setSpecialization(specialization);
                                    }
                                }
                            }
                        break;
                        case 19:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank Admission Type at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank Admission Type at row number <"+rowNumber+"> is not allowed.");
                            }
                            
                            preAdmissionData.setAdmissionTypeValue(currentCell.getStringCellValue().trim());                            
                            AdmissionType admissiontype = ExcelHelper.admissionTypeRepository.findByName(currentCell.getStringCellValue().trim());
                            if(Objects.isNull(admissiontype)){
                                throw new ServiceException("Admission type at row number <"+rowNumber+"> not found.");
                            }
                            else{
                                preAdmissionData.setAdmissionType(admissiontype);
                            }
                        break;
                        case 20:
                            if( Objects.isNull(currentCell) ){
                                preAdmissionData.setSpecialization(null);
                            }
                            else{
                                if( currentCell.getCellType() == BLANK ){
                                    preAdmissionData.setSpecialization(null);
                                }
                                else{
                                    preAdmissionData.setSponsoredBy(currentCell.getStringCellValue().trim());
                                }
                            }
                        break;
                        case 21:
                            if( Objects.isNull(currentCell) ){
                                preAdmissionData.setAir(null);
                            }
                            else{
                                if( currentCell.getCellType() == BLANK ){
                                    preAdmissionData.setAir(null);
                                }
                                else{
                                    if( currentCell.getCellType() != NUMERIC ){
                                        throw new ServiceException("Invalid format of AIR at row number <"+rowNumber+">.");                                    
                                    }
                                    Long airval = (long) currentCell.getNumericCellValue();
                                    preAdmissionData.setAir(airval);
                                }
                            }
                            break;
                        case 22:
                            if( Objects.isNull(currentCell) ){
                                preAdmissionData.setScore(null);
                            }
                            else{
                                if( currentCell.getCellType() == BLANK ){
                                    preAdmissionData.setScore(null);
                                }
                                else{
                                    if( currentCell.getCellType() != NUMERIC ){
                                        throw new ServiceException("Invalid format of Score at row number <"+rowNumber+">.");                                    
                                    }
                                    Double scoreval = currentCell.getNumericCellValue();
                                    preAdmissionData.setScore(scoreval);   
                                } 
                            }                        
                        break;
                        case 23:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank Year at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank Year at row number <"+rowNumber+"> is not allowed.");
                            }
                            else{
                                if( currentCell.getCellType() != NUMERIC ){
                                    throw new ServiceException("Invalid format of Year at row number <"+rowNumber+">.");                                    
                                }
                                int yearVal = (int) currentCell.getNumericCellValue();
                                preAdmissionData.setYear(yearVal);   
                            }                         
                        break;
                        case 24:
                            if( Objects.isNull(currentCell) ){
                                preAdmissionData.setEmail(null);
                            }
                            else{
                                if( currentCell.getCellType() == BLANK ){
                                    preAdmissionData.setEmail(null);
                                }
                                else{
                                    preAdmissionData.setEmail(currentCell.getStringCellValue().trim());
                                }
                            }
                            break;
                        case 25:
                            if( Objects.isNull(currentCell) ){
                                throw new ServiceException("Blank AdmitPwD at row number <"+rowNumber+"> is not allowed.");
                            }
                            if( currentCell.getCellType() == BLANK ){
                                throw new ServiceException("Blank AdmitPwD at row number <"+rowNumber+"> is not allowed.");
                            }
                            else{
                                if ( currentCell.getCellType() != NUMERIC ){
                                        throw new ServiceException("Invalid format of Mobile Number at row number <"+rowNumber+">.");
                                }

                                preAdmissionData.setMobile((long)currentCell.getNumericCellValue());
                            }
                            break;
                        case 26:
                            if( Objects.isNull(currentCell) ){
                                preAdmissionData.setFeePayment(null);
                            }
                            else{
                                if( currentCell.getCellType() == BLANK ){
                                    preAdmissionData.setFeePayment(null);
                                }
                                else{
                                    preAdmissionData.setFeePayment(currentCell.getStringCellValue().trim());
                                }
                            }
                            break;
                        default:
                        break;
                    }
                    cellIdx++;
                }
                if( isValidRow(preAdmissionData.getSelectionBoard(), preAdmissionData.getYear(), preAdmissionData.getRollNo(), preAdmissionData.getDob()) ){
                    preAdmissionDataList.add(preAdmissionData);
                } 
                else {
                    throw new ServiceException("Duplicate entry at row number <"+rowNumber+"> is not allowed.");
                }  
                ++rowNumber;
            }
            workbook.close();
            return preAdmissionDataList;
        } 
        catch (IOException e) {
        	log.error("",e);
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }catch (Exception e) {
        	log.error("",e);
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}