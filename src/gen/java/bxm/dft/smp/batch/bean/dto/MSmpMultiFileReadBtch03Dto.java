/**
 * BXM V5 - IO Java Source Generator
 */

package bxm.dft.smp.batch.bean.dto;

import bxm.common.annotaion.BxmCategory;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTmp001Dto;
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
 * MULTI FILE READ 샘플 03 DTO
 */
@XmlType(propOrder={
	"grid01"
}, name="MSmpMultiFileReadBtch03Dto")
@XmlRootElement(name="MSmpMultiFileReadBtch03Dto")
@BxmCategory(logicalName="", description="MULTI FILE READ 샘플 03 DTO") 
public class MSmpMultiFileReadBtch03Dto implements IOmmObject, Predictable, FieldInfo
{
	private static final long serialVersionUID= 242654961L;
	
	@XmlTransient
	public static final String OMM_DESCRIPTION= "MULTI FILE READ 샘플 03 DTO";
	@XmlTransient
	public static final String OMM_VERSION= "";
	
	
	
	/**
	 * 임시직원용GRID01
	 */
	@XmlElement
	@JsonProperty("grid01")
	@BxmOmm_Field(length=0, decimal=0, description="임시직원용GRID01", align="left", fill="")
	private DSmpEmpTmp001Dto grid01= null;
	
	
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_grid01= false;
	protected final boolean isSet_grid01(){
		return this.isSet_grid01;
	}
	private void setIsSet_grid01(boolean value){
		this.isSet_grid01= value;
	}
	/**
	 * 임시직원용GRID01
	 */
	@XmlTransient
	public DSmpEmpTmp001Dto getGrid01(){
		return this.grid01;
	}
	
	/**
	 * 임시직원용GRID01
	 * 
	 * @param grid01 임시직원용GRID01
	 */
	public void setGrid01(DSmpEmpTmp001Dto grid01){
		this.grid01= grid01;
		this.setIsSet_grid01(true);
	}
	
	@Override
	public MSmpMultiFileReadBtch03Dto clone(){
		try{
			MSmpMultiFileReadBtch03Dto object= (MSmpMultiFileReadBtch03Dto)super.clone();
			if ( this.grid01== null ) object.grid01= null;
			else object.grid01= (DSmpEmpTmp001Dto)this.grid01.clone();
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
		
		result= prime * result + ((this.grid01==null)?0:this.grid01.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this== obj ) return true;
		if ( obj== null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		
		final MSmpMultiFileReadBtch03Dto other= (MSmpMultiFileReadBtch03Dto)obj;
		{
			Object _grid01= getGrid01();
			Object __grid01= other.getGrid01();
			if ( _grid01== null ) { if ( __grid01!= null ) return false; }
			else if ( !_grid01.equals(__grid01) ) return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	
		sb.append("\n[").append(MSmpMultiFileReadBtch03Dto.class.getName()).append(":\n");
		sb.append("\tgrid01: ");
		sb.append(grid01==null?"null":getGrid01());
		sb.append("\n");
		sb.append("]\n");
	
		return sb.toString();
	}
	
	@Override
	public long predictMessageLength(){
		long messageLen= 0;
		
		if ( grid01 != null && !(grid01 instanceof Predictable) )
			throw new IllegalStateException( "Message length can not be predicted.");
		{
			DSmpEmpTmp001Dto temp= grid01;
			if ( temp== null ) temp= new DSmpEmpTmp001Dto();
			messageLen+= ( (Predictable)temp).predictMessageLength(); /* grid01 */
		}
	
		return messageLen;
	}
	
	@Override
	@JsonIgnore
	public List<String> getFieldNames(){
		List<String> list= new ArrayList<>();
	
		list.add("grid01");
	
		return list;
	}
	
	@Override
	@JsonIgnore
	public Map<String, Object> getFieldValues(){
		Map<String, Object> map= new LinkedHashMap<>();
	
		map.put("grid01", get("grid01"));
	
		return map;
	}
	
	
	@Override
	public Object get(String key) throws IllegalArgumentException{
		switch( key.hashCode() ){
		case -1237657657:/* grid01 */
			return getGrid01();
		default:
			throw new IllegalArgumentException("Field name not found. : " + key);
		}
	}
	
	@Override
	public void set(String key, Object value){
		switch( key.hashCode() ){
		case -1237657657:/* grid01 */
			setGrid01((DSmpEmpTmp001Dto)value);
			break;
		default:
			break;
		}
	}
	
}
