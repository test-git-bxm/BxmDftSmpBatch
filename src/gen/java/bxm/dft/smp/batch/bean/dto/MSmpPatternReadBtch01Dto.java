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
	"patternA"
}, name="MSmpPatternReadBtch01Dto")
@XmlRootElement(name="MSmpPatternReadBtch01Dto")
@BxmCategory(logicalName="", description="") 
public class MSmpPatternReadBtch01Dto implements IOmmObject, Predictable, FieldInfo
{
	private static final long serialVersionUID= 2004401816L;
	
	@XmlTransient
	public static final String OMM_DESCRIPTION= "";
	@XmlTransient
	public static final String OMM_VERSION= "";
	
	
	
	/**
	 * 
	 */
	@XmlElement
	@JsonProperty("patternA")
	@BxmOmm_Field(length=10, decimal=0, align="left", fill="")
	private String patternA= "";
	
	
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_patternA= false;
	protected final boolean isSet_patternA(){
		return this.isSet_patternA;
	}
	private void setIsSet_patternA(boolean value){
		this.isSet_patternA= value;
	}
	/**
	 * 
	 */
	@XmlTransient
	public String getPatternA(){
		return this.patternA;
	}
	
	/**
	 * 
	 * 
	 * @param patternA 
	 */
	public void setPatternA(String patternA){
		this.patternA= patternA;
		this.setIsSet_patternA(true);
	}
	
	@Override
	public MSmpPatternReadBtch01Dto clone(){
		try{
			MSmpPatternReadBtch01Dto object= (MSmpPatternReadBtch01Dto)super.clone();
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
		
		result= prime * result + ((this.patternA==null)?0:this.patternA.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this== obj ) return true;
		if ( obj== null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		
		final MSmpPatternReadBtch01Dto other= (MSmpPatternReadBtch01Dto)obj;
		{
			Object _patternA= getPatternA();
			Object __patternA= other.getPatternA();
			if ( _patternA== null ) { if ( __patternA!= null ) return false; }
			else if ( !_patternA.equals(__patternA) ) return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	
		sb.append("\n[").append(MSmpPatternReadBtch01Dto.class.getName()).append(":\n");
		sb.append("\tpatternA: ");
		sb.append(patternA==null?"null":getPatternA());
		sb.append("\n");
		sb.append("]\n");
	
		return sb.toString();
	}
	
	@Override
	public long predictMessageLength(){
		long messageLen= 0;
		
		messageLen+= 10; /* patternA */
	
		return messageLen;
	}
	
	@Override
	@JsonIgnore
	public List<String> getFieldNames(){
		List<String> list= new ArrayList<>();
	
		list.add("patternA");
	
		return list;
	}
	
	@Override
	@JsonIgnore
	public Map<String, Object> getFieldValues(){
		Map<String, Object> map= new LinkedHashMap<>();
	
		map.put("patternA", get("patternA"));
	
		return map;
	}
	
	
	@Override
	public Object get(String key) throws IllegalArgumentException{
		switch( key.hashCode() ){
		case 1246004913:/* patternA */
			return getPatternA();
		default:
			throw new IllegalArgumentException("Field name not found. : " + key);
		}
	}
	
	@Override
	public void set(String key, Object value){
		switch( key.hashCode() ){
		case 1246004913:/* patternA */
			setPatternA((String)value);
			break;
		default:
			break;
		}
	}
	
}
