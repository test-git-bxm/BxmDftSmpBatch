package bxm.dft.smp.batch.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;

import bxm.common.annotaion.BxmCategory;
import bxm.container.annotation.BxmBean;
import bxm.dft.smp.batch.bean.dto.MSmpFixedMultiTypeBtch01Dto;
import bxm.dft.smp.batch.bean.dto.MSmpFixedMultiTypeBtch02Dto;
import bxm.dft.smp.batch.bean.dto.MSmpFixedMultiTypeBtch03Dto;
import bxm.dft.smp.batch.bean.dto.MSmpFixedMultiTypeBtch04Dto;
import bxm.omm.root.IOmmObject;

/**
 * @file         	shb.jfm.smp.bean.MSmpMultiTypeToMultiTypeBtch.java
 * @filetype     	java source file
 * @brief
 * @author       	개발자(한글이름)
 * @version      	0.1
 * @history
 * <pre>
 * 버전          		성명                   	일자              		변경내용
 * -------       	----------------       	-----------       	-----------------
 * 0.1           	개발자(한글이름)       	2015. 01. 06.       신규 작성
 * </pre>
 */

@BxmBean("MSmpFixedMultiTypeBtch")
@Scope("step")
@BxmCategory(logicalName = "FIXED MULTI TYPE 샘플", description = "FIXED MULTI TYPE 샘플")
public class MSmpFixedMultiTypeBtch implements ItemProcessor<IOmmObject, MSmpFixedMultiTypeBtch01Dto> {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * process
	 * - 처리 대상 데이터를 건 별로 업무요건에 따라 처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "process : 샘플용 직원정보 Multi Type 처리", description = "process : 샘플용 직원정보 Multi Type 처리")
	public MSmpFixedMultiTypeBtch01Dto process(IOmmObject in) throws Exception 
	{
		/***************************************************************************
		 * Multi Type Read는 다양한 형태의 OMM 으로 Item이 전달되기 때문에 OMM의 상위객체인
		 * IOmmObject로 처리한다. 
		 ***************************************************************************/

		MSmpFixedMultiTypeBtch01Dto out = new MSmpFixedMultiTypeBtch01Dto(); // Out Dto
		
		MSmpFixedMultiTypeBtch02Dto header = null;
		MSmpFixedMultiTypeBtch03Dto body = null;
		MSmpFixedMultiTypeBtch04Dto footer = null;
		
		/**
		 * Header/Body/Footer 구분처리
		 * - instanceof 를 이용하여 판단한다. 
		 */
		if(in instanceof MSmpFixedMultiTypeBtch02Dto) // Header
		{
			header = (MSmpFixedMultiTypeBtch02Dto) in;
		}
		else if(in instanceof MSmpFixedMultiTypeBtch03Dto) // Body
		{
			body = (MSmpFixedMultiTypeBtch03Dto) in;
		}
		else if(in instanceof MSmpFixedMultiTypeBtch04Dto) // Footer
		{
			footer = (MSmpFixedMultiTypeBtch04Dto) in;
		}
		
		/**
		 * 개별 업무로직 수행
		 * ...
		 * ...
		 */
		
		
		/**
		 * Multi Type Out Dto 처리
		 * - Multi Type의 Write 처리는 Master DTO에 include하여 정의한다.
		 * - 참고로 이 샘플은 Read한 DTO를 그대로 Write로 전달하도록 구성한 샘플이다. 
		 */
		if(header != null)
		{
			out.setHeader(header);
		}
		if(body != null)
		{
			out.setBody(body);
		}
		if(footer != null)
		{
			out.setFooter(footer);
		}
		
		return out;
	}
}