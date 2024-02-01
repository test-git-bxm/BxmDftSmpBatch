package bxm.dft.smp.batch.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;

import bxm.common.annotaion.BxmCategory;
import bxm.container.annotation.BxmBean;
import bxm.dft.smp.batch.bean.dto.MSmpMultiTypeBtch01Dto;
import bxm.dft.smp.batch.bean.dto.MSmpMultiTypeBtch02Dto;
import bxm.dft.smp.batch.bean.dto.MSmpMultiTypeBtch03Dto;
import bxm.dft.smp.batch.bean.dto.MSmpMultiTypeBtch04Dto;
import bxm.omm.root.IOmmObject;

/**
 * @file         	bxm.jbk.smp.bean.MSmpMultiTypeBtch.java
 * @filetype     	java source file
 * @brief
 * @author       	개발자(한글이름)
 * @version      	0.1
 * @history
 * <pre>
 * 버전          			성명                   				일자              				변경내용
 * -------       	----------------       	-----------       	-----------------
 * 0.1           	개발자(한글이름)       		2017. 11. 03.       신규 작성
 * </pre>
 */

@BxmBean("MSmpMultiTypeBtch")
@Scope("step")
@BxmCategory(logicalName = "RMultiTypeFixed to WMultiTypeDelimited 샘플")
public class MSmpMultiTypeBtch implements ItemProcessor<IOmmObject, MSmpMultiTypeBtch01Dto> {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * process
	 * - 처리 대상 데이터를 건 별로 업무요건에 따라 처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "process : MultiType 처리")
	public MSmpMultiTypeBtch01Dto process(IOmmObject in) throws Exception {
		MSmpMultiTypeBtch01Dto out = new MSmpMultiTypeBtch01Dto();

		MSmpMultiTypeBtch02Dto header = null;
		MSmpMultiTypeBtch03Dto body = null;
		MSmpMultiTypeBtch04Dto footer = null;

		if (in instanceof MSmpMultiTypeBtch02Dto) { // Header
			header = (MSmpMultiTypeBtch02Dto) in;
		} else if (in instanceof MSmpMultiTypeBtch03Dto) { // Body
			body = (MSmpMultiTypeBtch03Dto) in;
		} else if (in instanceof MSmpMultiTypeBtch04Dto) { // Footer
			footer = (MSmpMultiTypeBtch04Dto) in;
		}

		/**
		 * Multi Type Out Dto 처리
		 * - Multi Type의 Write 처리는 Master DTO에 include하여 정의한다.
		 * - 참고로 이 샘플은 Read한 DTO를 그대로 Write로 전달하도록 구성한 샘플이다. 
		 */
		if (header != null) {
			out.setHeader(header);
		}
		if (body != null) {
			out.setBody(body);
		}
		if (footer != null) {
			out.setFooter(footer);
		}

		return out;
	}
}