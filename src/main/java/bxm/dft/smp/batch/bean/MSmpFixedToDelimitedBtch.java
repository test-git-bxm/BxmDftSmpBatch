package bxm.dft.smp.batch.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Scope;

import bxm.dft.smp.batch.bean.dto.MSmpFixedToDelimitedBtch01Dto;
import bxm.common.annotaion.BxmCategory;
import bxm.container.annotation.BxmBean;

/**
 * @file         	bxm.jbk.smp.bean.MSmpFixedToDelimitedBtch.java
 * @filetype     	java source file
 * @brief
 * @author       	개발자(한글이름)
 * @version      	0.1
 * @history
 * <pre>
 * 버전          			성명                   				일자              				변경내용
 * -------       	----------------       	-----------       	-----------------
 * 0.1           	개발자(한글이름)       		2015. 01. 06.       신규 작성
 * </pre>
 */

@BxmBean("MSmpFixedToDelimitedBtch")
@Scope("step")
@BxmCategory(logicalName = "FILE(FIXED) TO FILE(DELIMITED) 샘플", description = "FILE(FIXED) TO FILE(DELIMITED) 샘플")
public class MSmpFixedToDelimitedBtch
		implements ItemProcessor<MSmpFixedToDelimitedBtch01Dto, MSmpFixedToDelimitedBtch01Dto> {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * process
	 * - 처리 대상 데이터를 건 별로 업무요건에 따라 처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "process : 샘플용 직원정보 처리", description = "process : 샘플용 직원정보를 1건씩 처리한다.")
	public MSmpFixedToDelimitedBtch01Dto process(MSmpFixedToDelimitedBtch01Dto in) throws Exception {
		MSmpFixedToDelimitedBtch01Dto out = null;

		/**
		 * File Read한 데이터 확인
		 */
		if (in.getFeduEmpNo() == 0 || in.getFeduDeptNo() == 0) {
			logger.warn("사원번호[{}], 부서번호[{}]가 유효하지 않습니다. Skip 처리합니다.",
					new Object[] { in.getFeduEmpNo(), in.getFeduDeptNo() });
			return null;
		}

		/**
		 * 개별 업무로직 수행
		 * ...
		 * ...
		 */
		out = in;

		return out;
	}
}