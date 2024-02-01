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
import bxm.batch.dft.util.DefaultFileUtils;
import bxm.batch.item.file.ext.DelimitedFileWriter;
import bxm.batch.item.file.ext.FixedFileWriter;
import bxm.common.annotaion.BxmCategory;
import bxm.common.util.StringUtils;
import bxm.container.annotation.BxmBean;
import bxm.dft.context.DefaultApplicationContext;
import bxm.dft.smp.batch.bean.dto.MSmpMultiFileWriteBtch01Dto;
import bxm.dft.smp.batch.bean.dto.MSmpMultiFileWriteBtch02Dto;
import bxm.dft.smp.batch.bean.dto.MSmpMultiFileWriteBtch03Dto;
import bxm.dft.smp.batch.dbio.DSmpEmpTst001;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01InDto;
import bxm.dft.smp.batch.dbio.dto.DSmpEmpTst001selectList01OutDto;

/**
 * @file         bxm.jbk.smp.bean.MSmpMultiFileWriteBtch.java
 * @filetype     java source file
 * @brief
 * @author       개발자(한글이름)
 * @version      0.1
 * @history
 * <pre>
 * 버전          성명                   일자              변경내용
 * -------       ----------------       -----------       -----------------
 * 0.1           개발자(한글이름)       2015. 01. 06.       신규 작성
 * </pre>
 */

@BxmBean("MSmpMultiFileWriteBtch")
@Scope("step")
@BxmCategory(logicalName = "MULTI FILE WRITE 샘플")
public class MSmpMultiFileWriteBtch implements ItemStream, ItemReader<MSmpMultiFileWriteBtch01Dto>,
		ItemProcessor<MSmpMultiFileWriteBtch01Dto, MSmpMultiFileWriteBtch01Dto>,
		ItemWriter<MSmpMultiFileWriteBtch01Dto> {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	private DSmpEmpTst001 dSmpEmpTst100; // 샘플용직원정보TST

	private Iterator<DSmpEmpTst001selectList01OutDto> iterator;

	private FixedFileWriter<MSmpMultiFileWriteBtch02Dto> empInfoFixedWriter;
	private DelimitedFileWriter<MSmpMultiFileWriteBtch03Dto> empInfoDelimitedWriter;

	/**
	 * open
	 * - 초기화를 위해 구현해야 하는 Method 로서 스텝이 시작되기 전에 FW에서 최초 1번 호출된다. 
	 */
	@Override
	@BxmCategory(logicalName = "open : 샘플용 직원정보 File Open 처리")
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		if (dSmpEmpTst100 == null) {
			dSmpEmpTst100 = DefaultApplicationContext.getBean(DSmpEmpTst001.class);
		}

		/**
		 * 배치 입력아규먼트에서 입력된 "oDate" 를 가지고 온다.
		 */
		String oDate = DefaultBatchApplicationContext.getJobParameter("oDate");
		if (StringUtils.isEmpty(oDate)) {
			throw new ItemStreamException("배치 입력아규먼트 'oDate' 값이 존재하지 않습니다.");
		}

		/**
		 * 배치 입력아규먼트에서 "deptNo" 를 가지고 온다.
		 */
		String feduDeptNo = DefaultBatchApplicationContext.getJobParameter("deptNo");
		if (StringUtils.isEmpty(feduDeptNo)) {
			throw new ItemStreamException("배치 입력아규먼트 'deptNo' 값이 존재하지 않습니다.");
		}

		/**
		 * 샘플용 직원정보 Fixed File Open
		 */
		String empInfoFixedFileName = "/data1/prod/bxm400/dat/fixed_file_" + oDate + ".txt";
		empInfoFixedWriter = DefaultFileUtils.getFixedFileWriter(empInfoFixedFileName,
				MSmpMultiFileWriteBtch02Dto.class, "UTF-8");
		empInfoFixedWriter.open(executionContext); // File Open

		/**
		 * 샘플용 직원정보 Delimited File Open
		 */
		String empInfoDelimitedFileName = "/data1/prod/bxm400/dat/delimited_file_" + oDate + ".txt";
		empInfoDelimitedWriter = DefaultFileUtils.getDelimitedFileWriter(empInfoDelimitedFileName,
				MSmpMultiFileWriteBtch03Dto.class, ";", "UTF-8");
		empInfoDelimitedWriter.open(executionContext); // File Open

		/**
		 * 입력받은 "deptNo" 에 대하여 샘플용 직원정보를 Iterator로 가져온다.
		 */
		DSmpEmpTst001selectList01InDto inDto = new DSmpEmpTst001selectList01InDto();
		inDto.setFeduDeptNo(Integer.parseInt(feduDeptNo));
		iterator = dSmpEmpTst100.selectList01(inDto).iterator();
		if (iterator == null) {
			throw new ItemStreamException("connected result is not prepared.");
		}
	}

	/**
	 * read
	 * - 처리 대상 데이터를 건 별로 DB 또는 파일에서 읽어오는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "read : 샘플용 직원정보 Read")
	public MSmpMultiFileWriteBtch01Dto read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		MSmpMultiFileWriteBtch01Dto out = null;
		if (iterator.hasNext()) {
			out = new MSmpMultiFileWriteBtch01Dto();
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
	 * process
	 * - 처리 대상 데이터를 건 별로 업무요건에 따라 처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "process : 샘플용 직원정보 처리")
	public MSmpMultiFileWriteBtch01Dto process(MSmpMultiFileWriteBtch01Dto in) throws Exception {
		MSmpMultiFileWriteBtch01Dto out;

		/**
		 *  관리자임직원번호가 없는 경우에는 null 를 return 하여 해당 Item에 대하여 Skip 처리한다.
		 *  - 참고 : process에서 null을 return 하면 write로 item이 넘어가지 않는다.
		 */
		if (in.getFeduMngrEmpNo() == 0) {
			logger.warn("임직원번호[{}]에 대한 관리자 번호가 0 입니다. Skip 처리합니다.");
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

	/**
	 * write
	 * - 처리 대상 결과 데이터를 커밋 단위 별로 DB 또는 파일에 기록처리하는 역할을 수행한다.
	 */
	@Override
	@BxmCategory(logicalName = "write : 샘플용 직원정보 처리")
	public void write(List<? extends MSmpMultiFileWriteBtch01Dto> in) throws Exception {
		List<MSmpMultiFileWriteBtch02Dto> mSmpMultiFileWriteBtch02Dtos = new ArrayList<MSmpMultiFileWriteBtch02Dto>();
		List<MSmpMultiFileWriteBtch03Dto> mSmpMultiFileWriteBtch03Dtos = new ArrayList<MSmpMultiFileWriteBtch03Dto>();

		for (MSmpMultiFileWriteBtch01Dto mSmpMultiFileWriteBtch01Dto : in) {
			/**
			 * Fixed File 처리 Data 설정
			 */
			MSmpMultiFileWriteBtch02Dto mSmpMultiFileWriteBtch02Dto = new MSmpMultiFileWriteBtch02Dto();

			mSmpMultiFileWriteBtch02Dto.setFeduEmpNo(mSmpMultiFileWriteBtch01Dto.getFeduEmpNo()); // set [FW샘플 임직원번호]
			mSmpMultiFileWriteBtch02Dto.setFeduEmpNm(mSmpMultiFileWriteBtch01Dto.getFeduEmpNm()); // set [FW샘플 임직원명]
			mSmpMultiFileWriteBtch02Dto.setFeduOccpNm(mSmpMultiFileWriteBtch01Dto.getFeduOccpNm()); // set [FW샘플 직업명]
			mSmpMultiFileWriteBtch02Dto.setFeduMngrEmpNo(mSmpMultiFileWriteBtch01Dto.getFeduMngrEmpNo()); // set [FW샘플 관리자임직원번호]
			mSmpMultiFileWriteBtch02Dto.setFeduHireDt(mSmpMultiFileWriteBtch01Dto.getFeduHireDt()); // set [FW샘플 입사일자]
			mSmpMultiFileWriteBtch02Dto.setFeduPayAmt(mSmpMultiFileWriteBtch01Dto.getFeduPayAmt()); // set [FW샘플 급여금액]
			mSmpMultiFileWriteBtch02Dto.setFeduDeptNo(mSmpMultiFileWriteBtch01Dto.getFeduDeptNo()); // set [FW샘플 부서번호]

			mSmpMultiFileWriteBtch02Dtos.add(mSmpMultiFileWriteBtch02Dto);

			/**
			 * Delimited File 처리 Data 설정
			 */
			MSmpMultiFileWriteBtch03Dto mSmpMultiFileWriteBtch03Dto = new MSmpMultiFileWriteBtch03Dto();

			mSmpMultiFileWriteBtch03Dto.setFeduEmpNo(mSmpMultiFileWriteBtch01Dto.getFeduEmpNo()); // set [FW샘플 임직원번호]
			mSmpMultiFileWriteBtch03Dto.setFeduEmpNm(mSmpMultiFileWriteBtch01Dto.getFeduEmpNm()); // set [FW샘플 임직원명]
			mSmpMultiFileWriteBtch03Dto.setFeduOccpNm(mSmpMultiFileWriteBtch01Dto.getFeduOccpNm()); // set [FW샘플 직업명]
			mSmpMultiFileWriteBtch03Dto.setFeduMngrEmpNo(mSmpMultiFileWriteBtch01Dto.getFeduMngrEmpNo()); // set [FW샘플 관리자임직원번호]
			mSmpMultiFileWriteBtch03Dto.setFeduHireDt(mSmpMultiFileWriteBtch01Dto.getFeduHireDt()); // set [FW샘플 입사일자]
			mSmpMultiFileWriteBtch03Dto.setFeduPayAmt(mSmpMultiFileWriteBtch01Dto.getFeduPayAmt()); // set [FW샘플 급여금액]
			mSmpMultiFileWriteBtch03Dto.setFeduDeptNo(mSmpMultiFileWriteBtch01Dto.getFeduDeptNo()); // set [FW샘플 부서번호]

			mSmpMultiFileWriteBtch03Dtos.add(mSmpMultiFileWriteBtch03Dto);
		}

		/**
		 * File Write 처리
		 */
		empInfoFixedWriter.write(mSmpMultiFileWriteBtch02Dtos); // Fixed File Write
		empInfoDelimitedWriter.write(mSmpMultiFileWriteBtch03Dtos); // Delimited File Write
	}

	/**
	 * update
	 * - 진행 상태를 기록하기 위해 구현해야 하는 Method로서 구간 별로 Commit 시에 FW에서 호출된다. 
	 */
	@Override
	@BxmCategory(logicalName = "update")
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		if (empInfoFixedWriter != null)
			empInfoFixedWriter.update(executionContext);
		if (empInfoDelimitedWriter != null)
			empInfoDelimitedWriter.update(executionContext);
	}

	/**
	 * close
	 * - 리소스 정리 작업을 위해 구현해야 하는 Method로서 Step 완료 시에 FW에서 호출된다. 
	 */
	@Override
	@BxmCategory(logicalName = "close")
	public void close() throws ItemStreamException {
		if (empInfoFixedWriter != null)
			empInfoFixedWriter.close();
		if (empInfoDelimitedWriter != null)
			empInfoDelimitedWriter.close();
	}
}