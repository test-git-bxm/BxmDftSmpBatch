package bxm.dft.smp.batch.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;

import bxm.dft.smp.batch.bean.dto.MSmpVariableToVariableBtch01Dto;
import bxm.common.annotaion.BxmCategory;
import bxm.container.annotation.BxmBean;

/**
 * @file         bxm.jbk.smp.bean.MSmpVariableToVariableBtch.java
 * @filetype     java source file
 * @brief
 * @author       개발자(한글이름)
 * @version      0.1
 * @history
 * <pre>
 * 버전			성명						일자				변경내용
 * -------		----------------       	-----------		-----------------
 * 0.1			개발자(한글이름)				2015. 01. 06.	신규 작성
 * </pre>
 */

@BxmBean("MSmpVariableToVariableBtch")
@Scope("step")
@BxmCategory(logicalName = "VARIABLE TO VARIABLE 샘플", description = "VARIABLE TO VARIABLE 샘플")
public class MSmpVariableToVariableBtch implements ItemProcessor<String, String> {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * process
	 * - 처리 대상 데이터를 건 별로 업무요건에 따라 처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "process : 샘플용 직원정보 처리", description = "process : 샘플용 직원정보 처리")
	public String process(String in) throws Exception {
		/********************************************************************
		 * Variable 유형은 직접 문자열을 읽거나 혹은 문자열을 쓸때 사용하는 유형이다.
		 * - 파일 read  : 1라인씩 read하여 process로 전달
		 * - 파일 write : process에서 return한 문자열을 1라인씩 write  
		 ********************************************************************/

		MSmpVariableToVariableBtch01Dto inDto = new MSmpVariableToVariableBtch01Dto();
		MSmpVariableToVariableBtch01Dto outDto;

		inDto.setFeduEmpNo(Integer.parseInt(in.substring(0, 4)));
		inDto.setFeduEmpNm(in.substring(4));

		/**
		 * 개별 업무로직 수행
		 * ...
		 * ...
		 */
		outDto = inDto;

		/**
		 * OMM을 문자열로 변환
		 */
		String out = String.format("%4d%10s", outDto.getFeduEmpNo(), outDto.getFeduEmpNm());

		return out;
	}
}