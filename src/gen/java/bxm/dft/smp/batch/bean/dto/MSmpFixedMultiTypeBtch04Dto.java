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
 * FIXED MULTI TYPE 샘플 04 Dto(Footer)
 */
@XmlType(propOrder={
	"procCount"
}, name="MSmpFixedMultiTypeBtch04Dto")
@XmlRootElement(name="MSmpFixedMultiTypeBtch04Dto")
@BxmCategory(logicalName="", description="FIXED MULTI TYPE 샘플 04 Dto(Footer)") 
public class MSmpFixedMultiTypeBtch04Dto implements IOmmObject, Predictable, FieldInfo
{
	private static final long serialVersionUID= -1409255218L;
	
	@XmlTransient
	public static final String OMM_DESCRIPTION= "FIXED MULTI TYPE 샘플 04 Dto(Footer)";
	@XmlTransient
	public static final String OMM_VERSION= "";
	
	
	
	/**
	 * 처리건수
	 */
	@XmlElement
	@JsonProperty("procCount")
	@BxmOmm_Field(length=8, decimal=0, description="처리건수", align="right", fill="")
	private Integer procCount= 0;
	
	
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_procCount= false;
	protected final boolean isSet_procCount(){
		return this.isSet_procCount;
	}
	private void setIsSet_procCount(boolean value){
		this.isSet_procCount= value;
	}
	/**
	 * 처리건수
	 */
	@XmlTransient
	public Integer getProcCount(){
		return this.procCount;
	}
	
	/**
	 * 처리건수
	 * 
	 * @param procCount 처리건수
	 */
	public void setProcCount(Integer procCount){
		this.procCount= procCount;
		this.setIsSet_procCount(true);
	}
	
	@Override
	public MSmpFixedMultiTypeBtch04Dto clone(){
		try{
			MSmpFixedMultiTypeBtch04Dto object= (MSmpFixedMultiTypeBtch04Dto)super.clone();
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
		
		result= prime * result + ((this.procCount==null)?0:this.procCount.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this== obj ) return true;
		if ( obj== null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		
		final MSmpFixedMultiTypeBtch04Dto other= (MSmpFixedMultiTypeBtch04Dto)obj;
		{
			Object _procCount= getProcCount();
			Object __procCount= other.getProcCount();
			if ( _procCount== null ) { if ( __procCount!= null ) return false; }
			else if ( !_procCount.equals(__procCount) ) return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	
		sb.append("\n[").append(MSmpFixedMultiTypeBtch04Dto.class.getName()).append(":\n");
		sb.append("\tprocCount: ");
		sb.append(procCount==null?"null":getProcCount());
		sb.append("\n");
		sb.append("]\n");
	
		return sb.toString();
	}
	
	@Override
	public long predictMessageLength(){
		long messageLen= 0;
		
		messageLen+= 8; /* procCount */
	
		return messageLen;
	}
	
	@Override
	@JsonIgnore
	public List<String> getFieldNames(){
		List<String> list= new ArrayList<>();
	
		list.add("procCount");
	
		return list;
	}
	
	@Override
	@JsonIgnore
	public Map<String, Object> getFieldValues(){
		Map<String, Object> map= new LinkedHashMap<>();
	
		map.put("procCount", get("procCount"));
	
		return map;
	}
	
	
	@Override
	public Object get(String key) throws IllegalArgumentException{
		switch( key.hashCode() ){
		case -1126276263:/* procCount */
			return getProcCount();
		default:
			throw new IllegalArgumentException("Field name not found. : " + key);
		}
	}
	
	@Override
	public void set(String key, Object value){
		switch( key.hashCode() ){
		case -1126276263:/* procCount */
			setProcCount((Integer)value);
			break;
		default:
			break;
		}
	}
	
}
