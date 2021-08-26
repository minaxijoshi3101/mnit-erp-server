package com.mnit.erp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mnit.erp.response.CustomResponseMessage;

@Service
public class CommonUtils {
	
    @Value("${local-file-store}")
	private static String localFileStorage="/home/localfiles";

    public static CustomResponseMessage buildResponse(Object obj,String procMsg){
        ResponseMessageType responseMessageType = Objects.nonNull(obj) ? ResponseMessageType.SUCCESS : ResponseMessageType.ERROR;
        return CustomResponseMessage
        		.builder()
        		.message(procMsg)
                .messageType(responseMessageType)
                .response(obj).build();
    }
  
    @SuppressWarnings("unchecked")
	public static Map<String , Object> copyPropertiesForUpdate(Map<String , Object> sourceMap, Object destination){
    	ObjectMapper oMapper = new ObjectMapper();
    	Map<String , Object> destinationMap = oMapper.convertValue(destination, Map.class);
		for (String mapKey : sourceMap.keySet()) {
			destinationMap.put(mapKey,sourceMap.get(mapKey));
		}
		return destinationMap;   
    }

    /**
     * Copies properties from one object to another
     * @param source
     * @destination
     * @return
     */
    public static void copyNonNullProperties(Object source, Object destination){
        BeanUtils.copyProperties(source, destination,
                getNullPropertyNames(source));
    }

    /**
     * Returns an array of null properties of an object
     * @param source
     * @return
     */
    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set emptyNames = new HashSet();
        for(java.beans.PropertyDescriptor pd : pds) {
            //check if value of this property is null then add it to the collection
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return (String[]) emptyNames.toArray(result);
    }
    
    public static String saveFile(String filePath,MultipartFile file) {
		String fileName = "";
    	try {
    		filePath = localFileStorage + "/" + filePath;
    		Path path = Paths.get(filePath);

    		try {
    		    Files.createDirectories(path);
    		} catch (IOException e) {
    		    System.err.println("Cannot create directories - " + e);
    		}
    		
    		if (file != null && file.getBytes().length > 0) {
        		fileName =  filePath + "/" + StringUtils.cleanPath(file.getOriginalFilename());
        		File file2Write = new File(fileName);
        		OutputStream os = new FileOutputStream(file2Write);
        		os.write(file.getBytes());
        		os.close();
    		}
		} catch (IOException ex) {
			ex.printStackTrace();
			fileName="";
		}catch(Exception ex) {
			ex.printStackTrace();
			fileName="";
		}
    	return fileName;
    }
    
	public static String readFileAsBase64(String filePath) throws IOException {
		File file = new File(filePath);
	    byte[] encoded = Base64.encodeBase64(FileUtils.readFileToByteArray(file));
	    return new String(encoded);
    }
}
