package bxm.dft.smp.batch.bean;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Scope;

import bxm.common.annotaion.BxmCategory;
import bxm.container.annotation.BxmBean;
import bxm.dft.context.DefaultApplicationContext;
import bxm.dft.smp.batch.bean.dto.MSmpDelimitedToDBBtch01Dto;
import bxm.dft.smp.batch.bean.dto.MSmpDelimitedToDBBtch02Dto;
import bxm.dft.smp.batch.dbio.DSmpEmpTmp001;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTmp001Dto;

/**
 * @file         	bxm.jbk.smp.bean.MSmpDelimitedToDBBtch.java
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

@BxmBean("MSmpDelimitedToDBBtch")
@Scope("step")
@BxmCategory(logicalName = "FILE(DELIMITED) TO DB 샘플", description = "FILE(DELIMITED) TO DB 샘플")
public class MSmpDelimitedToDBBtch implements ItemProcessor<MSmpDelimitedToDBBtch01Dto, MSmpDelimitedToDBBtch02Dto>,
		ItemWriter<MSmpDelimitedToDBBtch02Dto> {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DSmpEmpTmp001 dSmpEmpTmp100;

	/**
	 * process
	 * - 처리 대상 데이터를 건 별로 업무요건에 따라 처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "process : 샘플용 직원정보 처리", description = "process : 샘플용 직원정보를 1건씩 처리한다.")
	public MSmpDelimitedToDBBtch02Dto process(MSmpDelimitedToDBBtch01Dto in) throws Exception {
		MSmpDelimitedToDBBtch02Dto out = null;

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
		 */

		/**
		 *  write에 전달할 Item 설정
		 */
		out = new MSmpDelimitedToDBBtch02Dto();
		out.setFeduEmpNo(in.getFeduEmpNo()); // set [FW샘플 임직원번호]
		out.setFeduEmpNm(in.getFeduEmpNm()); // set [FW샘플 임직원명]
		out.setFeduOccpNm(in.getFeduOccpNm()); // set [FW샘플 직업명]
		out.setFeduMngrEmpNo(in.getFeduMngrEmpNo()); // set [FW샘플 관리자임직원번호]
		out.setFeduHireDt(in.getFeduHireDt()); // set [FW샘플 입사일자]
		out.setFeduPayAmt(in.getFeduPayAmt()); // set [FW샘플 급여금액]
		out.setFeduDeptNo(in.getFeduDeptNo()); // set [FW샘플 부서번호]

		return out;
	}

	/**
	 * write
	 * - 처리 대상 결과 데이터를 커밋 단위 별로 DB 또는 파일에 기록처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "write : 샘플용 직원정보 처리", description = "write : 샘플용 직원정보를 등록한다.")
	public void write(List<? extends MSmpDelimitedToDBBtch02Dto> in) throws Exception {
		if (dSmpEmpTmp100 == null) {
			dSmpEmpTmp100 = DefaultApplicationContext.getBean(DSmpEmpTmp001.class);
		}

		/**
		 * List로 전달된 Item를 Loop를 돌면서 DB에 Insert할 DTO를 List형태로 설정한다.
		 */
		List<DSmpEmpTmp001Dto> dSmpEmpTmp100insert02InDtos = new ArrayList<DSmpEmpTmp001Dto>();
		for (MSmpDelimitedToDBBtch02Dto mSmpDelimitedToDBBtch02Dto : in) {
			DSmpEmpTmp001Dto smpEmpTmp001Dto = new DSmpEmpTmp001Dto();

			smpEmpTmp001Dto.setFeduEmpNo(mSmpDelimitedToDBBtch02Dto.getFeduEmpNo()); // set [FW샘플 임직원번호]
			smpEmpTmp001Dto.setFeduEmpNm(mSmpDelimitedToDBBtch02Dto.getFeduEmpNm()); // set [FW샘플 임직원명]
			smpEmpTmp001Dto.setFeduOccpNm(mSmpDelimitedToDBBtch02Dto.getFeduOccpNm()); // set [FW샘플 직업명]
			smpEmpTmp001Dto.setFeduMngrEmpNo(mSmpDelimitedToDBBtch02Dto.getFeduMngrEmpNo()); // set [FW샘플 관리자임직원번호]
			smpEmpTmp001Dto.setFeduHireDt(mSmpDelimitedToDBBtch02Dto.getFeduHireDt()); // set [FW샘플 입사일자]
			smpEmpTmp001Dto.setFeduPayAmt(mSmpDelimitedToDBBtch02Dto.getFeduPayAmt()); // set [FW샘플 급여금액]
			smpEmpTmp001Dto.setFeduDeptNo(mSmpDelimitedToDBBtch02Dto.getFeduDeptNo()); // set [FW샘플 부서번호]
		}

		/**
		 * insert 처리
		 * - 참고 1 : CONNECTED_BATCH로 C/U/D 처리는 Commit 시점에 bulk로 쿼리가 수행이 된다.
		 * - 참고 2 : CONNECTED_BATCH로 C/U/D 처리는 Commit 시점에 수행이 되기때문에 return 값은 임의의 음수 값이다.(의미없음)
		 */
		dSmpEmpTmp100.insert02(dSmpEmpTmp100insert02InDtos);
	}
}