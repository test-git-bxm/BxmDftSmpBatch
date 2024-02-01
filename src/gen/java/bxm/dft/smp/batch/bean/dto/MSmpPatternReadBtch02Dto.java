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
	"patternB"
}, name="MSmpPatternReadBtch02Dto")
@XmlRootElement(name="MSmpPatternReadBtch02Dto")
@BxmCategory(logicalName="", description="") 
public class MSmpPatternReadBtch02Dto implements IOmmObject, Predictable, FieldInfo
{
	private static final long serialVersionUID= 2004431607L;
	
	@XmlTransient
	public static final String OMM_DESCRIPTION= "";
	@XmlTransient
	public static final String OMM_VERSION= "";
	
	
	
	/**
	 * 
	 */
	@XmlElement
	@JsonProperty("patternB")
	@BxmOmm_Field(length=10, decimal=0, align="left", fill="")
	private String patternB= "";
	
	
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_patternB= false;
	protected final boolean isSet_patternB(){
		return this.isSet_patternB;
	}
	private void setIsSet_patternB(boolean value){
		this.isSet_patternB= value;
	}
	/**
	 * 
	 */
	@XmlTransient
	public String getPatternB(){
		return this.patternB;
	}
	
	/**
	 * 
	 * 
	 * @param patternB 
	 */
	public void setPatternB(String patternB){
		this.patternB= patternB;
		this.setIsSet_patternB(true);
	}
	
	@Override
	public MSmpPatternReadBtch02Dto clone(){
		try{
			MSmpPatternReadBtch02Dto object= (MSmpPatternReadBtch02Dto)super.clone();
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
		
		result= prime * result + ((this.patternB==null)?0:this.patternB.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this== obj ) return true;
		if ( obj== null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		
		final MSmpPatternReadBtch02Dto other= (MSmpPatternReadBtch02Dto)obj;
		{
			Object _patternB= getPatternB();
			Object __patternB= other.getPatternB();
			if ( _patternB== null ) { if ( __patternB!= null ) return false; }
			else if ( !_patternB.equals(__patternB) ) return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	
		sb.append("\n[").append(MSmpPatternReadBtch02Dto.class.getName()).append(":\n");
		sb.append("\tpatternB: ");
		sb.append(patternB==null?"null":getPatternB());
		sb.append("\n");
		sb.append("]\n");
	
		return sb.toString();
	}
	
	@Override
	public long predictMessageLength(){
		long messageLen= 0;
		
		messageLen+= 10; /* patternB */
	
		return messageLen;
	}
	
	@Override
	@JsonIgnore
	public List<String> getFieldNames(){
		List<String> list= new ArrayList<>();
	
		list.add("patternB");
	
		return list;
	}
	
	@Override
	@JsonIgnore
	public Map<String, Object> getFieldValues(){
		Map<String, Object> map= new LinkedHashMap<>();
	
		map.put("patternB", get("patternB"));
	
		return map;
	}
	
	
	@Override
	public Object get(String key) throws IllegalArgumentException{
		switch( key.hashCode() ){
		case 1246004914:/* patternB */
			return getPatternB();
		default:
			throw new IllegalArgumentException("Field name not found. : " + key);
		}
	}
	
	@Override
	public void set(String key, Object value){
		switch( key.hashCode() ){
		case 1246004914:/* patternB */
			setPatternB((String)value);
			break;
		default:
			break;
		}
	}
	
}
