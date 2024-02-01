package bxm.dft.smp.batch.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.context.annotation.Scope;

import bxm.batch.dft.context.DefaultBatchApplicationContext;
import bxm.common.annotaion.BxmCategory;
import bxm.common.util.StringUtils;
import bxm.container.annotation.BxmBean;
import bxm.context.das.DasUtils;
import bxm.dft.context.DefaultApplicationContext;
import bxm.dft.smp.batch.bean.dto.MSmpDBToDBBtch01Dto;
import bxm.dft.smp.batch.bean.dto.MSmpDBToDBBtch02Dto;
import bxm.dft.smp.batch.dbio.DSmpEmpTmp001;
import bxm.dft.smp.batch.dbio.DSmpEmpTst001;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTmp001Dto;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01InDto;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01OutDto;

@BxmBean("MSmpDBToDBBtch")
@Scope("step")
@BxmCategory(logicalName = "DB TO DB 샘플")
public class MSmpDBToDBBtch implements ItemReader<MSmpDBToDBBtch01Dto>,
		ItemProcessor<MSmpDBToDBBtch01Dto, MSmpDBToDBBtch02Dto>, ItemWriter<MSmpDBToDBBtch02Dto>, ItemStream {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DSmpEmpTst001 dSmpEmpTst001;
	private DSmpEmpTmp001 dSmpEmpTmp100;

	private Iterator<DSmpEmpTst001selectList01OutDto> iterator;

	/**
	 * open 
	 * - 초기화를 위해 구현해야 하는 Method 로서 스텝이 시작되기 전에 FW에서 최초 1번 호출된다.
	 */
	@Override
	@BxmCategory(logicalName = "DB TO DB 샘플 open")
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		if (dSmpEmpTst001 == null) {
			dSmpEmpTst001 = DefaultApplicationContext.getBean(DSmpEmpTst001.class);
		}

		/**
		 * 배치 입력파라미터 "deptNo" Get
		 */
		String feduDeptNo = DefaultBatchApplicationContext.getJobParameter("deptNo");
		if (StringUtils.isEmpty(feduDeptNo)) {
			throw new ItemStreamException("배치 입력아규먼트 'deptNo' 값이 존재하지 않습니다.");
		}

		/**
		 * 입력받은 "deptNo" 에 대하여 샘플용 직원정보를 Iterator로 가져온다.
		 */

		DSmpEmpTst001selectList01InDto inDto = new DSmpEmpTst001selectList01InDto();
		inDto.setFeduDeptNo(Integer.parseInt(feduDeptNo));
		iterator = dSmpEmpTst001.selectList01(inDto).iterator();
	}

	/**
	 * read - 처리 대상 데이터를 건 별로 DB 또는 파일에서 읽어오는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "DB TO DB 샘플 read")
	public MSmpDBToDBBtch01Dto read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		MSmpDBToDBBtch01Dto out = null;
		if (iterator.hasNext()) {
			out = new MSmpDBToDBBtch01Dto();
			DSmpEmpTst001selectList01OutDto input = iterator.next();

			out.setFeduEmpNo(input.getFeduEmpNo()); // set [FW샘플 임직원번호]
			out.setFeduEmpNm(input.getFeduEmpNm()); // set [FW샘플 임직원명]
			out.setFeduOccpNm(input.getFeduOccpNm()); // set [FW샘플 직업명]
			out.setFeduMngrEmpNo(input.getFeduMngrEmpNo()); // set [FW샘플 관리자임직원번호]
			out.setFeduHireDt(input.getFeduHireDt()); // set [FW샘플 입사일자]
			out.setFeduPayAmt(input.getFeduPayAmt()); // set [FW샘플 급여금액]
			out.setFeduDeptNo(input.getFeduDeptNo()); // set [FW샘플 부서번호]
		}

		return out;
	}

	/**
	 * process - 처리 대상 데이터를 건 별로 업무요건에 따라 처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "DB TO DB 샘플 process")
	public MSmpDBToDBBtch02Dto process(MSmpDBToDBBtch01Dto in) throws Exception {
		MSmpDBToDBBtch02Dto out = new MSmpDBToDBBtch02Dto();

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
	 * write - 처리 대상 결과 데이터를 커밋 단위 별로 DB 또는 파일에 기록처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "DB TO DB 샘플 write")
	public void write(List<? extends MSmpDBToDBBtch02Dto> in) throws Exception {
		if (dSmpEmpTmp100 == null) {
			dSmpEmpTmp100 = DefaultApplicationContext.getBean(DSmpEmpTmp001.class);
		}

		/**
		 * List로 전달된 Item를 Loop를 돌면서 DB에 Insert할 DTO를 List형태로 설정한다.
		 */
		List<DSmpEmpTmp001Dto> dSmpEmpTmp100insert02InDtos = new ArrayList<DSmpEmpTmp001Dto>();
		for (MSmpDBToDBBtch02Dto mSmpDBToDBBtch02Dto : in) {
			DSmpEmpTmp001Dto smpEmpTmp001Dto = new DSmpEmpTmp001Dto();

			smpEmpTmp001Dto.setFeduEmpNo(mSmpDBToDBBtch02Dto.getFeduEmpNo()); // set [FW샘플 임직원번호]
			smpEmpTmp001Dto.setFeduEmpNm(mSmpDBToDBBtch02Dto.getFeduEmpNm()); // set [FW샘플 임직원명]
			smpEmpTmp001Dto.setFeduOccpNm(mSmpDBToDBBtch02Dto.getFeduOccpNm()); // set [FW샘플 직업명]
			smpEmpTmp001Dto.setFeduMngrEmpNo(mSmpDBToDBBtch02Dto.getFeduMngrEmpNo()); // set [FW샘플 관리자임직원번호]
			smpEmpTmp001Dto.setFeduHireDt(mSmpDBToDBBtch02Dto.getFeduHireDt()); // set [FW샘플 입사일자]
			smpEmpTmp001Dto.setFeduPayAmt(mSmpDBToDBBtch02Dto.getFeduPayAmt()); // set [FW샘플 급여금액]
			smpEmpTmp001Dto.setFeduDeptNo(mSmpDBToDBBtch02Dto.getFeduDeptNo()); // set [FW샘플 부서번호]

			dSmpEmpTmp100insert02InDtos.add(smpEmpTmp001Dto);
		}

		/**
		 * insert 처리 - 참고 1 : CONNECTED_BATCH로 C/U/D 처리는 Commit 시점에 bulk로 쿼리가 수행이 된다. -
		 * 참고 2 : CONNECTED_BATCH로 C/U/D 처리는 Commit 시점에 수행이 되기때문에 return 값은 임의의 음수
		 * 값이다.(의미없음)
		 */
		dSmpEmpTmp100.insert02(dSmpEmpTmp100insert02InDtos);
	}

	/**
	 * update - 진행 상태를 기록하기 위해 구현해야 하는 Method로서 구간 별로 Commit 시에 FW에서 호출된다.
	 */
	@Override
	@BxmCategory(logicalName = "DB TO DB 샘플 update")
	public void update(ExecutionContext executionContext) throws ItemStreamException {

	}

	/**
	 * close - 리소스 정리 작업을 위해 구현해야 하는 Method로서 Step 완료 시에 FW에서 호출된다.
	 */
	@Override
	@BxmCategory(logicalName = "DB TO DB 샘플 close")
	public void close() throws ItemStreamException {
		DasUtils.disconnectDasExecutor(iterator);
	}
}
