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
 * FIXED MULTI TYPE 샘플 01 Dto
 */
@XmlType(propOrder={
	"header", "body", "footer"
}, name="MSmpFixedMultiTypeBtch01Dto")
@XmlRootElement(name="MSmpFixedMultiTypeBtch01Dto")
@BxmCategory(logicalName="", description="FIXED MULTI TYPE 샘플 01 Dto") 
public class MSmpFixedMultiTypeBtch01Dto implements IOmmObject, Predictable, FieldInfo
{
	private static final long serialVersionUID= -1409344591L;
	
	@XmlTransient
	public static final String OMM_DESCRIPTION= "FIXED MULTI TYPE 샘플 01 Dto";
	@XmlTransient
	public static final String OMM_VERSION= "";
	
	
	
	/**
	 * 헤더
	 */
	@XmlElement
	@JsonProperty("header")
	@BxmOmm_Field(length=0, decimal=0, description="헤더", align="left", fill="")
	private MSmpFixedMultiTypeBtch02Dto header= null;
	
	
	/**
	 * 바디
	 */
	@XmlElement
	@JsonProperty("body")
	@BxmOmm_Field(length=0, decimal=0, description="바디", align="left", fill="")
	private MSmpFixedMultiTypeBtch03Dto body= null;
	
	
	/**
	 * 푸터
	 */
	@XmlElement
	@JsonProperty("footer")
	@BxmOmm_Field(length=0, decimal=0, description="푸터", align="left", fill="")
	private MSmpFixedMultiTypeBtch04Dto footer= null;
	
	
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_header= false;
	protected final boolean isSet_header(){
		return this.isSet_header;
	}
	private void setIsSet_header(boolean value){
		this.isSet_header= value;
	}
	/**
	 * 헤더
	 */
	@XmlTransient
	public MSmpFixedMultiTypeBtch02Dto getHeader(){
		return this.header;
	}
	
	/**
	 * 헤더
	 * 
	 * @param header 헤더
	 */
	public void setHeader(MSmpFixedMultiTypeBtch02Dto header){
		this.header= header;
		this.setIsSet_header(true);
	}
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_body= false;
	protected final boolean isSet_body(){
		return this.isSet_body;
	}
	private void setIsSet_body(boolean value){
		this.isSet_body= value;
	}
	/**
	 * 바디
	 */
	@XmlTransient
	public MSmpFixedMultiTypeBtch03Dto getBody(){
		return this.body;
	}
	
	/**
	 * 바디
	 * 
	 * @param body 바디
	 */
	public void setBody(MSmpFixedMultiTypeBtch03Dto body){
		this.body= body;
		this.setIsSet_body(true);
	}
	
	@XmlTransient
	@JsonIgnore
	private boolean isSet_footer= false;
	protected final boolean isSet_footer(){
		return this.isSet_footer;
	}
	private void setIsSet_footer(boolean value){
		this.isSet_footer= value;
	}
	/**
	 * 푸터
	 */
	@XmlTransient
	public MSmpFixedMultiTypeBtch04Dto getFooter(){
		return this.footer;
	}
	
	/**
	 * 푸터
	 * 
	 * @param footer 푸터
	 */
	public void setFooter(MSmpFixedMultiTypeBtch04Dto footer){
		this.footer= footer;
		this.setIsSet_footer(true);
	}
	
	@Override
	public MSmpFixedMultiTypeBtch01Dto clone(){
		try{
			MSmpFixedMultiTypeBtch01Dto object= (MSmpFixedMultiTypeBtch01Dto)super.clone();
			if ( this.header== null ) object.header= null;
			else object.header= (MSmpFixedMultiTypeBtch02Dto)this.header.clone();
			if ( this.body== null ) object.body= null;
			else object.body= (MSmpFixedMultiTypeBtch03Dto)this.body.clone();
			if ( this.footer== null ) object.footer= null;
			else object.footer= (MSmpFixedMultiTypeBtch04Dto)this.footer.clone();
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
		
		result= prime * result + ((this.header==null)?0:this.header.hashCode());
		result= prime * result + ((this.body==null)?0:this.body.hashCode());
		result= prime * result + ((this.footer==null)?0:this.footer.hashCode());
		
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this== obj ) return true;
		if ( obj== null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		
		final MSmpFixedMultiTypeBtch01Dto other= (MSmpFixedMultiTypeBtch01Dto)obj;
		{
			Object _header= getHeader();
			Object __header= other.getHeader();
			if ( _header== null ) { if ( __header!= null ) return false; }
			else if ( !_header.equals(__header) ) return false;
		}
		{
			Object _body= getBody();
			Object __body= other.getBody();
			if ( _body== null ) { if ( __body!= null ) return false; }
			else if ( !_body.equals(__body) ) return false;
		}
		{
			Object _footer= getFooter();
			Object __footer= other.getFooter();
			if ( _footer== null ) { if ( __footer!= null ) return false; }
			else if ( !_footer.equals(__footer) ) return false;
		}
		
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
	
		sb.append("\n[").append(MSmpFixedMultiTypeBtch01Dto.class.getName()).append(":\n");
		sb.append("\theader: ");
		sb.append(header==null?"null":getHeader());
		sb.append("\n");
		sb.append("\tbody: ");
		sb.append(body==null?"null":getBody());
		sb.append("\n");
		sb.append("\tfooter: ");
		sb.append(footer==null?"null":getFooter());
		sb.append("\n");
		sb.append("]\n");
	
		return sb.toString();
	}
	
	@Override
	public long predictMessageLength(){
		long messageLen= 0;
		
		if ( header != null && !(header instanceof Predictable) )
			throw new IllegalStateException( "Message length can not be predicted.");
		{
			MSmpFixedMultiTypeBtch02Dto temp= header;
			if ( temp== null ) temp= new MSmpFixedMultiTypeBtch02Dto();
			messageLen+= ( (Predictable)temp).predictMessageLength(); /* header */
		}
		if ( body != null && !(body instanceof Predictable) )
			throw new IllegalStateException( "Message length can not be predicted.");
		{
			MSmpFixedMultiTypeBtch03Dto temp= body;
			if ( temp== null ) temp= new MSmpFixedMultiTypeBtch03Dto();
			messageLen+= ( (Predictable)temp).predictMessageLength(); /* body */
		}
		if ( footer != null && !(footer instanceof Predictable) )
			throw new IllegalStateException( "Message length can not be predicted.");
		{
			MSmpFixedMultiTypeBtch04Dto temp= footer;
			if ( temp== null ) temp= new MSmpFixedMultiTypeBtch04Dto();
			messageLen+= ( (Predictable)temp).predictMessageLength(); /* footer */
		}
	
		return messageLen;
	}
	
	@Override
	@JsonIgnore
	public List<String> getFieldNames(){
		List<String> list= new ArrayList<>();
	
		list.add("header");
		list.add("body");
		list.add("footer");
	
		return list;
	}
	
	@Override
	@JsonIgnore
	public Map<String, Object> getFieldValues(){
		Map<String, Object> map= new LinkedHashMap<>();
	
		map.put("header", get("header"));
		map.put("body", get("body"));
		map.put("footer", get("footer"));
	
		return map;
	}
	
	
	@Override
	public Object get(String key) throws IllegalArgumentException{
		switch( key.hashCode() ){
		case -1221270899:/* header */
			return getHeader();
		case 3029410:/* body */
			return getBody();
		case -1268861541:/* footer */
			return getFooter();
		default:
			throw new IllegalArgumentException("Field name not found. : " + key);
		}
	}
	
	@Override
	public void set(String key, Object value){
		switch( key.hashCode() ){
		case -1221270899:/* header */
			setHeader((MSmpFixedMultiTypeBtch02Dto)value);
			break;
		case 3029410:/* body */
			setBody((MSmpFixedMultiTypeBtch03Dto)value);
			break;
		case -1268861541:/* footer */
			setFooter((MSmpFixedMultiTypeBtch04Dto)value);
			break;
		default:
			break;
		}
	}
	
}
