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
 * 
 */
@XmlType(propOrder={
	"patternC"
}, name="MSmpPatternReadBtch03Dto")
@XmlRootElement(name="MSmpPatternReadBtch03Dto")
@BxmCategory(logicalName="", description="") 
public class MSmpPatternReadBtch03Dto implements IOmmObject, Predictable, FieldInfo
{
	private static final long serialVersionUID= 2004461398L;
	
	@XmlTransient
	public static final String OMM_DESCRIPTION= "";
	@XmlTransient
	public static final String OMM_VERSION= "";
	
	
	
	/**
	 * 
	 */
	@XmlElement
	@JsonProperty("patternC")
	@BxmOmm_Field(length=10, decimal=0, align="left", fill="")
	private String patternC= "";
	
	
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_patternC= false;
	protected final boolean isSet_patternC(){
		return this.isSet_patternC;
	}
	private void setIsSet_patternC(boolean value){
		this.isSet_patternC= value;
	}
	/**
	 * 
	 */
	@XmlTransient
	public String getPatternC(){
		return this.patternC;
	}
	
	/**
	 * 
	 * 
	 * @param patternC 
	 */
	public void setPatternC(String patternC){
		this.patternC= patternC;
		this.setIsSet_patternC(true);
	}
	
	@Override
	public MSmpPatternReadBtch03Dto clone(){
		try{
			MSmpPatternReadBtch03Dto object= (MSmpPatternReadBtch03Dto)super.clone();
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
		
		result= prime * result + ((this.patternC==null)?0:this.patternC.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this== obj ) return true;
		if ( obj== null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		
		final MSmpPatternReadBtch03Dto other= (MSmpPatternReadBtch03Dto)obj;
		{
			Object _patternC= getPatternC();
			Object __patternC= other.getPatternC();
			if ( _patternC== null ) { if ( __patternC!= null ) return false; }
			else if ( !_patternC.equals(__patternC) ) return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	
		sb.append("\n[").append(MSmpPatternReadBtch03Dto.class.getName()).append(":\n");
		sb.append("\tpatternC: ");
		sb.append(patternC==null?"null":getPatternC());
		sb.append("\n");
		sb.append("]\n");
	
		return sb.toString();
	}
	
	@Override
	public long predictMessageLength(){
		long messageLen= 0;
		
		messageLen+= 10; /* patternC */
	
		return messageLen;
	}
	
	@Override
	@JsonIgnore
	public List<String> getFieldNames(){
		List<String> list= new ArrayList<>();
	
		list.add("patternC");
	
		return list;
	}
	
	@Override
	@JsonIgnore
	public Map<String, Object> getFieldValues(){
		Map<String, Object> map= new LinkedHashMap<>();
	
		map.put("patternC", get("patternC"));
	
		return map;
	}
	
	
	@Override
	public Object get(String key) throws IllegalArgumentException{
		switch( key.hashCode() ){
		case 1246004915:/* patternC */
			return getPatternC();
		default:
			throw new IllegalArgumentException("Field name not found. : " + key);
		}
	}
	
	@Override
	public void set(String key, Object value){
		switch( key.hashCode() ){
		case 1246004915:/* patternC */
			setPatternC((String)value);
			break;
		default:
			break;
		}
	}
	
}
