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
	"sub01", "sub02"
}, name="MSmpMultiWriterBtch02Dto")
@XmlRootElement(name="MSmpMultiWriterBtch02Dto")
@BxmCategory(logicalName="", description="") 
public class MSmpMultiWriterBtch02Dto implements IOmmObject, Predictable, FieldInfo
{
	private static final long serialVersionUID= -776737807L;
	
	@XmlTransient
	public static final String OMM_DESCRIPTION= "";
	@XmlTransient
	public static final String OMM_VERSION= "";
	
	
	
	/**
	 * 
	 */
	@XmlElement
	@JsonProperty("sub01")
	@BxmOmm_Field(length=20, decimal=0, align="left", fill="")
	private MSmpMultiWriterBtch03Dto sub01= null;
	
	
	/**
	 * 
	 */
	@XmlElement
	@JsonProperty("sub02")
	@BxmOmm_Field(length=20, decimal=0, align="left", fill="")
	private MSmpMultiWriterBtch04Dto sub02= null;
	
	
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_sub01= false;
	protected final boolean isSet_sub01(){
		return this.isSet_sub01;
	}
	private void setIsSet_sub01(boolean value){
		this.isSet_sub01= value;
	}
	/**
	 * 
	 */
	@XmlTransient
	public MSmpMultiWriterBtch03Dto getSub01(){
		return this.sub01;
	}
	
	/**
	 * 
	 * 
	 * @param sub01 
	 */
	public void setSub01(MSmpMultiWriterBtch03Dto sub01){
		this.sub01= sub01;
		this.setIsSet_sub01(true);
	}
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_sub02= false;
	protected final boolean isSet_sub02(){
		return this.isSet_sub02;
	}
	private void setIsSet_sub02(boolean value){
		this.isSet_sub02= value;
	}
	/**
	 * 
	 */
	@XmlTransient
	public MSmpMultiWriterBtch04Dto getSub02(){
		return this.sub02;
	}
	
	/**
	 * 
	 * 
	 * @param sub02 
	 */
	public void setSub02(MSmpMultiWriterBtch04Dto sub02){
		this.sub02= sub02;
		this.setIsSet_sub02(true);
	}
	
	@Override
	public MSmpMultiWriterBtch02Dto clone(){
		try{
			MSmpMultiWriterBtch02Dto object= (MSmpMultiWriterBtch02Dto)super.clone();
			if ( this.sub01== null ) object.sub01= null;
			else object.sub01= (MSmpMultiWriterBtch03Dto)this.sub01.clone();
			if ( this.sub02== null ) object.sub02= null;
			else object.sub02= (MSmpMultiWriterBtch04Dto)this.sub02.clone();
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
		
		result= prime * result + ((this.sub01==null)?0:this.sub01.hashCode());
		result= prime * result + ((this.sub02==null)?0:this.sub02.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this== obj ) return true;
		if ( obj== null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		
		final MSmpMultiWriterBtch02Dto other= (MSmpMultiWriterBtch02Dto)obj;
		{
			Object _sub01= getSub01();
			Object __sub01= other.getSub01();
			if ( _sub01== null ) { if ( __sub01!= null ) return false; }
			else if ( !_sub01.equals(__sub01) ) return false;
		}
		{
			Object _sub02= getSub02();
			Object __sub02= other.getSub02();
			if ( _sub02== null ) { if ( __sub02!= null ) return false; }
			else if ( !_sub02.equals(__sub02) ) return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	
		sb.append("\n[").append(MSmpMultiWriterBtch02Dto.class.getName()).append(":\n");
		sb.append("\tsub01: ");
		sb.append(sub01==null?"null":getSub01());
		sb.append("\n");
		sb.append("\tsub02: ");
		sb.append(sub02==null?"null":getSub02());
		sb.append("\n");
		sb.append("]\n");
	
		return sb.toString();
	}
	
	@Override
	public long predictMessageLength(){
		long messageLen= 0;
		
		if ( sub01 != null && !(sub01 instanceof Predictable) )
			throw new IllegalStateException( "Message length can not be predicted.");
		{
			MSmpMultiWriterBtch03Dto temp= sub01;
			if ( temp== null ) temp= new MSmpMultiWriterBtch03Dto();
			messageLen+= ( (Predictable)temp).predictMessageLength(); /* sub01 */
		}
		if ( sub02 != null && !(sub02 instanceof Predictable) )
			throw new IllegalStateException( "Message length can not be predicted.");
		{
			MSmpMultiWriterBtch04Dto temp= sub02;
			if ( temp== null ) temp= new MSmpMultiWriterBtch04Dto();
			messageLen+= ( (Predictable)temp).predictMessageLength(); /* sub02 */
		}
	
		return messageLen;
	}
	
	@Override
	@JsonIgnore
	public List<String> getFieldNames(){
		List<String> list= new ArrayList<>();
	
		list.add("sub01");
		list.add("sub02");
	
		return list;
	}
	
	@Override
	@JsonIgnore
	public Map<String, Object> getFieldValues(){
		Map<String, Object> map= new LinkedHashMap<>();
	
		map.put("sub01", get("sub01"));
		map.put("sub02", get("sub02"));
	
		return map;
	}
	
	
	@Override
	public Object get(String key) throws IllegalArgumentException{
		switch( key.hashCode() ){
		case 109786177:/* sub01 */
			return getSub01();
		case 109786178:/* sub02 */
			return getSub02();
		default:
			throw new IllegalArgumentException("Field name not found. : " + key);
		}
	}
	
	@Override
	public void set(String key, Object value){
		switch( key.hashCode() ){
		case 109786177:/* sub01 */
			setSub01((MSmpMultiWriterBtch03Dto)value);
			break;
		case 109786178:/* sub02 */
			setSub02((MSmpMultiWriterBtch04Dto)value);
			break;
		default:
			break;
		}
	}
	
}
