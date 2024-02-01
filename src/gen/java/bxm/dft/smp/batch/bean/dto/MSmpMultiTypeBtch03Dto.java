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
	"dummy", "dummy1"
}, name="MSmpMultiTypeBtch03Dto")
@XmlRootElement(name="MSmpMultiTypeBtch03Dto")
@BxmCategory(logicalName="", description="") 
public class MSmpMultiTypeBtch03Dto implements IOmmObject, Predictable, FieldInfo
{
	private static final long serialVersionUID= 366128361L;
	
	@XmlTransient
	public static final String OMM_DESCRIPTION= "";
	@XmlTransient
	public static final String OMM_VERSION= "";
	
	
	
	/**
	 * 
	 */
	@XmlElement
	@JsonProperty("dummy")
	@BxmOmm_Field(length=10, decimal=0, align="left", fill="")
	private String dummy= "";
	
	
	/**
	 * 
	 */
	@XmlElement
	@JsonProperty("dummy1")
	@BxmOmm_Field(length=10, decimal=0, align="left", fill="")
	private String dummy1= "";
	
	
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_dummy= false;
	protected final boolean isSet_dummy(){
		return this.isSet_dummy;
	}
	private void setIsSet_dummy(boolean value){
		this.isSet_dummy= value;
	}
	/**
	 * 
	 */
	@XmlTransient
	public String getDummy(){
		return this.dummy;
	}
	
	/**
	 * 
	 * 
	 * @param dummy 
	 */
	public void setDummy(String dummy){
		this.dummy= dummy;
		this.setIsSet_dummy(true);
	}
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_dummy1= false;
	protected final boolean isSet_dummy1(){
		return this.isSet_dummy1;
	}
	private void setIsSet_dummy1(boolean value){
		this.isSet_dummy1= value;
	}
	/**
	 * 
	 */
	@XmlTransient
	public String getDummy1(){
		return this.dummy1;
	}
	
	/**
	 * 
	 * 
	 * @param dummy1 
	 */
	public void setDummy1(String dummy1){
		this.dummy1= dummy1;
		this.setIsSet_dummy1(true);
	}
	
	@Override
	public MSmpMultiTypeBtch03Dto clone(){
		try{
			MSmpMultiTypeBtch03Dto object= (MSmpMultiTypeBtch03Dto)super.clone();
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
		
		result= prime * result + ((this.dummy==null)?0:this.dummy.hashCode());
		result= prime * result + ((this.dummy1==null)?0:this.dummy1.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this== obj ) return true;
		if ( obj== null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		
		final MSmpMultiTypeBtch03Dto other= (MSmpMultiTypeBtch03Dto)obj;
		{
			Object _dummy= getDummy();
			Object __dummy= other.getDummy();
			if ( _dummy== null ) { if ( __dummy!= null ) return false; }
			else if ( !_dummy.equals(__dummy) ) return false;
		}
		{
			Object _dummy1= getDummy1();
			Object __dummy1= other.getDummy1();
			if ( _dummy1== null ) { if ( __dummy1!= null ) return false; }
			else if ( !_dummy1.equals(__dummy1) ) return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	
		sb.append("\n[").append(MSmpMultiTypeBtch03Dto.class.getName()).append(":\n");
		sb.append("\tdummy: ");
		sb.append(dummy==null?"null":getDummy());
		sb.append("\n");
		sb.append("\tdummy1: ");
		sb.append(dummy1==null?"null":getDummy1());
		sb.append("\n");
		sb.append("]\n");
	
		return sb.toString();
	}
	
	@Override
	public long predictMessageLength(){
		long messageLen= 0;
		
		messageLen+= 10; /* dummy */
		messageLen+= 10; /* dummy1 */
	
		return messageLen;
	}
	
	@Override
	@JsonIgnore
	public List<String> getFieldNames(){
		List<String> list= new ArrayList<>();
	
		list.add("dummy");
		list.add("dummy1");
	
		return list;
	}
	
	@Override
	@JsonIgnore
	public Map<String, Object> getFieldValues(){
		Map<String, Object> map= new LinkedHashMap<>();
	
		map.put("dummy", get("dummy"));
		map.put("dummy1", get("dummy1"));
	
		return map;
	}
	
	
	@Override
	public Object get(String key) throws IllegalArgumentException{
		switch( key.hashCode() ){
		case 95945896:/* dummy */
			return getDummy();
		case -1320644471:/* dummy1 */
			return getDummy1();
		default:
			throw new IllegalArgumentException("Field name not found. : " + key);
		}
	}
	
	@Override
	public void set(String key, Object value){
		switch( key.hashCode() ){
		case 95945896:/* dummy */
			setDummy((String)value);
			break;
		case -1320644471:/* dummy1 */
			setDummy1((String)value);
			break;
		default:
			break;
		}
	}
	
}
