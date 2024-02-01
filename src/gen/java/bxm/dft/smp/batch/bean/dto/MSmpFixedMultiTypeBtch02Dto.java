/**
 * BXM V5 - IO Java Source Generator
 */

package bxm.dft.smp.batch.bean.dto;

import bxm.common.annotaion.BxmCategory;
import bxm.omm.annotation.BxmOmm_Field;
import bxm.omm.exception.CloneFailedException;
import bxm.omm.predict.FieldInfo;
import bxm.omm.predict.Predictable;
import bxm.omm.root.IOmmObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 * FIXED MULTI TYPE 샘플 02 Dto(Header)
 */
@XmlType(propOrder={
	"fileType", "procDate"
}, name="MSmpFixedMultiTypeBtch02Dto")
@XmlRootElement(name="MSmpFixedMultiTypeBtch02Dto")
@BxmCategory(logicalName="", description="FIXED MULTI TYPE 샘플 02 Dto(Header)") 
public class MSmpFixedMultiTypeBtch02Dto implements IOmmObject, Predictable, FieldInfo
{
	private static final long serialVersionUID= -1409314800L;
	
	@XmlTransient
	public static final String OMM_DESCRIPTION= "FIXED MULTI TYPE 샘플 02 Dto(Header)";
	@XmlTransient
	public static final String OMM_VERSION= "";
	
	
	
	/**
	 * 파일유형
	 */
	@XmlElement
	@JsonProperty("fileType")
	@BxmOmm_Field(length=10, decimal=0, description="파일유형", align="left", fill="")
	private String fileType= "";
	
	
	/**
	 * 처리일자
	 */
	@XmlElement
	@JsonProperty("procDate")
	@BxmOmm_Field(length=8, decimal=0, description="처리일자", align="left", fill="")
	private String procDate= "";
	
	
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_fileType= false;
	protected final boolean isSet_fileType(){
		return this.isSet_fileType;
	}
	private void setIsSet_fileType(boolean value){
		this.isSet_fileType= value;
	}
	/**
	 * 파일유형
	 */
	@XmlTransient
	public String getFileType(){
		return this.fileType;
	}
	
	/**
	 * 파일유형
	 * 
	 * @param fileType 파일유형
	 */
	public void setFileType(String fileType){
		this.fileType= fileType;
		this.setIsSet_fileType(true);
	}
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_procDate= false;
	protected final boolean isSet_procDate(){
		return this.isSet_procDate;
	}
	private void setIsSet_procDate(boolean value){
		this.isSet_procDate= value;
	}
	/**
	 * 처리일자
	 */
	@XmlTransient
	public String getProcDate(){
		return this.procDate;
	}
	
	/**
	 * 처리일자
	 * 
	 * @param procDate 처리일자
	 */
	public void setProcDate(String procDate){
		this.procDate= procDate;
		this.setIsSet_procDate(true);
	}
	
	@Override
	public MSmpFixedMultiTypeBtch02Dto clone(){
		try{
			MSmpFixedMultiTypeBtch02Dto object= (MSmpFixedMultiTypeBtch02Dto)super.clone();
			return object;
		}
		catch(CloneNotSupportedException e){
			throw new CloneFailedException(e);
		}
	}
	
	@Override
	public int hashCode(){
		final int prime=31;
		int result= 1;
		
		result= prime * result + ((this.fileType==null)?0:this.fileType.hashCode());
		result= prime * result + ((this.procDate==null)?0:this.procDate.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this== obj ) return true;
		if ( obj== null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		
		final MSmpFixedMultiTypeBtch02Dto other= (MSmpFixedMultiTypeBtch02Dto)obj;
		{
			Object _fileType= getFileType();
			Object __fileType= other.getFileType();
			if ( _fileType== null ) { if ( __fileType!= null ) return false; }
			else if ( !_fileType.equals(__fileType) ) return false;
		}
		{
			Object _procDate= getProcDate();
			Object __procDate= other.getProcDate();
			if ( _procDate== null ) { if ( __procDate!= null ) return false; }
			else if ( !_procDate.equals(__procDate) ) return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	
		sb.append("\n[").append(MSmpFixedMultiTypeBtch02Dto.class.getName()).append(":\n");
		sb.append("\tfileType: ");
		sb.append(fileType==null?"null":getFileType());
		sb.append("\n");
		sb.append("\tprocDate: ");
		sb.append(procDate==null?"null":getProcDate());
		sb.append("\n");
		sb.append("]\n");
	
		return sb.toString();
	}
	
	@Override
	public long predictMessageLength(){
		long messageLen= 0;
		
		messageLen+= 10; /* fileType */
		messageLen+= 8; /* procDate */
	
		return messageLen;
	}
	
	@Override
	@JsonIgnore
	public List<String> getFieldNames(){
		List<String> list= new ArrayList<>();
	
		list.add("fileType");
		list.add("procDate");
	
		return list;
	}
	
	@Override
	@JsonIgnore
	public Map<String, Object> getFieldValues(){
		Map<String, Object> map= new LinkedHashMap<>();
	
		map.put("fileType", get("fileType"));
		map.put("procDate", get("procDate"));
	
		return map;
	}
	
	
	@Override
	public Object get(String key) throws IllegalArgumentException{
		switch( key.hashCode() ){
		case -735520042:/* fileType */
			return getFileType();
		case -1006146524:/* procDate */
			return getProcDate();
		default:
			throw new IllegalArgumentException("Field name not found. : " + key);
		}
	}
	
	@Override
	public void set(String key, Object value){
		switch( key.hashCode() ){
		case -735520042:/* fileType */
			setFileType((String)value);
			break;
		case -1006146524:/* procDate */
			setProcDate((String)value);
			break;
		default:
			break;
		}
	}
	
}
